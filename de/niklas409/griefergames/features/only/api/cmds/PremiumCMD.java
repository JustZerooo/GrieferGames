package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.event.EventHandler;
import ru.tehkode.permissions.bukkit.PermissionsEx;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.command.ConsoleCommandSender;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.event.Listener;
import org.bukkit.command.CommandExecutor;

public class PremiumCMD implements CommandExecutor, Listener
{
    private Main plugin;
    public static File PremTime;
    public static YamlConfiguration yPremTime;
    
    static {
        PremiumCMD.PremTime = new File("plugins/GrieferGames/Data/Premium.yml");
        PremiumCMD.yPremTime = YamlConfiguration.loadConfiguration(PremiumCMD.PremTime);
    }
    
    public PremiumCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("premium").setExecutor((CommandExecutor)this);
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.premium")) {
                if (p.hasPermission("system.premium.time.bypass")) {
                    if (args.length == 1) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.premium.bypass")) {
                                    final Integer PremiumLaengeInTage = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage")) * 24 * 60 * 60;
                                    final Integer PremiumLaengeInTageSek = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage")) * 24 * 60 * 60;
                                    final String PremiumRangName = this.plugin.getConfig().getString("PremiumRangName");
                                    p.sendMessage("§fDu hast " + tar.getDisplayName() + " §fden §6" + PremiumRangName + "-Rang §faktiviert!");
                                    tar.kickPlayer("§fDu hast von " + p.getDisplayName() + " §fden §6" + PremiumRangName + "-Rang §ffür " + PremiumLaengeInTage + " Tage erhalten!");
                                    this.SetPremRang(tar, PremiumLaengeInTageSek);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §chat bereits einen besseren Rang!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst dir nicht selber den Premium Rang geben!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Premium <Spieler>");
                    }
                }
                else if (PremiumCMD.yPremTime.getString(p.getUniqueId().toString()) == null) {
                    if (args.length == 1) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.premium.bypass")) {
                                    final Integer PremiumLaengeInTage = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage")) * 24 * 60 * 60;
                                    final Integer PremiumLaengeInTageSek = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage")) * 24 * 60 * 60;
                                    final Integer PremiumWaitTimeInSek = Integer.valueOf(this.plugin.getConfig().getString("PremiumWaitTimeInMinutes")) * 60;
                                    final String PremiumRangName2 = this.plugin.getConfig().getString("PremiumRangName");
                                    this.SetPremTimeWait(p, PremiumWaitTimeInSek);
                                    p.sendMessage("§fDu hast " + tar.getDisplayName() + " §fden §6" + PremiumRangName2 + "-Rang §faktiviert!");
                                    tar.kickPlayer("§fDu hast von " + p.getDisplayName() + " §fden §6" + PremiumRangName2 + "-Rang §ffür " + PremiumLaengeInTage + " Tage erhalten!");
                                    this.SetPremRang(tar, PremiumLaengeInTageSek);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §chat bereits einen besseren Rang!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst dir nicht selber den Premium Rang geben!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Premium <Spieler>");
                    }
                }
                else if (PremiumCMD.yPremTime.getLong(p.getUniqueId().toString()) < System.currentTimeMillis()) {
                    PremiumCMD.yPremTime.set(p.getUniqueId().toString(), (Object)null);
                    p.sendMessage(String.valueOf(Prefix) + "§eDas System musste sich erstmal updaten!");
                    p.sendMessage(String.valueOf(Prefix) + "§cGib den Command bitte nocheinmal ein!");
                    try {
                        PremiumCMD.yPremTime.save(PremiumCMD.PremTime);
                    }
                    catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else {
                    final Date date = new Date(PremiumCMD.yPremTime.getLong(p.getUniqueId().toString()));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                    p.sendMessage(String.valueOf(Prefix) + "§fDu kannst erst am §e" + mm_dd_yyyy + " um " + hour_min + " §fwieder den §6Premium-Rang §fvergeben.");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
            if (args.length == 1) {
                if (!args[0].equalsIgnoreCase(p2.getName())) {
                    final String target = args[0];
                    final Player tar = Bukkit.getPlayer(target);
                    if (tar != null) {
                        if (!tar.hasPermission("system.premium.bypass")) {
                            final Integer PremiumLaengeInTage = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage"));
                            final Integer PremiumLaengeInTageSek = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage")) * 24 * 60 * 60;
                            final String PremiumRangName = this.plugin.getConfig().getString("PremiumRangName");
                            p2.sendMessage("§fDu hast " + tar.getDisplayName() + " §fden §6" + PremiumRangName + "-Rang §faktiviert!");
                            tar.kickPlayer("§fDu hast von §4§l" + p2.getName() + " §fden §6" + PremiumRangName + "-Rang §ffür " + PremiumLaengeInTage + " Tage erhalten!");
                            this.SetPremRang(tar, PremiumLaengeInTageSek);
                        }
                        else {
                            p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §chat bereits einen besseren Rang!");
                        }
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§cDu kannst dir nicht selber den Premium Rang geben!");
                }
            }
            else {
                p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Premium <Spieler>");
            }
        }
        return true;
    }
    
    @EventHandler
    public void onRegister(final PlayerLoginEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final Player p = e.getPlayer();
        if (PremiumCMD.yPremTime.getString("PremRang." + p.getUniqueId() + ".Laenge") != null && PremiumCMD.yPremTime.getLong("PremRang." + p.getUniqueId() + ".Laenge") < System.currentTimeMillis()) {
            final String RD = PremiumCMD.yPremTime.getString("PremRang." + p.getUniqueId() + ".RD");
            PermissionsEx.getUser(p).setGroups(new String[] { RD });
            PremiumCMD.yPremTime.set("PremRang." + p.getUniqueId() + ".Laenge", (Object)null);
            PremiumCMD.yPremTime.set("PremRang." + p.getUniqueId() + ".RD", (Object)null);
            try {
                PremiumCMD.yPremTime.save(PremiumCMD.PremTime);
            }
            catch (IOException e2) {
                e2.printStackTrace();
            }
            final Integer PremiumLaengeInTage = Integer.valueOf(this.plugin.getConfig().getString("PremiumLaengeInTage"));
            final String PremiumRangName = this.plugin.getConfig().getString("PremiumRangName");
            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                final /* synthetic */ PremiumCMD this$0;
                private final /* synthetic */ Player val$p;
                private final /* synthetic */ String val$Prefix;
                private final /* synthetic */ Integer val$PremiumLaengeInTage;
                private final /* synthetic */ String val$PremiumRangName;
                private final /* synthetic */ String val$RD;
                
                PremiumCMD$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public void run() {
                    p.sendMessage(String.valueOf(Prefix) + "§fDeine " + PremiumLaengeInTage + " Tage mit dem §6" + PremiumRangName + "-Rang §fsind vorbei, du hast nun wieder den §a" + RD + "-Rang.");
                }
            }, 20L);
        }
    }
    
    public void SetPremTimeWait(final Player p, final int time) {
        PremiumCMD.yPremTime.set(p.getUniqueId().toString(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            PremiumCMD.yPremTime.save(PremiumCMD.PremTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void SetPremRang(final Player p, final int time) {
        final String JetzigeGruppe = PermissionsEx.getUser(p).getGroups()[0].getName();
        PremiumCMD.yPremTime.set("PremRang." + p.getUniqueId() + ".RD", (Object)JetzigeGruppe);
        PremiumCMD.yPremTime.set("PremRang." + p.getUniqueId() + ".Laenge", (Object)(System.currentTimeMillis() + time * 1000));
        final String PremiumRangName = this.plugin.getConfig().getString("PremiumRangName");
        PermissionsEx.getUser(p).setGroups(new String[] { PremiumRangName });
        try {
            PremiumCMD.yPremTime.save(PremiumCMD.PremTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
