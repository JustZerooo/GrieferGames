package de.niklas409.griefergames.features.cmds;

import org.bukkit.World;
import org.bukkit.Location;
import org.bukkit.plugin.Plugin;
import java.io.IOException;
import java.io.OutputStream;
import java.io.DataOutputStream;
import java.io.ByteArrayOutputStream;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.CommandExecutor;

public class PortalCMD implements CommandExecutor
{
    public static File Portal;
    public static YamlConfiguration yPortal;
    private Main plugin;
    
    static {
        PortalCMD.Portal = new File("plugins/GrieferGames/Data/Portal.yml");
        PortalCMD.yPortal = YamlConfiguration.loadConfiguration(PortalCMD.Portal);
    }
    
    public PortalCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("portal").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                if (this.plugin.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Server")) {
                    final String Server = this.plugin.getConfig().getString("Servername[Bungee-Config] vom PortalServer");
                    this.connect(Server, p);
                }
                else if (this.plugin.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Map")) {
                    if (PortalCMD.Portal.exists()) {
                        TPPortalMap(p, PortalCMD.yPortal, ".Portal");
                        p.sendMessage(String.valueOf(Prefix) + "§fDu bist nun im §5§lPortalraum§f. Wähle deinen Citybuild aus.");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spawn für den §5Portalraum §cwurde noch nicht gesetzt!");
                    }
                }
            }
            else if (args.length == 1) {
                if (p.hasPermission("system.portal.admin")) {
                    if (this.plugin.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Map")) {
                        this.setPortalMap(p, PortalCMD.yPortal, PortalCMD.Portal, ".Portal");
                        p.sendMessage(String.valueOf(Prefix) + "§7Du hast den Spawn für den §5Portalraum §7gesetzt!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst in der Config, PortalSettings auf\n §a\"Map\" §cstellen.");
                    }
                }
                else {
                    p.sendMessage(NoPerms);
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Portal (<set>)");
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cKann eine Console ueberhaupt in ein Portal laufen?");
        }
        return true;
    }
    
    public void connect(final String server, final Player p) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final ByteArrayOutputStream b = new ByteArrayOutputStream();
        final DataOutputStream out = new DataOutputStream(b);
        try {
            out.writeUTF("Connect");
            out.writeUTF(server);
        }
        catch (IOException ex) {
            ex.printStackTrace();
            if (!p.hasPermission("system.portal.admin")) {
                p.sendMessage(String.valueOf(Prefix) + "§cEin Fehler ist aufgetreten, bitte kontaktiere ein Admin.");
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cBist du dir sicher, dass der Server in der Config §a" + server + " §cheißt?");
            }
        }
        try {
            p.sendPluginMessage((Plugin)this.plugin, "BungeeCord", b.toByteArray());
        }
        catch (Exception e1) {
            if (!p.hasPermission("system.portal.admin")) {
                p.sendMessage(String.valueOf(Prefix) + "§cEin Fehler ist aufgetreten, bitte kontaktiere ein Admin.");
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cBist du dir sicher, dass der Server in der Config §a" + server + " §cheißt?");
            }
        }
    }
    
    public void setPortalMap(final Player p, final YamlConfiguration cfg, final File file, final String path) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final double X = p.getLocation().getX();
        final double Y = p.getLocation().getY();
        final double Z = p.getLocation().getZ();
        final float Yaw = p.getLocation().getYaw();
        final float Pitch = p.getLocation().getPitch();
        final String World = p.getLocation().getWorld().getName();
        cfg.set("Portal." + path + ".X", (Object)X);
        cfg.set("Portal." + path + ".Y", (Object)Y);
        cfg.set("Portal." + path + ".Z", (Object)Z);
        cfg.set("Portal." + path + ".Yaw", (Object)Yaw);
        cfg.set("Portal." + path + ".Pitch", (Object)Pitch);
        cfg.set("Portal." + path + ".World", (Object)World);
        try {
            cfg.save(file);
        }
        catch (IOException e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDatei konnte nicht gespeichert werden! §bConfig");
        }
    }
    
    public static void TPPortalMap(final Player p, final YamlConfiguration cfg, final String path) {
        final Double X = cfg.getDouble("Portal." + path + ".X");
        final Double Y = cfg.getDouble("Portal." + path + ".Y");
        final Double Z = cfg.getDouble("Portal." + path + ".Z");
        final float Yaw = (float)cfg.getDouble("Portal." + path + ".Yaw");
        final float Pitch = (float)cfg.getDouble("Portal." + path + ".Pitch");
        final World World = Bukkit.getWorld(cfg.getString("Portal." + path + ".World"));
        final Location loc = new Location(World, (double)X, (double)Y, (double)Z, Yaw, Pitch);
        p.teleport(loc);
    }
}
