package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class NeinCMD implements CommandExecutor
{
    private Main plugin;
    
    public NeinCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("nein").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final String PrefixStartKick = this.plugin.getConfig().getString("StartKickPrefix").replace("&", "§");
            if (StartKickCMD.voting.contains("true")) {
                if (!StartKickCMD.Ja.contains(p.getName()) && !StartKickCMD.Nein.contains(p.getName())) {
                    StartKickCMD.Nein.add(p.getName());
                    p.sendMessage(String.valueOf(PrefixStartKick) + "§eDu hast für §cNein §eabgestimmt!");
                }
                else {
                    p.sendMessage(String.valueOf(PrefixStartKick) + "§cDu hast bereits abgestimmt!");
                }
            }
            else {
                p.sendMessage(String.valueOf(PrefixStartKick) + "§eEs läuft keine Abstimmung!");
            }
        }
        else {
            final String PrefixStartKick2 = this.plugin.getConfig().getString("StartKickPrefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(PrefixStartKick2) + "§cDie §4§lConsole §cdarf nicht mit Abstimmen.");
        }
        return true;
    }
}
