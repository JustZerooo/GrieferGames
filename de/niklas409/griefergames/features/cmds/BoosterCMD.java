package de.niklas409.griefergames.features.cmds;

import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import org.bukkit.Sound;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.main.Booster;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class BoosterCMD implements CommandExecutor
{
    private Main plugin;
    public static Integer DropB;
    public static Integer XPB;
    public static Integer FlyB;
    public static Integer MobB;
    public static Integer BreakB;
    
    static {
        BoosterCMD.DropB = 0;
        BoosterCMD.XPB = 0;
        BoosterCMD.FlyB = 0;
        BoosterCMD.MobB = 0;
        BoosterCMD.BreakB = 0;
    }
    
    public BoosterCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("booster").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 0) {
                p.sendMessage(String.valueOf(Prefix) + "§fFolgende §b§lBooster §fsind hier aktiv:");
                if (BoosterCMD.DropB != 0) {
                    p.sendMessage(String.valueOf(Prefix) + "§eDrop-Booster §fMultiplikator: §a" + BoosterCMD.DropB + "x");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§eDrop-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.XPB != 0) {
                    p.sendMessage(String.valueOf(Prefix) + "§eErfahrungsbooster §fMultiplikator: §a" + BoosterCMD.XPB + "x");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§eErfahrungsbooster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.FlyB != 0) {
                    p.sendMessage(String.valueOf(Prefix) + "§eFly-Booster §fMultiplikator: §a" + BoosterCMD.FlyB + "x");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§eFly-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.MobB != 0) {
                    p.sendMessage(String.valueOf(Prefix) + "§eMob-Booster §fMultiplikator: §a" + BoosterCMD.MobB + "x");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§eMob-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.BreakB != 0) {
                    p.sendMessage(String.valueOf(Prefix) + "§eBreak-Booster §fMultiplikator: §a" + BoosterCMD.BreakB + "x");
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§eBreak-Booster §fMultiplikator: §caus.");
                }
                p.sendMessage(String.valueOf(Prefix) + "§aMit §b§lbooster help §abekommst du weitere Informationen zu den §b§lBoostern§a!");
            }
            else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast noch §c§l" + Booster.getBooster(p) + " Booster §bübrig!");
                    p.sendMessage(String.valueOf(Prefix) + "§aAktiviere einen §b§lBooster für den Server §amit:");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Erfahrung");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Drops");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Fliegen");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Mobs");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Break");
                }
                else if (args[0].equalsIgnoreCase("Erfahrung")) {
                    if (Booster.getBooster(p) >= 1) {
                        if (BoosterCMD.XPB != 3) {
                            Booster.RemoveBooster(p, 1);
                            ++BoosterCMD.XPB;
                            for (Integer i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage(String.valueOf(Prefix) + p.getDisplayName() + " §ahat für die Community den §b§lErfahrungs-Booster für §e§l30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.XPB + "x");
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ BoosterCMD this$0;
                                private final /* synthetic */ String val$Prefix;
                                
                                BoosterCMD$1() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    --BoosterCMD.XPB;
                                    for (Integer i = 0; i < 10; ++i) {
                                        Bukkit.broadcastMessage(" ");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eErfahrungs-Booster §cist abgelaufen!");
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.XPB + "x");
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                    }
                                }
                            }, 36000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lErfahrungs-Boostern §cist bereits erreicht!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§bLeider hast du §c§lkeine Booster §bmehr übrig! Kaufe dir welche im §eShop§b!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Drops")) {
                    if (Booster.getBooster(p) >= 1) {
                        if (BoosterCMD.DropB != 3) {
                            Booster.RemoveBooster(p, 1);
                            ++BoosterCMD.DropB;
                            for (Integer i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage(String.valueOf(Prefix) + p.getDisplayName() + " §ahat für die Community den §b§lDrop-Booster für §e§l30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.DropB + "x");
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ BoosterCMD this$0;
                                private final /* synthetic */ String val$Prefix;
                                
                                BoosterCMD$2() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    --BoosterCMD.DropB;
                                    for (Integer i = 0; i < 10; ++i) {
                                        Bukkit.broadcastMessage(" ");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eDrop-Booster §cist abgelaufen!");
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.DropB + "x");
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                    }
                                }
                            }, 36000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lDrop-Boostern §cist bereits erreicht!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§bLeider hast du §c§lkeine Booster §bmehr übrig! Kaufe dir welche im §eShop§b!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Fliegen")) {
                    if (Booster.getBooster(p) >= 1) {
                        if (BoosterCMD.FlyB == 0) {
                            Booster.RemoveBooster(p, 1);
                            ++BoosterCMD.FlyB;
                            for (Integer i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage(String.valueOf(Prefix) + p.getDisplayName() + " §ahat für die Community den §b§lFly-Booster für §e§l10 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.FlyB + "x");
                            Bukkit.broadcastMessage("§aDu kannst jetzt wegen dem §b§lServer Booster §avon " + p.getDisplayName() + " §afliegen!");
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                                all.setAllowFlight(true);
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ BoosterCMD this$0;
                                private final /* synthetic */ String val$Prefix;
                                
                                BoosterCMD$3() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    for (Integer i = 0; i < 10; ++i) {
                                        Bukkit.broadcastMessage(" ");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cDer Server Booster ist beendet. Dein Flugmodus wird deaktiviert...");
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 1.0f);
                                    }
                                    Bukkit.getScheduler().runTaskLater((Plugin)this.this$0.plugin, (Runnable)new Runnable() {
                                        final /* synthetic */ BoosterCMD$3 this$1;
                                        private final /* synthetic */ String val$Prefix;
                                        
                                        BoosterCMD$3$1() {
                                            this.this$1 = this$1;
                                            super();
                                        }
                                        
                                        @Override
                                        public void run() {
                                            for (Integer i = 0; i < 10; ++i) {
                                                Bukkit.broadcastMessage(" ");
                                            }
                                            Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cDer Server Booster ist beendet. Dein Flugmodus wird deaktiviert...");
                                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 1.0f);
                                            }
                                            Bukkit.getScheduler().runTaskLater((Plugin)this.this$1.this$0.plugin, (Runnable)new Runnable() {
                                                final /* synthetic */ BoosterCMD$3$1 this$2;
                                                private final /* synthetic */ String val$Prefix;
                                                
                                                BoosterCMD$3$1$1() {
                                                    this.this$2 = this$2;
                                                    super();
                                                }
                                                
                                                @Override
                                                public void run() {
                                                    --BoosterCMD.FlyB;
                                                    for (Integer i = 0; i < 10; ++i) {
                                                        Bukkit.broadcastMessage(" ");
                                                    }
                                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eFly-Booster §cist abgelaufen!");
                                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.FlyB + "x");
                                                    Bukkit.broadcastMessage("§cDer Server Booster ist beendet. Dein Flugmodus wurde deaktiviert.");
                                                    Bukkit.broadcastMessage("§a§lFly-Booster §cist jetzt wieder deaktiviert!");
                                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                                        all.setAllowFlight(false);
                                                    }
                                                }
                                            }, 80L);
                                        }
                                    }, 80L);
                                }
                                
                                static /* synthetic */ BoosterCMD access$0(final BoosterCMD$3 runnable) {
                                    return runnable.this$0;
                                }
                            }, 12000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cEs ist bereits ein §b§lFly-Booster §caktiv!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§bLeider hast du §c§lkeine Booster §bmehr übrig! Kaufe dir welche im §eShop§b!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Mobs")) {
                    if (Booster.getBooster(p) >= 1) {
                        if (BoosterCMD.MobB != 3) {
                            Booster.RemoveBooster(p, 1);
                            ++BoosterCMD.MobB;
                            for (Integer i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage(String.valueOf(Prefix) + p.getDisplayName() + " §ahat für die Community den §b§lMob-Booster für §e§l30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.MobB + "x");
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ BoosterCMD this$0;
                                private final /* synthetic */ String val$Prefix;
                                
                                BoosterCMD$4() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    --BoosterCMD.MobB;
                                    for (Integer i = 0; i < 10; ++i) {
                                        Bukkit.broadcastMessage(" ");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eMob-Booster §cist abgelaufen!");
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.MobB + "x");
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                    }
                                }
                            }, 36000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lMob-Boostern §cist bereits erreicht!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§bLeider hast du §c§lkeine Booster §bmehr übrig! Kaufe dir welche im §eShop§b!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Break")) {
                    if (Booster.getBooster(p) >= 1) {
                        if (BoosterCMD.BreakB != 1) {
                            Booster.RemoveBooster(p, 1);
                            ++BoosterCMD.BreakB;
                            for (Integer i = 0; i < 100; ++i) {
                                Bukkit.broadcastMessage(" ");
                            }
                            Bukkit.broadcastMessage(String.valueOf(Prefix) + p.getDisplayName() + " §ahat für die Community den §b§lBreakB-Booster für §e§l30 Minuten §aaktiviert!");
                            Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.BreakB + "x");
                            for (final Player all : Bukkit.getOnlinePlayers()) {
                                all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                                all.removePotionEffect(PotionEffectType.FAST_DIGGING);
                                all.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 20));
                            }
                            Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                                final /* synthetic */ BoosterCMD this$0;
                                private final /* synthetic */ String val$Prefix;
                                
                                BoosterCMD$5() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void run() {
                                    --BoosterCMD.BreakB;
                                    for (Integer i = 0; i < 10; ++i) {
                                        Bukkit.broadcastMessage(" ");
                                    }
                                    Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eBreakB-Booster §cist abgelaufen!");
                                    Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.BreakB + "x");
                                    for (final Player all : Bukkit.getOnlinePlayers()) {
                                        all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                    }
                                }
                            }, 36000L);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lBreak-Boostern §cist bereits erreicht!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§bLeider hast du §c§lkeine Booster §bmehr übrig! Kaufe dir welche im §eShop§b!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast noch §c§l" + Booster.getBooster(p) + " Booster §bübrig!");
                    p.sendMessage(String.valueOf(Prefix) + "§aAktiviere einen §b§lBooster für den Server §amit:");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Erfahrung");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Drops");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Fliegen");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Mobs");
                    p.sendMessage(String.valueOf(Prefix) + "§e/booster Break");
                }
            }
            else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (p.hasPermission("system.booster.give")) {
                        final String target = args[1];
                        final Player tar = Bukkit.getPlayer(target);
                        if (tar != null) {
                            if (args[2].matches("[0-9]+")) {
                                p.sendMessage(String.valueOf(Prefix) + "§7Du hast dem Spieler §a" + tar.getName() + " §c§l" + args[2] + " Booster §7gegeben.");
                                tar.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7hat dir §c§l" + args[2] + " Booster §7gegeben.");
                                Booster.AddBooster(tar, Integer.valueOf(args[2]));
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cBitte gebe eine Zahl an.");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht online!");
                        }
                    }
                    else {
                        p.sendMessage(NoPerms);
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Booster <Help/Erfahrung/Drops/Fliegen/Mobs/give> (<Spieler>) (<Anzahl>)");
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Booster <Help/Erfahrung/Drops/Fliegen/Mobs/give> (<Spieler>) (<Anzahl>)");
            }
        }
        else {
            final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
            if (args.length == 0) {
                p2.sendMessage(String.valueOf(Prefix) + "§fFolgende §b§lBooster §fsind hier aktiv:");
                if (BoosterCMD.DropB != 0) {
                    p2.sendMessage(String.valueOf(Prefix) + "§eDrop-Booster §fMultiplikator: §a" + BoosterCMD.DropB + "x");
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§eDrop-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.XPB != 0) {
                    p2.sendMessage(String.valueOf(Prefix) + "§eErfahrungsbooster §fMultiplikator: §a" + BoosterCMD.XPB + "x");
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§eErfahrungsbooster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.FlyB != 0) {
                    p2.sendMessage(String.valueOf(Prefix) + "§eFly-Booster §fMultiplikator: §a" + BoosterCMD.FlyB + "x");
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§eFly-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.MobB != 0) {
                    p2.sendMessage(String.valueOf(Prefix) + "§eMob-Booster §fMultiplikator: §a" + BoosterCMD.MobB + "x");
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§eMob-Booster §fMultiplikator: §caus.");
                }
                if (BoosterCMD.BreakB != 0) {
                    p2.sendMessage(String.valueOf(Prefix) + "§eBreak-Booster §fMultiplikator: §a" + BoosterCMD.BreakB + "x");
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§eBreak-Booster §fMultiplikator: §caus.");
                }
                p2.sendMessage(String.valueOf(Prefix) + "§aMit §b§lbooster help §abekommst du weitere Informationen zu den §b§lBoostern§a!");
            }
            else if (args.length == 1) {
                if (args[0].equalsIgnoreCase("help")) {
                    p2.sendMessage(String.valueOf(Prefix) + "§aDu hast noch §c§l" + "unendlich" + " Booster §bübrig!");
                    p2.sendMessage(String.valueOf(Prefix) + "§aAktiviere einen §b§lBooster für den Server §amit:");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Erfahrung");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Drops");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Fliegen");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Mobs");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Break");
                }
                else if (args[0].equalsIgnoreCase("Erfahrung")) {
                    if (BoosterCMD.XPB != 3) {
                        ++BoosterCMD.XPB;
                        for (Integer i = 0; i < 100; ++i) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§4§l" + p2.getName() + " §ahat für die Community den §b§lErfahrungs-Booster für §e§l30 Minuten §aaktiviert!");
                        Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.XPB + "x");
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                        }
                        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ BoosterCMD this$0;
                            private final /* synthetic */ String val$Prefix;
                            
                            BoosterCMD$6() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                --BoosterCMD.XPB;
                                for (Integer i = 0; i < 10; ++i) {
                                    Bukkit.broadcastMessage(" ");
                                }
                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eErfahrungs-Booster §cist abgelaufen!");
                                Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.XPB + "x");
                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                }
                            }
                        }, 36000L);
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lErfahrungs-Boostern §cist bereits erreicht!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Drops")) {
                    if (BoosterCMD.DropB != 3) {
                        ++BoosterCMD.DropB;
                        for (Integer i = 0; i < 100; ++i) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§4§l" + p2.getName() + " §ahat für die Community den §b§lDrop-Booster für §e§l30 Minuten §aaktiviert!");
                        Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.DropB + "x");
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                        }
                        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ BoosterCMD this$0;
                            private final /* synthetic */ String val$Prefix;
                            
                            BoosterCMD$7() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                --BoosterCMD.DropB;
                                for (Integer i = 0; i < 10; ++i) {
                                    Bukkit.broadcastMessage(" ");
                                }
                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eDrop-Booster §cist abgelaufen!");
                                Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.DropB + "x");
                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                }
                            }
                        }, 36000L);
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lDrop-Boostern §cist bereits erreicht!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Fliegen")) {
                    if (BoosterCMD.FlyB == 0) {
                        ++BoosterCMD.FlyB;
                        for (Integer i = 0; i < 100; ++i) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§4§l" + p2.getName() + " §ahat für die Community den §b§lFly-Booster für §e§l10 Minuten §aaktiviert!");
                        Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.FlyB + "x");
                        Bukkit.broadcastMessage("§aDu kannst jetzt wegen dem §b§lServer Booster §avon §4§l" + p2.getName() + " §afliegen!");
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            all.setAllowFlight(true);
                        }
                        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ BoosterCMD this$0;
                            private final /* synthetic */ String val$Prefix;
                            
                            BoosterCMD$8() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                for (Integer i = 0; i < 10; ++i) {
                                    Bukkit.broadcastMessage(" ");
                                }
                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cDer Server Booster ist beendet. Dein Flugmodus wird deaktiviert...");
                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 1.0f);
                                }
                                Bukkit.getScheduler().runTaskLater((Plugin)this.this$0.plugin, (Runnable)new Runnable() {
                                    final /* synthetic */ BoosterCMD$8 this$1;
                                    private final /* synthetic */ String val$Prefix;
                                    
                                    BoosterCMD$8$1() {
                                        this.this$1 = this$1;
                                        super();
                                    }
                                    
                                    @Override
                                    public void run() {
                                        for (Integer i = 0; i < 10; ++i) {
                                            Bukkit.broadcastMessage(" ");
                                        }
                                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cDer Server Booster ist beendet. Dein Flugmodus wird deaktiviert...");
                                        for (final Player all : Bukkit.getOnlinePlayers()) {
                                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_WINGS, 1.0f, 1.0f);
                                        }
                                        Bukkit.getScheduler().runTaskLater((Plugin)this.this$1.this$0.plugin, (Runnable)new Runnable() {
                                            final /* synthetic */ BoosterCMD$8$1 this$2;
                                            private final /* synthetic */ String val$Prefix;
                                            
                                            BoosterCMD$8$1$1() {
                                                this.this$2 = this$2;
                                                super();
                                            }
                                            
                                            @Override
                                            public void run() {
                                                --BoosterCMD.FlyB;
                                                for (Integer i = 0; i < 10; ++i) {
                                                    Bukkit.broadcastMessage(" ");
                                                }
                                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eFly-Booster §cist abgelaufen!");
                                                Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.FlyB + "x");
                                                Bukkit.broadcastMessage("§cDer Server Booster ist beendet. Dein Flugmodus wurde deaktiviert.");
                                                Bukkit.broadcastMessage("§a§lFly-Booster §cist jetzt wieder deaktiviert!");
                                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                                    all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                                    all.setAllowFlight(false);
                                                }
                                            }
                                        }, 80L);
                                    }
                                }, 80L);
                            }
                            
                            static /* synthetic */ BoosterCMD access$0(final BoosterCMD$8 runnable) {
                                return runnable.this$0;
                            }
                        }, 12000L);
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cEs ist bereits ein §b§lFly-Booster §caktiv!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Mobs")) {
                    if (BoosterCMD.MobB != 3) {
                        ++BoosterCMD.MobB;
                        for (Integer i = 0; i < 100; ++i) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§4§l" + p2.getName() + " §ahat für die Community den §b§lMob-Booster für §e§l30 Minuten §aaktiviert!");
                        Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.MobB + "x");
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                        }
                        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ BoosterCMD this$0;
                            private final /* synthetic */ String val$Prefix;
                            
                            BoosterCMD$9() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                --BoosterCMD.MobB;
                                for (Integer i = 0; i < 10; ++i) {
                                    Bukkit.broadcastMessage(" ");
                                }
                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eMob-Booster §cist abgelaufen!");
                                Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.MobB + "x");
                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                }
                            }
                        }, 36000L);
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lMob-Boostern §cist bereits erreicht!");
                    }
                }
                else if (args[0].equalsIgnoreCase("Break")) {
                    if (BoosterCMD.BreakB != 1) {
                        ++BoosterCMD.BreakB;
                        for (Integer i = 0; i < 100; ++i) {
                            Bukkit.broadcastMessage(" ");
                        }
                        Bukkit.broadcastMessage(String.valueOf(Prefix) + "§4§l" + p2.getName() + " §ahat für die Community den §b§lBreak-Booster für §e§l30 Minuten §aaktiviert!");
                        Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.BreakB + "x");
                        for (final Player all : Bukkit.getOnlinePlayers()) {
                            all.playSound(all.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            all.removePotionEffect(PotionEffectType.FAST_DIGGING);
                            all.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 200, 20));
                        }
                        Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ BoosterCMD this$0;
                            private final /* synthetic */ String val$Prefix;
                            
                            BoosterCMD$10() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                --BoosterCMD.BreakB;
                                for (Integer i = 0; i < 10; ++i) {
                                    Bukkit.broadcastMessage(" ");
                                }
                                Bukkit.broadcastMessage(String.valueOf(Prefix) + "§cEin §eBreak-Booster §cist abgelaufen!");
                                Bukkit.broadcastMessage("§eMultiplikator: §a§l" + BoosterCMD.BreakB + "x");
                                for (final Player all : Bukkit.getOnlinePlayers()) {
                                    all.playSound(all.getLocation(), Sound.WITHER_IDLE, 1.0f, 1.0f);
                                }
                            }
                        }, 36000L);
                    }
                    else {
                        p2.sendMessage(String.valueOf(Prefix) + "§cDas Limit vom den §b§lBreak-Boostern §cist bereits erreicht!");
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§aDu hast noch §c§l" + "unendlich" + " Booster §bübrig!");
                    p2.sendMessage(String.valueOf(Prefix) + "§aAktiviere einen §b§lBooster für den Server §amit:");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Erfahrung");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Drops");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Fliegen");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Mobs");
                    p2.sendMessage(String.valueOf(Prefix) + "§e/booster Break");
                }
            }
            else if (args.length == 3) {
                if (args[0].equalsIgnoreCase("give")) {
                    if (p2.hasPermission("system.booster.give")) {
                        final String target = args[1];
                        final Player tar = Bukkit.getPlayer(target);
                        if (tar != null) {
                            if (args[2].matches("[0-9]+")) {
                                p2.sendMessage(String.valueOf(Prefix) + "§7Du hast dem Spieler §a" + tar.getName() + " §c§l" + args[2] + " Booster §7gegeben.");
                                tar.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p2.getName() + " §7hat dir §c§l" + args[2] + " Booster §7gegeben.");
                                Booster.AddBooster(tar, Integer.valueOf(args[2]));
                            }
                            else {
                                p2.sendMessage(String.valueOf(Prefix) + "§cBitte gebe eine Zahl an.");
                            }
                        }
                        else {
                            p2.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht online!");
                        }
                    }
                    else {
                        p2.sendMessage(NoPerms);
                    }
                }
                else {
                    p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Booster <Help/Erfahrung/Drops/Fliegen/Mobs/give> (<Spieler>) (<Anzahl>)");
                }
            }
            else {
                p2.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Booster <Help/Erfahrung/Drops/Fliegen/Mobs/give> (<Spieler>) (<Anzahl>)");
            }
        }
        return true;
    }
    
    static /* synthetic */ Main access$0(final BoosterCMD boosterCMD) {
        return boosterCMD.plugin;
    }
}
