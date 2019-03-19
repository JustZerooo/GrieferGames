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

public class UnmuteCMD implements CommandExecutor
{
    private Main plugin;
    
    public UnmuteCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("unmute").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.bansystem.unmute")) {
                if (args.length == 1) {
                    final String target = args[0];
                    final String UUID = String.valueOf(Bukkit.getOfflinePlayer(target).getUniqueId());
                    if (BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                        BanCMD.Mutet_cfg.set(UUID, (Object)null);
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (all.hasPermission("system.bansystem.unmute.see")) {
                                all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p.getName() + " §7entmutet!");
                            }
                        }
                        try {
                            BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gemutet.");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unmute <Spieler>");
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
                if (BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Laenge") != null) {
                    BanCMD.Mutet_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.bansystem.unmute.see")) {
                            all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entmutet!");
                        }
                    }
                    p2.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + target + " §7wurde von §a" + p2.getName() + " §7entmutet!");
                    try {
                        BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gemutet.");
                }
            }
            else {
                p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unmute <Spieler>");
            }
        }
        return true;
    }
}
