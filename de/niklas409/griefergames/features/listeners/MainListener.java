package de.niklas409.griefergames.features.listeners;

import com.plotsquared.bukkit.events.PlotClearEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.block.Block;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.entity.Minecart;
import org.bukkit.entity.minecart.HopperMinecart;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.block.Furnace;
import org.bukkit.block.Hopper;
import org.bukkit.block.Dropper;
import org.bukkit.block.Dispenser;
import de.niklas409.griefergames.features.main.ActionBar;
import org.bukkit.block.Chest;
import org.bukkit.World;
import de.niklas409.griefergames.features.only.api.cmds.SchildCMD;
import java.lang.reflect.Constructor;
import de.niklas409.griefergames.features.only.api.cmds.Reflect;
import de.niklas409.griefergames.features.main.UUIDFetcher;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.player.PlayerExpChangeEvent;
import org.bukkit.entity.EntityType;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.GameMode;
import de.niklas409.griefergames.features.cmds.BoosterCMD;
import com.intellectualcrafters.plot.config.Configuration;
import com.intellectualcrafters.plot.object.PlotBlock;
import com.intellectualcrafters.plot.object.Plot;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.entity.Snowball;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import java.util.List;
import de.niklas409.griefergames.features.cmds.MeldungCMD;
import de.niklas409.griefergames.features.only.api.cmds.BreakBlockCMD;
import de.niklas409.griefergames.features.cmds.VanishCMD;
import org.bukkit.event.player.PlayerQuitEvent;
import java.util.Iterator;
import de.niklas409.griefergames.features.cmds.BanCMD;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import de.niklas409.griefergames.features.cmds.LuckyBlockCMD;
import org.bukkit.Effect;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.AnvilInventory;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.event.block.SignChangeEvent;
import de.niklas409.griefergames.features.main.ItemBuilder;
import java.math.BigDecimal;
import com.earth2me.essentials.Essentials;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.PluginManager;
import de.niklas409.griefergames.features.only.api.cmds.MergeCMD;
import de.niklas409.griefergames.features.blocklog.MySQL;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.Sound;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import de.niklas409.griefergames.features.main.Scoreboard;
import org.bukkit.event.player.PlayerJoinEvent;
import de.niklas409.griefergames.features.clans.ClanListener;
import de.niklas409.griefergames.features.cmds.BoldCMD;
import de.niklas409.griefergames.features.cmds.ViewarmorCMD;
import org.bukkit.command.CommandSender;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import de.niklas409.griefergames.features.cmds.StartKickCMD;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import de.niklas409.griefergames.features.cmds.MutepCMD;
import org.bukkit.event.EventHandler;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.cmds.SlowchatCMD;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.Location;
import java.util.ArrayList;
import org.bukkit.event.Listener;

public class MainListener implements Listener
{
    public static ArrayList<String> slowchatpause;
    public static ArrayList<String> rand;
    public static ArrayList<Location> LuckyBlockSaver;
    public static HashMap<Player, String> Name;
    public static HashMap<Player, Material> guntype;
    public static HashMap<Location, Material> gunblock;
    public static HashMap<Location, Byte> gunblockdata;
    public static ArrayList<Location> guncheck;
    public static ArrayList<String> gunwait;
    public static HashMap<Player, String> menucanceltap;
    public static File Prefix2;
    public static YamlConfiguration yPrefix;
    private Main plugin;
    public static HashMap<Inventory, Integer> VerbotenStufe;
    public static HashMap<Inventory, Integer> Stufe;
    
    static {
        MainListener.slowchatpause = new ArrayList<String>();
        MainListener.rand = new ArrayList<String>();
        MainListener.LuckyBlockSaver = new ArrayList<Location>();
        MainListener.Name = new HashMap<Player, String>();
        MainListener.guntype = new HashMap<Player, Material>();
        MainListener.gunblock = new HashMap<Location, Material>();
        MainListener.gunblockdata = new HashMap<Location, Byte>();
        MainListener.guncheck = new ArrayList<Location>();
        MainListener.gunwait = new ArrayList<String>();
        MainListener.menucanceltap = new HashMap<Player, String>();
        MainListener.Prefix2 = new File("plugins/GrieferGames/Data/Prefix.yml");
        MainListener.yPrefix = YamlConfiguration.loadConfiguration(MainListener.Prefix2);
        MainListener.VerbotenStufe = new HashMap<Inventory, Integer>();
        MainListener.Stufe = new HashMap<Inventory, Integer>();
    }
    
