package de.niklas409.griefergames.features.cmds;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import java.util.ArrayList;
import org.bukkit.command.CommandExecutor;

public class SlowchatCMD implements CommandExecutor
{
    public static ArrayList<String> slowchat;
    private Main plugin;
    public static File SCTime;
    public static YamlConfiguration SC_cfg;
    
    static {
        SlowchatCMD.slowchat = new ArrayList<String>();
        SlowchatCMD.SCTime = new File("plugins/GrieferGames/Data/SlowChat.yml");
        SlowchatCMD.SC_cfg = YamlConfiguration.loadConfiguration(SlowchatCMD.SCTime);
    }
    
    public SlowchatCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("slowchat").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = (Player)sender;
            final String name = p.getName();
            final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
            final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
            if (p.hasPermission("system.slowchat.time.bypass")) {
                if (yPerk.getString(p.getUniqueId() + ".SlowChat") != null) {
                    if (SlowchatCMD.slowchat.contains("true")) {
                        SlowchatCMD.slowchat.remove("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lauf normal gestellt.");
                    }
                    else {
                        SlowchatCMD.slowchat.add("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lverlangsamt.");
                    }
                }
                else if (!p.hasPermission("system.perk.slowchat.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else if (SlowchatCMD.slowchat.contains("true")) {
                    SlowchatCMD.slowchat.remove("true");
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lauf normal gestellt.");
                }
                else {
                    SlowchatCMD.slowchat.add("true");
                    Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lverlangsamt.");
                }
            }
            else if (SlowchatCMD.SC_cfg.get(name) == null) {
                if (yPerk.getString(p.getUniqueId() + ".SlowChat") != null) {
                    if (SlowchatCMD.slowchat.contains("true")) {
                        SlowchatCMD.slowchat.remove("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lauf normal gestellt.");
                    }
                    else {
                        SlowchatCMD.slowchat.add("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lverlangsamt.");
                    }
                    int time = 0;
                    time = Integer.parseInt(this.plugin.getConfig().getString("SlowChatWaitTimeInMinutes"));
                    this.SetSCTime(p, time * 60);
                }
                else if (!p.hasPermission("system.perk.slowchat.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else {
                    if (SlowchatCMD.slowchat.contains("true")) {
                        SlowchatCMD.slowchat.remove("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lauf normal gestellt.");
                    }
                    else {
                        SlowchatCMD.slowchat.add("true");
                        Bukkit.broadcastMessage("§e§lDer Chat wurde von " + p.getDisplayName() + " §e§lverlangsamt.");
                    }
                    int time = 0;
                    time = Integer.parseInt(this.plugin.getConfig().getString("SlowChatWaitTimeInMinutes"));
                    this.SetSCTime(p, time * 60);
                }
            }
            else if (SlowchatCMD.SC_cfg.getLong(name) < System.currentTimeMillis()) {
                SlowchatCMD.SC_cfg.set(name, (Object)null);
                p.sendMessage(String.valueOf(Prefix) + "§eDas System musste sich erstmal updaten!");
                p.sendMessage(String.valueOf(Prefix) + "§cGib den Command bitte nocheinmal ein!");
                try {
                    SlowchatCMD.SC_cfg.save(SlowchatCMD.SCTime);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                final Date date = new Date(SlowchatCMD.SC_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Befehl erst wieder am" + "\n" + " §a§l" + mm_dd_yyyy + " §c§lum §a§l" + hour_min + " §c§lUhr benutzen.");
            }
        }
        else if (SlowchatCMD.slowchat.contains("true")) {
            SlowchatCMD.slowchat.remove("true");
            Bukkit.broadcastMessage("§e§lDer Chat wurde von der §4§lConsole §e§lauf normal gestellt.");
        }
        else {
            SlowchatCMD.slowchat.add("true");
            Bukkit.broadcastMessage("§e§lDer Chat wurde von der §4§l§lConsole §everlangsamt.");
        }
        return true;
    }
    
    public void SetSCTime(final Player p, final int time) {
        SlowchatCMD.SC_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            SlowchatCMD.SC_cfg.save(SlowchatCMD.SCTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
