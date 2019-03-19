package de.niklas409.griefergames.features.clans;

import org.bukkit.inventory.Inventory;
import java.io.IOException;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class ClanCMD implements CommandExecutor
{
    private Main plugin;
    
    public ClanCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("clan").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                if (ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") == null) {
                    p.sendMessage(String.valueOf(Prefix) + "§6§lDas Clanmenü wurde geöffnet.");
                    final Inventory ClanMainMenü = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lClan-Menü");
                    ClanMainMenü.setItem(3, ItemBuilder.createItem(Material.FIREWORK_CHARGE, "§6§lClan erstellen", 1, new String[] { "§c§lKlicke hier um einen Clan zu gründen." }));
                    ClanMainMenü.setItem(5, ItemBuilder.createItem(Material.BOOK, "§6§lAusstehende Anfragen", 1, new String[] { "§c§lHier siehst du deine Clananfragen." }));
                    p.openInventory(ClanMainMenü);
                }
                else {
                    final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                    if (ClanListener.yClans.getString("Clans." + Clanname) != null) {
                        p.sendMessage(String.valueOf(Prefix) + "§6§lDas Clanmenü wurde geöffnet.");
                        final Inventory ClanMainMenü2 = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lClan-Menü");
                        ClanMainMenü2.setItem(0, ItemBuilder.createItem(Material.PAPER, "§6§lSpieler einladen", 1, new String[] { "§c§lSpieler in deinen Clan einladen." }));
                        final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        final SkullMeta im = (SkullMeta)i.getItemMeta();
                        im.setOwner(p.getName());
                        im.setDisplayName("§6§lClanmitglieder anzeigen");
                        final ArrayList<String> lore = new ArrayList<String>();
                        lore.add("§c§lAlle Clanmitglieder anzeigen.");
                        im.setLore((List)lore);
                        i.setItemMeta((ItemMeta)im);
                        ClanMainMenü2.setItem(3, i);
                        ClanMainMenü2.setItem(5, ItemBuilder.createItem(Material.GOLD_INGOT, "§6§lClankonto Optionen", 1, new String[] { "§c§lClankonto anschauen." }));
                        ClanMainMenü2.setItem(8, ItemBuilder.createItem(Material.BARRIER, "§6§lClan verlassen", 1, new String[] { "§c§lKlicke hier um deinen Clan zu verlassen." }));
                        p.openInventory(ClanMainMenü2);
                    }
                    else {
                        ClanListener.yClans.set("Spieler." + p.getName(), (Object)null);
                        try {
                            ClanListener.yClans.save(ClanListener.Clans);
                        }
                        catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        p.sendMessage(String.valueOf(Prefix) + "§6§lDas Clanmenü wurde geöffnet.");
                        final Inventory ClanMainMenü3 = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lClan-Menü");
                        ClanMainMenü3.setItem(3, ItemBuilder.createItem(Material.FIREWORK_CHARGE, "§6§lClan erstellen", 1, new String[] { "§c§lKlicke hier um einen Clan zu gründen." }));
                        ClanMainMenü3.setItem(5, ItemBuilder.createItem(Material.BOOK, "§6§lAusstehende Anfragen", 1, new String[] { "§c§lHier siehst du deine Clananfragen." }));
                        p.openInventory(ClanMainMenü3);
                    }
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cBitte gib /Clan ein um das Menü zu öffnen.");
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console darf keine Clans erstellen!");
        }
        return true;
    }
}
