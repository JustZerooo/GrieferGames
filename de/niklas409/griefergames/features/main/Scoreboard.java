package de.niklas409.griefergames.features.main;

import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import net.minecraft.server.v1_8_R3.ScoreboardObjective;
import com.earth2me.essentials.Essentials;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardScore;
import net.minecraft.server.v1_8_R3.ScoreboardScore;
import org.bukkit.Bukkit;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardDisplayObjective;
import net.minecraft.server.v1_8_R3.PacketPlayOutScoreboardObjective;
import net.minecraft.server.v1_8_R3.IScoreboardCriteria;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;

public class Scoreboard
{
    public Scoreboard() {
        super();
    }
    
    public static void sendScoreboard(final Player p) {
        final File SB = new File("plugins/GrieferGames/Scoreboard.yml");
        final YamlConfiguration ySB = YamlConfiguration.loadConfiguration(SB);
        final String Titel = ySB.getString("Titel").replaceAll("&", "§");
        final net.minecraft.server.v1_8_R3.Scoreboard board = new net.minecraft.server.v1_8_R3.Scoreboard();
        final ScoreboardObjective obj = board.registerObjective(Titel, IScoreboardCriteria.b);
        final PacketPlayOutScoreboardObjective removepacket = new PacketPlayOutScoreboardObjective(obj, 1);
        final PacketPlayOutScoreboardObjective createpacket = new PacketPlayOutScoreboardObjective(obj, 0);
        final PacketPlayOutScoreboardDisplayObjective display = new PacketPlayOutScoreboardDisplayObjective(1, obj);
        SendPacket(p, (Packet<?>)removepacket);
        SendPacket(p, (Packet<?>)createpacket);
        SendPacket(p, (Packet<?>)display);
        obj.setDisplayName(Titel);
        final String OnPlayer = String.valueOf(Bukkit.getOnlinePlayers().size());
        final String MaxPlayer = String.valueOf(Bukkit.getMaxPlayers());
        final PluginManager manager = Bukkit.getServer().getPluginManager();
        final Plugin ess = manager.getPlugin("Essentials");
        if (ess == null || !ess.isEnabled()) {
            final String EssentialsGeld = "Essentials nicht installiert";
            final String Line13 = ySB.getString("Line13").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line13.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s0 = new ScoreboardScore(board, obj, Line13);
                s0.setScore(13);
                final PacketPlayOutScoreboardScore ps0 = new PacketPlayOutScoreboardScore(s0);
                SendPacket(p, (Packet<?>)ps0);
            }
            final String Line14 = ySB.getString("Line12").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line14.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s2 = new ScoreboardScore(board, obj, Line14);
                s2.setScore(12);
                final PacketPlayOutScoreboardScore ps2 = new PacketPlayOutScoreboardScore(s2);
                SendPacket(p, (Packet<?>)ps2);
            }
            final String Line15 = ySB.getString("Line11").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line15.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s3 = new ScoreboardScore(board, obj, Line15);
                s3.setScore(11);
                final PacketPlayOutScoreboardScore ps3 = new PacketPlayOutScoreboardScore(s3);
                SendPacket(p, (Packet<?>)ps3);
            }
            final String Line16 = ySB.getString("Line10").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line16.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s4 = new ScoreboardScore(board, obj, Line16);
                s4.setScore(10);
                final PacketPlayOutScoreboardScore ps4 = new PacketPlayOutScoreboardScore(s4);
                SendPacket(p, (Packet<?>)ps4);
            }
            final String Line17 = ySB.getString("Line9").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line17.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s5 = new ScoreboardScore(board, obj, Line17);
                s5.setScore(9);
                final PacketPlayOutScoreboardScore ps5 = new PacketPlayOutScoreboardScore(s5);
                SendPacket(p, (Packet<?>)ps5);
            }
            final String Line18 = ySB.getString("Line8").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line18.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s6 = new ScoreboardScore(board, obj, Line18);
                s6.setScore(8);
                final PacketPlayOutScoreboardScore ps6 = new PacketPlayOutScoreboardScore(s6);
                SendPacket(p, (Packet<?>)ps6);
            }
            final String Line19 = ySB.getString("Line7").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line19.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s7 = new ScoreboardScore(board, obj, Line19);
                s7.setScore(7);
                final PacketPlayOutScoreboardScore ps7 = new PacketPlayOutScoreboardScore(s7);
                SendPacket(p, (Packet<?>)ps7);
            }
            final String Line20 = ySB.getString("Line6").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line20.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s8 = new ScoreboardScore(board, obj, Line20);
                s8.setScore(6);
                final PacketPlayOutScoreboardScore ps8 = new PacketPlayOutScoreboardScore(s8);
                SendPacket(p, (Packet<?>)ps8);
            }
            final String Line21 = ySB.getString("Line5").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line21.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s9 = new ScoreboardScore(board, obj, Line21);
                s9.setScore(5);
                final PacketPlayOutScoreboardScore ps9 = new PacketPlayOutScoreboardScore(s9);
                SendPacket(p, (Packet<?>)ps9);
            }
            final String Line22 = ySB.getString("Line4").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line22.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s10 = new ScoreboardScore(board, obj, Line22);
                s10.setScore(4);
                final PacketPlayOutScoreboardScore ps10 = new PacketPlayOutScoreboardScore(s10);
                SendPacket(p, (Packet<?>)ps10);
            }
            final String Line23 = ySB.getString("Line3").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line23.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s11 = new ScoreboardScore(board, obj, Line23);
                s11.setScore(3);
                final PacketPlayOutScoreboardScore ps11 = new PacketPlayOutScoreboardScore(s11);
                SendPacket(p, (Packet<?>)ps11);
            }
            final String Line24 = ySB.getString("Line2").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line24.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s12 = new ScoreboardScore(board, obj, Line24);
                s12.setScore(2);
                final PacketPlayOutScoreboardScore ps12 = new PacketPlayOutScoreboardScore(s12);
                SendPacket(p, (Packet<?>)ps12);
            }
            final String Line25 = ySB.getString("Line1").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line25.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s13 = new ScoreboardScore(board, obj, Line25);
                s13.setScore(1);
                final PacketPlayOutScoreboardScore ps13 = new PacketPlayOutScoreboardScore(s13);
                SendPacket(p, (Packet<?>)ps13);
            }
        }
        else {
            final Essentials essp = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
            final String EssentialsGeld2 = String.valueOf(essp.getUser(p).getMoney());
            final String Line26 = ySB.getString("Line13").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line26.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s14 = new ScoreboardScore(board, obj, Line26);
                s14.setScore(13);
                final PacketPlayOutScoreboardScore ps14 = new PacketPlayOutScoreboardScore(s14);
                SendPacket(p, (Packet<?>)ps14);
            }
            final String Line27 = ySB.getString("Line12").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line27.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s15 = new ScoreboardScore(board, obj, Line27);
                s15.setScore(12);
                final PacketPlayOutScoreboardScore ps15 = new PacketPlayOutScoreboardScore(s15);
                SendPacket(p, (Packet<?>)ps15);
            }
            final String Line28 = ySB.getString("Line11").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line28.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s16 = new ScoreboardScore(board, obj, Line28);
                s16.setScore(11);
                final PacketPlayOutScoreboardScore ps16 = new PacketPlayOutScoreboardScore(s16);
                SendPacket(p, (Packet<?>)ps16);
            }
            final String Line29 = ySB.getString("Line10").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line29.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s17 = new ScoreboardScore(board, obj, Line29);
                s17.setScore(10);
                final PacketPlayOutScoreboardScore ps17 = new PacketPlayOutScoreboardScore(s17);
                SendPacket(p, (Packet<?>)ps17);
            }
            final String Line30 = ySB.getString("Line9").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line30.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s18 = new ScoreboardScore(board, obj, Line30);
                s18.setScore(9);
                final PacketPlayOutScoreboardScore ps18 = new PacketPlayOutScoreboardScore(s18);
                SendPacket(p, (Packet<?>)ps18);
            }
            final String Line31 = ySB.getString("Line8").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line31.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s19 = new ScoreboardScore(board, obj, Line31);
                s19.setScore(8);
                final PacketPlayOutScoreboardScore ps19 = new PacketPlayOutScoreboardScore(s19);
                SendPacket(p, (Packet<?>)ps19);
            }
            final String Line32 = ySB.getString("Line7").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line32.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s20 = new ScoreboardScore(board, obj, Line32);
                s20.setScore(7);
                final PacketPlayOutScoreboardScore ps20 = new PacketPlayOutScoreboardScore(s20);
                SendPacket(p, (Packet<?>)ps20);
            }
            final String Line33 = ySB.getString("Line6").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line33.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s21 = new ScoreboardScore(board, obj, Line33);
                s21.setScore(6);
                final PacketPlayOutScoreboardScore ps21 = new PacketPlayOutScoreboardScore(s21);
                SendPacket(p, (Packet<?>)ps21);
            }
            final String Line34 = ySB.getString("Line5").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line34.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s22 = new ScoreboardScore(board, obj, Line34);
                s22.setScore(5);
                final PacketPlayOutScoreboardScore ps22 = new PacketPlayOutScoreboardScore(s22);
                SendPacket(p, (Packet<?>)ps22);
            }
            final String Line35 = ySB.getString("Line4").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line35.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s23 = new ScoreboardScore(board, obj, Line35);
                s23.setScore(4);
                final PacketPlayOutScoreboardScore ps23 = new PacketPlayOutScoreboardScore(s23);
                SendPacket(p, (Packet<?>)ps23);
            }
            final String Line36 = ySB.getString("Line3").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line36.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s24 = new ScoreboardScore(board, obj, Line36);
                s24.setScore(3);
                final PacketPlayOutScoreboardScore ps24 = new PacketPlayOutScoreboardScore(s24);
                SendPacket(p, (Packet<?>)ps24);
            }
            final String Line37 = ySB.getString("Line2").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line37.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s25 = new ScoreboardScore(board, obj, Line37);
                s25.setScore(2);
                final PacketPlayOutScoreboardScore ps25 = new PacketPlayOutScoreboardScore(s25);
                SendPacket(p, (Packet<?>)ps25);
            }
            final String Line38 = ySB.getString("Line1").replaceAll("&", "§").replaceAll("%ONPLAYER%", OnPlayer).replaceAll("%MAXPLAYER%", MaxPlayer).replaceAll("%ESSENTIALSMONEY%", EssentialsGeld2).replaceAll("%WORLD%", p.getWorld().getName());
            if (!Line38.equalsIgnoreCase("%NULL%")) {
                final ScoreboardScore s26 = new ScoreboardScore(board, obj, Line38);
                s26.setScore(1);
                final PacketPlayOutScoreboardScore ps26 = new PacketPlayOutScoreboardScore(s26);
                SendPacket(p, (Packet<?>)ps26);
            }
        }
    }
    
    public static void SendPacket(final Player p, final Packet<?> packet) {
        ((CraftPlayer)p).getHandle().playerConnection.sendPacket((Packet)packet);
    }
}
