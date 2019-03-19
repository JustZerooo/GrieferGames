package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class StatusCMD implements CommandExecutor
{
    private Main plugin;
    
    public StatusCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("status").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final File Status = new File("plugins/GrieferGames/Data/Status.yml");
            final YamlConfiguration yStatus = YamlConfiguration.loadConfiguration(Status);
            if (p.hasPermission("system.status")) {
                if (args.length >= 1) {
                    if (!args[0].equalsIgnoreCase("off")) {
                        String Message = "";
                        for (int i = 0; i < args.length; ++i) {
                            Message = String.valueOf(Message) + args[i] + " ";
                        }
                        yStatus.set(p.getUniqueId().toString(), (Object)Message);
                        try {
                            yStatus.save(Status);
                        }
                        catch (IOException e) {
                            e.printStackTrace();
                        }
                        p.sendMessage(String.valueOf(Prefix) + "§7Du hast dein Status zu: §a" + Message + " §7geändert!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§7Du hast dein Status gelöscht!");
                        yStatus.set(p.getUniqueId().toString(), (Object)null);
                        try {
                            yStatus.save(Status);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Status <Off/Nachricht>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann kein Status setzen!");
        }
        return true;
    }
}
