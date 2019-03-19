package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class SchildCMD implements CommandExecutor
{
    private Main plugin;
    public static ArrayList<Player> Schild;
    
    static {
        SchildCMD.Schild = new ArrayList<Player>();
    }
    
    public SchildCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("schild").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            p.sendMessage(String.valueOf(Prefix) + "§aWird programmiert...");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Schilder bearbeiten!");
        }
        return true;
    }
}
