package de.niklas409.griefergames.features.only.api.cmds;

import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class PlayerholoCMD implements CommandExecutor
{
    private static Main plugin;
    public static File holo;
    public static YamlConfiguration yholo;
    public static File list;
    public static YamlConfiguration ylist;
    public static ArrayList<String> Spamm;
    
    static {
        PlayerholoCMD.holo = new File("plugins/HolographicDisplays/database.yml");
        PlayerholoCMD.yholo = YamlConfiguration.loadConfiguration(PlayerholoCMD.holo);
        PlayerholoCMD.list = new File("plugins/GrieferGames/Data/Holo.yml");
        PlayerholoCMD.ylist = YamlConfiguration.loadConfiguration(PlayerholoCMD.list);
        PlayerholoCMD.Spamm = new ArrayList<String>();
    }
    
    public PlayerholoCMD(final Main plugin) {
        super();
        PlayerholoCMD.plugin = plugin;
        plugin.getCommand("playerholo").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = PlayerholoCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + PlayerholoCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        final Integer Anzahl = Integer.valueOf(PlayerholoCMD.plugin.getConfig().getString("WieVieleHolosProSpieler"));
        final PlotAPI papi = new PlotAPI();
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (!PlayerholoCMD.Spamm.contains(p.getName())) {
                PlayerholoCMD.Spamm.add(p.getName());
                Bukkit.getScheduler().runTaskLater((Plugin)PlayerholoCMD.plugin, (Runnable)new Runnable() {
                    final /* synthetic */ PlayerholoCMD this$0;
                    private final /* synthetic */ Player val$p;
                    
                    PlayerholoCMD$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public void run() {
                        PlayerholoCMD.Spamm.remove(p.getName());
                    }
                }, 200L);
                if (p.hasPermission("system.playerholo.create")) {
                    if (args.length != 0) {
                        if (args[0].equalsIgnoreCase("editline")) {
                            if (args.length >= 4) {
                                if (papi.isInPlot(p)) {
                                    if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                                        if (PlayerholoCMD.yholo.get(args[1]) != null) {
                                            if (PlayerholoCMD.yholo.getString(String.valueOf(args[1]) + ".Owner").equalsIgnoreCase(p.getName())) {
                                                try {
                                                    String Message = "";
                                                    for (int i = 3; i < args.length; ++i) {
                                                        Message = String.valueOf(Message) + args[i] + " ";
                                                    }
                                                    final List<String> lines = (List<String>)PlayerholoCMD.yholo.getStringList(String.valueOf(args[1]) + ".lines");
                                                    final Integer line = Integer.valueOf(args[2]) - 1;
                                                    lines.remove(lines.get(line));
                                                    lines.add(line, Message.substring(0, Message.length() - 1));
                                                    PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".lines", (Object)lines);
                                                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast eine Line bearbeitet!");
                                                    try {
                                                        PlayerholoCMD.yholo.save(PlayerholoCMD.holo);
                                                    }
                                                    catch (IOException e) {
                                                        e.printStackTrace();
                                                    }
                                                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "holo reload");
                                                }
                                                catch (Exception e8) {
                                                    p.sendMessage(String.valueOf(Prefix) + "§cDie Line gibt es nicht!");
                                                }
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDas ist nicht dein Hologramm!");
                                            }
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDieses Hologramm gibt es nicht!");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                }
                            }
                            else {
                                p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo list");
                                p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                                p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                                p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                                p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Löscht das Hologramm.");
                            }
                        }
                        else if (args[0].equalsIgnoreCase("create")) {
                            final Integer anzahl = PlayerholoCMD.ylist.getInt(p.getUniqueId() + ".Anzahl");
                            if (anzahl <= Anzahl - 1) {
                                if (args.length >= 3) {
                                    if (papi.isInPlot(p)) {
                                        if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                                            if (PlayerholoCMD.yholo.get(args[1]) == null) {
                                                String Message2 = "";
                                                for (int j = 2; j < args.length; ++j) {
                                                    Message2 = String.valueOf(Message2) + args[j] + " ";
                                                }
                                                final double X = p.getLocation().getX();
                                                final double Y = p.getLocation().getY() + 2.0;
                                                final double Z = p.getLocation().getZ();
                                                final String World = p.getLocation().getWorld().getName();
                                                final List<String> lines2 = (List<String>)PlayerholoCMD.yholo.getStringList(String.valueOf(args[1]) + ".lines");
                                                lines2.add(Message2.substring(0, Message2.length() - 1));
                                                PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".Owner", (Object)p.getName());
                                                PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".location", (Object)(String.valueOf(World) + ", " + X + ", " + Y + ", " + Z));
                                                PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".lines", (Object)lines2);
                                                p.sendMessage(String.valueOf(Prefix) + "§aDu hast ein Hologramm erstellt! §7[§4" + args[1] + "§7]");
                                                try {
                                                    PlayerholoCMD.yholo.save(PlayerholoCMD.holo);
                                                }
                                                catch (IOException e2) {
                                                    e2.printStackTrace();
                                                }
                                                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "holo reload");
                                                PlayerholoCMD.ylist.set(p.getUniqueId() + ".Anzahl", (Object)(PlayerholoCMD.ylist.getInt(p.getUniqueId() + ".Anzahl") + 1));
                                                final List<String> liste = (List<String>)PlayerholoCMD.ylist.getStringList(p.getUniqueId() + ".Names");
                                                liste.add(args[1]);
                                                PlayerholoCMD.ylist.set(p.getUniqueId() + ".Names", (Object)liste);
                                                try {
                                                    PlayerholoCMD.ylist.save(PlayerholoCMD.list);
                                                }
                                                catch (IOException e3) {
                                                    e3.printStackTrace();
                                                }
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDen Holonamen gibt es schon!");
                                            }
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                    }
                                }
                                else {
                                    p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                    p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                                    p.sendMessage("§6§l/Playerholo list");
                                    p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                                    p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                    p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                                    p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                                    p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                                    p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                                    p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                                    p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                                    p.sendMessage("§7Löscht das Hologramm.");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDu darfst nur §4" + Anzahl + " §cHologramme haben!");
                            }
                        }
                        else if (args[0].equalsIgnoreCase("addline")) {
                            if (args.length >= 3) {
                                if (papi.isInPlot(p)) {
                                    if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                                        if (PlayerholoCMD.yholo.get(args[1]) != null) {
                                            if (PlayerholoCMD.yholo.getString(String.valueOf(args[1]) + ".Owner").equalsIgnoreCase(p.getName())) {
                                                String Message = "";
                                                for (int i = 2; i < args.length; ++i) {
                                                    Message = String.valueOf(Message) + args[i] + " ";
                                                }
                                                final List<String> lines = (List<String>)PlayerholoCMD.yholo.getStringList(String.valueOf(args[1]) + ".lines");
                                                lines.add(Message.substring(0, Message.length() - 1));
                                                PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".lines", (Object)lines);
                                                p.sendMessage(String.valueOf(Prefix) + "§aDu hast eine Line hinzugefügt!");
                                                try {
                                                    PlayerholoCMD.yholo.save(PlayerholoCMD.holo);
                                                }
                                                catch (IOException e4) {
                                                    e4.printStackTrace();
                                                }
                                                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "holo reload");
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDas ist nicht dein Hologramm!");
                                            }
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDieses Hologramm gibt es nicht!");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                }
                            }
                            else {
                                p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo list");
                                p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                                p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                                p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                                p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Löscht das Hologramm.");
                            }
                        }
                        else if (args[0].equalsIgnoreCase("move")) {
                            if (args.length == 2) {
                                if (papi.isInPlot(p)) {
                                    if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                                        if (PlayerholoCMD.yholo.get(args[1]) != null) {
                                            if (PlayerholoCMD.yholo.getString(String.valueOf(args[1]) + ".Owner").equalsIgnoreCase(p.getName())) {
                                                final double X2 = p.getLocation().getX();
                                                final double Y2 = p.getLocation().getY() + 2.0;
                                                final double Z2 = p.getLocation().getZ();
                                                final String World2 = p.getLocation().getWorld().getName();
                                                PlayerholoCMD.yholo.set(String.valueOf(args[1]) + ".location", (Object)(String.valueOf(World2) + ", " + X2 + ", " + Y2 + ", " + Z2));
                                                p.sendMessage(String.valueOf(Prefix) + "§aDu hast dein Hologramm zu dir Teleportiert.");
                                                try {
                                                    PlayerholoCMD.yholo.save(PlayerholoCMD.holo);
                                                }
                                                catch (IOException e5) {
                                                    e5.printStackTrace();
                                                }
                                                Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "holo reload");
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDas ist nicht dein Hologramm!");
                                            }
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDieses Hologramm gibt es nicht!");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu musst auf dein Grundstück sein!");
                                }
                            }
                            else {
                                p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo list");
                                p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                                p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                                p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                                p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Löscht das Hologramm.");
                            }
                        }
                        else if (args[0].equalsIgnoreCase("delete")) {
                            if (args.length == 2) {
                                if (PlayerholoCMD.yholo.get(args[1]) != null) {
                                    if (PlayerholoCMD.yholo.getString(String.valueOf(args[1]) + ".Owner").equalsIgnoreCase(p.getName())) {
                                        PlayerholoCMD.yholo.set(args[1], (Object)null);
                                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast dein Hologramm gelöscht!");
                                        try {
                                            PlayerholoCMD.yholo.save(PlayerholoCMD.holo);
                                        }
                                        catch (IOException e6) {
                                            e6.printStackTrace();
                                        }
                                        Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "holo reload");
                                        PlayerholoCMD.ylist.set(p.getUniqueId() + ".Anzahl", (Object)(PlayerholoCMD.ylist.getInt(p.getUniqueId() + ".Anzahl") - 1));
                                        final List<String> liste2 = (List<String>)PlayerholoCMD.ylist.getStringList(p.getUniqueId() + ".Names");
                                        liste2.remove(args[1]);
                                        PlayerholoCMD.ylist.set(p.getUniqueId() + ".Names", (Object)liste2);
                                        try {
                                            PlayerholoCMD.ylist.save(PlayerholoCMD.list);
                                        }
                                        catch (IOException e7) {
                                            e7.printStackTrace();
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDas ist nicht dein Hologramm!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDieses Hologramm gibt es nicht!");
                                }
                            }
                            else {
                                p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo list");
                                p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                                p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                                p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                                p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                                p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                                p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                                p.sendMessage("§7Löscht das Hologramm.");
                            }
                        }
                        else if (args[0].equalsIgnoreCase("list")) {
                            p.sendMessage("§6Du besitzt folgende Hologramme:");
                            for (final String list : PlayerholoCMD.ylist.getStringList(p.getUniqueId() + ".Names")) {
                                p.sendMessage("§8§l- §6§l" + list);
                            }
                        }
                        else {
                            p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                            p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                            p.sendMessage("§6§l/Playerholo list");
                            p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                            p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                            p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                            p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                            p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                            p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                            p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                            p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                            p.sendMessage("§7Löscht das Hologramm.");
                        }
                    }
                    else {
                        p.sendMessage("§6§l/Playerholo create §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                        p.sendMessage("§7Erstellt ein Hologramm mit deinem Text!");
                        p.sendMessage("§6§l/Playerholo list");
                        p.sendMessage("§7Es werden dir alle Hologramme angezeigt die du erstellt hast.");
                        p.sendMessage("§6§l/Playerholo addline §8§l<§6§lName§8§l> §8§l<§6§lText§8§l>");
                        p.sendMessage("§7Erstellt eine zusätzliche Zeile mit deinem Text!");
                        p.sendMessage("§6§l/Playerholo editline §8§l<§6§lName§8§l> §8§l<§6§lZeilennummer§8§l> §8§l<§6§lText§8§l>");
                        p.sendMessage("§7Ändert die angegebene Zeile zu deinem Text!");
                        p.sendMessage("§6§l/Playerholo move §8§l<§6§lName§8§l>");
                        p.sendMessage("§7Teleportiert dein Hologramm auf deine Position!");
                        p.sendMessage("§6§l/Playerholo delete §8§l<§6§lName§8§l>");
                        p.sendMessage("§7Löscht das Hologramm.");
                    }
                }
                else {
                    p.sendMessage(NoPerms);
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§6§lBitte warte §4§l10 §6§lSekunden bis du den Command wieder eingibst!");
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Hologramme erstellen!");
        }
        return true;
    }
}
