package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class UnStartKickCMD implements CommandExecutor
{
    private Main plugin;
    
    public UnStartKickCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("unstartkick").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final Player p = (Player)sender;
            if (p.hasPermission("system.unstartkick")) {
                if (args.length == 1) {
                    final String target = args[0];
                    if (StartKickCMD.Banned_cfg.getLong(target) > System.currentTimeMillis()) {
                        StartKickCMD.Banned_cfg.set(target, (Object)null);
                        try {
                            StartKickCMD.Banned_cfg.save(StartKickCMD.Banned);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast " + target + " §aentkickt!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gestartkickt!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/UnStartKick <Spieler>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else if (args.length == 1) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String target2 = args[0];
            if (StartKickCMD.Banned_cfg.getLong(target2) > System.currentTimeMillis()) {
                StartKickCMD.Banned_cfg.set(target2, (Object)null);
                try {
                    StartKickCMD.Banned_cfg.save(StartKickCMD.Banned);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDu hast " + target2 + " §aentkickt!");
            }
            else {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gestartkickt!");
            }
        }
        else {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/UnStartKick <Spieler>");
        }
        return true;
    }
}
