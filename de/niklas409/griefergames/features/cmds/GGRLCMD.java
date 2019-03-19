package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class GGRLCMD implements CommandExecutor
{
    private Main plugin;
    
    public GGRLCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("ggrl").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("System.reload")) {
                Main.reload();
                p.sendMessage(String.valueOf(Prefix) + "§7Die Config wurde erfolgreich reloadet!");
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Main.reload();
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§7Die Config wurde erfolgreich reloadet!");
        }
        return true;
    }
}
