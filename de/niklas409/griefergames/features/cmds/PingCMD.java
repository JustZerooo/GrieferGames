package de.niklas409.griefergames.features.cmds;

import net.minecraft.server.v1_8_R3.EntityPlayer;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class PingCMD implements CommandExecutor
{
    private Main plugin;
    
    public PingCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("ping").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            p.sendMessage(String.valueOf(Prefix) + "§7Dein Ping beträgt §c" + this.getPing(p) + "§7ms.");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDer Command ist nur fuer Spieler.");
        }
        return true;
    }
    
    public int getPing(final Player p) {
        final CraftPlayer pingc = (CraftPlayer)p;
        final EntityPlayer pinge = pingc.getHandle();
        return pinge.ping;
    }
}
