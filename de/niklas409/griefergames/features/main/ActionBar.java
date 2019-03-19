package de.niklas409.griefergames.features.main;

import net.minecraft.server.v1_8_R3.Packet;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import net.minecraft.server.v1_8_R3.PacketPlayOutChat;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.entity.Player;

public class ActionBar
{
    public ActionBar() {
        super();
    }
    
    public static void sendActionBarTime(final Player player, final String Nachricht) {
        final String NachrichtNeu = Nachricht.replace("_", " ");
        final String s = ChatColor.translateAlternateColorCodes('&', NachrichtNeu);
        final IChatBaseComponent icbc = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + s + "\"}");
        final PacketPlayOutChat bar = new PacketPlayOutChat(icbc, (byte)2);
        ((CraftPlayer)player).getHandle().playerConnection.sendPacket((Packet)bar);
    }
}
