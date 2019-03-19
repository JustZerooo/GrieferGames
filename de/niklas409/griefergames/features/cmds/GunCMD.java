package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class GunCMD implements CommandExecutor
{
    private Main plugin;
    
    public GunCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("gun").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.gun")) {
                final Inventory ItemKanone = Bukkit.createInventory((InventoryHolder)null, 9, "§3Wähle eine Itemkanone aus!");
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.BEACON) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.DRAGON_EGG) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.MOB_SPAWNER) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.EMERALD_BLOCK) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.SPONGE) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.OBSIDIAN) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.BEDROCK) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.DIAMOND_ORE) });
                ItemKanone.addItem(new ItemStack[] { ItemBuilder.createItemON(Material.TNT) });
                p.openInventory(ItemKanone);
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Gun's benutzen.");
        }
        return true;
    }
}
