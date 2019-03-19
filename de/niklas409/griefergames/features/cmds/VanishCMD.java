package de.niklas409.griefergames.features.cmds;

import org.bukkit.command.ConsoleCommandSender;
import java.util.Iterator;
import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class VanishCMD implements CommandExecutor
{
    private Main plugin;
    public static File Vanish;
    public static YamlConfiguration yVanish;
    
    static {
        VanishCMD.Vanish = new File("plugins/GrieferGames/Data/Vanish.yml");
        VanishCMD.yVanish = YamlConfiguration.loadConfiguration(VanishCMD.Vanish);
    }
    
    public VanishCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("vanish").setExecutor((CommandExecutor)this);
        plugin.getCommand("v").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.vanish")) {
                if (args.length == 0) {
                    if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish") == null) {
                        p.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"true");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("system.vanish.see")) {
                                all.hidePlayer(p);
                            }
                            else {
                                all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7ist in den Vanish gegangen.");
                            }
                        }
                    }
                    else if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("false")) {
                        p.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"true");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (!all.hasPermission("system.vanish.see")) {
                                all.hidePlayer(p);
                            }
                            else {
                                all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7ist in den Vanish gegangen.");
                            }
                        }
                    }
                    else if (VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("true")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4§lDu bist nun sichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(p.getName()) + ".Vanish", (Object)"false");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.showPlayer(p);
                            if (all.hasPermission("system.vanish.see")) {
                                all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7ist aus den Vanish gegangen.");
                            }
                        }
                    }
                }
                else if (args.length == 1) {
                    final String target = args[0];
                    final Player tar = Bukkit.getPlayer(target);
                    if (tar != null) {
                        if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish") == null) {
                            tar.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                            p.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                            VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                            try {
                                VanishCMD.yVanish.save(VanishCMD.Vanish);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                if (!all2.hasPermission("system.vanish.see")) {
                                    all2.hidePlayer(p);
                                }
                                else {
                                    all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                }
                            }
                        }
                        else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("false")) {
                            tar.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                            p.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                            VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                            try {
                                VanishCMD.yVanish.save(VanishCMD.Vanish);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                if (!all2.hasPermission("system.vanish.see")) {
                                    all2.hidePlayer(p);
                                }
                                else {
                                    all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                                }
                            }
                        }
                        else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("true")) {
                            tar.sendMessage(String.valueOf(Prefix) + "§4§lDu bist nun sichtbar!");
                            p.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §4§lsichtbar!");
                            VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"false");
                            try {
                                VanishCMD.yVanish.save(VanishCMD.Vanish);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                            for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                all2.showPlayer(tar);
                                if (all2.hasPermission("system.vanish.see")) {
                                    all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde aus den Vanish gesetzt.");
                                }
                            }
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Vanish (<Spieler>)");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
            if (args.length == 1) {
                final String target = args[0];
                final Player tar = Bukkit.getPlayer(target);
                if (tar != null) {
                    if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish") == null) {
                        tar.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        for (final Player all2 : Bukkit.getOnlinePlayers()) {
                            if (!all2.hasPermission("system.vanish.see")) {
                                all2.hidePlayer(tar);
                            }
                            else {
                                all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                            }
                        }
                    }
                    else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("false")) {
                        tar.sendMessage(String.valueOf(Prefix) + "§2§lDu bist nun unsichtbar!");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §2§lunsichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"true");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        for (final Player all2 : Bukkit.getOnlinePlayers()) {
                            if (!all2.hasPermission("system.vanish.see")) {
                                all2.hidePlayer(tar);
                            }
                            else {
                                all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde in den Vanish gesetzt.");
                            }
                        }
                    }
                    else if (VanishCMD.yVanish.getString(String.valueOf(tar.getName()) + ".Vanish").contains("true")) {
                        tar.sendMessage(String.valueOf(Prefix) + "§4§lDu bist nun sichtbar!");
                        p2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §c" + tar.getName() + " §7ist nun §4§lsichtbar!");
                        VanishCMD.yVanish.set(String.valueOf(tar.getName()) + ".Vanish", (Object)"false");
                        try {
                            VanishCMD.yVanish.save(VanishCMD.Vanish);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                        for (final Player all2 : Bukkit.getOnlinePlayers()) {
                            all2.showPlayer(tar);
                            if (all2.hasPermission("system.vanish.see")) {
                                all2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + tar.getName() + " §7wurde aus den Vanish gesetzt.");
                            }
                        }
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                }
            }
            else {
                p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Vanish <Spieler>");
            }
        }
        return true;
    }
}
