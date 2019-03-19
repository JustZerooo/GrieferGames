package de.niklas409.griefergames.features.cmds;

import org.bukkit.command.ConsoleCommandSender;
import java.util.Iterator;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.HashMap;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class AntiBotCMD implements CommandExecutor
{
    private Main plugin;
    public static HashMap<String, Long> AntiBot;
    public static HashMap<String, Integer> Abfrage;
    
    static {
        AntiBotCMD.AntiBot = new HashMap<String, Long>();
        AntiBotCMD.Abfrage = new HashMap<String, Integer>();
    }
    
    public AntiBotCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("antibot").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.antibot")) {
                if (!AntiBotCMD.AntiBot.containsKey(p.getName())) {
                    AntiBotCMD.AntiBot.put(p.getName(), System.currentTimeMillis() + 900000L);
                    p.sendMessage(String.valueOf(Prefix) + "§7Es wurden alle Spieler für 15 Minuten" + "\n" + " §4gebannt§7, die ein §eBot §7sein §4könnten§7!");
                    p.sendMessage(String.valueOf(Prefix) + "§4§lWICHTIG: §cSollten die Bots alle andere Ips haben, werden diese nicht gebannt!");
                    for (final Player all : Bukkit.getOnlinePlayers()) {
                        if (AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) == null) {
                            AntiBotCMD.Abfrage.put(all.getAddress().getAddress().getHostAddress(), 0);
                        }
                        final Integer i = AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) + 1;
                        AntiBotCMD.Abfrage.put(all.getAddress().getAddress().getHostAddress(), i);
                        if (AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) >= 2 && all.getAddress().getAddress().getHostAddress().equalsIgnoreCase(all.getAddress().getAddress().getHostAddress())) {
                            BanCMD.SetBanned(all, all.getUniqueId(), all.getName(), 900, "Bot", p.getName(), p);
                        }
                    }
                    AntiBotCMD.Abfrage.clear();
                    Bukkit.getScheduler().runTaskLater((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ AntiBotCMD this$0;
                        private final /* synthetic */ Player val$p;
                        
                        AntiBotCMD$1() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            AntiBotCMD.AntiBot.remove(p.getName());
                        }
                    }, 18000L);
                }
                else {
                    final Date date = new Date(AntiBotCMD.AntiBot.get(p.getName()));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                    p.sendMessage(String.valueOf(Prefix) + "§4Du kannst diesen Befehl erst wieder am" + "\n" + " §a" + mm_dd_yyyy + " §4um §a" + hour_min + " §4Uhr benutzen.");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final ConsoleCommandSender p2 = Bukkit.getConsoleSender();
            if (p2.hasPermission("system.antibot")) {
                p2.sendMessage(String.valueOf(Prefix) + "§7Es wurden alle Spieler für 15 Minuten" + "\n" + " §4gebannt§7, die ein §eBot §7sein §4könnten§7!");
                p2.sendMessage(String.valueOf(Prefix) + "§4§lWICHTIG: §cSollten die Bots alle andere Ips haben, werden diese nicht gebannt!");
                for (final Player all : Bukkit.getOnlinePlayers()) {
                    if (AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) == null) {
                        AntiBotCMD.Abfrage.put(all.getAddress().getAddress().getHostAddress(), 0);
                    }
                    final Integer i = AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) + 1;
                    AntiBotCMD.Abfrage.put(all.getAddress().getAddress().getHostAddress(), i);
                    if (AntiBotCMD.Abfrage.get(all.getAddress().getAddress().getHostAddress()) >= 2 && all.getAddress().getAddress().getHostAddress().equalsIgnoreCase(all.getAddress().getAddress().getHostAddress())) {
                        BanCMD.SetBannedConsole(all, all.getUniqueId(), all.getName(), 900, "Bot", "Console", p2);
                    }
                }
                AntiBotCMD.Abfrage.clear();
            }
            else {
                p2.sendMessage(NoPerms);
            }
        }
        return true;
    }
}
