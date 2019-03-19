package de.niklas409.griefergames.features.cmds;

import org.bukkit.OfflinePlayer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.bukkit.plugin.Plugin;
import org.bukkit.Sound;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.CommandExecutor;

public class KopfCMD implements CommandExecutor
{
    public static File KopfTime;
    public static YamlConfiguration kopf_cfg;
    private Main plugin;
    
    static {
        KopfCMD.KopfTime = new File("plugins/GrieferGames/Data/Kopf.yml");
        KopfCMD.kopf_cfg = YamlConfiguration.loadConfiguration(KopfCMD.KopfTime);
    }
    
    public KopfCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("kopf").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final String name = p.getName();
            if (p.hasPermission("system.kopf.time.bypass")) {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
                if (p.hasPermission("system.kopf")) {
                    if (args.length == 1) {
                        final String target = args[0];
                        final OfflinePlayer taroffline = Bukkit.getOfflinePlayer(target);
                        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        final SkullMeta meta = (SkullMeta)item.getItemMeta();
                        meta.setDisplayName("§7Kopf von §a§l§n" + target);
                        meta.setOwner(target);
                        item.setItemMeta((ItemMeta)meta);
                        p.getInventory().addItem(new ItemStack[] { item });
                        p.sendMessage(String.valueOf(Prefix) + "§eDu hast dir den Kopf von §a" + taroffline.getName() + " §egeholt!");
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ KopfCMD this$0;
                            private final /* synthetic */ Player val$p;
                            
                            KopfCMD$1() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.this$0.plugin, (Runnable)new Runnable() {
                                    final /* synthetic */ KopfCMD$1 this$1;
                                    private final /* synthetic */ Player val$p;
                                    
                                    KopfCMD$1$1() {
                                        this.this$1 = this$1;
                                        super();
                                    }
                                    
                                    @Override
                                    public void run() {
                                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.this$1.this$0.plugin, (Runnable)new Runnable() {
                                            final /* synthetic */ KopfCMD$1$1 this$2;
                                            private final /* synthetic */ Player val$p;
                                            
                                            KopfCMD$1$1$1() {
                                                this.this$2 = this$2;
                                                super();
                                            }
                                            
                                            @Override
                                            public void run() {
                                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                            }
                                        }, 3L);
                                    }
                                }, 3L);
                            }
                            
                            static /* synthetic */ KopfCMD access$0(final KopfCMD$1 runnable) {
                                return runnable.this$0;
                            }
                        }, 3L);
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Kopf <Spieler>");
                    }
                }
                else {
                    p.sendMessage(NoPerms);
                }
            }
            else if (KopfCMD.kopf_cfg.get(name) == null) {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
                if (p.hasPermission("system.kopf")) {
                    if (args.length == 1) {
                        final String target = args[0];
                        final OfflinePlayer taroffline = Bukkit.getOfflinePlayer(target);
                        final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                        final SkullMeta meta = (SkullMeta)item.getItemMeta();
                        meta.setDisplayName("§7Kopf von §a§l§n" + target);
                        meta.setOwner(target);
                        item.setItemMeta((ItemMeta)meta);
                        p.getInventory().addItem(new ItemStack[] { item });
                        p.sendMessage(String.valueOf(Prefix) + "§eDu hast dir den Kopf von §a" + taroffline.getName() + " §egeholt!");
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                            final /* synthetic */ KopfCMD this$0;
                            private final /* synthetic */ Player val$p;
                            
                            KopfCMD$2() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void run() {
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.this$0.plugin, (Runnable)new Runnable() {
                                    final /* synthetic */ KopfCMD$2 this$1;
                                    private final /* synthetic */ Player val$p;
                                    
                                    KopfCMD$2$1() {
                                        this.this$1 = this$1;
                                        super();
                                    }
                                    
                                    @Override
                                    public void run() {
                                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                        p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                        Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.this$1.this$0.plugin, (Runnable)new Runnable() {
                                            final /* synthetic */ KopfCMD$2$1 this$2;
                                            private final /* synthetic */ Player val$p;
                                            
                                            KopfCMD$2$1$1() {
                                                this.this$2 = this$2;
                                                super();
                                            }
                                            
                                            @Override
                                            public void run() {
                                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_WINGS, 10.0f, 1.0f);
                                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_HIT, 10.0f, 1.0f);
                                            }
                                        }, 3L);
                                    }
                                }, 3L);
                            }
                            
                            static /* synthetic */ KopfCMD access$0(final KopfCMD$2 runnable) {
                                return runnable.this$0;
                            }
                        }, 3L);
                        int time = 0;
                        time = Integer.parseInt(this.plugin.getConfig().getString("KopfWaitTimeInMinutes"));
                        this.SetKopfTime(p, time * 60);
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Kopf <Spieler>");
                    }
                }
                else {
                    p.sendMessage(NoPerms);
                }
            }
            else if (KopfCMD.kopf_cfg.getLong(name) < System.currentTimeMillis()) {
                KopfCMD.kopf_cfg.set(name, (Object)null);
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                p.sendMessage(String.valueOf(Prefix) + "§eDas System musste sich erstmal updaten!");
                p.sendMessage(String.valueOf(Prefix) + "§cGib den Command bitte nocheinmal ein!");
                try {
                    KopfCMD.kopf_cfg.save(KopfCMD.KopfTime);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                final Date date = new Date(KopfCMD.kopf_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Befehl erst wieder am" + "\n" + " §a§l" + mm_dd_yyyy + " §c§lum §a§l" + hour_min + " §c§lUhr benutzen.");
            }
        }
        else {
            final String Prefix2 = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix2) + "§cDie §4§lConsole §ckann keine Koepfe annehmen.");
        }
        return true;
    }
    
    public void SetKopfTime(final Player p, final int time) {
        KopfCMD.kopf_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            KopfCMD.kopf_cfg.save(KopfCMD.KopfTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    static /* synthetic */ Main access$0(final KopfCMD kopfCMD) {
        return kopfCMD.plugin;
    }
}
