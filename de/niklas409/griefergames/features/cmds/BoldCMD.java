package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import java.io.IOException;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.CommandExecutor;

public class BoldCMD implements CommandExecutor
{
    public static File Bold;
    public static YamlConfiguration yBold;
    private Main plugin;
    
    static {
        BoldCMD.Bold = new File("plugins/GrieferGames/Data/Bold.yml");
        BoldCMD.yBold = YamlConfiguration.loadConfiguration(BoldCMD.Bold);
    }
    
    public BoldCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("b").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final Player p = (Player)sender;
            if (p.hasPermission("system.bold")) {
                if (BoldCMD.yBold.getString(p.getName()) == null) {
                    BoldCMD.yBold.set(p.getName(), (Object)"on");
                    try {
                        BoldCMD.yBold.save(BoldCMD.Bold);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(String.valueOf(Prefix) + "§eDu schreibst nun fett!");
                }
                else if (BoldCMD.yBold.getString(p.getName()).equalsIgnoreCase("off")) {
                    BoldCMD.yBold.set(p.getName(), (Object)"on");
                    try {
                        BoldCMD.yBold.save(BoldCMD.Bold);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(String.valueOf(Prefix) + "§eDu schreibst nun fett!");
                }
                else if (BoldCMD.yBold.getString(p.getName()).equalsIgnoreCase("on")) {
                    BoldCMD.yBold.set(p.getName(), (Object)"off");
                    try {
                        BoldCMD.yBold.save(BoldCMD.Bold);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                    p.sendMessage(String.valueOf(Prefix) + "§aDu schreibst nun nicht mehr fett!");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDiesen Command kann nur ein Spieler ausführen!");
        }
        return true;
    }
}
