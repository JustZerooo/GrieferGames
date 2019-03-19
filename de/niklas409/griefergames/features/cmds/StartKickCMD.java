package de.niklas409.griefergames.features.cmds;

import java.io.IOException;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.HashMap;
import java.util.ArrayList;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class StartKickCMD implements CommandExecutor
{
    private Main plugin;
    public static int cd;
    public static int cdz;
    public static File SKTime;
    public static YamlConfiguration SK_cfg;
    public static File Banned;
    public static YamlConfiguration Banned_cfg;
    public static ArrayList<String> voting;
    public static ArrayList<String> Ja;
    public static ArrayList<String> Nein;
    public static HashMap<String, String> Name;
    
    static {
        StartKickCMD.SKTime = new File("plugins/GrieferGames/Data/StartKick.yml");
        StartKickCMD.SK_cfg = YamlConfiguration.loadConfiguration(StartKickCMD.SKTime);
        StartKickCMD.Banned = new File("plugins/GrieferGames/Data/StartKick/Banned.yml");
        StartKickCMD.Banned_cfg = YamlConfiguration.loadConfiguration(StartKickCMD.Banned);
        StartKickCMD.voting = new ArrayList<String>();
        StartKickCMD.Ja = new ArrayList<String>();
        StartKickCMD.Nein = new ArrayList<String>();
        StartKickCMD.Name = new HashMap<String, String>();
    }
    
    public StartKickCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("startkick").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final String name = p.getName();
            final String PrefixStartKick = this.plugin.getConfig().getString("StartKickPrefix").replace("&", "§");
            final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
            final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
            if (p.hasPermission("system.startkick.time.bypass")) {
                if (!StartKickCMD.voting.contains("true")) {
                    if (yPerk.getString(p.getUniqueId() + ".StartKick") != null) {
                        if (args.length >= 2) {
                            if (!args[0].equalsIgnoreCase(p.getName())) {
                                final String target = args[0];
                                final Player tar = Bukkit.getPlayer(target);
                                if (tar != null) {
                                    if (!tar.hasPermission("system.startkick.bypass")) {
                                        StartKickCMD.voting.add("true");
                                        StartKickCMD.Name.put("N", tar.getName());
                                        StartKickCMD.cdz = 31;
                                        String Message = "";
                                        for (int i = 1; i < args.length; ++i) {
                                            Message = String.valueOf(Message) + args[i] + " ";
                                        }
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                                        }
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§e§lAchtung §4§lStartKick §e§lAbstimmung!");
                                        Bukkit.broadcastMessage("");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eSoll der Spieler §a§l" + tar.getName() + " §erausgeworfen" + "\n" + " §ewerden? §a/ja §c/nein");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fErsteller: §a" + p.getName());
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fDauer: §430 Sekunden");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fBegründung: §3§l" + Message);
                                        final TextComponent JaTxt = new TextComponent();
                                        JaTxt.setText("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab. §a[Klick hier!]");
                                        JaTxt.setBold(Boolean.valueOf(true));
                                        JaTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Ja"));
                                        JaTxt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                        final TextComponent NeinTxt = new TextComponent();
                                        NeinTxt.setText("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab. " + "\n" + "§c[Klick hier!]");
                                        NeinTxt.setBold(Boolean.valueOf(true));
                                        NeinTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Nein"));
                                        NeinTxt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                        Bukkit.spigot().broadcast((BaseComponent)JaTxt);
                                        Bukkit.spigot().broadcast((BaseComponent)NeinTxt);
                                        StartKickCMD.cd = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                                            final /* synthetic */ StartKickCMD this$0;
                                            private final /* synthetic */ String val$PrefixStartKick;
                                            private final /* synthetic */ Player val$tar;
                                            
                                            StartKickCMD$1() {
                                                this.this$0 = this$0;
                                                super();
                                            }
                                            
                                            @Override
                                            public void run() {
                                                --StartKickCMD.cdz;
                                                if (StartKickCMD.cdz == 3) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 3 Sekunden!");
                                                }
                                                if (StartKickCMD.cdz == 2) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 2 Sekunden!");
                                                }
                                                if (StartKickCMD.cdz == 1) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in einer Sekunde!");
                                                }
                                                if (StartKickCMD.cdz == 0) {
                                                    StartKickCMD.voting.remove("true");
                                                    if (StartKickCMD.Ja.size() > StartKickCMD.Nein.size()) {
                                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                                            all.playSound(all.getLocation(), Sound.WITHER_HURT, 10.0f, 1.0f);
                                                        }
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(" ");
                                                        String players = "";
                                                        for (final String all2 : StartKickCMD.Ja) {
                                                            players = String.valueOf(players) + all2 + "§7, §a";
                                                        }
                                                        String players2 = "";
                                                        for (final String all3 : StartKickCMD.Nein) {
                                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                        }
                                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§c§l" + tar.getName() + " §c§lwurde bestraft und für §b§l5" + "\n" + " §b§lMinuten §c§lrausgeworfen!");
                                                        this.this$0.SetBanned(tar, 300);
                                                        final Date date = new Date(StartKickCMD.Banned_cfg.getLong((String)StartKickCMD.Name.get("N")));
                                                        final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                                        final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                                        tar.kickPlayer("§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
                                                    }
                                                    else {
                                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                                            all.playSound(all.getLocation(), Sound.WITHER_IDLE, 10.0f, 1.0f);
                                                        }
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(" ");
                                                        String players = "";
                                                        for (final String all2 : StartKickCMD.Ja) {
                                                            players = String.valueOf(players) + all2 + "§7, §a";
                                                        }
                                                        String players2 = "";
                                                        for (final String all3 : StartKickCMD.Nein) {
                                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                        }
                                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§a" + tar.getName() + " §awurde nicht bestraft!");
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDer Chat ist wieder freigegeben!");
                                                    StartKickCMD.Name.clear();
                                                    StartKickCMD.Ja.clear();
                                                    StartKickCMD.Nein.clear();
                                                    Bukkit.getScheduler().cancelTask(StartKickCMD.cd);
                                                    StartKickCMD.cdz = 31;
                                                }
                                            }
                                        }, 20L, 20L);
                                    }
                                    else {
                                        p.kickPlayer("§4§lStart§e§lKick\n\n             §cDu kannst diesen Spieler nicht §4§lStart§e§lKicken§c!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(PrefixStartKick) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(PrefixStartKick) + "§cDu kannst dich nicht selber §e§lkicken§c!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(PrefixStartKick) + "§cBenutze: §a/§4§lStart§e§lKick §a<Spieler> <Grund>");
                        }
                    }
                    else if (!p.hasPermission("system.perk.startkick.bypass")) {
                        p.sendMessage(String.valueOf(PrefixStartKick) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                    }
                    else if (args.length >= 2) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.startkick.bypass")) {
                                    StartKickCMD.voting.add("true");
                                    StartKickCMD.Name.put("N", tar.getName());
                                    StartKickCMD.cdz = 31;
                                    String Message = "";
                                    for (int i = 1; i < args.length; ++i) {
                                        Message = String.valueOf(Message) + args[i] + " ";
                                    }
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§e§lAchtung §4§lStartKick §e§lAbstimmung!");
                                    Bukkit.broadcastMessage("");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eSoll der Spieler §a§l" + tar.getName() + " §erausgeworfen" + "\n" + " §ewerden? §a/ja §c/nein");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fErsteller: §a" + p.getName());
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fDauer: §430 Sekunden");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fBegründung: §3§l" + Message);
                                    final TextComponent JaTxt = new TextComponent();
                                    JaTxt.setText("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab. §a[Klick hier!]");
                                    JaTxt.setBold(Boolean.valueOf(true));
                                    JaTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Ja"));
                                    JaTxt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                    final TextComponent NeinTxt = new TextComponent();
                                    NeinTxt.setText("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab. " + "\n" + "§c[Klick hier!]");
                                    NeinTxt.setBold(Boolean.valueOf(true));
                                    NeinTxt.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Nein"));
                                    NeinTxt.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                    Bukkit.spigot().broadcast((BaseComponent)JaTxt);
                                    Bukkit.spigot().broadcast((BaseComponent)NeinTxt);
                                    StartKickCMD.cd = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                                        final /* synthetic */ StartKickCMD this$0;
                                        private final /* synthetic */ String val$PrefixStartKick;
                                        private final /* synthetic */ Player val$tar;
                                        
                                        StartKickCMD$2() {
                                            this.this$0 = this$0;
                                            super();
                                        }
                                        
                                        @Override
                                        public void run() {
                                            --StartKickCMD.cdz;
                                            if (StartKickCMD.cdz == 3) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 3 Sekunden!");
                                            }
                                            if (StartKickCMD.cdz == 2) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 2 Sekunden!");
                                            }
                                            if (StartKickCMD.cdz == 1) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in einer Sekunde!");
                                            }
                                            if (StartKickCMD.cdz == 0) {
                                                StartKickCMD.voting.remove("true");
                                                if (StartKickCMD.Ja.size() > StartKickCMD.Nein.size()) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.WITHER_HURT, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(" ");
                                                    String players = "";
                                                    for (final String all2 : StartKickCMD.Ja) {
                                                        players = String.valueOf(players) + all2 + "§7, §a";
                                                    }
                                                    String players2 = "";
                                                    for (final String all3 : StartKickCMD.Nein) {
                                                        players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                    }
                                                    Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§c§l" + tar.getName() + " §c§lwurde bestraft und für §b§l5" + "\n" + " §b§lMinuten §c§lrausgeworfen!");
                                                    this.this$0.SetBanned(tar, 300);
                                                    final Date date = new Date(StartKickCMD.Banned_cfg.getLong((String)StartKickCMD.Name.get("N")));
                                                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                                    tar.kickPlayer("§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
                                                }
                                                else {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(" ");
                                                    String players = "";
                                                    for (final String all2 : StartKickCMD.Ja) {
                                                        players = String.valueOf(players) + all2 + "§7, §a";
                                                    }
                                                    String players2 = "";
                                                    for (final String all3 : StartKickCMD.Nein) {
                                                        players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                    }
                                                    Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§a" + tar.getName() + " §awurde nicht bestraft!");
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDer Chat ist wieder freigegeben!");
                                                StartKickCMD.Name.clear();
                                                StartKickCMD.Ja.clear();
                                                StartKickCMD.Nein.clear();
                                                Bukkit.getScheduler().cancelTask(StartKickCMD.cd);
                                                StartKickCMD.cdz = 31;
                                            }
                                        }
                                    }, 20L, 20L);
                                }
                                else {
                                    p.kickPlayer("§4§lStart§e§lKick\n\n             §cDu kannst diesen Spieler nicht §4§lStart§e§lKicken§c!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(PrefixStartKick) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(PrefixStartKick) + "§cDu kannst dich nicht selber §e§lkicken§c!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(PrefixStartKick) + "§cBenutze: §a/§4§lStart§e§lKick §a<Spieler> <Grund>");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(PrefixStartKick) + "§cEs läuft bereits eine Abstimmung!");
                }
            }
            else if (StartKickCMD.SK_cfg.get(name) == null) {
                if (!StartKickCMD.voting.contains("true")) {
                    if (yPerk.getString(p.getUniqueId() + ".StartKick") != null) {
                        if (args.length >= 2) {
                            if (!args[0].equalsIgnoreCase(p.getName())) {
                                final String target = args[0];
                                final Player tar = Bukkit.getPlayer(target);
                                if (tar != null) {
                                    if (!tar.hasPermission("system.startkick.bypass")) {
                                        StartKickCMD.Name.put("N", tar.getName());
                                        int time = 0;
                                        time = Integer.parseInt(this.plugin.getConfig().getString("StartKickWaitTimeInMinutes"));
                                        this.SetSkTime(p, time * 60);
                                        StartKickCMD.voting.add("true");
                                        StartKickCMD.cdz = 31;
                                        String Message2 = "";
                                        for (int j = 1; j < args.length; ++j) {
                                            Message2 = String.valueOf(Message2) + args[j] + " ";
                                        }
                                        for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                            all2.playSound(all2.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                                        }
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§e§lAchtung §4§lStartKick §e§lAbstimmung!");
                                        Bukkit.broadcastMessage("");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eSoll der Spieler §a§l" + tar.getName() + " §erausgeworfen" + "\n" + " §ewerden? §a/ja §c/nein");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fErsteller: §a" + p.getName());
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fDauer: §430 Sekunden");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fBegründung: §3§l" + Message2);
                                        final TextComponent JaTxt2 = new TextComponent();
                                        JaTxt2.setText("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab. §a[Klick hier!]");
                                        JaTxt2.setBold(Boolean.valueOf(true));
                                        JaTxt2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Ja"));
                                        JaTxt2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                        final TextComponent NeinTxt2 = new TextComponent();
                                        NeinTxt2.setText("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab. " + "\n" + "§c[Klick hier!]");
                                        NeinTxt2.setBold(Boolean.valueOf(true));
                                        NeinTxt2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Nein"));
                                        NeinTxt2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                        Bukkit.spigot().broadcast((BaseComponent)JaTxt2);
                                        Bukkit.spigot().broadcast((BaseComponent)NeinTxt2);
                                        StartKickCMD.cd = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                                            final /* synthetic */ StartKickCMD this$0;
                                            private final /* synthetic */ String val$PrefixStartKick;
                                            private final /* synthetic */ Player val$tar;
                                            
                                            StartKickCMD$3() {
                                                this.this$0 = this$0;
                                                super();
                                            }
                                            
                                            @Override
                                            public void run() {
                                                --StartKickCMD.cdz;
                                                if (StartKickCMD.cdz == 3) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 3 Sekunden!");
                                                }
                                                if (StartKickCMD.cdz == 2) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 2 Sekunden!");
                                                }
                                                if (StartKickCMD.cdz == 1) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in einer Sekunde!");
                                                }
                                                if (StartKickCMD.cdz == 0) {
                                                    StartKickCMD.voting.remove("true");
                                                    if (StartKickCMD.Ja.size() > StartKickCMD.Nein.size()) {
                                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                                            all.playSound(all.getLocation(), Sound.WITHER_HURT, 10.0f, 1.0f);
                                                        }
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(" ");
                                                        String players = "";
                                                        for (final String all2 : StartKickCMD.Ja) {
                                                            players = String.valueOf(players) + all2 + "§7, §a";
                                                        }
                                                        String players2 = "";
                                                        for (final String all3 : StartKickCMD.Nein) {
                                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                        }
                                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§c§l" + tar.getName() + " §c§lwurde bestraft und für §b§l5" + "\n" + " §b§lMinuten §c§lrausgeworfen!");
                                                        this.this$0.SetBanned(tar, 300);
                                                        final Date date = new Date(StartKickCMD.Banned_cfg.getLong((String)StartKickCMD.Name.get("N")));
                                                        final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                                        final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                                        tar.kickPlayer("§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
                                                    }
                                                    else {
                                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                                            all.playSound(all.getLocation(), Sound.WITHER_IDLE, 10.0f, 1.0f);
                                                        }
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(" ");
                                                        String players = "";
                                                        for (final String all2 : StartKickCMD.Ja) {
                                                            players = String.valueOf(players) + all2 + "§7, §a";
                                                        }
                                                        String players2 = "";
                                                        for (final String all3 : StartKickCMD.Nein) {
                                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                        }
                                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                        Bukkit.broadcastMessage(" ");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§a" + tar.getName() + " §awurde nicht bestraft!");
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDer Chat ist wieder freigegeben!");
                                                    StartKickCMD.Name.clear();
                                                    StartKickCMD.Ja.clear();
                                                    StartKickCMD.Nein.clear();
                                                    Bukkit.getScheduler().cancelTask(StartKickCMD.cd);
                                                    StartKickCMD.cdz = 31;
                                                }
                                            }
                                        }, 20L, 20L);
                                    }
                                    else {
                                        p.kickPlayer("§4§lStart§e§lKick\n\n             §cDu kannst diesen Spieler nicht §4§lStart§e§lKicken§c!");
                                    }
                                }
                                else {
                                    p.sendMessage(String.valueOf(PrefixStartKick) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(PrefixStartKick) + "§cDu kannst dich nicht selber §e§lkicken§c!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(PrefixStartKick) + "§cBenutze: §a/§4§lStart§e§lKick §a<Spieler> <Grund>");
                        }
                    }
                    else if (!p.hasPermission("system.perk.startkick.bypass")) {
                        p.sendMessage(String.valueOf(PrefixStartKick) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                    }
                    else if (args.length >= 2) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.startkick.bypass")) {
                                    StartKickCMD.Name.put("N", tar.getName());
                                    int time = 0;
                                    time = Integer.parseInt(this.plugin.getConfig().getString("StartKickWaitTimeInMinutes"));
                                    this.SetSkTime(p, time * 60);
                                    StartKickCMD.voting.add("true");
                                    StartKickCMD.cdz = 31;
                                    String Message2 = "";
                                    for (int j = 1; j < args.length; ++j) {
                                        Message2 = String.valueOf(Message2) + args[j] + " ";
                                    }
                                    for (final Player all2 : Bukkit.getOnlinePlayers()) {
                                        all2.playSound(all2.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§e§lAchtung §4§lStartKick §e§lAbstimmung!");
                                    Bukkit.broadcastMessage("");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eSoll der Spieler §a§l" + tar.getName() + " §erausgeworfen" + "\n" + " §ewerden? §a/ja §c/nein");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fErsteller: §a" + p.getName());
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fDauer: §430 Sekunden");
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§fBegründung: §3§l" + Message2);
                                    final TextComponent JaTxt2 = new TextComponent();
                                    JaTxt2.setText("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab. §a[Klick hier!]");
                                    JaTxt2.setBold(Boolean.valueOf(true));
                                    JaTxt2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Ja"));
                                    JaTxt2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §afür §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                    final TextComponent NeinTxt2 = new TextComponent();
                                    NeinTxt2.setText("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab. " + "\n" + "§c[Klick hier!]");
                                    NeinTxt2.setBold(Boolean.valueOf(true));
                                    NeinTxt2.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Nein"));
                                    NeinTxt2.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §cgegen §fden Rauswurf von §a" + tar.getName() + " §fab.").create()));
                                    Bukkit.spigot().broadcast((BaseComponent)JaTxt2);
                                    Bukkit.spigot().broadcast((BaseComponent)NeinTxt2);
                                    StartKickCMD.cd = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                                        final /* synthetic */ StartKickCMD this$0;
                                        private final /* synthetic */ String val$PrefixStartKick;
                                        private final /* synthetic */ Player val$tar;
                                        
                                        StartKickCMD$4() {
                                            this.this$0 = this$0;
                                            super();
                                        }
                                        
                                        @Override
                                        public void run() {
                                            --StartKickCMD.cdz;
                                            if (StartKickCMD.cdz == 3) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 3 Sekunden!");
                                            }
                                            if (StartKickCMD.cdz == 2) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in 2 Sekunden!");
                                            }
                                            if (StartKickCMD.cdz == 1) {
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDie Abstimmung den Spieler §b" + tar.getName() + " §azu" + "\n" + " §abestrafen endet in einer Sekunde!");
                                            }
                                            if (StartKickCMD.cdz == 0) {
                                                StartKickCMD.voting.remove("true");
                                                if (StartKickCMD.Ja.size() > StartKickCMD.Nein.size()) {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.WITHER_HURT, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(" ");
                                                    String players = "";
                                                    for (final String all2 : StartKickCMD.Ja) {
                                                        players = String.valueOf(players) + all2 + "§7, §a";
                                                    }
                                                    String players2 = "";
                                                    for (final String all3 : StartKickCMD.Nein) {
                                                        players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                    }
                                                    Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§c§l" + tar.getName() + " §c§lwurde bestraft und für §b§l5" + "\n" + " §b§lMinuten §c§lrausgeworfen!");
                                                    this.this$0.SetBanned(tar, 300);
                                                    final Date date = new Date(StartKickCMD.Banned_cfg.getLong((String)StartKickCMD.Name.get("N")));
                                                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                                    tar.kickPlayer("§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
                                                }
                                                else {
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 10.0f, 1.0f);
                                                    }
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(" ");
                                                    String players = "";
                                                    for (final String all2 : StartKickCMD.Ja) {
                                                        players = String.valueOf(players) + all2 + "§7, §a";
                                                    }
                                                    String players2 = "";
                                                    for (final String all3 : StartKickCMD.Nein) {
                                                        players2 = String.valueOf(players2) + all3 + "§7, §c";
                                                    }
                                                    Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                                    Bukkit.broadcastMessage(" ");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§cEs wird die Mehrheit benötigt ...");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§eDie Abstimmung " + tar.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§a" + tar.getName() + " §awurde nicht bestraft!");
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(PrefixStartKick) + "§aDer Chat ist wieder freigegeben!");
                                                StartKickCMD.Name.clear();
                                                StartKickCMD.Ja.clear();
                                                StartKickCMD.Nein.clear();
                                                Bukkit.getScheduler().cancelTask(StartKickCMD.cd);
                                                StartKickCMD.cdz = 31;
                                            }
                                        }
                                    }, 20L, 20L);
                                }
                                else {
                                    p.kickPlayer("§4§lStart§e§lKick\n\n             §cDu kannst diesen Spieler nicht §4§lStart§e§lKicken§c!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(PrefixStartKick) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(PrefixStartKick) + "§cDu kannst dich nicht selber §e§lkicken§c!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(PrefixStartKick) + "§cBenutze: §a/§4§lStart§e§lKick §a<Spieler> <Grund>");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(PrefixStartKick) + "§cEs läuft bereits eine Abstimmung!");
                }
            }
            else if (StartKickCMD.SK_cfg.getLong(name) < System.currentTimeMillis()) {
                StartKickCMD.SK_cfg.set(name, (Object)null);
                p.sendMessage(String.valueOf(PrefixStartKick) + "§eDas System musste sich erstmal updaten!");
                p.sendMessage(String.valueOf(PrefixStartKick) + "§cGib den Command bitte nocheinmal ein!");
                try {
                    StartKickCMD.SK_cfg.save(StartKickCMD.SKTime);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                final Date date = new Date(StartKickCMD.SK_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                p.sendMessage(String.valueOf(PrefixStartKick) + "§c§lDu kannst diesen Befehl erst wieder am" + "\n" + " §a§l" + mm_dd_yyyy + " §c§lum §a§l" + hour_min + " §c§lUhr benutzen.");
            }
        }
        else {
            final String PrefixStartKick2 = this.plugin.getConfig().getString("StartKickPrefix").replace("&", "§");
            if (!StartKickCMD.voting.contains("true")) {
                if (args.length >= 2) {
                    final String target2 = args[0];
                    final Player tar2 = Bukkit.getPlayer(target2);
                    if (tar2 != null) {
                        StartKickCMD.Name.put("N", tar2.getName());
                        StartKickCMD.voting.add("true");
                        StartKickCMD.cdz = 31;
                        String Message3 = "";
                        for (int k = 1; k < args.length; ++k) {
                            Message3 = String.valueOf(Message3) + args[k] + " ";
                        }
                        for (final Player all3 : Bukkit.getOnlinePlayers()) {
                            all3.playSound(all3.getLocation(), Sound.ENDERDRAGON_GROWL, 10.0f, 1.0f);
                        }
                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§e§lAchtung §4§lStartKick §e§lAbstimmung!");
                        Bukkit.broadcastMessage("");
                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§eSoll der Spieler §a§l" + tar2.getName() + " §erausgeworfen" + "\n" + " §ewerden? §a/ja §c/nein");
                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§fErsteller: §4§lConsole");
                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§fDauer: §430 Sekunden");
                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§fBegründung: §3§l" + Message3);
                        final TextComponent JaTxt3 = new TextComponent();
                        JaTxt3.setText("§fStimme §afür §fden Rauswurf von §a" + tar2.getName() + " §fab. §a[Klick hier!]");
                        JaTxt3.setBold(Boolean.valueOf(true));
                        JaTxt3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Ja"));
                        JaTxt3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §afür §fden Rauswurf von §a" + tar2.getName() + " §fab.").create()));
                        final TextComponent NeinTxt3 = new TextComponent();
                        NeinTxt3.setText("§fStimme §cgegen §fden Rauswurf von §a" + tar2.getName() + " §fab. " + "\n" + "§c[Klick hier!]");
                        NeinTxt3.setBold(Boolean.valueOf(true));
                        NeinTxt3.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/Nein"));
                        NeinTxt3.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§fStimme §cgegen §fden Rauswurf von §a" + tar2.getName() + " §fab.").create()));
                        Bukkit.spigot().broadcast((BaseComponent)JaTxt3);
                        Bukkit.spigot().broadcast((BaseComponent)NeinTxt3);
                        StartKickCMD.cd = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ StartKickCMD this$0;
                            private final /* synthetic */ String val$PrefixStartKick;
                            private final /* synthetic */ Player val$tar;
                            
                            StartKickCMD$5() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                --StartKickCMD.cdz;
                                if (StartKickCMD.cdz == 3) {
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§aDie Abstimmung den Spieler §b" + tar2.getName() + " §azu" + "\n" + " §abestrafen endet in 3 Sekunden!");
                                }
                                if (StartKickCMD.cdz == 2) {
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§aDie Abstimmung den Spieler §b" + tar2.getName() + " §azu" + "\n" + " §abestrafen endet in 2 Sekunden!");
                                }
                                if (StartKickCMD.cdz == 1) {
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.NOTE_BASS, 10.0f, 1.0f);
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§aDie Abstimmung den Spieler §b" + tar2.getName() + " §azu" + "\n" + " §abestrafen endet in einer Sekunde!");
                                }
                                if (StartKickCMD.cdz == 0) {
                                    StartKickCMD.voting.remove("true");
                                    if (StartKickCMD.Ja.size() > StartKickCMD.Nein.size()) {
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            all.playSound(all.getLocation(), Sound.WITHER_HURT, 10.0f, 1.0f);
                                        }
                                        Bukkit.broadcastMessage(" ");
                                        Bukkit.broadcastMessage(" ");
                                        String players = "";
                                        for (final String all2 : StartKickCMD.Ja) {
                                            players = String.valueOf(players) + all2 + "§7, §a";
                                        }
                                        String players2 = "";
                                        for (final String all3 : StartKickCMD.Nein) {
                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                        }
                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                        Bukkit.broadcastMessage(" ");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§cEs wird die Mehrheit benötigt ...");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§eDie Abstimmung " + tar2.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§c§l" + tar2.getName() + " §c§lwurde bestraft und für §b§l5" + "\n" + " §b§lMinuten §c§lrausgeworfen!");
                                        this.this$0.SetBanned(tar2, 300);
                                        final Date date = new Date(StartKickCMD.Banned_cfg.getLong((String)StartKickCMD.Name.get("N")));
                                        final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                        final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                                        tar2.kickPlayer("§4§lStart§e§lKick\n\n     §cDu wurdest von der Community\n §cfür §e5 Minuten §causgeschlossen!\n\n§eVersuch es nochmal am §a" + mm_dd_yyyy + " §eum §a" + hour_min + " §eUhr§7!");
                                    }
                                    else {
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            all.playSound(all.getLocation(), Sound.WITHER_IDLE, 10.0f, 1.0f);
                                        }
                                        Bukkit.broadcastMessage(" ");
                                        Bukkit.broadcastMessage(" ");
                                        String players = "";
                                        for (final String all2 : StartKickCMD.Ja) {
                                            players = String.valueOf(players) + all2 + "§7, §a";
                                        }
                                        String players2 = "";
                                        for (final String all3 : StartKickCMD.Nein) {
                                            players2 = String.valueOf(players2) + all3 + "§7, §c";
                                        }
                                        Bukkit.broadcastMessage("§a" + players + "\n" + "§c" + players2);
                                        Bukkit.broadcastMessage(" ");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§cEs wird die Mehrheit benötigt ...");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§eDie Abstimmung " + tar2.getName() + " §eging §a" + StartKickCMD.Ja.size() + " §ezu §c" + StartKickCMD.Nein.size() + " §eaus!");
                                        Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§a" + tar2.getName() + " §awurde nicht bestraft!");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(PrefixStartKick2) + "§aDer Chat ist wieder freigegeben!");
                                    StartKickCMD.Name.clear();
                                    StartKickCMD.Ja.clear();
                                    StartKickCMD.Nein.clear();
                                    Bukkit.getScheduler().cancelTask(StartKickCMD.cd);
                                    StartKickCMD.cdz = 31;
                                }
                            }
                        }, 20L, 20L);
                    }
                    else {
                        Bukkit.getConsoleSender().sendMessage(String.valueOf(PrefixStartKick2) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                    }
                }
                else {
                    Bukkit.getConsoleSender().sendMessage(String.valueOf(PrefixStartKick2) + "§cBenutze: §a/§4§lStart§e§lKick §a<Spieler> <Grund>");
                }
            }
            else {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(PrefixStartKick2) + "§cEs läuft bereits eine Abstimmung!");
            }
        }
        return true;
    }
    
    public void SetSkTime(final Player p, final int time) {
        StartKickCMD.SK_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            StartKickCMD.SK_cfg.save(StartKickCMD.SKTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void SetBanned(final Player p, final int MutepTime) {
        StartKickCMD.Banned_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + MutepTime * 1000));
        try {
            StartKickCMD.Banned_cfg.save(StartKickCMD.Banned);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
