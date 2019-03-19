package de.niklas409.griefergames.features.cmds;

import org.bukkit.command.ConsoleCommandSender;
import java.util.Iterator;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class UnbanCMD implements CommandExecutor
{
    private Main plugin;
    
    public UnbanCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("unban").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.bansystem.unban")) {
                if (args.length == 1) {
                    final String target = args[0];
                    final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                    if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                        BanCMD.Banned_cfg.set(UUID, (Object)null);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("system.bansystem.unban.see")) {
                                all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p.getName() + " §7entbannt!");
                            }
                        }
                        try {
                            BanCMD.Banned_cfg.save(BanCMD.Banned);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gebannt.");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unban <Spieler>");
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
                final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                    BanCMD.Banned_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.bansystem.unban.see")) {
                            all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entbannt!");
                        }
                    }
                    p2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entbannt!");
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gebannt.");
                }
            }
            else {
                p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unban <Spieler>");
            }
        }
        return true;
    }
}
