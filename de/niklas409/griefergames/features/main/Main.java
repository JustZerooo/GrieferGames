package de.niklas409.griefergames.features.main;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import java.util.Arrays;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import org.bukkit.entity.Player;
import org.bukkit.Effect;
import org.bukkit.Location;
import java.util.Iterator;
import java.util.List;
import org.bukkit.configuration.file.FileConfiguration;
import java.io.IOException;
import org.bukkit.Material;
import de.niklas409.griefergames.features.only.api.cmds.MenuCMD;
import de.niklas409.griefergames.features.only.api.cmds.PremiumCMD;
import de.niklas409.griefergames.features.cmds.PortalCMD;
import de.niklas409.griefergames.features.cmds.BankCMD;
import de.niklas409.griefergames.features.blocklog.BlockLogListener;
import de.niklas409.griefergames.features.blocklog.BlockLogCMD;
import de.niklas409.griefergames.features.only.api.cmds.PlayerholoCMD;
import de.niklas409.griefergames.features.clans.ClanListener;
import de.niklas409.griefergames.features.listeners.MainListener;
import de.niklas409.griefergames.features.only.api.cmds.LeuchtenCMD;
import de.niklas409.griefergames.features.cmds.DupCMD;
import de.niklas409.griefergames.features.cmds.FreeKisteCMD;
import de.niklas409.griefergames.features.cmds.SetCaseBlockCMD;
import de.niklas409.griefergames.features.cmds.CreateCaseItemCMD;
import de.niklas409.griefergames.features.cmds.CaseCMD;
import de.niklas409.griefergames.features.only.api.cmds.SchildCMD;
import de.niklas409.griefergames.features.cmds.SignCMD;
import de.niklas409.griefergames.features.cmds.BoosterCMD;
import de.niklas409.griefergames.features.cmds.GrieferBoostCMD;
import de.niklas409.griefergames.features.only.api.cmds.MergeCMD;
import de.niklas409.griefergames.features.cmds.SkinCMD;
import de.niklas409.griefergames.features.cmds.GunCMD;
import de.niklas409.griefergames.features.cmds.FeuerwerkCMD;
import de.niklas409.griefergames.features.cmds.StatusCMD;
import de.niklas409.griefergames.features.cmds.PerkCMD;
import de.niklas409.griefergames.features.cmds.AntiBotCMD;
import de.niklas409.griefergames.features.cmds.MeldungCMD;
import de.niklas409.griefergames.features.only.api.cmds.BreakBlockCMD;
import de.niklas409.griefergames.features.clans.ClanCMD;
import de.niklas409.griefergames.features.cmds.VanishCMD;
import de.niklas409.griefergames.features.cmds.UnmuteCMD;
import de.niklas409.griefergames.features.cmds.UnbanCMD;
import de.niklas409.griefergames.features.cmds.BanCMD;
import de.niklas409.griefergames.features.cmds.PingCMD;
import de.niklas409.griefergames.features.cmds.LuckyBlockCMD;
import de.niklas409.griefergames.features.cmds.GGRLCMD;
import de.niklas409.griefergames.features.only.api.cmds.RandCMD;
import de.niklas409.griefergames.features.cmds.UnStartKickCMD;
import de.niklas409.griefergames.features.cmds.UnmutepCMD;
import de.niklas409.griefergames.features.cmds.ViewarmorCMD;
import de.niklas409.griefergames.features.cmds.BoldCMD;
import de.niklas409.griefergames.features.cmds.PrefixCMD;
import de.niklas409.griefergames.features.cmds.KopfCMD;
import de.niklas409.griefergames.features.cmds.NeinCMD;
import de.niklas409.griefergames.features.cmds.JaCMD;
import de.niklas409.griefergames.features.cmds.StartKickCMD;
import de.niklas409.griefergames.features.cmds.MutepCMD;
import de.niklas409.griefergames.features.cmds.ClearchatCMD;
import de.niklas409.griefergames.features.cmds.SlowchatCMD;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.listeners.CaseOpeningBETA;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.inventory.ItemStack;
import java.util.ArrayList;
import de.niklas409.griefergames.features.blocklog.MySQL;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin
{
    public static String Version;
    static Main instance;
    public static MySQL mysql;
    public static ArrayList<ItemStack> mittel;
    public static ArrayList<ItemStack> hoch;
    public static ArrayList<ItemStack> extrem;
    public static ArrayList<ItemStack> unendlich;
    public static ArrayList<ItemStack> verboten;
    public static File AD;
    public static YamlConfiguration yAD;
    
    static {
        Main.Version = "v3.0";
        Main.mittel = new ArrayList<ItemStack>();
        Main.hoch = new ArrayList<ItemStack>();
        Main.extrem = new ArrayList<ItemStack>();
        Main.unendlich = new ArrayList<ItemStack>();
        Main.verboten = new ArrayList<ItemStack>();
        Main.AD = new File("plugins/GrieferGames/AntiDupp.yml");
        Main.yAD = YamlConfiguration.loadConfiguration(Main.AD);
    }
    
    public Main() {
        super();
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
    
    public void onEnable() {
        Rezepte();
        CaseOpeningBETA.using = false;
        (Main.instance = this).LuckyBlockEffekte();
        this.loadConfig();
        this.loadSBConfig();
        this.loadMySQLFile();
        this.SBUpdate();
        final String Prefix = this.getConfig().getString("Prefix").replace("&", "§");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDas Plugin wurde erfolgreich gestartet!");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§6" + Main.Version + " by §2Niklas409");
        if (isVersionOld((Plugin)this)) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDu benutzt eine §4§lalte §cVersion vom Plugin!");
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDu benutzt die §2§lneuste §aVersion vom Plugin!");
        }
        this.init();
        this.LoadAntiDuppConfig();
        this.LoadPlayerHolo();
        this.LoadBlockLog();
        this.LoadBank();
        this.LoadPortal();
        this.LoadPremium();
        this.BoosterChecker();
        this.LoadMenu();
        this.LoadCaseOpeningFile();
    }
    
    public void onDisable() {
        final String Prefix = this.getConfig().getString("Prefix").replace("&", "§");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aDas Plugin wurde erfolgreich beendet!");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§6" + Main.Version + " by §2Niklas409");
    }
    
    public void init() {
        new SlowchatCMD(this);
        new ClearchatCMD(this);
        new MutepCMD(this);
        new StartKickCMD(this);
        new JaCMD(this);
        new NeinCMD(this);
        new KopfCMD(this);
        new PrefixCMD(this);
        new BoldCMD(this);
        new ViewarmorCMD(this);
        new UnmutepCMD(this);
        new UnStartKickCMD(this);
        new RandCMD(this);
        new GGRLCMD(this);
        new LuckyBlockCMD(this);
        new PingCMD(this);
        new BanCMD(this);
        new UnbanCMD(this);
        new UnmuteCMD(this);
        new VanishCMD(this);
        new ClanCMD(this);
        new BreakBlockCMD(this);
        new MeldungCMD(this);
        new AntiBotCMD(this);
        new PerkCMD(this);
        new StatusCMD(this);
        new FeuerwerkCMD(this);
        new GunCMD(this);
        new SkinCMD(this);
        new MergeCMD(this);
        new GrieferBoostCMD(this);
        new BoosterCMD(this);
        new SignCMD(this);
        new SchildCMD(this);
        new CaseCMD(this);
        new CreateCaseItemCMD(this);
        new SetCaseBlockCMD(this);
        new FreeKisteCMD(this);
        new DupCMD(this);
        new LeuchtenCMD(this);
        new MainListener(this);
        new ClanListener(this);
        new CaseOpeningBETA(this);
    }
    
    public void LoadPlayerHolo() {
        if (this.getConfig().getString("/Playerholo").equalsIgnoreCase("true")) {
            new PlayerholoCMD(this);
        }
    }
    
    public void LoadBlockLog() {
        if (this.getConfig().getString("BlockLog").equalsIgnoreCase("true")) {
            this.ConnectMySQLBlockLog();
            if (MySQL.con != null) {
                new BlockLogCMD(this);
                new BlockLogListener(this);
            }
        }
    }
    
    public void LoadBank() {
        if (this.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("MySQL")) {
            this.ConnectMySQLBank();
            if (MySQL.con != null) {
                new BankCMD(this);
            }
        }
        else if (this.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File")) {
            new BankCMD(this);
        }
    }
    
    public void LoadPortal() {
        if (this.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Server") || this.getConfig().getString("PortalSettings false/Server/Map").equalsIgnoreCase("Map")) {
            new PortalCMD(this);
        }
    }
    
    public void LoadPremium() {
        if (this.getConfig().getString("/Premium").equalsIgnoreCase("true")) {
            new PremiumCMD(this);
        }
    }
    
    public void LoadMenu() {
        if (this.getConfig().getString("/Menu").equalsIgnoreCase("true")) {
            new MenuCMD(this);
        }
    }
    
    public void LoadCaseOpeningFile() {
        final ItemStack test1 = ItemBuilder.createItemWD(Material.STONE, "§4Test Item", 1, 0, new String[] { "§aNormal" });
        final ItemStack test2 = ItemBuilder.createItemWD(Material.DIAMOND, "§4lol", 5, 0, new String[] { "§6Selten" });
        final ItemStack test3 = ItemBuilder.createItemWD(Material.STICK, "§5ibims", 1, 0, new String[] { "§bUltra" });
        final ItemStack test4 = ItemBuilder.createItemWD(Material.BEACON, "§6abc", 5, 0, new String[] { "§5Episch" });
        final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
        final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
        yCO.addDefault("Truhe.Episch.Preise.1", (Object)test1);
        yCO.addDefault("Truhe.Episch.Preise.2", (Object)test2);
        yCO.addDefault("Truhe.Supreme.Preise.1", (Object)test3);
        yCO.addDefault("Truhe.Supreme.Preise.2", (Object)test4);
        yCO.options().copyDefaults(true);
        try {
            yCO.save(CO);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadConfig() {
        this.getConfig().addDefault("Prefix", (Object)"&8[&4&lGG Features&8] &r");
        this.getConfig().addDefault("NoPerms", (Object)"&cDazu hast du keine Rechte!");
        this.getConfig().addDefault("Servername", (Object)"Server.de");
        this.getConfig().addDefault("UnbanAntragIp", (Object)"Server.de");
        this.getConfig().addDefault("StartKickPrefix", (Object)"&7[&4&lStart&e&lKick&7] &r");
        this.getConfig().addDefault("NoPermsStartKick", (Object)"&cDazu hast du keine Rechte!");
        this.getConfig().addDefault("Shop", (Object)"&7Dein Link zum Shop -> &2Vernikt.de");
        this.getConfig().addDefault("WerbungBeimJoinen", (Object)"true");
        this.getConfig().addDefault("UpdaterBeimJoinen", (Object)"true");
        this.getConfig().addDefault("ClearChatWaitTimeInMinutes", (Object)"60");
        this.getConfig().addDefault("KopfWaitTimeInMinutes", (Object)"20160");
        this.getConfig().addDefault("MutepWaitTimeInMinutes", (Object)"60");
        this.getConfig().addDefault("MutepTime", (Object)"15");
        this.getConfig().addDefault("SlowChatWaitTimeInMinutes", (Object)"10");
        this.getConfig().addDefault("StartKickWaitTimeInMinutes", (Object)"1440");
        this.getConfig().addDefault("PremiumWaitTimeInMinutes", (Object)"10080");
        this.getConfig().addDefault("/p", (Object)"false");
        this.getConfig().addDefault("/Rand", (Object)"false");
        this.getConfig().addDefault("/Menu", (Object)"false");
        this.getConfig().addDefault("/Premium", (Object)"false");
        this.getConfig().addDefault("PremiumRangName", (Object)"Premium");
        this.getConfig().addDefault("PremiumLaengeInTage", (Object)"7");
        this.getConfig().addDefault("PlotPreise", (Object)10000);
        this.getConfig().addDefault("NoFallPerk", (Object)500000);
        this.getConfig().addDefault("NoHungerPerk", (Object)250000);
        this.getConfig().addDefault("MutepPerk", (Object)1000000);
        this.getConfig().addDefault("ClearChatPerk", (Object)250000);
        this.getConfig().addDefault("SlowChatPerk", (Object)250000);
        this.getConfig().addDefault("StartKickPerk", (Object)750000);
        this.getConfig().addDefault("ChatSystem[Deaktivieren, wenn es Bugs mit dem Chat gibt]", (Object)"true");
        this.getConfig().addDefault("GruenerChatFuerOp", (Object)"true");
        this.getConfig().addDefault("ChatPfeile(AK)", (Object)"true");
        this.getConfig().addDefault("/Playerholo", (Object)"true");
        this.getConfig().addDefault("WieVieleHolosProSpieler", (Object)"3");
        this.getConfig().addDefault("ChatLog", (Object)"true");
        this.getConfig().addDefault("CommandLog", (Object)"true");
        this.getConfig().addDefault("BlockLog", (Object)"false");
        this.getConfig().addDefault("PortalSettings false/Server/Map", (Object)"false");
        this.getConfig().addDefault("Servername[Bungee-Config] vom PortalServer", (Object)"false");
        this.getConfig().addDefault("BankSettings false/File/MySQL", (Object)"File");
        final FileConfiguration cfg = this.getConfig();
        cfg.options().copyDefaults(true);
        this.saveConfig();
    }
    
    public void LoadAntiDuppConfig() {
        Main.yAD.addDefault("Info", (Object)"Die Zahl vor dem / steht für die Item oder Block Id und die Zahl nach dem / für die Data.");
        Main.yAD.addDefault("DuppCheck", (Object)true);
        Main.yAD.addDefault("JoinDuppCheckInv/Ec", (Object)false);
        final List<String> mittel = (List<String>)Main.yAD.getStringList("AntiDupp.medium");
        final List<String> hoch = (List<String>)Main.yAD.getStringList("AntiDupp.high");
        final List<String> extrem = (List<String>)Main.yAD.getStringList("AntiDupp.extreme");
        final List<String> unendlich = (List<String>)Main.yAD.getStringList("AntiDupp.infinitely");
        final List<String> verboten = (List<String>)Main.yAD.getStringList("AntiDupp.banned");
        mittel.add("42/0");
        mittel.add("41/0");
        mittel.add("57/0");
        mittel.add("133/0");
        hoch.add("397/0");
        hoch.add("397/1");
        hoch.add("397/2");
        hoch.add("397/3");
        hoch.add("397/4");
        hoch.add("138/0");
        hoch.add("399/0");
        extrem.add("122/0");
        extrem.add("166/0");
        extrem.add("383/50");
        extrem.add("383/51");
        extrem.add("383/55");
        extrem.add("383/57");
        extrem.add("383/58");
        extrem.add("383/62");
        extrem.add("383/66");
        extrem.add("383/68");
        extrem.add("383/94");
        unendlich.add("383/120");
        unendlich.add("52/0");
        verboten.add("137/0");
        Main.yAD.addDefault("AntiDupp.medium", (Object)mittel);
        Main.yAD.addDefault("AntiDupp.high", (Object)hoch);
        Main.yAD.addDefault("AntiDupp.extreme", (Object)extrem);
        Main.yAD.addDefault("AntiDupp.infinitely", (Object)unendlich);
        Main.yAD.addDefault("AntiDupp.banned", (Object)verboten);
        Main.yAD.options().copyDefaults(true);
        try {
            Main.yAD.save(Main.AD);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        for (final String ng : mittel) {
            final String[] all = ng.split("/");
            final Integer datai = Integer.valueOf(all[1]);
            final short data = datai.shortValue();
            final ItemStack i = new ItemStack(Material.getMaterial((int)Integer.valueOf(all[0])), 1, data);
            Main.mittel.add(i);
        }
        for (final String ng : hoch) {
            final String[] all = ng.split("/");
            final Integer datai = Integer.valueOf(all[1]);
            final short data = datai.shortValue();
            final ItemStack i = new ItemStack(Material.getMaterial((int)Integer.valueOf(all[0])), 1, data);
            Main.hoch.add(i);
        }
        for (final String ng : extrem) {
            final String[] all = ng.split("/");
            final Integer datai = Integer.valueOf(all[1]);
            final short data = datai.shortValue();
            final ItemStack i = new ItemStack(Material.getMaterial((int)Integer.valueOf(all[0])), 1, data);
            Main.extrem.add(i);
        }
        for (final String ng : unendlich) {
            final String[] all = ng.split("/");
            final Integer datai = Integer.valueOf(all[1]);
            final short data = datai.shortValue();
            final ItemStack i = new ItemStack(Material.getMaterial((int)Integer.valueOf(all[0])), 1, data);
            Main.unendlich.add(i);
        }
        for (final String ng : verboten) {
            final String[] all = ng.split("/");
            final Integer datai = Integer.valueOf(all[1]);
            final short data = datai.shortValue();
            final ItemStack i = new ItemStack(Material.getMaterial((int)Integer.valueOf(all[0])), 1, data);
            Main.verboten.add(i);
        }
        final String Prefix = this.getConfig().getString("Prefix").replace("&", "§");
        Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§aAntiDuppSystem wurde geladen!");
    }
    
    public void loadSBConfig() {
        final File SB = new File("plugins/GrieferGames/Scoreboard.yml");
        final YamlConfiguration ySB = YamlConfiguration.loadConfiguration(SB);
        ySB.addDefault("Info", (Object)"Der Titel darf nicht mehr als 16 Zeichen haben! %ONPLAYER% bedeutet wie viele online sind, %MAXPLAYER% bedeutet wie viele maximal auf denn Server duerfen, %ESSENTIALSMONEY% ist das GeldSystem von Essentials, %WORLD% zeigt die Welt an wo man selber ist und %NULL% bedeutet das die Line nicht angezeigt wird.");
        ySB.addDefault("Scoreboard", (Object)"true");
        ySB.addDefault("Sekunden fuer Update", (Object)"5");
        ySB.addDefault("Titel", (Object)"&6Server.de");
        ySB.addDefault("Line13", (Object)"&1 ");
        ySB.addDefault("Line12", (Object)"&7> &3&lServer");
        ySB.addDefault("Line11", (Object)"&fCB1");
        ySB.addDefault("Line10", (Object)"&2 ");
        ySB.addDefault("Line9", (Object)"&7> &3&lOnline");
        ySB.addDefault("Line8", (Object)"&f%ONPLAYER%/%MAXPLAYER%");
        ySB.addDefault("Line7", (Object)"&3 ");
        ySB.addDefault("Line6", (Object)"&7> &3&lKontostand");
        ySB.addDefault("Line5", (Object)"&f%ESSENTIALSMONEY%");
        ySB.addDefault("Line4", (Object)"&4 ");
        ySB.addDefault("Line3", (Object)"&7> &3&lShop");
        ySB.addDefault("Line2", (Object)"&fServer.de");
        ySB.addDefault("Line1", (Object)"&5 ");
        ySB.options().copyDefaults(true);
        try {
            ySB.save(SB);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void loadMySQLFile() {
        final File MySQL = new File("plugins/GrieferGames/MySQL.yml");
        final YamlConfiguration yMySQL = YamlConfiguration.loadConfiguration(MySQL);
        yMySQL.addDefault("Info", (Object)"Die MySQL wird derzeit fuer BlockLog und der Bank benoetigt, solltest du dies nicht brauchen kannst du das einfach in der Config ausstellen.");
        yMySQL.addDefault("MySQL.Host", (Object)"localhost");
        yMySQL.addDefault("MySQL.Port", (Object)"3306");
        yMySQL.addDefault("MySQL.Database", (Object)"gg");
        yMySQL.addDefault("MySQL.User", (Object)"root");
        yMySQL.addDefault("MySQL.Password", (Object)"123");
        yMySQL.options().copyDefaults(true);
        try {
            yMySQL.save(MySQL);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void reload() {
        Bukkit.getPluginManager().getPlugin("GrieferGames").getConfig();
        Bukkit.getPluginManager().getPlugin("GrieferGames").reloadConfig();
    }
    
    public void LuckyBlockEffekte() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
            final /* synthetic */ Main this$0;
            
            Main$1() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void run() {
                try {
                    for (final Location loc : MainListener.LuckyBlockSaver) {
                        if (loc.getBlock().getType() == Material.SPONGE) {
                            loc.getWorld().playEffect(new Location(loc.getWorld(), loc.getX() + 1.0, loc.getY(), loc.getZ()), Effect.MOBSPAWNER_FLAMES, 1);
                            loc.getWorld().playEffect(new Location(loc.getWorld(), loc.getX() - 1.0, loc.getY(), loc.getZ()), Effect.MOBSPAWNER_FLAMES, 1);
                            loc.getWorld().playEffect(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() + 1.0), Effect.MOBSPAWNER_FLAMES, 1);
                            loc.getWorld().playEffect(new Location(loc.getWorld(), loc.getX(), loc.getY(), loc.getZ() - 1.0), Effect.MOBSPAWNER_FLAMES, 1);
                        }
                        else {
                            MainListener.LuckyBlockSaver.remove(loc);
                        }
                    }
                }
                catch (Exception ex) {}
            }
        }, 0L, 18L);
    }
    
    private void SBUpdate() {
        final File SB = new File("plugins/GrieferGames/Scoreboard.yml");
        final YamlConfiguration ySB = YamlConfiguration.loadConfiguration(SB);
        if (ySB.getString("Scoreboard").equalsIgnoreCase("true")) {
            final Integer Zahl = Integer.valueOf(ySB.getString("Sekunden fuer Update"));
            Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
                final /* synthetic */ Main this$0;
                
                Main$2() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public void run() {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        Scoreboard.sendScoreboard(all);
                    }
                }
            }, 0L, (long)(20 * Zahl));
        }
    }
    
    private void ConnectMySQLBlockLog() {
        final File MySQL = new File("plugins/GrieferGames/MySQL.yml");
        final YamlConfiguration yMySQL = YamlConfiguration.loadConfiguration(MySQL);
        final String Host = yMySQL.getString("MySQL.Host");
        final String Port = yMySQL.getString("MySQL.Port");
        final String Database = yMySQL.getString("MySQL.Database");
        final String User = yMySQL.getString("MySQL.User");
        final String Password = yMySQL.getString("MySQL.Password");
        (Main.mysql = new MySQL(Host, Port, Database, User, Password)).update("CREATE TABLE IF NOT EXISTS BlöckeAbbauen(Location varchar(1000),Block varchar(1000), SpielerUUID varchar(1000),Datum varchar(1000), Uhrzeit varchar(1000));");
        Main.mysql.update("CREATE TABLE IF NOT EXISTS BlöckeBauen(Location varchar(1000),Block varchar(1000), SpielerUUID varchar(1000),Datum varchar(1000), Uhrzeit varchar(1000));");
    }
    
    private void ConnectMySQLBank() {
        final File MySQL = new File("plugins/GrieferGames/MySQL.yml");
        final YamlConfiguration yMySQL = YamlConfiguration.loadConfiguration(MySQL);
        final String Host = yMySQL.getString("MySQL.Host");
        final String Port = yMySQL.getString("MySQL.Port");
        final String Database = yMySQL.getString("MySQL.Database");
        final String User = yMySQL.getString("MySQL.User");
        final String Password = yMySQL.getString("MySQL.Password");
        (Main.mysql = new MySQL(Host, Port, Database, User, Password)).update("CREATE TABLE IF NOT EXISTS Bank(UUID varchar(1000), Geld Int(16));");
    }
    
    public static boolean isVersionOld(final Plugin pl) {
        final String Prefix = pl.getConfig().getString("Prefix").replace("&", "§");
        String newVersion = "";
        String oldVersion = "";
        try {
            final HttpURLConnection c = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=46956").openConnection();
            c.setDoOutput(true);
            c.setRequestMethod("POST");
            c.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=46956".getBytes("UTF-8"));
            oldVersion = Main.Version.substring(1);
            newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine().replaceAll("[a-zA-Z ]", "");
            newVersion = newVersion.substring(1);
        }
        catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§4§lFehler: §a" + e.getMessage());
        }
        return !newVersion.equals(oldVersion);
    }
    
    public static String getNewVersion() {
        String newVersion = "";
        try {
            final HttpURLConnection c = (HttpURLConnection)new URL("https://api.spigotmc.org/legacy/update.php?resource=46956").openConnection();
            c.setDoOutput(true);
            c.setRequestMethod("POST");
            c.getOutputStream().write("key=98BE0FE67F88AB82B4C197FAF1DC3B69206EFDCC4D3B80FC83A00037510B99B4&resource=46956".getBytes("UTF-8"));
            newVersion = new BufferedReader(new InputStreamReader(c.getInputStream())).readLine().replaceAll("[a-zA-Z ]", "");
            newVersion = newVersion.substring(1);
        }
        catch (Exception ex) {}
        return newVersion;
    }
    
    public void BoosterChecker() {
        Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this, (Runnable)new Runnable() {
            final /* synthetic */ Main this$0;
            
            Main$3() {
                this.this$0 = this$0;
                super();
            }
            
            @Override
            public void run() {
                if (BoosterCMD.FlyB != 0) {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        all.setAllowFlight(true);
                    }
                }
                if (BoosterCMD.BreakB != 0) {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        all.removePotionEffect(PotionEffectType.FAST_DIGGING);
                        all.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 20));
                    }
                }
            }
        }, 0L, 200L);
    }
    
    public static void Rezepte() {
        final ItemStack AS = new ItemStack(Material.DIAMOND_SWORD);
        final ItemMeta ASMeta = AS.getItemMeta();
        ASMeta.setDisplayName("§e§lAntikes Schwert");
        ASMeta.addEnchant(Enchantment.DAMAGE_ALL, 8, true);
        ASMeta.setLore((List)Arrays.asList("§fDieses Schwert ist antik.", "§fEs ist über §c1.000 Jahre §falt!"));
        AS.setItemMeta(ASMeta);
        final ShapedRecipe asr = new ShapedRecipe(AS);
        asr.shape(new String[] { "DBD", "BEB", "DBD" });
        asr.setIngredient('D', Material.DRAGON_EGG);
        asr.setIngredient('B', Material.BEACON);
        asr.setIngredient('E', Material.ENDER_PORTAL_FRAME);
        Bukkit.addRecipe((Recipe)asr);
        final ShapedRecipe stoner = new ShapedRecipe(new ItemBuilder2(Material.STONE, 8).build());
        stoner.shape(new String[] { "CCC", "CKC", "CCC" });
        stoner.setIngredient('C', Material.COBBLESTONE);
        stoner.setIngredient('K', Material.COAL);
        Bukkit.addRecipe((Recipe)stoner);
        final ShapedRecipe yeezysr = new ShapedRecipe(new ItemBuilder2(Material.DIAMOND_BOOTS, 1).setName("§e§lYeezys").setLore("Abgenutzt will diese Schuhe keiner anziehen!").build());
        yeezysr.shape(new String[] { "SSS", "SLS", "SSS" });
        yeezysr.setIngredient('S', Material.EMERALD);
        yeezysr.setIngredient('L', Material.LEATHER_BOOTS);
        Bukkit.addRecipe((Recipe)yeezysr);
        final ShapedRecipe colar = new ShapedRecipe(new ItemBuilder2(Material.POTION, 1).setName("§5Cola").setData(34).setLore("§fGibt dir einen Energieschub!").build());
        colar.shape(new String[] { "ZZZ", "ZWZ", "MMM" });
        colar.setIngredient('Z', Material.SUGAR);
        colar.setIngredient('M', Material.MAGMA_CREAM);
        colar.setIngredient('W', Material.WATER_BUCKET);
        Bukkit.addRecipe((Recipe)colar);
    }
}
