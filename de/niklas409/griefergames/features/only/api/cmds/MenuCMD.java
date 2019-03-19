package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.listeners.MainListener;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class MenuCMD implements CommandExecutor
{
    private Main plugin;
    
    public MenuCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("menu").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                final Inventory Menu = Bukkit.createInventory((InventoryHolder)null, 36, "§6§lPlotMenu");
                Menu.setItem(0, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das nächste freie Plot", 1, new String[] { "§6/p auto" }));
                Menu.setItem(2, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§a§lFüge einen Helfer für kurze Zeit hinzu", 1, 5, new String[] { "§6/p add (Freund)" }));
                Menu.setItem(9, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das Plot auf dem du gerade stehst", 2, new String[] { "§6/p claim" }));
                Menu.setItem(11, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§6§lFüge deinen Freund zu deinen Plot", 2, 4, new String[] { "§6/p trust (Freund)" }));
                Menu.setItem(13, ItemBuilder.createItem(Material.WOOD_DOOR, "§e§lTeleportiere zu deinem Plot!", 1, new String[] { "§6/p h" }));
                Menu.setItem(15, ItemBuilder.createItemOL(Material.GOLD_INGOT, "§e§lKaufe dir ein weiteres Plot", 2));
                Menu.setItem(17, ItemBuilder.createItemOL(Material.BARRIER, "§c§lSchliesse das Menu", 1));
                Menu.setItem(18, ItemBuilder.createItem(Material.DIRT, "§c§lLösche dein Plot", 3, new String[] { "§6/p delete" }));
                Menu.setItem(20, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§c§lEntferne deinen Freund", 3, 14, new String[] { "§6/p remove (Freund)" }));
                Menu.setItem(22, ItemBuilder.createItemOL(Material.MINECART, "§e§lBesuche deinen Freund!", 3));
                Menu.setItem(24, ItemBuilder.createItem(Material.GOLD_NUGGET, "§6§lMein Money", 2, new String[] { "§6§l*klick*" }));
                p.openInventory(ItemBuilder.InvFüller(Menu, ItemBuilder.createItemWDOL(Material.STAINED_GLASS_PANE, "§7§lPlatzhalter", 1, 15), 4));
            }
            else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("cancel")) {
                    if (MainListener.menucanceltap.containsKey(p)) {
                        MainListener.menucanceltap.remove(p);
                        p.sendMessage(String.valueOf(Prefix) + "§a§lAlle Operationen wurden abgebrochen!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu hast nichts zum canceln!");
                    }
                }
                else {
                    final Inventory Menu = Bukkit.createInventory((InventoryHolder)null, 36, "§6§lPlotMenu");
                    Menu.setItem(0, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das nächste freie Plot", 1, new String[] { "§6/p auto" }));
                    Menu.setItem(2, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§a§lFüge einen Helfer für kurze Zeit hinzu", 1, 5, new String[] { "§6/p add (Freund)" }));
                    Menu.setItem(9, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das Plot auf dem du gerade stehst", 2, new String[] { "§6/p claim" }));
                    Menu.setItem(11, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§6§lFüge deinen Freund zu deinen Plot", 2, 4, new String[] { "§6/p trust (Freund)" }));
                    Menu.setItem(13, ItemBuilder.createItem(Material.WOOD_DOOR, "§e§lTeleportiere zu deinem Plot!", 1, new String[] { "§6/p h" }));
                    Menu.setItem(15, ItemBuilder.createItemOL(Material.GOLD_INGOT, "§e§lKaufe dir ein weiteres Plot", 2));
                    Menu.setItem(17, ItemBuilder.createItemOL(Material.BARRIER, "§c§lSchliesse das Menu", 1));
                    Menu.setItem(18, ItemBuilder.createItem(Material.DIRT, "§c§lLösche dein Plot", 3, new String[] { "§6/p delete" }));
                    Menu.setItem(20, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§c§lEntferne deinen Freund", 3, 14, new String[] { "§6/p remove (Freund)" }));
                    Menu.setItem(22, ItemBuilder.createItemOL(Material.MINECART, "§e§lBesuche deinen Freund!", 3));
                    Menu.setItem(24, ItemBuilder.createItem(Material.GOLD_NUGGET, "§6§lMein Money", 2, new String[] { "§6§l*klick*" }));
                    p.openInventory(ItemBuilder.InvFüller(Menu, ItemBuilder.createItemWDOL(Material.STAINED_GLASS_PANE, "§7§lPlatzhalter", 1, 15), 4));
                }
            }
            else {
                final Inventory Menu = Bukkit.createInventory((InventoryHolder)null, 36, "§6§lPlotMenu");
                Menu.setItem(0, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das nächste freie Plot", 1, new String[] { "§6/p auto" }));
                Menu.setItem(2, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§a§lFüge einen Helfer für kurze Zeit hinzu", 1, 5, new String[] { "§6/p add (Freund)" }));
                Menu.setItem(9, ItemBuilder.createItem(Material.GRASS, "§a§lClaim dir das Plot auf dem du gerade stehst", 2, new String[] { "§6/p claim" }));
                Menu.setItem(11, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§6§lFüge deinen Freund zu deinen Plot", 2, 4, new String[] { "§6/p trust (Freund)" }));
                Menu.setItem(13, ItemBuilder.createItem(Material.WOOD_DOOR, "§e§lTeleportiere zu deinem Plot!", 1, new String[] { "§6/p h" }));
                Menu.setItem(15, ItemBuilder.createItemOL(Material.GOLD_INGOT, "§e§lKaufe dir ein weiteres Plot", 2));
                Menu.setItem(17, ItemBuilder.createItemOL(Material.BARRIER, "§c§lSchliesse das Menu", 1));
                Menu.setItem(18, ItemBuilder.createItem(Material.DIRT, "§c§lLösche dein Plot", 3, new String[] { "§6/p delete" }));
                Menu.setItem(20, ItemBuilder.createItemWD(Material.STAINED_GLASS, "§c§lEntferne deinen Freund", 3, 14, new String[] { "§6/p remove (Freund)" }));
                Menu.setItem(22, ItemBuilder.createItemOL(Material.MINECART, "§e§lBesuche deinen Freund!", 3));
                Menu.setItem(24, ItemBuilder.createItem(Material.GOLD_NUGGET, "§6§lMein Money", 2, new String[] { "§6§l*klick*" }));
                p.openInventory(ItemBuilder.InvFüller(Menu, ItemBuilder.createItemWDOL(Material.STAINED_GLASS_PANE, "§7§lPlatzhalter", 1, 15), 4));
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann das Menu nicht nutzen!");
        }
        return true;
    }
}
