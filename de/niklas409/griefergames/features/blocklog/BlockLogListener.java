package de.niklas409.griefergames.features.blocklog;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.Material;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.entity.Player;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.plugin.Plugin;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.event.Listener;

public class BlockLogListener implements Listener
{
    private Main plugin;
    
    public BlockLogListener(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
        final Player p = e.getPlayer();
        final Date date = new Date(System.currentTimeMillis());
        final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
        final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
        Main.mysql.update("INSERT INTO BlöckeAbbauen(Location, Block, SpielerUUID, Datum, Uhrzeit) VALUES ('" + e.getBlock().getLocation() + "', '" + e.getBlock().getType() + "', '" + p.getUniqueId() + "', '" + mm_dd_yyyy + "', '" + hour_min_sec + "');");
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() != Material.BEDROCK || !p.getItemInHand().hasItemMeta() || !p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lBlockLog=Abgebaut") || !p.getItemInHand().getItemMeta().hasLore() || !p.getItemInHand().getItemMeta().getLore().contains("§4§lGG Features")) {
            final Date date = new Date(System.currentTimeMillis());
            final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            Main.mysql.update("INSERT INTO BlöckeBauen(Location, Block, SpielerUUID, Datum, Uhrzeit) VALUES ('" + e.getBlock().getLocation() + "', '" + e.getBlock().getType() + "', '" + p.getUniqueId() + "', '" + mm_dd_yyyy + "', '" + hour_min_sec + "');");
        }
    }
    
    @EventHandler
    public void onClick(final PlayerInteractEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_BLOCK && e.getClickedBlock().getType() != Material.AIR && p.getItemInHand().getType() == Material.DIAMOND_PICKAXE && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lBlockLog=Gebaut") && p.getItemInHand().getItemMeta().hasLore() && p.getItemInHand().getItemMeta().getLore().contains("§4§lGG Features")) {
            final Location loc = e.getClickedBlock().getLocation();
            if (BlockExists(loc, "BlöckeBauen")) {
                final UUID uuid = UUID.fromString(getUUID(loc, "BlöckeBauen"));
                p.sendMessage(String.valueOf(Prefix) + "§7Gebaut am: §c" + getDatum(loc, "BlöckeBauen") + " §7um §c" + getUhrzeit(loc, "BlöckeBauen") + " §7von §c" + Bukkit.getOfflinePlayer(uuid).getName() + "\n" + " §7[§c" + getBlock(loc, "BlöckeBauen") + "§7]");
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cDer Block wurde von der Welt platziert, oder von einem Spieler vor der installation des Logs.");
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
            }
        }
    }
    
    @EventHandler
    public void onBlockCheck(final BlockPlaceEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if (p.getItemInHand().getType() == Material.BEDROCK && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lBlockLog=Abgebaut") && p.getItemInHand().getItemMeta().hasLore() && p.getItemInHand().getItemMeta().getLore().contains("§4§lGG Features")) {
            e.setCancelled(true);
            final Location loc = e.getBlock().getLocation();
            if (BlockExists(loc, "BlöckeAbbauen")) {
                final UUID uuid = UUID.fromString(getUUID(loc, "BlöckeAbbauen"));
                p.sendMessage(String.valueOf(Prefix) + "§7Abgebaut am: §c" + getDatum(loc, "BlöckeAbbauen") + " §7um §c" + getUhrzeit(loc, "BlöckeAbbauen") + " §7von §c" + Bukkit.getOfflinePlayer(uuid).getName() + "\n" + " §7[§c" + getBlock(loc, "BlöckeAbbauen") + "§7]");
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cDer Block wurde von einem Spieler vor der installation des Logs abgebaut.");
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
            }
        }
    }
    
    public static boolean BlockExists(final Location loc, final String TName) {
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM " + TName + " WHERE Location= '" + loc + "'");
            return rs.next() && rs.getString("Location") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    
    public static String getUUID(final Location loc, final String TName) {
        String i = "";
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM " + TName + " WHERE Location= '" + loc + "'");
            if (!rs.next() || String.valueOf(rs.getString("SpielerUUID")) == null) {}
            i = rs.getString("SpielerUUID");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public static String getBlock(final Location loc, final String TName) {
        String i = "";
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM " + TName + " WHERE Location= '" + loc + "'");
            if (!rs.next() || String.valueOf(rs.getString("Block")) == null) {}
            i = rs.getString("Block");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public static String getDatum(final Location loc, final String TName) {
        String i = "";
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM " + TName + " WHERE Location= '" + loc + "'");
            if (!rs.next() || String.valueOf(rs.getString("Datum")) == null) {}
            i = rs.getString("Datum");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
    
    public static String getUhrzeit(final Location loc, final String TName) {
        String i = "";
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM " + TName + " WHERE Location= '" + loc + "'");
            if (!rs.next() || String.valueOf(rs.getString("Uhrzeit")) == null) {}
            i = rs.getString("Uhrzeit");
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
        return i;
    }
}
