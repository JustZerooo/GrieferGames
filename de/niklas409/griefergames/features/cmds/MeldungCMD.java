package de.niklas409.griefergames.features.cmds;

import java.util.Iterator;
import org.bukkit.inventory.Inventory;
import java.util.List;
import org.bukkit.plugin.Plugin;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import java.util.HashMap;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class MeldungCMD implements CommandExecutor
{
    private Main plugin;
    public static HashMap<String, Long> Meldung;
    public static File MeldungC;
    public static YamlConfiguration yMeldungC;
    
    static {
        MeldungCMD.Meldung = new HashMap<String, Long>();
        MeldungCMD.MeldungC = new File("plugins/GrieferGames/Data/Meldung.yml");
        MeldungCMD.yMeldungC = YamlConfiguration.loadConfiguration(MeldungCMD.MeldungC);
    }
    
    public MeldungCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("meldung").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String Shop = this.plugin.getConfig().getString("Shop").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.meldung")) {
                if (p.hasPermission("system.meldung.see")) {
                    if (args.length >= 1) {
                        if (args[0].equalsIgnoreCase("admin")) {
                            final List<String> MeldungenL = (List<String>)MeldungCMD.yMeldungC.getStringList("Meldungen von");
                            final Inventory Meldungen = Bukkit.createInventory((InventoryHolder)null, 54, "§4§lMeldungen");
                            for (final String all : MeldungenL) {
                                final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                                final SkullMeta sm = (SkullMeta)i.getItemMeta();
                                sm.setOwner(all);
                                sm.setDisplayName("§a§l" + all);
                                i.setItemMeta((ItemMeta)sm);
                                Meldungen.addItem(new ItemStack[] { i });
                            }
                            p.openInventory(Meldungen);
                        }
                        else if (!MeldungCMD.Meldung.containsKey(p.getName())) {
                            final List<String> MeldungenL = (List<String>)MeldungCMD.yMeldungC.getStringList("Meldungen von");
                            if (!MeldungenL.contains(p.getName())) {
                                MeldungCMD.Meldung.put(p.getName(), System.currentTimeMillis() + 900000L);
                                String Message = "";
                                for (int j = 0; j < args.length; ++j) {
                                    Message = String.valueOf(Message) + args[j] + " ";
                                }
                                p.sendMessage(String.valueOf(Prefix) + "§2Danke dass du ein Duplizierer gemeldet hast!");
                                final Date date = new Date(System.currentTimeMillis());
                                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Meldung", (Object)Message);
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Datum", (Object)mm_dd_yyyy);
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Uhrzeit", (Object)hour_min);
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Welt", (Object)p.getLocation().getWorld().getName());
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".X", (Object)p.getLocation().getX());
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Y", (Object)p.getLocation().getY());
                                MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Z", (Object)p.getLocation().getZ());
                                MeldungenL.add(p.getName());
                                MeldungCMD.yMeldungC.set("Meldungen von", (Object)MeldungenL);
                                try {
                                    MeldungCMD.yMeldungC.save(MeldungCMD.MeldungC);
                                }
                                catch (IOException e) {
                                    e.printStackTrace();
                                }
                                Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                    final /* synthetic */ MeldungCMD this$0;
                                    private final /* synthetic */ Player val$p;
                                    
                                    MeldungCMD$1() {
                                        this.this$0 = this$0;
                                        super();
                                    }
                                    
                                    @Override
                                    public void run() {
                                        MeldungCMD.Meldung.remove(p.getName());
                                    }
                                }, 18000L);
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDu hast bereits einen Spieler gemeldet, bitte warte bis ein Admin sich um dein Beitrag gekümmert hat.");
                            }
                        }
                        else {
                            final Date date2 = new Date(MeldungCMD.Meldung.get(p.getName()));
                            final String mm_dd_yyyy2 = new SimpleDateFormat("dd.MM.yyyy").format(date2);
                            final String hour_min2 = new SimpleDateFormat("HH:mm").format(date2);
                            p.sendMessage(String.valueOf(Prefix) + "§4Du kannst diesen Befehl erst wieder am" + "\n" + " §a" + mm_dd_yyyy2 + " §4um §a" + hour_min2 + " §4Uhr benutzen.");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Meldung Admin/<Nachricht>");
                    }
                }
                else if (!MeldungCMD.Meldung.containsKey(p.getName())) {
                    if (args.length >= 1) {
                        final List<String> MeldungenL = (List<String>)MeldungCMD.yMeldungC.getStringList("Meldungen von");
                        if (!MeldungenL.contains(p.getName())) {
                            MeldungCMD.Meldung.put(p.getName(), System.currentTimeMillis() + 900000L);
                            String Message = "";
                            for (int j = 0; j < args.length; ++j) {
                                Message = String.valueOf(Message) + args[j] + " ";
                            }
                            p.sendMessage(String.valueOf(Prefix) + "§2Danke dass du ein Duplizierer gemeldet hast!");
                            final Date date = new Date(System.currentTimeMillis());
                            final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                            final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Meldung", (Object)Message);
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Datum", (Object)mm_dd_yyyy);
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Uhrzeit", (Object)hour_min);
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Welt", (Object)p.getLocation().getWorld().getName());
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".X", (Object)p.getLocation().getX());
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Y", (Object)p.getLocation().getY());
                            MeldungCMD.yMeldungC.set("Meldungen." + p.getName() + ".Location" + ".Z", (Object)p.getLocation().getZ());
                            MeldungenL.add(p.getName());
                            MeldungCMD.yMeldungC.set("Meldungen von", (Object)MeldungenL);
                            try {
                                MeldungCMD.yMeldungC.save(MeldungCMD.MeldungC);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ MeldungCMD this$0;
                                private final /* synthetic */ Player val$p;
                                
                                MeldungCMD$2() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    MeldungCMD.Meldung.remove(p.getName());
                                }
                            }, 18000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast bereits einen Spieler gemeldet, bitte warte bis ein Admin sich um dein Beitrag gekümmert hat.");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Meldung <Nachricht>");
                    }
                }
                else {
                    final Date date2 = new Date(MeldungCMD.Meldung.get(p.getName()));
                    final String mm_dd_yyyy2 = new SimpleDateFormat("dd.MM.yyyy").format(date2);
                    final String hour_min2 = new SimpleDateFormat("HH:mm").format(date2);
                    p.sendMessage(String.valueOf(Prefix) + "§4Du kannst diesen Befehl erst wieder am" + "\n" + " §a" + mm_dd_yyyy2 + " §4um §a" + hour_min2 + " §4Uhr benutzen.");
                }
            }
            else {
                p.sendMessage(Shop);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Spieler melden!");
        }
        return true;
    }
}
