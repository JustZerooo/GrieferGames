package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class PerkCMD implements CommandExecutor
{
    private Main plugin;
    
    public PerkCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("perk").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
        final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.perk.nofall.bypass") && yPerk.getString(p.getUniqueId() + ".NoFall") == null) {
                yPerk.set(p.getUniqueId() + ".NoFall", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (p.hasPermission("system.perk.mutep.bypass") && yPerk.getString(p.getUniqueId() + ".Mutep") == null) {
                yPerk.set(p.getUniqueId() + ".Mutep", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (p.hasPermission("system.perk.SlowChat.bypass") && yPerk.getString(p.getUniqueId() + ".SlowChat") == null) {
                yPerk.set(p.getUniqueId() + ".SlowChat", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (p.hasPermission("system.perk.NoHunger.bypass") && yPerk.getString(p.getUniqueId() + ".NoHunger") == null) {
                yPerk.set(p.getUniqueId() + ".NoHunger", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (p.hasPermission("system.perk.ClearChat.bypass") && yPerk.getString(p.getUniqueId() + ".ClearChat") == null) {
                yPerk.set(p.getUniqueId() + ".ClearChat", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (p.hasPermission("system.perk.StartKick.bypass") && yPerk.getString(p.getUniqueId() + ".StartKick") == null) {
                yPerk.set(p.getUniqueId() + ".StartKick", (Object)true);
                try {
                    yPerk.save(Perk);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            final Inventory Perks = Bukkit.createInventory((InventoryHolder)null, 36, "§6§lPerks");
            final ItemStack Aktiviert = new ItemStack(351, 1, (short)10);
            final ItemMeta AktiviertM = Aktiviert.getItemMeta();
            AktiviertM.setDisplayName("§aAktiviert");
            Aktiviert.setItemMeta(AktiviertM);
            final ItemStack Deaktiviert = new ItemStack(351, 1, (short)8);
            final ItemMeta DeaktiviertM = Deaktiviert.getItemMeta();
            DeaktiviertM.setDisplayName("§cDeaktiviert");
            Deaktiviert.setItemMeta(DeaktiviertM);
            final Integer NoFall = this.plugin.getConfig().getInt("NoFallPerk");
            final Integer NoHunger = this.plugin.getConfig().getInt("NoHungerPerk");
            final Integer MutepPerk = this.plugin.getConfig().getInt("MutepPerk");
            final Integer ClearChatPerk = this.plugin.getConfig().getInt("ClearChatPerk");
            final Integer SlowChatPerk = this.plugin.getConfig().getInt("SlowChatPerk");
            final Integer StartKickPerk = this.plugin.getConfig().getInt("StartKickPerk");
            Perks.setItem(1, ItemBuilder.createItem(Material.FEATHER, "§6§lNoFall Perk", 1, new String[] { "Kein Fallschaden mehr" }));
            if (yPerk.getString(p.getUniqueId() + ".NoFall") == null) {
                Perks.setItem(2, this.preis(NoFall));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".NoFall")) {
                Perks.setItem(2, Aktiviert);
            }
            else if (!yPerk.getBoolean(p.getUniqueId() + ".NoFall")) {
                Perks.setItem(2, Deaktiviert);
            }
            if (yPerk.getString(p.getUniqueId() + ".NoHunger") == null) {
                Perks.setItem(6, this.preis(NoHunger));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".NoHunger")) {
                Perks.setItem(6, Aktiviert);
            }
            else if (!yPerk.getBoolean(p.getUniqueId() + ".NoHunger")) {
                Perks.setItem(6, Deaktiviert);
            }
            Perks.setItem(7, ItemBuilder.createItem(Material.COOKED_BEEF, "§6§lKein Hunger", 1, new String[] { "Kein Hunger mehr" }));
            Perks.setItem(10, ItemBuilder.createItem(Material.DIAMOND, "§6§l/mutep", 1, new String[] { "Benutze den /mutep Befehl" }));
            if (yPerk.getString(p.getUniqueId() + ".Mutep") == null) {
                Perks.setItem(11, this.preis(MutepPerk));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".Mutep")) {
                Perks.setItem(11, Aktiviert);
            }
            if (yPerk.getString(p.getUniqueId() + ".ClearChat") == null) {
                Perks.setItem(15, this.preis(ClearChatPerk));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".ClearChat")) {
                Perks.setItem(15, Aktiviert);
            }
            Perks.setItem(16, ItemBuilder.createItem(Material.LAPIS_ORE, "§6§l/clearchat", 1, new String[] { "Du kannst den Chat clearen" }));
            Perks.setItem(19, ItemBuilder.createItem(Material.GOLD_ORE, "§6§l/slowchat", 1, new String[] { "Du kannst den Chat verlangsamen" }));
            if (yPerk.getString(p.getUniqueId() + ".SlowChat") == null) {
                Perks.setItem(20, this.preis(SlowChatPerk));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".SlowChat")) {
                Perks.setItem(20, Aktiviert);
            }
            if (yPerk.getString(p.getUniqueId() + ".StartKick") == null) {
                Perks.setItem(24, this.preis(StartKickPerk));
            }
            else if (yPerk.getBoolean(p.getUniqueId() + ".StartKick")) {
                Perks.setItem(24, Aktiviert);
            }
            Perks.setItem(25, ItemBuilder.createItem(Material.IRON_ORE, "§6§l/startkick", 1, new String[] { "Benutze den /startkick Befehl" }));
            p.openInventory(Perks);
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Perks nutzen!");
        }
        return true;
    }
    
    public ItemStack preis(final Integer Preis) {
        final ItemStack i = ItemBuilder.createItemOL(Material.PAPER, "§e§lPreis: §f" + Preis + "$", 1);
        return i;
    }
}
