package de.niklas409.griefergames.features.cmds;

import com.mojang.authlib.properties.Property;
import java.util.Collection;
import com.mojang.authlib.GameProfile;
import net.minecraft.server.v1_8_R3.PacketPlayOutNamedEntitySpawn;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerInfo;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutEntityDestroy;
import de.niklas409.griefergames.features.main.GameProfileBuilder;
import de.niklas409.griefergames.features.main.UUIDFetcher;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.Location;
import java.util.HashMap;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class SkinCMD implements CommandExecutor
{
    private static Main plugin;
    public static HashMap<String, Location> oldloc;
    public static HashMap<String, Integer> oldxp;
    
    static {
        SkinCMD.oldloc = new HashMap<String, Location>();
        SkinCMD.oldxp = new HashMap<String, Integer>();
    }
    
    public SkinCMD(final Main plugin) {
        super();
        SkinCMD.plugin = plugin;
        plugin.getCommand("skin").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = SkinCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + SkinCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.skin")) {
                if (args.length == 1) {
                    SkinCMD.oldloc.put(p.getName(), p.getLocation());
                    SkinCMD.oldxp.put(p.getName(), p.getTotalExperience());
                    changeSkin(p, args[0]);
                    p.sendMessage(String.valueOf(Prefix) + "§7Dein Skin wurde zu: §a" + args[0] + " §7geändert!");
                    p.teleport((Location)SkinCMD.oldloc.get(p.getName()));
                    p.setTotalExperience(0);
                    p.setLevel(0);
                    p.setExp(0.0f);
                    p.giveExp((int)SkinCMD.oldxp.get(p.getName()));
                    SkinCMD.oldloc.remove(p.getName());
                    SkinCMD.oldxp.remove(p.getName());
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Skin <Name>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann kein Skin aendern!");
        }
        return true;
    }
    
    public static void changeSkin(final Player p, final String SkinName) {
        GameProfile gp = ((CraftPlayer)p).getProfile();
        try {
            gp = GameProfileBuilder.fetch(UUIDFetcher.getUUID(SkinName));
        }
        catch (Exception ex) {}
        final Collection<Property> props = (Collection<Property>)gp.getProperties().get((Object)"textures");
        ((CraftPlayer)p).getProfile().getProperties().removeAll((Object)"textures");
        ((CraftPlayer)p).getProfile().getProperties().putAll((Object)"textures", (Iterable)props);
        final PacketPlayOutEntityDestroy destroy = new PacketPlayOutEntityDestroy(new int[] { ((CraftPlayer)p).getEntityId() });
        sendPacket((Packet<?>)destroy);
        final PacketPlayOutPlayerInfo tabInfoRemove = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.REMOVE_PLAYER, new EntityPlayer[] { ((CraftPlayer)p).getHandle() });
        sendPacket((Packet<?>)tabInfoRemove);
        ((CraftPlayer)p).getHandle().setHealth(0.0f);
        ((CraftPlayer)p).spigot().respawn();
        Bukkit.getScheduler().runTaskLater((Plugin)SkinCMD.plugin, (Runnable)new Runnable() {
            private final /* synthetic */ Player val$p;
            
            SkinCMD$1() {
                super();
            }
            
            @Override
            public void run() {
                final PacketPlayOutPlayerInfo tabInfoAdd = new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER, new EntityPlayer[] { ((CraftPlayer)p).getHandle() });
                sendPacket((Packet<?>)tabInfoAdd);
                final PacketPlayOutNamedEntitySpawn spawn = SkinCMD.PacketPlayOutNamedEntitySpawn(((CraftPlayer)p).getHandle());
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (all != p) {
                        ((CraftPlayer)all).getHandle().playerConnection.sendPacket((Packet)spawn);
                        all.hidePlayer(p);
                    }
                }
                Bukkit.getScheduler().runTaskLater((Plugin)SkinCMD.plugin, (Runnable)new Runnable() {
                    final /* synthetic */ SkinCMD$1 this$1;
                    private final /* synthetic */ Player val$p;
                    
                    SkinCMD$1$1() {
                        this.this$1 = this$1;
                        super();
                    }
                    
                    @Override
                    public void run() {
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            if (all != p) {
                                all.showPlayer(p);
                            }
                        }
                    }
                }, 10L);
            }
        }, 10L);
    }
    
    protected static PacketPlayOutNamedEntitySpawn PacketPlayOutNamedEntitySpawn(final EntityPlayer handle) {
        return null;
    }
    
    private static void sendPacket(final Packet<?> packet) {
        for (final Player all : Bukkit.getOnlinePlayers()) {
            ((CraftPlayer)all).getHandle().playerConnection.sendPacket((Packet)packet);
        }
    }
    
    static /* synthetic */ void access$0(final Packet packet) {
        sendPacket((Packet<?>)packet);
    }
    
    static /* synthetic */ Main access$1() {
        return SkinCMD.plugin;
    }
}
