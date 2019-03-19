package de.niklas409.griefergames.features.cmds;

import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.CommandExecutor;

public class ClearchatCMD implements CommandExecutor
{
    public static File CCTime;
    public static YamlConfiguration CC_cfg;
    private Main plugin;
    
    static {
        ClearchatCMD.CCTime = new File("plugins/GrieferGames/Data/ClearChat.yml");
        ClearchatCMD.CC_cfg = YamlConfiguration.loadConfiguration(ClearchatCMD.CCTime);
    }
    
    public ClearchatCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("clearchat").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = (Player)sender;
            final String name = p.getName();
            final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
            final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
            if (p.hasPermission("system.clearchat.time.bypass")) {
                if (yPerk.getString(p.getUniqueId() + ".ClearChat") != null) {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("System.ClearChat.See.Bypass")) {
                            for (int i = 0; i < 100; ++i) {
                                all.sendMessage(" ");
                            }
                        }
                    }
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lgeleert.");
                }
                else if (!p.hasPermission("system.perk.clearchat.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("System.ClearChat.See.Bypass")) {
                            for (int i = 0; i < 100; ++i) {
                                all.sendMessage(" ");
                            }
                        }
                    }
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lgeleert.");
                }
            }
            else if (ClearchatCMD.CC_cfg.get(name) == null) {
                if (yPerk.getString(p.getUniqueId() + ".ClearChat") != null) {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("System.ClearChat.See.Bypass")) {
                            for (int i = 0; i < 100; ++i) {
                                all.sendMessage(" ");
                            }
                        }
                    }
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lgeleert.");
                    int time = 0;
                    time = Integer.parseInt(this.plugin.getConfig().getString("ClearChatWaitTimeInMinutes"));
                    this.SetCCTime(p, time * 60);
                }
                else if (!p.hasPermission("system.perk.clearchat.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else {
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (!all.hasPermission("System.ClearChat.See.Bypass")) {
                            for (int i = 0; i < 100; ++i) {
                                all.sendMessage(" ");
                            }
                        }
                    }
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lgeleert.");
                    int time = 0;
                    time = Integer.parseInt(this.plugin.getConfig().getString("ClearChatWaitTimeInMinutes"));
                    this.SetCCTime(p, time * 60);
                }
            }
            else if (ClearchatCMD.CC_cfg.getLong(name) < System.currentTimeMillis()) {
                ClearchatCMD.CC_cfg.set(name, (Object)null);
                p.sendMessage(String.valueOf(Prefix) + "§eDas System musste sich erstmal updaten!");
                p.sendMessage(String.valueOf(Prefix) + "§cGib den Command bitte nocheinmal ein!");
                try {
                    ClearchatCMD.CC_cfg.save(ClearchatCMD.CCTime);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                final Date date = new Date(ClearchatCMD.CC_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Befehl erst wieder am" + "\n" + " §a§l" + mm_dd_yyyy + " §c§lum §a§l" + hour_min + " §c§lUhr benutzen.");
            }
        }
        else {
            for (final Player all2 : Bukkit.getOnlinePlayers()) {
                if (!all2.hasPermission("System.ClearChat.See.Bypass")) {
                    for (int j = 0; j < 100; ++j) {
                        all2.sendMessage(" ");
                    }
                }
            }
            Bukkit.broadcastMessage("§e§lDer Chat wurde von der §4§lConsole §e§lgeleert.");
        }
        return true;
    }
    
    public void SetCCTime(final Player p, final int time) {
        ClearchatCMD.CC_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            ClearchatCMD.CC_cfg.save(ClearchatCMD.CCTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
