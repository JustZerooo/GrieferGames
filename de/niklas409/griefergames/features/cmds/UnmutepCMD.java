package de.niklas409.griefergames.features.cmds;

import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class UnmutepCMD implements CommandExecutor
{
    private Main plugin;
    
    public UnmutepCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("unmutep").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final Player p = (Player)sender;
            if (p.hasPermission("system.unmutep")) {
                if (args.length == 1) {
                    final String target = args[0];
                    final Player tar = Bukkit.getPlayer(target);
                    if (MutepCMD.Mutep_cfg.getLong(target) > System.currentTimeMillis()) {
                        MutepCMD.Mutep_cfg.set(target, (Object)null);
                        try {
                            MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast " + target + " §aentstummt!");
                        if (tar != null) {
                            tar.sendMessage(String.valueOf(Prefix) + "§aDer Spieler " + p.getName() + " §ahat dich entstummt!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht gestummt!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unmutep <Spieler>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else if (args.length == 1) {
            final String target2 = args[0];
            final Player tar2 = Bukkit.getPlayer(target2);
            final String Prefix2 = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            if (MutepCMD.Mutep_cfg.getLong(target2) > System.currentTimeMillis()) {
                MutepCMD.Mutep_cfg.set(target2, (Object)null);
                try {
                    MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix2) + "§aDu hast " + target2 + " §aentstummt!");
                if (tar2 != null) {
                    tar2.sendMessage(String.valueOf(Prefix2) + "§aDie §4§lConsole §ahat dich entstummt!");
                }
            }
            else {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix2) + "§cDer Spieler ist nicht gestummt!");
            }
        }
        else {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Unmutep <Spieler>");
        }
        return true;
    }
}