    public MainListener(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onSlowchat(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (!p.hasPermission("system.slowchat.bypass") && SlowchatCMD.slowchat.contains("true")) {
            if (!MainListener.slowchatpause.contains(p.getName())) {
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    final /* synthetic */ MainListener this$0;
                    private final /* synthetic */ Player val$p;
                    
                    MainListener$1() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public void run() {
                        MainListener.slowchatpause.add(p.getName());
                    }
                }, 1L);
                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                    final /* synthetic */ MainListener this$0;
                    private final /* synthetic */ Player val$p;
                    
                    MainListener$2() {
                        this.this$0 = this$0;
                        super();
                    }
                    
                    @Override
                    public void run() {
                        MainListener.slowchatpause.remove(p.getName());
                    }
                }, 60L);
            }
            else if (MainListener.slowchatpause.contains(p.getName())) {
                e.setCancelled(true);
                p.sendMessage("§eDu kannst nur jede 3 Sekunden schreiben.");
            }
        }
    }
    
    @EventHandler
    public void onMutep(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String name = p.getName();
        if (MutepCMD.Mutep_cfg.get(name) != null) {
            if (MutepCMD.Mutep_cfg.getLong(name) < System.currentTimeMillis()) {
                MutepCMD.Mutep_cfg.set(name, (Object)null);
                try {
                    MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                e.setCancelled(true);
                final Date date = new Date(MutepCMD.Mutep_cfg.getLong(name));
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                final String MutepPlayer = MutepCMD.Mutep_cfg.getString(String.valueOf(p.getName()) + " mutet by");
                p.sendMessage("§eDu bist noch bis §c" + hour_min + " §eUhr gemutet. §b" + MutepPlayer + " §ehat dich gestummt.");
            }
        }
    }
    
    @EventHandler
    public void onVoting(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String PrefixStartKick = this.plugin.getConfig().getString("StartKickPrefix").replace("&", "§");
        if (StartKickCMD.voting.contains("true")) {
            if (!p.hasPermission("system.startkick.chat.bypass")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(PrefixStartKick) + "§7Derzeit läuft ein StartKick, daher kannst du" + "\n" + " §7nichts schreiben!");
                p.sendMessage(String.valueOf(PrefixStartKick) + "§7Abstimmen kannst du mit §a/ja §7für den Rauswurf" + "\n" + " §7und §c/nein §7gegen den Rauswurf des Spielers §b!");
            }
            else {
                e.setFormat(String.valueOf(PrefixStartKick) + "§8: §a" + p.getName() + " §7>> §e§l" + e.getMessage());
            }
        }
    }
    
    @EventHandler
    public void onStartKickBanned(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final String name = p.getName();
        if (StartKickCMD.Banned_cfg.get(name) != null) {
            if (StartKickCMD.Banned_cfg.getLong(name) < System.currentTimeMillis()) {
                StartKickCMD.Banned_cfg.set(name, (Object)null);
                try {
                    StartKickCMD.Banned_cfg.save(StartKickCMD.Banned);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                final Date date = new Date(StartKickCMD.Banned_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
            }
        }
    }
    
    @EventHandler
    public void Inventory(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        try {
            if (e.getInventory().getName().equalsIgnoreCase("§bPrefix Auswahl")) {
                e.setCancelled(true);
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                final String NoPerms = this.plugin.getConfig().getString("NoPerms").replace("&", "§");
                final String Shop = this.plugin.getConfig().getString("Shop").replace("&", "§");
                if (e.getCurrentItem().getType() == Material.STAINED_GLASS_PANE) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Goldener Name")) {
                        if (p.hasPermission("system.prefix.gold")) {
                            this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"§6\"");
                            p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Gold");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Gold");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(NoPerms);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cRoter Name")) {
                        if (p.hasPermission("system.prefix.rot")) {
                            this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"§c\"");
                            p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Rot");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Rot");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(NoPerms);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bBlauer Name")) {
                        if (p.hasPermission("system.prefix.blau")) {
                            this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"§b\"");
                            p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Blau");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Blau");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(NoPerms);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aGrüner Name")) {
                        if (p.hasPermission("system.prefix.grün")) {
                            this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"§a\"");
                            p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Grün");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Grün");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(NoPerms);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§kMagischer Name")) {
                        if (p.hasPermission("system.prefix.magisch")) {
                            this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"§c§k\"");
                            p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Magisch");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                            else {
                                MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)"Magisch");
                                try {
                                    MainListener.yPrefix.save(MainListener.Prefix2);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(NoPerms);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Farbe entfernen")) {
                        this.plugin.getServer().dispatchCommand((CommandSender)this.plugin.getServer().getConsoleSender(), "pex user " + p.getName() + " prefix \"\"");
                        p.kickPlayer(String.valueOf(Prefix) + "§2Dein Prefix wird geändert ...");
                        if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix") == null) {
                            MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)null);
                            try {
                                MainListener.yPrefix.save(MainListener.Prefix2);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        else {
                            MainListener.yPrefix.set(String.valueOf(p.getName()) + ".Prefix", (Object)null);
                            try {
                                MainListener.yPrefix.save(MainListener.Prefix2);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                    }
                }
                else if (e.getCurrentItem().getType() == Material.NETHER_STAR && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Zum Shop")) {
                    p.closeInventory();
                    p.sendMessage(Shop);
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase(ViewarmorCMD.Name.get(p))) {
                e.setCancelled(true);
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onChatSystem(final AsyncPlayerChatEvent e) {
        if (this.plugin.getConfig().getString("ChatSystem[Deaktivieren, wenn es Bugs mit dem Chat gibt]").equalsIgnoreCase("true")) {
            final Player p = e.getPlayer();
            if (this.plugin.getConfig().getString("GruenerChatFuerOp").equalsIgnoreCase("true") && p.hasPermission("system.chat.green")) {
                e.setMessage("§a§l" + e.getMessage());
            }
            if (BoldCMD.yBold.getString(p.getName()) != null && BoldCMD.yBold.getString(p.getName()).equalsIgnoreCase("on")) {
                e.setMessage("§b§l" + e.getMessage());
            }
            if (this.plugin.getConfig().getString("ChatPfeile(AK)").equalsIgnoreCase("true")) {
                if (p.hasPermission("System.Chat.AK")) {
                    if (ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") != null) {
                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        final String Tag = ClanListener.yClans.getString("Clans." + Clanname + ".Kuerzel");
                        final String Suffix = " §6[" + Tag + "]";
                        e.setFormat("§8»\n§r" + p.getDisplayName() + Suffix + " §8: §r" + e.getMessage() + "\n" + "§8»");
                    }
                    else {
                        e.setFormat("§8»\n§r" + p.getDisplayName() + " §8: §r" + e.getMessage() + "\n" + "§8»");
                    }
                }
                else if (ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") != null) {
                    final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                    final String Tag = ClanListener.yClans.getString("Clans." + Clanname + ".Kuerzel");
                    final String Suffix = " §6[" + Tag + "]";
                    e.setFormat(String.valueOf(p.getDisplayName()) + Suffix + " §8: §r" + e.getMessage());
                }
                else {
                    e.setFormat(String.valueOf(p.getDisplayName()) + " §8: §r" + e.getMessage());
                }
            }
            else if (ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") != null) {
                final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                final String Tag = ClanListener.yClans.getString("Clans." + Clanname + ".Kuerzel");
                final String Suffix = " §6[" + Tag + "]";
                e.setFormat(String.valueOf(p.getDisplayName()) + Suffix + " §8: §r" + e.getMessage());
            }
            else {
                e.setFormat(String.valueOf(p.getDisplayName()) + " §8: §r" + e.getMessage());
            }
        }
    }
    
    @EventHandler
    public void onSB(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final File SB = new File("plugins/GrieferGames/Scoreboard.yml");
        final YamlConfiguration ySB = YamlConfiguration.loadConfiguration(SB);
        if (ySB.getString("Scoreboard").equalsIgnoreCase("true")) {
            Scoreboard.sendScoreboard(p);
        }
    }
    
    @EventHandler
    public void onJoin(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final File Config = new File("plugins/GrieferGames/config.yml");
        final YamlConfiguration yConfig = YamlConfiguration.loadConfiguration(Config);
        try {
            if (yConfig.getString("WerbungBeimJoinen").contains("true")) {
                final TextComponent tc = new TextComponent();
                tc.setText("§8[§4§lGG Features§8] §6" + Main.Version + " by §2Niklas409 §6click for details.");
                tc.setBold(Boolean.valueOf(true));
                tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/gg"));
                tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§6click for details").create()));
                p.spigot().sendMessage((BaseComponent)tc);
            }
        }
        catch (Exception ex) {}
        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
            final /* synthetic */ MainListener this$0;
            private final /* synthetic */ YamlConfiguration val$yConfig;
            private final /* synthetic */ Player val$p;
            
            MainListener$3() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void run() {
                if (yConfig.getString("UpdaterBeimJoinen").contains("true") && p.hasPermission("system.updater") && Main.isVersionOld((Plugin)this.this$0.plugin)) {
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.sendMessage("┌────────────[§2§lGG Features§r]────────────┐");
                    p.sendMessage("§6§lUPDATE: §4§lv." + Main.getNewVersion() + "\n" + "§6§lDownload: §b§lhttps://www.spigotmc.org/resources/gg-features.46956/");
                    p.sendMessage("└────────────[§2§lGG Features§r]────────────┘");
                }
            }
        }, 20L);
    }
    
    @EventHandler
    public void onJoin2(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        try {
            if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix").contains("Gold")) {
                p.setPlayerListName("§6" + p.getName());
                p.setDisplayName("§6" + p.getName() + "§r");
            }
            else if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix").contains("Rot")) {
                p.setPlayerListName("§c" + p.getName());
                p.setDisplayName("§c" + p.getName() + "§r");
            }
            else if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix").contains("Blau")) {
                p.setPlayerListName("§b" + p.getName());
                p.setDisplayName("§b" + p.getName() + "§r");
            }
            else if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix").contains("Grün")) {
                p.setPlayerListName("§a" + p.getName());
                p.setDisplayName("§a" + p.getName() + "§r");
            }
            else if (MainListener.yPrefix.getString(String.valueOf(p.getName()) + ".Prefix").contains("Magisch")) {
                p.setPlayerListName("§c§k" + p.getName());
                p.setDisplayName("§c§k" + p.getName() + "§r");
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onJoin3(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        if (StartKickCMD.voting.contains("true") && StartKickCMD.Name.get("N").contains(p.getName())) {
            e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§4§lStart§e§lKick\n\n             §cEs wird gerade entschieden ob\ndu im Spiel bleiben darfst oder nicht!\n§cAus diesem Grund kannst du nicht relogen!");
        }
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        try {
            final Player p = (Player)e.getWhoClicked();
            if (e.getInventory().getName().equalsIgnoreCase("§eGS Menu")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.ACACIA_FENCE_GATE) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Teleport GS 1")) {
                        p.closeInventory();
                        p.chat("/plot h 1");
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Teleport GS 2")) {
                        p.closeInventory();
                        p.chat("/plot h 2");
                    }
                }
                else if (e.getCurrentItem().getType() == Material.DIAMOND_SWORD) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§ePvP aktivieren")) {
                        p.closeInventory();
                        p.chat("/plot flag set pvp allow");
                    }
                }
                else if (e.getCurrentItem().getType() == Material.STONE_SWORD && e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§cPvP deaktivieren")) {
                    p.closeInventory();
                    p.chat("/plot flag set pvp deny");
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onCMD(final PlayerCommandPreprocessEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        final String[] cmd = e.getMessage().substring(1).split(" ");
        if (cmd.length == 1 && cmd[0].equalsIgnoreCase("p")) {
            e.setCancelled(true);
            if (this.plugin.getConfig().getString("/p").equalsIgnoreCase("true")) {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin plotsquared = manager.getPlugin("PlotSquared");
                if (plotsquared == null || !plotsquared.isEnabled()) {
                    if (p.hasPermission("system.p.error.see")) {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§4Der Command /p konnte nicht aktiviert werden!");
                    }
                    else {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                    if (plotsquared == null || !plotsquared.isEnabled()) {
                        e.setCancelled(true);
                        if (p.hasPermission("system.p.error.see")) {
                            p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PlotSquared!");
                        }
                    }
                }
                else {
                    final Inventory GS = Bukkit.createInventory((InventoryHolder)null, 9, "§eGS Menu");
                    final ItemStack istack1 = new ItemStack(Material.ACACIA_FENCE_GATE);
                    final ItemMeta istackMeta1 = istack1.getItemMeta();
                    istackMeta1.setDisplayName("§2Teleport GS 1");
                    istack1.setItemMeta(istackMeta1);
                    final ItemStack istack2 = new ItemStack(Material.ACACIA_FENCE_GATE, 2);
                    final ItemMeta istackMeta2 = istack2.getItemMeta();
                    istackMeta2.setDisplayName("§2Teleport GS 2");
                    istack2.setItemMeta(istackMeta2);
                    final ItemStack istack3 = new ItemStack(Material.DIAMOND_SWORD);
                    final ItemMeta istackMeta3 = istack3.getItemMeta();
                    istackMeta3.setDisplayName("§ePvP aktivieren");
                    istack3.setItemMeta(istackMeta3);
                    final ItemStack istack4 = new ItemStack(Material.STONE_SWORD);
                    final ItemMeta istackMeta4 = istack4.getItemMeta();
                    istackMeta4.setDisplayName("§cPvP deaktivieren");
                    istack4.setItemMeta(istackMeta4);
                    GS.setItem(2, istack1);
                    GS.setItem(3, istack2);
                    GS.setItem(6, istack3);
                    GS.setItem(7, istack4);
                    e.getPlayer().openInventory(GS);
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
        }
        if (cmd[0].equalsIgnoreCase("playerholo") || cmd[0].equalsIgnoreCase("griefergames:playerholo")) {
            final PluginManager manager = Bukkit.getServer().getPluginManager();
            final Plugin plotsquared = manager.getPlugin("PlotSquared");
            final Plugin HolographicDisplays = manager.getPlugin("HolographicDisplays");
            if (!this.plugin.getConfig().getString("/Playerholo").equalsIgnoreCase("true")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else if (this.plugin.getConfig().getString("/Playerholo").equalsIgnoreCase("true")) {
                if (plotsquared == null || !plotsquared.isEnabled() || HolographicDisplays == null || !HolographicDisplays.isEnabled()) {
                    if (p.hasPermission("system.playerholo.error.see")) {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§4Der Command /PlayerHolo konnte nicht aktiviert werden!");
                    }
                    else {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
                if (plotsquared == null || !plotsquared.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.playerholo.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PlotSquared!");
                    }
                }
                if (HolographicDisplays == null || !HolographicDisplays.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.playerholo.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin HolographicDisplays!");
                    }
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("rand") || cmd[0].equalsIgnoreCase("griefergames:rand")) {
            final PluginManager manager = Bukkit.getServer().getPluginManager();
            final Plugin plotsquared = manager.getPlugin("PlotSquared");
            if (!this.plugin.getConfig().getString("/Rand").equalsIgnoreCase("true")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else if (this.plugin.getConfig().getString("/Rand").equalsIgnoreCase("true")) {
                if (plotsquared == null || !plotsquared.isEnabled()) {
                    if (p.hasPermission("system.rand.error.see")) {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§4Der Command /Rand konnte nicht aktiviert werden!");
                    }
                    else {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
                if (plotsquared == null || !plotsquared.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.rand.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PlotSquared!");
                    }
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("blocklog") || cmd[0].equalsIgnoreCase("griefergames:blocklog")) {
            if (!this.plugin.getConfig().getString("BlockLog").equalsIgnoreCase("true")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else if (MySQL.con == null) {
                if (p.hasPermission("system.blocklog.error.see")) {
                    p.sendMessage(String.valueOf(Prefix) + "§4Der Command /BlockLog konnte nicht aktiviert werden!");
                    p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst eine funktionierende MySQL Datenbank, ansonsten kannst du BlockLog auch in der Config ausstellen!");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("portal") || cmd[0].equalsIgnoreCase("griefergames:portal")) {
            if (!this.plugin.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Server") && !this.plugin.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Map")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
        }
        else if (cmd[0].equalsIgnoreCase("bank") || cmd[0].equalsIgnoreCase("griefergames:bank")) {
            if (!this.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File") && !this.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("MySQL")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else if (MySQL.con != null) {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin Essentials = manager.getPlugin("Essentials");
                if (Essentials == null || !Essentials.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.bank.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
            }
            else if (!this.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File")) {
                e.setCancelled(true);
                if (p.hasPermission("system.bank.error.see")) {
                    p.sendMessage(String.valueOf(Prefix) + "§4Der Command /Bank konnte nicht aktiviert werden!");
                    p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst eine funktionierende MySQL Datenbank, ansonsten kannst du die Bank auch in der Config auf File stellen!");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                }
            }
            else {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin Essentials = manager.getPlugin("Essentials");
                if (Essentials == null || !Essentials.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.bank.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("premium") || cmd[0].equalsIgnoreCase("griefergames:premium")) {
            if (!this.plugin.getConfig().getString("/Premium").equalsIgnoreCase("true")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin Pex = manager.getPlugin("PermissionsEx");
                if (Pex == null || !Pex.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.premium.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PermissionsEx!");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("2") || cmd[0].equalsIgnoreCase("p") || cmd[0].equalsIgnoreCase("p2") || cmd[0].toLowerCase().startsWith("plot") || cmd[0].toLowerCase().startsWith("plotme") || cmd[0].toLowerCase().startsWith("plots") || cmd[0].toLowerCase().startsWith("plotsquared") || cmd[0].toLowerCase().startsWith("plotsquared:ps") || cmd[0].toLowerCase().startsWith("plotsquared:2") || cmd[0].toLowerCase().startsWith("plotsquared:p") || cmd[0].toLowerCase().startsWith("plotsquared:p2") || cmd[0].toLowerCase().startsWith("plotsquared:plot") || cmd[0].toLowerCase().startsWith("plotsquared:plotme") || cmd[0].toLowerCase().startsWith("plotsquared:plots") || cmd[0].toLowerCase().startsWith("plotsquared:plotsquared") || cmd[0].toLowerCase().startsWith("plotsquared:ps")) {
            if (!p.hasPermission("system.merge.admin")) {
                try {
                    if (cmd[1].toLowerCase().contains("merge") || cmd[1].toLowerCase().contains("plots")) {
                        if (!MergeCMD.merge.contains(p)) {
                            e.setCancelled(true);
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst mit §e/merge §cdeine Plots zusammen mergen.");
                        }
                        else {
                            MergeCMD.merge.remove(p);
                        }
                    }
                    else if (cmd[1].equalsIgnoreCase("m") || cmd[1].equalsIgnoreCase("me") || cmd[1].equalsIgnoreCase("mer") || cmd[1].equalsIgnoreCase("merg")) {
                        if (!MergeCMD.merge.contains(p)) {
                            e.setCancelled(true);
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst mit §e/merge §cdeine Plots zusammen mergen.");
                        }
                        else {
                            MergeCMD.merge.remove(p);
                        }
                    }
                    if (cmd[1].contains("0") || cmd[1].contains("1") || cmd[1].contains("2") || cmd[1].contains("3") || cmd[1].contains("4") || cmd[1].contains("5") || cmd[1].contains("6") || cmd[1].contains("7") || cmd[1].contains("8") || cmd[1].contains("9") || cmd[1].contains("-f")) {
                        if (!MergeCMD.merge.contains(p)) {
                            e.setCancelled(true);
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst mit §e/merge §cdeine Plots zusammen mergen.");
                        }
                        else {
                            MergeCMD.merge.remove(p);
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }
        else if (cmd[0].equalsIgnoreCase("merge") || cmd[0].equalsIgnoreCase("griefergames:merge")) {
            final PluginManager manager = Bukkit.getServer().getPluginManager();
            final Plugin ess = manager.getPlugin("Essentials");
            final Plugin PlotSquared = manager.getPlugin("PlotSquared");
            if (ess == null || !ess.isEnabled() || PlotSquared == null || !PlotSquared.isEnabled()) {
                e.setCancelled(true);
                if (p.hasPermission("system.merge.error.see")) {
                    p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials und PlotSquared!");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("menu") || cmd[0].equalsIgnoreCase("griefergames:menu")) {
            if (!this.plugin.getConfig().getString("/Menu").equalsIgnoreCase("true")) {
                e.setCancelled(true);
                p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
            }
            else {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin Essentials = manager.getPlugin("Essentials");
                final Plugin PlotSquared = manager.getPlugin("PlotSquared");
                if (PlotSquared == null || !PlotSquared.isEnabled() || Essentials == null || !Essentials.isEnabled()) {
                    if (p.hasPermission("system.menu.error.see")) {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§4Der Command /Menu konnte nicht aktiviert werden!");
                    }
                    else {
                        e.setCancelled(true);
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                    }
                }
                if (PlotSquared == null || !PlotSquared.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.menu.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PlotSquared!");
                    }
                }
                if (Essentials == null || !Essentials.isEnabled()) {
                    e.setCancelled(true);
                    if (p.hasPermission("system.menu.error.see")) {
                        p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials!");
                    }
                }
            }
        }
        else if (cmd[0].equalsIgnoreCase("leuchten") || cmd[0].equalsIgnoreCase("griefergames:leuchten")) {
            final PluginManager manager = Bukkit.getServer().getPluginManager();
            final Plugin PlotSquared2 = manager.getPlugin("PlotSquared");
            if (PlotSquared2 == null || !PlotSquared2.isEnabled()) {
                if (p.hasPermission("system.leuchten.error.see")) {
                    e.setCancelled(true);
                    p.sendMessage(String.valueOf(Prefix) + "§4Der Command /Leuchten konnte nicht aktiviert werden!");
                }
                else {
                    e.setCancelled(true);
                    p.sendMessage(String.valueOf(Prefix) + "§cDer Command ist deaktiviert.");
                }
            }
            if (PlotSquared2 == null || !PlotSquared2.isEnabled()) {
                e.setCancelled(true);
                if (p.hasPermission("system.leuchten.error.see")) {
                    p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin PlotSquared!");
                }
            }
        }
        if (cmd[0].equalsIgnoreCase("gg") || cmd[0].equalsIgnoreCase("griefergames") || cmd[0].equalsIgnoreCase("system") || cmd[0].equalsIgnoreCase("niklas409")) {
            e.setCancelled(true);
            this.sendUpdate(p);
        }
    }
    
    @EventHandler
    public void onChatUpdate(final AsyncPlayerChatEvent e) {
        if (e.getMessage().toLowerCase().contains("#gg") || e.getMessage().toLowerCase().contains("#griefergames") || e.getMessage().toLowerCase().contains("#system") || e.getMessage().toLowerCase().contains("#niklas409")) {
            e.setCancelled(true);
            this.sendUpdate(e.getPlayer());
        }
    }
    
    public void sendUpdate(final Player p) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        p.sendMessage("§8------------§5§lGrieferGames Features§8------------");
        p.sendMessage(String.valueOf(Prefix) + "§7Plugin: §cGrieferGames Features");
        p.sendMessage(String.valueOf(Prefix) + "§7Version: §c" + Main.Version);
        p.sendMessage(String.valueOf(Prefix) + "§7Coded by: §cNiklas409");
        p.sendMessage(" ");
        p.sendMessage(String.valueOf(Prefix) + "§7Neuerungen von " + Main.Version);
        p.sendMessage(String.valueOf(Prefix) + "§2+ §4Anti§eDup§cSystem");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7/Dup - Listet alle verdächtigen Truhen auf.");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7/Leuchten - Verschönert dein Grundstück.");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §eBreakBooster");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7Neue Ränder: §aGlas§7, §aKürbis§7, §aEnchantment Table§7, §aLava§7 und §aWasser");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7Neue Rezepte: §aAntikes Schwert§7, §aCola§7 und §aYeezys");
        p.sendMessage(" ");
        p.sendMessage(String.valueOf(Prefix) + "§7Permissions:");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Leuchten - Zum verwenden des Commands (/Leuchten)");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.AntiDupp - Zum verwenden des Commands (/Dup)");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.AntiDupp.Bypass - Du wirst nicht mehr vom §4Anti§eDup§cSystem §7überprüft.");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Rand.Glas");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Rand.Kürbis");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Rand.EnchantmentTable");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Rand.Lava");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7System.Rand.Wasser");
        p.sendMessage(" ");
        p.sendMessage(String.valueOf(Prefix) + "§7Bugfixes:");
        p.sendMessage(String.valueOf(Prefix) + "§2+ §7Die Console konnte unendlich Booster stacken.");
        p.sendMessage(" ");
        p.sendMessage("§8------------§5§lGrieferGames Features§8------------");
    }
    
    @EventHandler
    public void onClick1(final InventoryClickEvent e) {
        try {
            final Player p = (Player)e.getWhoClicked();
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final PlotAPI papi = new PlotAPI();
            if (e.getInventory().getName().equalsIgnoreCase("§6Rand-Auswahl")) {
                e.setCancelled(true);
                if (!MainListener.rand.contains(p.getName())) {
                    MainListener.rand.add(p.getName());
                    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Player val$p;
                        
                        MainListener$4() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            MainListener.rand.remove(p.getName());
                        }
                    }, 100L);
                    if (papi.isInPlot(p)) {
                        if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fBruchsteinstufe")) {
                                if (p.hasPermission("system.rand.bruchsteinstufe")) {
                                    p.closeInventory();
                                    this.setRand(p, "44:3", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fNetherziegelstufe")) {
                                if (p.hasPermission("system.rand.netherziegelstufe")) {
                                    p.closeInventory();
                                    this.setRand(p, "44:6", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fBarriere")) {
                                if (p.hasPermission("system.rand.barriere")) {
                                    p.closeInventory();
                                    this.setRand(p, "AIR", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fGoldblock")) {
                                if (p.hasPermission("system.rand.goldblock")) {
                                    p.closeInventory();
                                    this.setRand(p, "gold_block", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fZiegelstufe")) {
                                if (p.hasPermission("system.rand.ziegelstufe")) {
                                    p.closeInventory();
                                    this.setRand(p, "44:4", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fEichenholzstufe")) {
                                if (p.hasPermission("system.rand.eichenholzstufe")) {
                                    p.closeInventory();
                                    this.setRand(p, "126", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fDiamantblock")) {
                                if (p.hasPermission("system.rand.diamantblock")) {
                                    p.closeInventory();
                                    this.setRand(p, "diamond_block", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fObsidian")) {
                                if (p.hasPermission("system.rand.obsidian")) {
                                    p.closeInventory();
                                    this.setRand(p, "obsidian", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fDiamanterz")) {
                                if (p.hasPermission("system.rand.diamanterz")) {
                                    p.closeInventory();
                                    this.setRand(p, "diamond_ore", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fBücherregal")) {
                                if (p.hasPermission("system.rand.bücherregal")) {
                                    p.closeInventory();
                                    this.setRand(p, "bookshelf", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fSmaragdblock")) {
                                if (p.hasPermission("system.rand.smaragdblock")) {
                                    p.closeInventory();
                                    this.setRand(p, "emerald_block", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fRedstone-Lampe")) {
                                if (p.hasPermission("system.rand.redstonelampe")) {
                                    p.closeInventory();
                                    this.setRand(p, "redstone_lamp", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fKohleblock")) {
                                if (p.hasPermission("system.rand.kohleblock")) {
                                    p.closeInventory();
                                    this.setRand(p, "coal_block", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fFackel")) {
                                if (p.hasPermission("system.rand.fackel")) {
                                    p.closeInventory();
                                    this.setRand(p, "torch:5", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fSchiene")) {
                                if (p.hasPermission("system.rand.schiene")) {
                                    p.closeInventory();
                                    this.setRand(p, "rail:1", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fRedstone-Block")) {
                                if (p.hasPermission("system.rand.RedstoneBlock")) {
                                    p.closeInventory();
                                    this.setRand(p, "redstone_block", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fZaun")) {
                                if (p.hasPermission("system.rand.Zaun")) {
                                    p.closeInventory();
                                    this.setRand(p, "fence", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fLeuchtfeuer")) {
                                if (p.hasPermission("system.rand.Leuchtfeuer")) {
                                    p.closeInventory();
                                    this.setRand(p, "beacon", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fNetherstein")) {
                                if (p.hasPermission("system.rand.netherstein")) {
                                    p.closeInventory();
                                    this.setRand(p, "87", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fSeelaterne")) {
                                if (p.hasPermission("system.rand.Seelaterne")) {
                                    p.closeInventory();
                                    this.setRand(p, "169", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fGrundgestein")) {
                                if (p.hasPermission("system.rand.Grundgestein")) {
                                    p.closeInventory();
                                    this.setRand(p, "7", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fEndstein")) {
                                if (p.hasPermission("system.rand.Endstein")) {
                                    p.closeInventory();
                                    this.setRand(p, "121", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fEndportalrahmen")) {
                                if (p.hasPermission("system.rand.Endportalrahmen")) {
                                    p.closeInventory();
                                    this.setRand(p, "120", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fGlas")) {
                                if (p.hasPermission("system.rand.Glas")) {
                                    p.closeInventory();
                                    this.setRand(p, "20", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fKürbis")) {
                                if (p.hasPermission("system.rand.Kürbis")) {
                                    p.closeInventory();
                                    this.setRand(p, "86", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fEnchantment Table")) {
                                if (p.hasPermission("system.rand.EnchantmentTable")) {
                                    p.closeInventory();
                                    this.setRand(p, "116", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fLava")) {
                                if (p.hasPermission("system.rand.Lava")) {
                                    p.closeInventory();
                                    final File Settings = new File("plugins/PlotSquared/config/worlds.yml");
                                    final YamlConfiguration ySettings = YamlConfiguration.loadConfiguration(Settings);
                                    final Integer GrößeDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".size");
                                    final Integer HöheDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".height");
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(1.0, (double)(-HöheDerPlots), 1.0).getBlock().setType(Material.LAVA);
                                    Integer i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)i, (double)(-HöheDerPlots), 1.0).getBlock().setType(Material.LAVA);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(-GrößeDerPlots), (double)(-HöheDerPlots), (double)i).getBlock().setType(Material.LAVA);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)i, (double)(-HöheDerPlots), (double)(-GrößeDerPlots)).getBlock().setType(Material.LAVA);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(1.0, (double)(-HöheDerPlots), (double)i).getBlock().setType(Material.LAVA);
                                    }
                                    this.setRand(p, "166", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                            else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§fWasser")) {
                                if (p.hasPermission("system.rand.Wasser")) {
                                    p.closeInventory();
                                    final File Settings = new File("plugins/PlotSquared/config/worlds.yml");
                                    final YamlConfiguration ySettings = YamlConfiguration.loadConfiguration(Settings);
                                    final Integer GrößeDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".size");
                                    final Integer HöheDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".height");
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(1.0, (double)(-HöheDerPlots), 1.0).getBlock().setType(Material.WATER);
                                    Integer i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)i, (double)(-HöheDerPlots), 1.0).getBlock().setType(Material.WATER);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(-GrößeDerPlots), (double)(-HöheDerPlots), (double)i).getBlock().setType(Material.WATER);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)i, (double)(-HöheDerPlots), (double)(-GrößeDerPlots)).getBlock().setType(Material.WATER);
                                    }
                                    i = 1;
                                    while (i > -GrößeDerPlots) {
                                        --i;
                                        papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(1.0, (double)(-HöheDerPlots), (double)i).getBlock().setType(Material.WATER);
                                    }
                                    this.setRand(p, "166", papi.getPlot(p.getLocation()));
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(NoPerms);
                                }
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Besitzer dieses Plots!");
                        }
                    }
                    else {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§cDu bist auf keinem Grundstück!");
                    }
                }
                else {
                    p.closeInventory();
                    p.sendMessage("§cDu kannst diesen Befehl nur alle 5 Sekunden benutzen!");
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lPerks")) {
                e.setCancelled(true);
                final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
                final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
                final Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                final Integer money = ess.getUser(p).getMoney().intValue();
                final Integer NoFall = this.plugin.getConfig().getInt("NoFallPerk");
                final Integer NoHunger = this.plugin.getConfig().getInt("NoHungerPerk");
                final Integer MutepPerk = this.plugin.getConfig().getInt("MutepPerk");
                final Integer ClearChatPerk = this.plugin.getConfig().getInt("ClearChatPerk");
                final Integer SlowChatPerk = this.plugin.getConfig().getInt("SlowChatPerk");
                final Integer StartKickPerk = this.plugin.getConfig().getInt("StartKickPerk");
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lNoFall Perk")) {
                    if (yPerk.getString(p.getUniqueId() + ".NoFall") == null) {
                        if (money >= NoFall) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - NoFall);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoFall-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".NoFall", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".NoFall")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoFall-Perk deaktiviert!");
                        yPerk.set(p.getUniqueId() + ".NoFall", (Object)false);
                        yPerk.save(Perk);
                    }
                    else if (!yPerk.getBoolean(p.getUniqueId() + ".NoFall")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoFall-Perk aktiviert!");
                        yPerk.set(p.getUniqueId() + ".NoFall", (Object)true);
                        yPerk.save(Perk);
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lKein Hunger")) {
                    if (yPerk.getString(p.getUniqueId() + ".NoHunger") == null) {
                        if (money >= NoHunger) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - NoHunger);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoHunger-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".NoHunger", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".NoHunger")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoHunger-Perk deaktiviert!");
                        yPerk.set(p.getUniqueId() + ".NoHunger", (Object)false);
                        yPerk.save(Perk);
                    }
                    else if (!yPerk.getBoolean(p.getUniqueId() + ".NoHunger")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das NoHunger-Perk aktiviert!");
                        yPerk.set(p.getUniqueId() + ".NoHunger", (Object)true);
                        yPerk.save(Perk);
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l/mutep")) {
                    if (yPerk.getString(p.getUniqueId() + ".Mutep") == null) {
                        if (money >= MutepPerk) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - MutepPerk);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das Mutep-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".Mutep", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".Mutep")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Perk nicht deaktivieren!");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l/clearchat")) {
                    if (yPerk.getString(p.getUniqueId() + ".ClearChat") == null) {
                        if (money >= ClearChatPerk) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - ClearChatPerk);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das ClearChat-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".ClearChat", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".ClearChat")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Perk nicht deaktivieren!");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l/slowchat")) {
                    if (yPerk.getString(p.getUniqueId() + ".SlowChat") == null) {
                        if (money >= SlowChatPerk) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - SlowChatPerk);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das SlowChat-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".SlowChat", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".SlowChat")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Perk nicht deaktivieren!");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l/startkick")) {
                    if (yPerk.getString(p.getUniqueId() + ".StartKick") == null) {
                        if (money >= StartKickPerk) {
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - StartKickPerk);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das StartKick-Perk gekauft!");
                            ess.getUser(p).setMoney(neuesgeld);
                            yPerk.set(p.getUniqueId() + ".StartKick", (Object)true);
                            yPerk.save(Perk);
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                        }
                    }
                    else if (yPerk.getBoolean(p.getUniqueId() + ".StartKick")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Perk nicht deaktivieren!");
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§3Wähle eine Itemkanone aus!")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getType() == Material.BEACON) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Beacon" }) });
                }
                else if (e.getCurrentItem().getType() == Material.DRAGON_EGG) {
                    p.closeInventory();
                    p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                    p.sendMessage(String.valueOf(Prefix) + "§7Die DrachenEi-Paintball Gun wird derzeit überarbeitet. §cGrund: §4§lDuplizier-Möglichkeit");
                }
                else if (e.getCurrentItem().getType() == Material.MOB_SPAWNER) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Spawner" }) });
                }
                else if (e.getCurrentItem().getType() == Material.EMERALD_BLOCK) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Emeraldblock" }) });
                }
                else if (e.getCurrentItem().getType() == Material.SPONGE) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Sponge" }) });
                }
                else if (e.getCurrentItem().getType() == Material.OBSIDIAN) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Obsidian" }) });
                }
                else if (e.getCurrentItem().getType() == Material.BEDROCK) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "Grundstein" }) });
                }
                else if (e.getCurrentItem().getType() == Material.DIAMOND_ORE) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "DiaOre" }) });
                }
                else if (e.getCurrentItem().getType() == Material.TNT) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§bDu hast eine Paintball Gun erhalten!");
                    p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_HOE, "§bPaintball Gun", 1, new String[] { "TNT" }) });
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lPlotMenu")) {
                e.setCancelled(true);
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lClaim dir das nächste freie Plot")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§a§lDu bekommst dein neues Plot gleich...");
                    p.chat("/p auto");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lClaim dir das Plot auf dem du gerade stehst")) {
                    p.closeInventory();
                    if (papi.getPlot(p.getLocation()) != null) {
                        p.sendMessage(String.valueOf(Prefix) + "§c§oFalls es nicht Funktioniert gehört es schon jemanden.");
                        p.chat("/p claim");
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu stehst auf keinem Plot!");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lLösche dein Plot")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§c§lFalls es nicht Funktioniert, sei dir sicher das du auf deinem Plot bist.");
                    p.sendMessage(String.valueOf(Prefix) + "§c§lFalls du dein Plot nicht löschen willst ignoriere diese Nachricht");
                    final TextComponent tc = new TextComponent();
                    tc.setText(String.valueOf(Prefix) + "§6Willst du dein Plot löschen!?§f. §a*Klick*");
                    tc.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c/plot delete").create()));
                    tc.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/plot delete"));
                    p.spigot().sendMessage((BaseComponent)tc);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§a§lFüge einen Helfer für kurze Zeit hinzu")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§a§lSchreibe den Namen deines Helfers in den Chat.");
                    p.sendMessage(String.valueOf(Prefix) + "§a§lWenn du das nicht willst breche es ab mit§r §a§o/menu cancel§a§l!");
                    MainListener.menucanceltap.remove(p);
                    MainListener.menucanceltap.put(p, "Helfer");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lFüge deinen Freund zu deinen Plot")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§a§lSchreibe den Namen deines Freundes in den Chat.");
                    p.sendMessage(String.valueOf(Prefix) + "§a§lWenn du das nicht willst breche es ab mit§r §a§o/menu cancel§a§l!");
                    MainListener.menucanceltap.remove(p);
                    MainListener.menucanceltap.put(p, "Freund");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lEntferne deinen Freund")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§a§lSchreibe den Namen in den Chat um ihn zu entfernen.");
                    p.sendMessage(String.valueOf(Prefix) + "§a§lWenn du das nicht willst breche es ab mit§r §a§o/menu cancel§a§l!");
                    MainListener.menucanceltap.remove(p);
                    MainListener.menucanceltap.put(p, "Remove");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lTeleportiere zu deinem Plot!")) {
                    p.closeInventory();
                    Integer j = 0;
                    while (j < papi.getPlayerPlots(p).size()) {
                        ++j;
                        final TextComponent tc2 = new TextComponent();
                        tc2.setText(String.valueOf(Prefix) + "§6WWillst du zu deinem Plot " + j + "?§f. §a*Klick*");
                        tc2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§c/p h " + j).create()));
                        tc2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/p h " + j));
                        p.spigot().sendMessage((BaseComponent)tc2);
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lBesuche deinen Freund!")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§a§lSchreibe in den Chat den Namen den du Besuchen möchtest.");
                    p.sendMessage(String.valueOf(Prefix) + "§a§lWenn du das nicht willst breche es ab mit§r §a§o/menu cancel§a§l!");
                    MainListener.menucanceltap.remove(p);
                    MainListener.menucanceltap.put(p, "Besuchen");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lMein Money")) {
                    final Essentials ess2 = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                    final Integer PlotPreise = this.plugin.getConfig().getInt("PlotPreise");
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§7§lDein Kontostand: §e§l" + ess2.getUser(p).getMoney());
                    p.sendMessage(String.valueOf(Prefix) + "§7§lDeine Plots: §e§l" + papi.getPlayerPlots(p).size());
                    p.sendMessage(String.valueOf(Prefix) + "§7§lAktueller Plot Preis: §e§l" + PlotPreise + "$");
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§e§lKaufe dir ein weiteres Plot")) {
                    final Essentials ess2 = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                    final Integer PlotPreise = this.plugin.getConfig().getInt("PlotPreise");
                    p.closeInventory();
                    final Integer money2 = ess2.getUser(p).getMoney().intValue();
                    if (papi.getPlot(p.getLocation()) != null) {
                        if (!papi.getPlot(p.getLocation()).hasOwner()) {
                            if (money2 >= PlotPreise) {
                                p.sendMessage(String.valueOf(Prefix) + "§a§lDu hast das Grundstück erfolgreich gekauft!");
                                final BigDecimal neuesgeld2 = BigDecimal.valueOf(money2 - PlotPreise);
                                ess2.getUser(p).setMoney(neuesgeld2);
                                papi.getPlot(p.getLocation()).setOwner(p.getUniqueId());
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§c§lDu hast nicht genug Geld!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§c§lDas Grundstück gehört schon jemanden!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§c§lDu stehst auf keinem Grundstück!");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§c§lSchliesse das Menu")) {
                    p.closeInventory();
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7§lPlatzhalter")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§cLULULU Abonniert alle Niklas409!");
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onSchild(final SignChangeEvent e) {
        if (e.getPlayer().hasPermission("System.Schild.Bunt")) {
            e.setLine(0, e.getLine(0).replace("&", "§"));
            e.setLine(1, e.getLine(1).replace("&", "§"));
            e.setLine(2, e.getLine(2).replace("&", "§"));
            e.setLine(3, e.getLine(3).replace("&", "§"));
        }
    }
    
    @EventHandler
    public void onChatCode(final AsyncPlayerChatEvent e) {
        if (e.getPlayer().hasPermission("system.chat.bunt")) {
            e.setMessage(ChatColor.translateAlternateColorCodes('&', e.getMessage()));
        }
    }
    
    @EventHandler
    public void onAmboss(final InventoryClickEvent e) {
        try {
            if (e.getWhoClicked().hasPermission("System.amboss.bunt")) {
                final Inventory inv = e.getInventory();
                if (inv instanceof AnvilInventory) {
                    final InventoryView view = e.getView();
                    final int rawSlot = e.getRawSlot();
                    if (rawSlot == view.convertSlot(rawSlot) && rawSlot == 2) {
                        final ItemStack item = e.getCurrentItem();
                        if (item != null) {
                            final ItemMeta meta = item.getItemMeta();
                            meta.setDisplayName(meta.getDisplayName().replace("&", "§"));
                            item.setItemMeta(meta);
                        }
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onLuckyBlock(final PlayerInteractEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK) && e.getClickedBlock().getType() == Material.SPONGE && MainListener.LuckyBlockSaver.contains(e.getClickedBlock().getLocation())) {
            if (p.hasPermission("system.luckyblock.use")) {
                MainListener.LuckyBlockSaver.remove(e.getClickedBlock().getLocation());
                p.playSound(e.getClickedBlock().getLocation(), Sound.CHICKEN_EGG_POP, 1.0f, 1.0f);
                p.playEffect(e.getClickedBlock().getLocation(), Effect.ENDER_SIGNAL, 10);
                e.getClickedBlock().setType(Material.AIR);
                LuckyBlockCMD.onLuckyBlockEvents(p, e.getClickedBlock());
                e.setCancelled(true);
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cDu benötigst die Permission: System.LuckyBlock.Use");
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onLuckyBlockPlace(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        try {
            if (p.getItemInHand().getType() == Material.SPONGE && p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lLuckyBlock")) {
                if (p.getItemInHand().getItemMeta().getLore() == null) {
                    e.setCancelled(true);
                    Bukkit.dispatchCommand((CommandSender)Bukkit.getConsoleSender(), "startkick " + p.getName() + " Bitte fälsche keine LuckyBlöcke!");
                }
                else if (p.getItemInHand().getItemMeta().getLore().contains("§4§lby Niklas409")) {
                    if (p.hasPermission("system.luckyblock.place")) {
                        MainListener.LuckyBlockSaver.add(e.getBlock().getLocation());
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu benötigst die Permission: System.LuckyBlock.Place");
                        e.setCancelled(true);
                    }
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onFakeDias(final PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        if (LuckyBlockCMD.FakeDias.contains(p)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onByeInv(final PlayerPickupItemEvent e) {
        final Player p = e.getPlayer();
        if (LuckyBlockCMD.ByeInv.contains(p)) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onSchinken(final EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player) {
            final Player p = (Player)e.getDamager();
            if (p.getItemInHand().getType() != Material.AIR && p.getItemInHand().getItemMeta().getLore() != null && p.getItemInHand().getItemMeta().getLore().contains("§d§lScharfer Schinken")) {
                p.sendMessage("§cWarum machst du kein schaden?");
                e.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onBan(final PlayerLoginEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String UnbanAntragIp = this.plugin.getConfig().getString("UnbanAntragIp").replace("&", "§");
        if (BanCMD.Banned_cfg.get(UUID) != null) {
            if (BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Laenge").equalsIgnoreCase("Permanent")) {
                BanCMD.Banned_cfg.set(String.valueOf(UUID) + ".Name", (Object)p.getName());
                e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§8[§4Spielverbot§8] §7Du wurdest vom Spiel §4ausgeschlossen.\n\n§eGrund: §7" + BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Grund") + "\n" + "\n" + "§eEnde des Bans: §4§lPERMANENT" + "\n" + "\n" + "§7             Einen Entbannantrag kannst du unter" + "\n" + "§e" + UnbanAntragIp + "\n" + "§7stellen.");
                try {
                    BanCMD.Banned_cfg.save(BanCMD.Banned);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else if (BanCMD.Banned_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
                if (BanCMD.Banned_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                    BanCMD.Banned_cfg.set(UUID, (Object)null);
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (all.hasPermission("system.bansystem.unban.see")) {
                            all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entbannt!");
                        }
                    }
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e3) {
                        e3.printStackTrace();
                    }
                }
                else {
                    final Date date = new Date(BanCMD.Banned_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                    final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                    BanCMD.Banned_cfg.set(String.valueOf(UUID) + ".Name", (Object)p.getName());
                    e.disallow(PlayerLoginEvent.Result.KICK_BANNED, "§8[§4Spielverbot§8] §7Du wurdest vom Spiel §4ausgeschlossen.\n\n§eGrund: §7" + BanCMD.Banned_cfg.getString(String.valueOf(UUID) + ".Grund") + "\n" + "\n" + "§eEnde des Bans: §7" + mm_dd_yyyy + " um " + hour_min_sec + " Uhr" + "\n" + "\n" + "§7Einen Entbannantrag kannst du unter" + "\n" + "§e" + UnbanAntragIp + "\n" + "§7stellen.");
                    try {
                        BanCMD.Banned_cfg.save(BanCMD.Banned);
                    }
                    catch (IOException e4) {
                        e4.printStackTrace();
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onMute(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (BanCMD.Mutet_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
            if (BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                BanCMD.Mutet_cfg.set(UUID, (Object)null);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("system.bansystem.unmute.see")) {
                        all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entmutet!");
                    }
                }
                try {
                    BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                e.setCancelled(true);
                final Date date = new Date(BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§7Du bist bis zum §e" + mm_dd_yyyy + " um " + hour_min_sec + " §7gemutet.");
                p.sendMessage(String.valueOf(Prefix) + "§7Grund: §e" + BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Grund"));
            }
        }
    }
    
    @EventHandler
    public void onMuteCMD(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        final String UUID = String.valueOf(p.getUniqueId());
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String[] cmd = e.getMessage().substring(1).split(" ");
        if ((cmd[0].equalsIgnoreCase("msg") || cmd[0].equalsIgnoreCase("r")) && BanCMD.Mutet_cfg.get(String.valueOf(UUID) + ".Laenge") != null) {
            if (BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge") < System.currentTimeMillis()) {
                BanCMD.Mutet_cfg.set(UUID, (Object)null);
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (all.hasPermission("system.bansystem.unmute.see")) {
                        all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7wurde von der §b§lAutomatischen Cloud §7entmutet!");
                    }
                }
                try {
                    BanCMD.Mutet_cfg.save(BanCMD.Mutet);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
            else {
                e.setCancelled(true);
                final Date date = new Date(BanCMD.Mutet_cfg.getLong(String.valueOf(UUID) + ".Laenge"));
                final String mm_dd_yyyy = new SimpleDateFormat("dd-MM-yyyy").format(date);
                final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§7Du bist bis zum §e" + mm_dd_yyyy + " um " + hour_min_sec + " §7gemutet.");
                p.sendMessage(String.valueOf(Prefix) + "§7Grund: §e" + BanCMD.Mutet_cfg.getString(String.valueOf(UUID) + ".Grund"));
            }
        }
    }
    
    @EventHandler
    public void onQuit(final PlayerQuitEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if (VanishCMD.yVanish.contains(p.getName()) && VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("true")) {
            e.setQuitMessage((String)null);
            for (final Player all : Bukkit.getOnlinePlayers()) {
                if (!all.hasPermission("system.vanish.see")) {
                    all.hidePlayer(p);
                }
                else {
                    all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + e.getPlayer().getName() + " §7ist geleavt und ist noch im Vanish.");
                }
            }
        }
    }
    
    @EventHandler
    public void onVanish(final PlayerJoinEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if (VanishCMD.yVanish.contains(p.getName()) && VanishCMD.yVanish.getString(String.valueOf(p.getName()) + ".Vanish").contains("true")) {
            e.setJoinMessage((String)null);
            for (final Player all : Bukkit.getOnlinePlayers()) {
                if (!all.hasPermission("system.vanish.see")) {
                    all.hidePlayer(p);
                }
                else {
                    all.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + e.getPlayer().getName() + " §7ist gejoint und ist noch im Vanish.");
                }
            }
        }
        for (final Player all : Bukkit.getOnlinePlayers()) {
            if (VanishCMD.yVanish.contains(all.getName()) && VanishCMD.yVanish.getString(String.valueOf(all.getName()) + ".Vanish").contains("true")) {
                if (!p.hasPermission("system.vanish.see")) {
                    p.hidePlayer(all);
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7ist im Vanish!");
                }
            }
        }
    }
    
    @EventHandler
    public void onBB(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (BreakBlockCMD.BB.contains(p)) {
            final PlotAPI papi = new PlotAPI();
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                BreakBlockCMD.BB.remove(p);
                if (papi.getPlot(e.getClickedBlock().getLocation()) != null) {
                    if (papi.getPlot(e.getClickedBlock().getLocation()).isOwner(p.getUniqueId())) {
                        if (e.getClickedBlock().getLocation().getY() >= 3.0) {
                            e.getClickedBlock().setType(Material.AIR);
                            p.sendMessage(String.valueOf(Prefix) + "§aDer Block wurde erfolgreich entfernt.");
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst auf den Ebenen 0-3 keinen Block zerstören!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diese Aktion nicht ausführen, da du nicht der Besitzer des Grundstücks bist.");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diese Aktion nicht ausführen, da du nicht der Besitzer des Grundstücks bist.");
                }
            }
        }
    }
    
    @EventHandler
    public void onChatLog(final AsyncPlayerChatEvent e) {
        final Player p = e.getPlayer();
        if (this.plugin.getConfig().getString("ChatLog").equalsIgnoreCase("true")) {
            final Date date = new Date(System.currentTimeMillis());
            final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            final File ChatLog = new File("plugins/GrieferGames/Data/Logs/Chat/" + mm_dd_yyyy + ".yml");
            final YamlConfiguration yChatLog = YamlConfiguration.loadConfiguration(ChatLog);
            yChatLog.set("ChatLog." + hour_min_sec + " : " + p.getName(), (Object)e.getMessage().replace("§", ""));
            try {
                yChatLog.save(ChatLog);
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    @EventHandler
    public void onCMDLog(final PlayerCommandPreprocessEvent e) {
        final Player p = e.getPlayer();
        if (this.plugin.getConfig().getString("CommandLog").equalsIgnoreCase("true")) {
            final Date date = new Date(System.currentTimeMillis());
            final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
            final String hour_min_sec = new SimpleDateFormat("HH:mm:ss").format(date);
            final File CMDLog = new File("plugins/GrieferGames/Data/Logs/Command/" + mm_dd_yyyy + ".yml");
            final YamlConfiguration yCMDLog = YamlConfiguration.loadConfiguration(CMDLog);
            yCMDLog.set("CommandLog." + hour_min_sec + " : " + p.getName(), (Object)e.getMessage().replace("§", ""));
            try {
                yCMDLog.save(CMDLog);
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
        }
    }
    
    @EventHandler
    public void onMeldungen(final InventoryClickEvent e) {
        final Player p = (Player)e.getWhoClicked();
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (e.getInventory().getName().equalsIgnoreCase("§4§lMeldungen")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().getType() == Material.SKULL_ITEM && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().getDisplayName().startsWith("§a§l")) {
                final String Name = e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§a§l", "");
                MainListener.Name.put(p, Name);
                final String DatumS = MeldungCMD.yMeldungC.getString("Meldungen." + MainListener.Name.get(p) + ".Datum");
                final String UhrzeitS = MeldungCMD.yMeldungC.getString("Meldungen." + MainListener.Name.get(p) + ".Uhrzeit");
                final Inventory MPlayer = Bukkit.createInventory((InventoryHolder)null, 9, "§4§lM§8§l:" + e.getCurrentItem().getItemMeta().getDisplayName());
                MPlayer.setItem(1, ItemBuilder.createItem(Material.PAPER, "§4§lMeldung", 1, new String[] { "§7§lKlicke um die Meldung im", "§7§lChat anzeigen zu lassen." }));
                MPlayer.setItem(3, ItemBuilder.createItemOL(Material.WATCH, "§e§lAm §4§l" + DatumS + " §e§lum §4§l" + UhrzeitS + " §e§lUhr", 1));
                MPlayer.setItem(5, ItemBuilder.createItem(Material.ENDER_PEARL, "§5§lLocation", 1, new String[] { "§7§lKlicke um dich zu teleportieren." }));
                MPlayer.setItem(7, ItemBuilder.createItem(Material.BARRIER, "§4§lLöschen", 1, new String[] { "§7§lKlicke um den Beitrag zu löschen." }));
                p.openInventory(MPlayer);
            }
        }
        if (e.getInventory().getName().equalsIgnoreCase("§4§lM§8§l:§a§l" + MainListener.Name.get(p))) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lMeldung")) {
                    final String MeldungS = MeldungCMD.yMeldungC.getString("Meldungen." + MainListener.Name.get(p) + ".Meldung");
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§4§lMeldung von " + MainListener.Name.get(p) + " §8§l: §f§l" + MeldungS);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5§lLocation")) {
                    final String WeltS = MeldungCMD.yMeldungC.getString("Meldungen." + MainListener.Name.get(p) + ".Location" + ".Welt");
                    final Integer XI = MeldungCMD.yMeldungC.getInt("Meldungen." + MainListener.Name.get(p) + ".Location" + ".X");
                    final Integer YI = MeldungCMD.yMeldungC.getInt("Meldungen." + MainListener.Name.get(p) + ".Location" + ".Y");
                    final Integer ZI = MeldungCMD.yMeldungC.getInt("Meldungen." + MainListener.Name.get(p) + ".Location" + ".Z");
                    final Location loc = new Location(Bukkit.getWorld(WeltS), (double)XI, (double)YI, (double)ZI);
                    p.closeInventory();
                    p.teleport(loc);
                    p.sendMessage(String.valueOf(Prefix) + "§5§lDu wurdest zur §4§lLocation §5§lteleportiert!");
                    p.playSound(p.getLocation(), Sound.ENDERMAN_TELEPORT, 1.0f, 1.0f);
                    p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 10);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4§lLöschen")) {
                    p.closeInventory();
                    p.sendMessage(String.valueOf(Prefix) + "§7Du hast die §eMeldung §7von §a" + MainListener.Name.get(p) + " §4gelöscht§7!");
                    final List<String> MeldungenL = (List<String>)MeldungCMD.yMeldungC.getStringList("Meldungen von");
                    MeldungenL.remove(MainListener.Name.get(p));
                    MeldungCMD.yMeldungC.set("Meldungen von", (Object)MeldungenL);
                    MeldungCMD.yMeldungC.set("Meldungen." + MainListener.Name.get(p), (Object)null);
                    try {
                        MeldungCMD.yMeldungC.save(MeldungCMD.MeldungC);
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onHunger(final FoodLevelChangeEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
            final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
            if (yPerk.getString(p.getUniqueId() + ".NoHunger") != null && yPerk.getBoolean(p.getUniqueId() + ".NoHunger")) {
                e.setFoodLevel(20);
            }
        }
    }
    
    @EventHandler
    public void onFallDMG(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player p = (Player)e.getEntity();
            if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
                final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
                final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
                if (yPerk.getString(p.getUniqueId() + ".NoFall") != null && yPerk.getBoolean(p.getUniqueId() + ".NoFall")) {
                    e.setCancelled(true);
                }
            }
        }
    }
    
    @EventHandler
    public void onStatus(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        final File Status = new File("plugins/GrieferGames/Data/Status.yml");
        final YamlConfiguration yStatus = YamlConfiguration.loadConfiguration(Status);
        if (yStatus.getString(p.getUniqueId().toString()) != null) {
            final String StatusS = yStatus.getString(p.getUniqueId().toString());
            p.chat(StatusS);
            final Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
            final FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
            final FireworkMeta meta = firework.getFireworkMeta();
            meta.addEffect(effect);
            meta.setPower(1);
            firework.setFireworkMeta(meta);
        }
    }
    
    @EventHandler
    public void onGun(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if ((e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) && e.getItem() != null && e.getItem().getType() == Material.DIAMOND_HOE && e.getItem().hasItemMeta() && e.getItem().getItemMeta().hasDisplayName() && e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bPaintball Gun") && e.getItem().getItemMeta().hasLore() && !MainListener.gunwait.contains(p.getName())) {
            MainListener.gunwait.add(p.getName());
            final String MaterialS = e.getItem().getItemMeta().getLore().get(0);
            final ItemStack newi = new ItemStack(e.getItem());
            newi.setDurability((short)(e.getItem().getDurability() + 196));
            if (MaterialS.equalsIgnoreCase("Beacon")) {
                MainListener.guntype.put(p, Material.BEACON);
            }
            else if (MaterialS.equalsIgnoreCase("DragonEgg")) {
                MainListener.guntype.put(p, Material.DRAGON_EGG);
            }
            else if (MaterialS.equalsIgnoreCase("Spawner")) {
                MainListener.guntype.put(p, Material.MOB_SPAWNER);
            }
            else if (MaterialS.equalsIgnoreCase("Emeraldblock")) {
                MainListener.guntype.put(p, Material.EMERALD_BLOCK);
            }
            else if (MaterialS.equalsIgnoreCase("Sponge")) {
                MainListener.guntype.put(p, Material.SPONGE);
            }
            else if (MaterialS.equalsIgnoreCase("Obsidian")) {
                MainListener.guntype.put(p, Material.OBSIDIAN);
            }
            else if (MaterialS.equalsIgnoreCase("Grundstein")) {
                MainListener.guntype.put(p, Material.BEDROCK);
            }
            else if (MaterialS.equalsIgnoreCase("DiaOre")) {
                MainListener.guntype.put(p, Material.DIAMOND_ORE);
            }
            else if (MaterialS.equalsIgnoreCase("TNT")) {
                MainListener.guntype.put(p, Material.TNT);
            }
            p.launchProjectile((Class)Snowball.class);
            if (newi.getDurability() <= 1561) {
                p.getInventory().setItemInHand(newi);
            }
            else {
                p.getInventory().setItemInHand(new ItemStack(Material.AIR));
                p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
            }
            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                final /* synthetic */ MainListener this$0;
                private final /* synthetic */ Player val$p;
                
                MainListener$5() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public void run() {
                    MainListener.gunwait.remove(p.getName());
                }
            }, 20L);
        }
        if (e.getClickedBlock() != null && MainListener.guncheck.contains(e.getClickedBlock().getLocation().getBlock().getLocation())) {
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onLaunch(final ProjectileLaunchEvent e) {
        try {
            final Player p = (Player)e.getEntity().getShooter();
            if (e.getEntity() instanceof Snowball && MainListener.guntype.containsKey(p)) {
                if (MainListener.guntype.get(p).equals((Object)Material.BEACON)) {
                    e.getEntity().setCustomName("Beacon");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.DRAGON_EGG)) {
                    e.getEntity().setCustomName("DragonEgg");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.MOB_SPAWNER)) {
                    e.getEntity().setCustomName("Spawner");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.EMERALD_BLOCK)) {
                    e.getEntity().setCustomName("Emeraldblock");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.SPONGE)) {
                    e.getEntity().setCustomName("Sponge");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.OBSIDIAN)) {
                    e.getEntity().setCustomName("Obsidian");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.BEDROCK)) {
                    e.getEntity().setCustomName("Grundstein");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.DIAMOND_ORE)) {
                    e.getEntity().setCustomName("DiaOre");
                    MainListener.guntype.remove(p);
                }
                else if (MainListener.guntype.get(p).equals((Object)Material.TNT)) {
                    e.getEntity().setCustomName("TNT");
                    MainListener.guntype.remove(p);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void hit(final ProjectileHitEvent e) {
        try {
            final PlotAPI papi = new PlotAPI();
            if (papi.getPlot(e.getEntity().getLocation()) == null && e.getEntity() instanceof Snowball) {
                if (e.getEntity().getCustomName().equalsIgnoreCase("Beacon")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.BEACON);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$6() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("DragonEgg")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.DRAGON_EGG);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$7() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("Spawner")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.MOB_SPAWNER);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$8() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("Emeraldblock")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.EMERALD_BLOCK);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$9() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("Sponge")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.SPONGE);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$10() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("Obsidian")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.OBSIDIAN);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$11() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("Grundstein")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.BEDROCK);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$12() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("DiaOre")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.DIAMOND_ORE);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$13() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
                else if (e.getEntity().getCustomName().equalsIgnoreCase("TNT")) {
                    MainListener.gunblock.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getType());
                    MainListener.gunblockdata.put(e.getEntity().getLocation().getBlock().getLocation(), e.getEntity().getLocation().getBlock().getData());
                    MainListener.guncheck.add(e.getEntity().getLocation().getBlock().getLocation());
                    final Location bl = e.getEntity().getLocation().getBlock().getLocation();
                    e.getEntity().getLocation().getBlock().setType(Material.TNT);
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ MainListener this$0;
                        private final /* synthetic */ Location val$bl;
                        
                        MainListener$14() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            bl.getBlock().setType((Material)MainListener.gunblock.get(bl));
                            bl.getBlock().setData((byte)MainListener.gunblockdata.get(bl));
                            MainListener.gunblock.remove(bl);
                            MainListener.gunblockdata.remove(bl);
                            MainListener.guncheck.remove(bl);
                        }
                    }, 100L);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    public void setRand(final Player p, final String id, final Plot plot) {
        final PlotAPI papi = new PlotAPI();
        final PlotBlock[] pb = (PlotBlock[])Configuration.BLOCKLIST.parseString(id);
        if (plot.getConnectedPlots().size() > 1) {
            for (final Plot plots : plot.getConnectedPlots()) {
                papi.getPlotManager(p.getWorld()).setComponent(plots.getArea(), plots.getId(), "border", pb);
            }
        }
        else {
            papi.getPlotManager(p.getWorld()).setComponent(plot.getArea(), plot.getId(), "border", pb);
        }
    }
    
    @EventHandler
    public void onBooster(final PlayerJoinEvent e) {
        final Player p = e.getPlayer();
        if (BoosterCMD.FlyB == 1) {
            p.setAllowFlight(true);
        }
        else if (p.getGameMode() == GameMode.SURVIVAL || p.getGameMode() == GameMode.ADVENTURE) {
            p.setAllowFlight(false);
        }
        if (BoosterCMD.BreakB != 0) {
            p.removePotionEffect(PotionEffectType.FAST_DIGGING);
            p.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 20));
        }
    }
    
    @EventHandler
    public void onMobBooster(final EntityDeathEvent e) {
        if (!(e.getEntity() instanceof Player) && e.getEntity().getKiller() instanceof Player && BoosterCMD.MobB != 0) {
            if (e.getEntity().getType() != EntityType.HORSE) {
                final Integer Multiplikator = BoosterCMD.MobB + 1;
                final List<ItemStack> items = (List<ItemStack>)e.getDrops();
                for (Integer i = 0; i < Multiplikator; ++i) {
                    for (final ItemStack newitems : items) {
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                    }
                }
                e.getDrops().clear();
            }
            else {
                final Integer Multiplikator = BoosterCMD.MobB + 1;
                final List<ItemStack> items = (List<ItemStack>)e.getDrops();
                for (Integer i = 0; i < Multiplikator; ++i) {
                    for (final ItemStack newitems : items) {
                        if (newitems.getType() == Material.LEATHER) {
                            e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems);
                        }
                    }
                }
                for (final ItemStack newitems2 : items) {
                    if (newitems2.getType() != Material.LEATHER) {
                        e.getEntity().getWorld().dropItemNaturally(e.getEntity().getLocation(), newitems2);
                    }
                }
                e.getDrops().clear();
            }
        }
    }
    
    @EventHandler
    public void onErfahrungsBooster(final PlayerExpChangeEvent e) {
        if (BoosterCMD.XPB != 0) {
            final Integer Multiplikator = BoosterCMD.XPB + 1;
            e.setAmount(e.getAmount() * Multiplikator);
        }
    }
    
    @EventHandler
    public void onDropBooster(final BlockBreakEvent e) {
        if (e.getPlayer().getGameMode() == GameMode.SURVIVAL && BoosterCMD.DropB != 0 && !e.getPlayer().getInventory().getItemInHand().getItemMeta().hasEnchant(Enchantment.SILK_TOUCH)) {
            if (e.getBlock().getType() == Material.IRON_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.IRON_INGOT, (int)Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.GOLD_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.GOLD_INGOT, (int)Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.COAL_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.COAL, (int)Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.DIAMOND_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.DIAMOND, (int)Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.EMERALD_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.EMERALD, (int)Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.LAPIS_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(351, 4 + Multiplikator, (short)4);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.REDSTONE_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.REDSTONE, 4 + Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
            else if (e.getBlock().getType() == Material.QUARTZ_ORE) {
                final Integer Multiplikator = BoosterCMD.DropB + 1;
                final ItemStack i = new ItemStack(Material.QUARTZ, 4 + Multiplikator);
                e.getBlock().getWorld().dropItemNaturally(e.getBlock().getLocation(), i);
                e.getBlock().setType(Material.AIR);
            }
        }
    }
    
    @EventHandler
    public void onMenuCancel(final AsyncPlayerChatEvent e) {
        if (MainListener.menucanceltap.get(e.getPlayer()) != null) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final PlotAPI papi = new PlotAPI();
            e.setCancelled(true);
            if (MainListener.menucanceltap.get(e.getPlayer()).equalsIgnoreCase("Besuchen")) {
                MainListener.menucanceltap.remove(e.getPlayer());
                e.getPlayer().chat("/p h " + e.getMessage().replaceAll("§a§l", ""));
            }
            else if (Bukkit.getPlayer(e.getMessage().replaceAll("§a§l", "")) != null) {
                if (papi.getPlot(e.getPlayer().getLocation()) != null) {
                    if (papi.getPlot(e.getPlayer().getLocation()).isOwner(e.getPlayer().getUniqueId())) {
                        if (MainListener.menucanceltap.get(e.getPlayer()).equalsIgnoreCase("Helfer")) {
                            papi.getPlot(e.getPlayer().getLocation()).addMember(UUIDFetcher.getUUID(e.getMessage().replaceAll("§a§l", "")));
                            MainListener.menucanceltap.remove(e.getPlayer());
                            e.getPlayer().sendMessage(String.valueOf(Prefix) + "§a§lDu hast " + e.getMessage().replaceAll("§a§l", "") + " nun als Helfer!");
                        }
                        else if (MainListener.menucanceltap.get(e.getPlayer()).equalsIgnoreCase("Freund")) {
                            papi.getPlot(e.getPlayer().getLocation()).addTrusted(UUIDFetcher.getUUID(e.getMessage().replaceAll("§a§l", "")));
                            MainListener.menucanceltap.remove(e.getPlayer());
                            e.getPlayer().sendMessage(String.valueOf(Prefix) + "§a§lDu hast " + e.getMessage().replaceAll("§a§l", "") + " nun als Freund!");
                        }
                        else if (MainListener.menucanceltap.get(e.getPlayer()).equalsIgnoreCase("Remove")) {
                            papi.getPlot(e.getPlayer().getLocation()).removeMember(UUIDFetcher.getUUID(e.getMessage().replaceAll("§a§l", "")));
                            papi.getPlot(e.getPlayer().getLocation()).removeTrusted(UUIDFetcher.getUUID(e.getMessage().replaceAll("§a§l", "")));
                            MainListener.menucanceltap.remove(e.getPlayer());
                            e.getPlayer().sendMessage(String.valueOf(Prefix) + "§a§lDu hast nun " + e.getMessage().replaceAll("§a§l", "") + " vom Grundstück entfernt!");
                        }
                    }
                    else {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§c§lDas ist nicht dein Grundstück!");
                        MainListener.menucanceltap.remove(e.getPlayer());
                    }
                }
                else {
                    e.getPlayer().sendMessage(String.valueOf(Prefix) + "§c§lDu stehst auf keinem Grundstück!");
                    MainListener.menucanceltap.remove(e.getPlayer());
                }
            }
            else {
                e.getPlayer().sendMessage(String.valueOf(Prefix) + "§c§lDer Spieler ist nicht online!");
                MainListener.menucanceltap.remove(e.getPlayer());
            }
        }
    }
    
    public boolean openSign(final Player p, final Location loc) {
        try {
            final Class<?> packetClass = Reflect.getNMSClass("PacketPlayOutOpenSignEditor");
            final Class<?> blockPositionClass = Reflect.getNMSClass("BlockPosition");
            final Constructor<?> blockPosCon = blockPositionClass.getConstructor(Integer.TYPE, Integer.TYPE, Integer.TYPE);
            final Object blockPosition = blockPosCon.newInstance(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ());
            final Constructor<?> packetCon = packetClass.getConstructor(blockPositionClass);
            final Object packet = packetCon.newInstance(blockPosition);
            Reflect.sendPacket(p, packet);
            return true;
        }
        catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }
    
    @EventHandler
    public void onSchild(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (SchildCMD.Schild.contains(p) && e.getClickedBlock() != null && e.getClickedBlock().getType() != null) {
            final PlotAPI papi = new PlotAPI();
            if (papi.getPlot(e.getClickedBlock().getLocation()) != null) {
                if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                    if (e.getClickedBlock().getType() == Material.SIGN || e.getClickedBlock().getType() == Material.SIGN_POST || e.getClickedBlock().getType() == Material.WALL_SIGN) {
                        this.openSign(p, e.getClickedBlock().getLocation());
                    }
                    else {
                        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                        SchildCMD.Schild.remove(p);
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Block war kein Schild!");
                    }
                }
                else if (!p.hasPermission("system.schild.admin")) {
                    final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                    SchildCMD.Schild.remove(p);
                    p.sendMessage(String.valueOf(Prefix) + "§cDu hast keine Rechte dieses Schild zu verändern.");
                }
            }
            else if (!p.hasPermission("system.schild.admin")) {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                SchildCMD.Schild.remove(p);
                p.sendMessage(String.valueOf(Prefix) + "§cDu hast keine Rechte dieses Schild zu verändern.");
            }
        }
    }
    
    public static boolean hasItemVerboten(final Inventory inv, final ItemStack i, final Integer anzahl) {
        MainListener.VerbotenStufe.remove(inv);
        Integer Stufe = 0;
        ItemStack[] contents;
        for (int length = (contents = inv.getContents()).length, j = 0; j < length; ++j) {
            final ItemStack is = contents[j];
            if (is != null && is.getType() == i.getType() && is.getAmount() >= anzahl) {
                Stufe += 1000000 * is.getAmount();
            }
        }
        MainListener.VerbotenStufe.put(inv, Stufe);
        ItemStack[] contents2;
        for (int length2 = (contents2 = inv.getContents()).length, k = 0; k < length2; ++k) {
            final ItemStack is = contents2[k];
            if (is != null && is.getType() == i.getType() && is.getAmount() >= anzahl) {
                return true;
            }
        }
        return false;
    }
    
    public static boolean hasItemT(final Inventory inv) {
        MainListener.Stufe.remove(inv);
        Integer Stufe = 0;
        for (final ItemStack un : Main.unendlich) {
            ItemStack[] contents;
            for (int length = (contents = inv.getContents()).length, i = 0; i < length; ++i) {
                final ItemStack is = contents[i];
                if (is != null && is.getType() == un.getType() && is.getData().getData() == un.getData().getData()) {
                    Stufe += 10000 * is.getAmount();
                }
            }
        }
        for (final ItemStack un : Main.extrem) {
            ItemStack[] contents2;
            for (int length2 = (contents2 = inv.getContents()).length, j = 0; j < length2; ++j) {
                final ItemStack is = contents2[j];
                if (is != null && is.getType() == un.getType() && is.getData().getData() == un.getData().getData()) {
                    Stufe += 2500 * is.getAmount();
                }
            }
        }
        for (final ItemStack un : Main.hoch) {
            ItemStack[] contents3;
            for (int length3 = (contents3 = inv.getContents()).length, k = 0; k < length3; ++k) {
                final ItemStack is = contents3[k];
                if (is != null && is.getType() == un.getType() && is.getData().getData() == un.getData().getData()) {
                    Stufe += 1000 * is.getAmount();
                }
            }
        }
        for (final ItemStack un : Main.mittel) {
            ItemStack[] contents4;
            for (int length4 = (contents4 = inv.getContents()).length, l = 0; l < length4; ++l) {
                final ItemStack is = contents4[l];
                if (is != null && is.getType() == un.getType() && is.getData().getData() == un.getData().getData()) {
                    Stufe += 500 * is.getAmount();
                }
            }
        }
        if (Stufe > 199998) {
            MainListener.Stufe.put(inv, Stufe);
            return true;
        }
        return false;
    }
    
    public static String locToString(final Location l) {
        final String ret = String.valueOf(l.getWorld().getName()) + "," + l.getBlockX() + "," + l.getBlockY() + "," + l.getBlockZ();
        return ret;
    }
    
    public static Location stringToLoc(final String s) {
        final String[] a = s.split("\\,");
        final World w = Bukkit.getWorld(a[0]);
        final float x = Float.parseFloat(a[1]);
        final float y = Float.parseFloat(a[2]);
        final float z = Float.parseFloat(a[3]);
        return new Location(w, (double)x, (double)y, (double)z);
    }
    
    @EventHandler
    public void onOpen(final PlayerInteractEvent e) {
        if (Main.yAD.getBoolean("DuppCheck") && !e.getPlayer().hasPermission("system.antidupp.bypass") && (e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_BLOCK)) {
            if (e.getClickedBlock().getType() == Material.CHEST) {
                final Chest c = (Chest)e.getClickedBlock().getState();
                if (c.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten(c.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT(c.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.TRAPPED_CHEST) {
                final Chest c = (Chest)e.getClickedBlock().getState();
                if (c.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten(c.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT(c.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.DISPENSER) {
                final Dispenser c2 = (Dispenser)e.getClickedBlock().getState();
                if (c2.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten(c2.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c2.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT(c2.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c2.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.DROPPER) {
                final Dropper c3 = (Dropper)e.getClickedBlock().getState();
                if (c3.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten(c3.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c3.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT(c3.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c3.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.HOPPER) {
                final Hopper c4 = (Hopper)e.getClickedBlock().getState();
                if (c4.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten(c4.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c4.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT(c4.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c4.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.FURNACE) {
                final Furnace c5 = (Furnace)e.getClickedBlock().getState();
                if (c5.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten((Inventory)c5.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c5.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT((Inventory)c5.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c5.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
            else if (e.getClickedBlock().getType() == Material.BURNING_FURNACE) {
                final Furnace c5 = (Furnace)e.getClickedBlock().getState();
                if (c5.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                    if (!Whitelist.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                        if (!blocked.contains(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()))) {
                            for (final ItemStack vi : Main.verboten) {
                                if (hasItemVerboten((Inventory)c5.getInventory(), vi, 1)) {
                                    e.setCancelled(true);
                                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                    blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                    Main.yAD.set("BlockLocs", (Object)blocked);
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.VerbotenStufe.get(c5.getInventory()));
                                    Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                    try {
                                        Main.yAD.save(Main.AD);
                                    }
                                    catch (IOException e2) {
                                        e2.printStackTrace();
                                    }
                                }
                            }
                            if (hasItemT((Inventory)c5.getInventory())) {
                                e.setCancelled(true);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                                ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                                blocked.add(locToString(e.getClickedBlock().getLocation().getBlock().getLocation()));
                                Main.yAD.set("BlockLocs", (Object)blocked);
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Stufe", (Object)MainListener.Stufe.get(c5.getInventory()));
                                Main.yAD.set("DuppLocs.loc." + locToString(e.getClickedBlock().getLocation().getBlock().getLocation()) + ".Spieler", (Object)p.getUniqueId().toString());
                                try {
                                    Main.yAD.save(Main.AD);
                                }
                                catch (IOException e3) {
                                    e3.printStackTrace();
                                }
                            }
                        }
                        else {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        }
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEntityEvent e) {
        if (Main.yAD.getBoolean("DuppCheck") && !e.getPlayer().hasPermission("system.antidupp.bypass")) {
            if (e.getRightClicked().getType() == EntityType.MINECART_HOPPER) {
                final HopperMinecart c = (HopperMinecart)e.getRightClicked();
                if (c.getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    for (final ItemStack vi : Main.verboten) {
                        if (hasItemVerboten(c.getInventory(), vi, 1)) {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                            blocked.add(locToString(e.getRightClicked().getLocation().getBlock().getLocation()));
                            c.remove();
                        }
                    }
                    if (hasItemT(((InventoryHolder)c).getInventory())) {
                        e.setCancelled(true);
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                        ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        blocked.add(locToString(e.getRightClicked().getLocation().getBlock().getLocation()));
                        c.remove();
                    }
                }
            }
            else if (e.getRightClicked().getType() == EntityType.MINECART_CHEST) {
                final Minecart c2 = (Minecart)e.getRightClicked();
                if (((InventoryHolder)c2).getInventory() != null) {
                    final Player p = e.getPlayer();
                    final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                    for (final ItemStack vi : Main.verboten) {
                        if (hasItemVerboten(((InventoryHolder)c2).getInventory(), vi, 1)) {
                            e.setCancelled(true);
                            p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                            ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                            blocked.add(locToString(e.getRightClicked().getLocation().getBlock().getLocation()));
                            c2.remove();
                        }
                    }
                    if (hasItemT(((InventoryHolder)c2).getInventory())) {
                        e.setCancelled(true);
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                        ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                        blocked.add(locToString(e.getRightClicked().getLocation().getBlock().getLocation()));
                        c2.remove();
                    }
                }
            }
        }
    }
    
    @EventHandler
    public void onJoinDupp(final PlayerJoinEvent e) {
        if (Main.yAD.getBoolean("JoinDuppCheckInv/Ec") && !e.getPlayer().hasPermission("system.antidupp.bypass")) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = e.getPlayer();
            if (hasItemT((Inventory)p.getInventory())) {
                p.getInventory().clear();
                p.sendMessage(String.valueOf(Prefix) + "§cDein Inventar wurde gecleart da du geduppte Items dabei hattest!");
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
            }
            if (hasItemT(p.getEnderChest())) {
                p.getInventory().clear();
                p.sendMessage(String.valueOf(Prefix) + "§cDeine EnderChest wurde gecleart da du geduppte Items dabei hattest!");
                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
            }
            for (final ItemStack vi : Main.verboten) {
                if (hasItemVerboten((Inventory)p.getInventory(), vi, 1)) {
                    p.getInventory().clear();
                    p.sendMessage(String.valueOf(Prefix) + "§cDein Inventar wurde gecleart da du verbotene Items dabei hattest!");
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                }
            }
            for (final ItemStack vi : Main.verboten) {
                if (hasItemVerboten(p.getEnderChest(), vi, 1)) {
                    p.getInventory().clear();
                    p.sendMessage(String.valueOf(Prefix) + "§cDeine EnderChest wurde gecleart da du verbotene Items dabei hattest!");
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                }
            }
        }
    }
    
    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
        if (e.getBlock().getType() == Material.CHEST || e.getBlock().getType() == Material.TRAPPED_CHEST || e.getBlock().getType() == Material.DISPENSER || e.getBlock().getType() == Material.DROPPER || e.getBlock().getType() == Material.HOPPER || e.getBlock().getType() == Material.FURNACE || e.getBlock().getType() == Material.BURNING_FURNACE) {
            if (!e.getPlayer().hasPermission("system.antidupp.bypass")) {
                final Player p = e.getPlayer();
                final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                if (blocked.contains(locToString(e.getBlock().getLocation().getBlock().getLocation()))) {
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                }
            }
            else {
                final Player p = e.getPlayer();
                final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                if (blocked.contains(locToString(e.getBlock().getLocation().getBlock().getLocation()))) {
                    final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                    p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                    p.sendMessage(String.valueOf(Prefix) + "§2Du hast erfolgreich die Location §c" + locToString(e.getBlock().getLocation().getBlock().getLocation()) + " \n §2aus der Liste entfernt!");
                    blocked.remove(locToString(e.getBlock().getLocation().getBlock().getLocation()));
                    Main.yAD.set("BlockLocs", (Object)blocked);
                    Main.yAD.set("DuppLocs.loc." + locToString(e.getBlock().getLocation().getBlock().getLocation()), (Object)null);
                    try {
                        Main.yAD.save(Main.AD);
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            final Player p = e.getPlayer();
            final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
            if (Whitelist.contains(locToString(e.getBlock().getLocation().getBlock().getLocation()))) {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                p.sendMessage(String.valueOf(Prefix) + "§7Der Block war auf der §fWhitelist §7da du ihn abgebaut hast wurde der daraus entfernt!");
                Whitelist.remove(locToString(e.getBlock().getLocation().getBlock().getLocation()));
                Main.yAD.set("Whitelist", (Object)Whitelist);
                try {
                    Main.yAD.save(Main.AD);
                }
                catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
    }
    
    @EventHandler
    public void onBuild(final BlockPlaceEvent e) {
        if (!e.getPlayer().hasPermission("system.antidupp.bypass")) {
            final Player p = e.getPlayer();
            final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
            for (final String blocks : blocked) {
                final Location loc = stringToLoc(blocks);
                if (e.getBlock().getWorld().equals(loc.getWorld()) && e.getBlock().getLocation().distance(loc) < 2.0) {
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                }
            }
        }
    }
    
    @EventHandler
    public void onBreak2(final BlockBreakEvent e) {
        if (!e.getPlayer().hasPermission("system.antidupp.bypass")) {
            final Player p = e.getPlayer();
            final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
            for (final String blocks : blocked) {
                final Location loc = stringToLoc(blocks);
                if (e.getBlock().getWorld().equals(loc.getWorld()) && e.getBlock().getLocation().distance(loc) < 2.0) {
                    e.setCancelled(true);
                    p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 2.0f, 2.0f);
                    ActionBar.sendActionBarTime(p, "§cDieser Block wurde beschlagnahmt!");
                }
            }
        }
    }
    
    @EventHandler
    public void EEE(final EntityExplodeEvent e) {
        try {
            final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
            for (final Block b : e.blockList()) {
                if (blocked.contains(locToString(b.getLocation()))) {
                    e.setCancelled(true);
                }
            }
        }
        catch (Exception ex) {}
    }
    
    @EventHandler
    public void onYeezys(final PlayerMoveEvent e) {
        final Player p = e.getPlayer();
        if (p.getInventory().getBoots() != null && p.getInventory().getBoots().getType() != null && p.getInventory().getBoots().hasItemMeta() && p.getInventory().getBoots().getItemMeta().hasLore() && p.getInventory().getBoots().getItemMeta().getLore().get(0) != null && p.getInventory().getBoots().getItemMeta().getLore().get(0).equalsIgnoreCase("Abgenutzt will diese Schuhe keiner anziehen!")) {
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 300, 6));
            p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 300, 6));
        }
    }
    
    @EventHandler
    public void onCola(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (p.getItemInHand() != null && p.getItemInHand().getType() != null && p.getItemInHand().hasItemMeta() && p.getItemInHand().getItemMeta().hasLore() && p.getItemInHand().getItemMeta().getLore().get(0) != null && p.getItemInHand().getItemMeta().getLore().get(0).equalsIgnoreCase("§fGibt dir einen Energieschub!")) {
            e.setCancelled(true);
            p.setItemInHand(new ItemStack(Material.AIR));
            p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 600, 10));
        }
    }
    
    @EventHandler
    public void onPlotDelete(final PlotClearEvent e) {
        final PlotAPI papi = new PlotAPI();
        final File Settings = new File("plugins/PlotSquared/config/worlds.yml");
        final YamlConfiguration ySettings = YamlConfiguration.loadConfiguration(Settings);
        final String[] block = ySettings.getString("worlds." + e.getWorld() + ".road" + ".block").split(":");
        final Material m = Material.getMaterial((int)Integer.valueOf(block[0]));
        final Byte d = Byte.valueOf(block[1]);
        final Integer GrößeDerPlots = ySettings.getInt("worlds." + e.getWorld() + ".plot" + ".size") + 2 + 3;
        final Integer HöheDerPlots = ySettings.getInt("worlds." + e.getWorld() + ".plot" + ".height");
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(m);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(2.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(3.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract(4.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.AIR);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setData((byte)d);
        papi.getBottomLocation(e.getPlot()).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setData((byte)d);
    }
    
    static /* synthetic */ Main access$0(final MainListener mainListener) {
        return mainListener.plugin;
    }
}
