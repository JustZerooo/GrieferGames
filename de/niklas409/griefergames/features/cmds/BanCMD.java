package de.niklas409.griefergames.features.cmds;

import java.util.Iterator;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.command.ConsoleCommandSender;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.CommandExecutor;

public class BanCMD implements CommandExecutor
{
    public static File Banned;
    public static YamlConfiguration Banned_cfg;
    public static File Mutet;
    public static YamlConfiguration Mutet_cfg;
    private static Main plugin;
    
    static {
        BanCMD.Banned = new File("plugins/GrieferGames/Data/BanSystem/Banned.yml");
        BanCMD.Banned_cfg = YamlConfiguration.loadConfiguration(BanCMD.Banned);
        BanCMD.Mutet = new File("plugins/GrieferGames/Data/BanSystem/Mutet.yml");
        BanCMD.Mutet_cfg = YamlConfiguration.loadConfiguration(BanCMD.Mutet);
    }
    
    public BanCMD(final Main plugin) {
        super();
        BanCMD.plugin = plugin;
        plugin.getCommand("ban").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = BanCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + BanCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.BanSystem.ban.use")) {
                if (args.length == 2) {
                    final String target = args[0];
                    final Player tar = Bukkit.getPlayer(target);
                    final UUID UUIDP = Bukkit.getOfflinePlayer(target).getUniqueId();
                    if (args[1].equalsIgnoreCase("1")) {
                        if (p.hasPermission("system.BanSystem.ban.1")) {
                            SetBanned(tar, UUIDP, target, 1209600, "Hacking", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("2")) {
                        if (p.hasPermission("system.BanSystem.ban.2")) {
                            SetBanned(tar, UUIDP, target, 1209600, "Rassismus in extremer Form", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("3")) {
                        if (p.hasPermission("system.BanSystem.ban.3")) {
                            SetMutet(tar, UUIDP, target, 10800, "harte Beleidigung", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("4")) {
                        if (p.hasPermission("system.BanSystem.ban.4")) {
                            SetMutet(tar, UUIDP, target, 86400, "mehrfache Werbung", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("5")) {
                        if (p.hasPermission("system.BanSystem.ban.5")) {
                            SetMutet(tar, UUIDP, target, 3600, "Extremer Spam", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("6")) {
                        if (p.hasPermission("system.BanSystem.ban.6")) {
                            SetBanned(tar, UUIDP, target, 604800, "Rangmissbrauch", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("7")) {
                        if (p.hasPermission("system.BanSystem.ban.7")) {
                            SetBanned(tar, UUIDP, target, 259200, "Unangebrachter Skin/Name", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("8")) {
                        if (p.hasPermission("system.BanSystem.ban.8")) {
                            SetMutet(tar, UUIDP, target, 1800, "Provokantes Verhalten", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("9")) {
                        if (p.hasPermission("system.BanSystem.ban.9")) {
                            SetBanned(tar, UUIDP, target, 86400, "Bugusing", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("10")) {
                        if (p.hasPermission("system.BanSystem.ban.10")) {
                            SetBanned(tar, UUIDP, target, 1814400, "Echtgeldhandel", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("11")) {
                        if (p.hasPermission("system.BanSystem.ban.11")) {
                            SetMutet(tar, UUIDP, target, 10800, "Nicken als bekannte Person", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("12")) {
                        if (p.hasPermission("system.BanSystem.ban.12")) {
                            SetMutet(tar, UUIDP, target, 43200, "Ingamebetrug", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("13")) {
                        if (p.hasPermission("system.BanSystem.ban.13")) {
                            SetMutet(tar, UUIDP, target, 1209600, "veröffentlichen privater Daten", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("14")) {
                        if (p.hasPermission("system.BanSystem.ban.14")) {
                            SetPermaBanned(tar, UUIDP, target, "Duplizieren", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("15")) {
                        if (p.hasPermission("system.BanSystem.ban.15")) {
                            SetMutet(tar, UUIDP, target, 604800, "Sexismus", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("16")) {
                        if (p.hasPermission("system.BanSystem.ban.16")) {
                            SetBanned(tar, UUIDP, target, 900, "Kurzer Timeout vom Admin", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("17")) {
                        if (p.hasPermission("system.BanSystem.ban.17")) {
                            SetMutet(tar, UUIDP, target, 1800, "Spam", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else if (args[1].equalsIgnoreCase("99")) {
                        if (p.hasPermission("system.BanSystem.ban.99")) {
                            SetPermaBanned(tar, UUIDP, target, "Ban eines Admins", p.getName(), p);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu darfst diese BanId nicht nutzen!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§7/Ban <Spieler> <GrundId>");
                        p.sendMessage("§4ID: 1 §c- §4BAN §c- §4Hacking");
                        p.sendMessage("§4ID: 2 §c- §4BAN §c- §4Rassismus in extremer Form");
                        p.sendMessage("§4ID: 3 §c- §4MUTE §c- §4harte Beleidigung");
                        p.sendMessage("§4ID: 4 §c- §4MUTE §c- §4mehrfache Werbung");
                        p.sendMessage("§4ID: 5 §c- §4MUTE §c- §4Extremer Spam");
                        p.sendMessage("§4ID: 6 §c- §4BAN §c- §4Rangmissbrauch");
                        p.sendMessage("§4ID: 7 §c- §4BAN §c- §4Unangebrachter Skin/Name");
                        p.sendMessage("§4ID: 8 §c- §4MUTE §c- §4Provokantes Verhalten");
                        p.sendMessage("§4ID: 9 §c- §4BAN §c- §4Bugusing");
                        p.sendMessage("§4ID: 10 §c- §4BAN §c- §4Echtgeldhandel");
                        p.sendMessage("§4ID: 11 §c- §4MUTE §c- §4Nicken als bekannte Person");
                        p.sendMessage("§4ID: 12 §c- §4MUTE §c- §4Ingamebetrug");
                        p.sendMessage("§4ID: 13 §c- §4MUTE §c- §4veröffentlichen privater Daten");
                        p.sendMessage("§4ID: 14 §c- §4BAN §c- §4Duplizieren");
                        p.sendMessage("§4ID: 15 §c- §4MUTE §c- §4Sexismus");
                        p.sendMessage("§4ID: 16 §c- §4BAN §c- §4Kurzer Timeout vom Admin");
                        p.sendMessage("§4ID: 17 §c- §4MUTE §c- §4Spam");
                        p.sendMessage("§4ID: 99 §c- §4BAN §c- §4Ban eines Admins");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§7/Ban <Spieler> <GrundId>");
                    p.sendMessage("§4ID: 1 §c- §4BAN §c- §4Hacking");
                    p.sendMessage("§4ID: 2 §c- §4BAN §c- §4Rassismus in extremer Form");
                    p.sendMessage("§4ID: 3 §c- §4MUTE §c- §4harte Beleidigung");
                    p.sendMessage("§4ID: 4 §c- §4MUTE §c- §4mehrfache Werbung");
                    p.sendMessage("§4ID: 5 §c- §4MUTE §c- §4Extremer Spam");
                    p.sendMessage("§4ID: 6 §c- §4BAN §c- §4Rangmissbrauch");
                    p.sendMessage("§4ID: 7 §c- §4BAN §c- §4Unangebrachter Skin/Name");
                    p.sendMessage("§4ID: 8 §c- §4MUTE §c- §4Provokantes Verhalten");
                    p.sendMessage("§4ID: 9 §c- §4BAN §c- §4Bugusing");
                    p.sendMessage("§4ID: 10 §c- §4BAN §c- §4Echtgeldhandel");
                    p.sendMessage("§4ID: 11 §c- §4MUTE §c- §4Nicken als bekannte Person");
                    p.sendMessage("§4ID: 12 §c- §4MUTE §c- §4Ingamebetrug");
                    p.sendMessage("§4ID: 13 §c- §4MUTE §c- §4veröffentlichen privater Daten");
                    p.sendMessage("§4ID: 14 §c- §4BAN §c- §4Duplizieren");
                    p.sendMessage("§4ID: 15 §c- §4MUTE §c- §4Sexismus");
                    p.sendMessage("§4ID: 16 §c- §4BAN §c- §4Kurzer Timeout vom Admin");
                    p.sendMessage("§4ID: 17 §c- §4MUTE §c- §4Spam");
                    p.sendMessage("§4ID: 99 §c- §4BAN §c- §4Ban eines Admins");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
            p2.sendMessage(String.valueOf(Prefix) + "§cDer Ban-Befehl ist derzeit aus Bug-Gruenden fuer die Console geblockt.");
        }
        return true;
    }
    
    public static void SetBanned(final Player BannedPlayer, final UUID uuid, final String Name, final int Laenge, final String Grund, final String Von, final Player von) {
        final String Prefix = BanCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String Servername = BanCMD.plugin.getConfig().getString("Servername").replace("&", "§");
        final UUID UUIDP = Bukkit.getOfflinePlayer(Name).getUniqueId();
        if (BannedPlayer != null) {
            if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
                BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
                BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
                BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
                BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
                final Date date = new Date(BanCMD.Banned_cfg.getLong(UUIDP + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    if (p2.hasPermission("system.bansystem.ban.see")) {
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                        p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                        p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                        p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    }
                }
                von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
                BannedPlayer.kickPlayer("§8[§eStrafe§8] §7Du wurdest für §4" + BanCMD.Banned_cfg.getString(BannedPlayer.getUniqueId() + ".Grund") + " §7bestraft." + "\n" + "§4Ende der Strafe: §7" + mm_dd_yyyy + " um " + hour_min_sec);
            }
            else {
                von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
            }
        }
        else if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
            BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
            BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
            BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
            BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
            final Date date = new Date(BanCMD.Banned_cfg.getLong(UUIDP + ".Laenge"));
            final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                if (p2.hasPermission("system.bansystem.ban.see")) {
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                    p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                    p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                    p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                }
            }
            von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
        }
        else {
            von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
        }
        try {
            BanCMD.Banned_cfg.save(BanCMD.Banned);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void SetMutet(final Player MutetPlayer, final UUID uuid, final String Name, final int Laenge, final String Grund, final String Von, final Player von) {
        final String Prefix = BanCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String Servername = BanCMD.plugin.getConfig().getString("Servername").replace("&", "§");
        final UUID UUIDP = Bukkit.getOfflinePlayer(Name).getUniqueId();
        if (MutetPlayer != null) {
            if (BanCMD.Mutet_cfg.getString(UUIDP + ".Laenge") == null) {
                BanCMD.Mutet_cfg.set(uuid + ".Name", (Object)Name);
                BanCMD.Mutet_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
                BanCMD.Mutet_cfg.set(uuid + ".Grund", (Object)Grund);
                BanCMD.Mutet_cfg.set(uuid + ".Von", (Object)Von);
                final Date date = new Date(BanCMD.Mutet_cfg.getLong(UUIDP + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    if (p2.hasPermission("system.bansystem.mute.see")) {
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                        p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gemutet.");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Mutet_cfg.getString(UUIDP + ".Grund"));
                        p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                        p2.sendMessage(String.valueOf(Prefix) + "§7Entmutedatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    }
                }
                von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gemutet!");
                MutetPlayer.kickPlayer("§8[§eStrafe§8] §7Du wurdest für §4" + BanCMD.Mutet_cfg.getString(MutetPlayer.getUniqueId() + ".Grund") + " §7bestraft." + "\n" + "§4Ende der Strafe: §7" + mm_dd_yyyy + " um " + hour_min_sec);
            }
            else {
                von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gemutet.");
            }
        }
        else if (BanCMD.Mutet_cfg.getString(UUIDP + ".Laenge") == null) {
            BanCMD.Mutet_cfg.set(uuid + ".Name", (Object)Name);
            BanCMD.Mutet_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
            BanCMD.Mutet_cfg.set(uuid + ".Grund", (Object)Grund);
            BanCMD.Mutet_cfg.set(uuid + ".Von", (Object)Von);
            final Date date = new Date(BanCMD.Mutet_cfg.getLong(UUIDP + ".Laenge"));
            final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                if (p2.hasPermission("system.bansystem.mute.see")) {
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gemutet.");
                    p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Mutet_cfg.getString(UUIDP + ".Grund"));
                    p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                    p2.sendMessage(String.valueOf(Prefix) + "§7Entmutedatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                }
            }
            von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gemutet!");
        }
        else {
            von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gemutet.");
        }
        try {
            BanCMD.Mutet_cfg.save(BanCMD.Mutet);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void SetPermaBanned(final Player BannedPlayer, final UUID uuid, final String Name, final String Grund, final String Von, final Player von) {
        final String Prefix = BanCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String Servername = BanCMD.plugin.getConfig().getString("Servername").replace("&", "§");
        final UUID UUIDP = Bukkit.getOfflinePlayer(Name).getUniqueId();
        if (BannedPlayer != null) {
            if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
                BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
                BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)"PERMANENT");
                BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
                BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    if (p2.hasPermission("system.bansystem.ban.see")) {
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                        p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                        p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                        p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + BanCMD.Banned_cfg.getString(BannedPlayer.getUniqueId() + ".Laenge"));
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    }
                }
                von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
                BannedPlayer.kickPlayer("§8[§eStrafe§8] §7Du wurdest für §4" + BanCMD.Banned_cfg.getString(BannedPlayer.getUniqueId() + ".Grund") + " §7bestraft." + "\n" + "§4Ende der Strafe: §4§lPERMANENT");
            }
            else {
                von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
            }
        }
        else if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
            BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
            BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)"PERMANENT");
            BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
            BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                if (p2.hasPermission("system.bansystem.ban.see")) {
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                    p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                    p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                    p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Laenge"));
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                }
            }
            von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
        }
        else {
            von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
        }
        try {
            BanCMD.Banned_cfg.save(BanCMD.Banned);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void SetBannedConsole(final Player BannedPlayer, final UUID uuid, final String Name, final int Laenge, final String Grund, final String Von, final ConsoleCommandSender von) {
        final String Prefix = BanCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String Servername = BanCMD.plugin.getConfig().getString("Servername").replace("&", "§");
        final UUID UUIDP = Bukkit.getOfflinePlayer(Name).getUniqueId();
        if (BannedPlayer != null) {
            if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
                BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
                BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
                BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
                BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
                final Date date = new Date(BanCMD.Banned_cfg.getLong(UUIDP + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                for (final Player p2 : Bukkit.getOnlinePlayers()) {
                    if (p2.hasPermission("system.bansystem.ban.see")) {
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                        p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                        p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                        p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                        p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    }
                }
                von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
                BannedPlayer.kickPlayer("§8[§eStrafe§8] §7Du wurdest für §4" + BanCMD.Banned_cfg.getString(BannedPlayer.getUniqueId() + ".Grund") + " §7bestraft." + "\n" + "§4Ende der Strafe: §7" + mm_dd_yyyy + " um " + hour_min_sec);
            }
            else {
                von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
            }
        }
        else if (BanCMD.Banned_cfg.getString(UUIDP + ".Laenge") == null) {
            BanCMD.Banned_cfg.set(uuid + ".Name", (Object)Name);
            BanCMD.Banned_cfg.set(uuid + ".Laenge", (Object)(System.currentTimeMillis() + Laenge * 1000));
            BanCMD.Banned_cfg.set(uuid + ".Grund", (Object)Grund);
            BanCMD.Banned_cfg.set(uuid + ".Von", (Object)Von);
            final Date date = new Date(BanCMD.Banned_cfg.getLong(UUIDP + ".Laenge"));
            final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            for (final Player p2 : Bukkit.getOnlinePlayers()) {
                if (p2.hasPermission("system.bansystem.ban.see")) {
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                    p2.sendMessage(String.valueOf(Prefix) + "§c" + Name + " §7wurde vom §cNetzwerk §7gesperrt.");
                    p2.sendMessage(String.valueOf(Prefix) + "§7Grund: §c" + BanCMD.Banned_cfg.getString(UUIDP + ".Grund"));
                    p2.sendMessage(String.valueOf(Prefix) + "§7Von: §c" + von.getName());
                    p2.sendMessage(String.valueOf(Prefix) + "§7Entbannungsdatum: §c" + mm_dd_yyyy + " §7um §c" + hour_min_sec);
                    p2.sendMessage("§8----------§7[§e" + Servername + "§7]§8----------");
                }
            }
            von.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + Name + " §7erfolgreich gebannt!");
        }
        else {
            von.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits gebannt.");
        }
        try {
            BanCMD.Banned_cfg.save(BanCMD.Banned);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
