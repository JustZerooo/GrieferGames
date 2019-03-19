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
import org.bukkit.command.CommandExecutor;

public class MutepCMD implements CommandExecutor
{
    private Main plugin;
    public static File MPTime;
    public static YamlConfiguration MPTime_cfg;
    public static File Mutep;
    public static YamlConfiguration Mutep_cfg;
    
    static {
        MutepCMD.MPTime = new File("plugins/GrieferGames/Data/Mutep.yml");
        MutepCMD.MPTime_cfg = YamlConfiguration.loadConfiguration(MutepCMD.MPTime);
        MutepCMD.Mutep = new File("plugins/GrieferGames/Data/Mutep/Mutep.yml");
        MutepCMD.Mutep_cfg = YamlConfiguration.loadConfiguration(MutepCMD.Mutep);
    }
    
    public MutepCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("mutep").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = (Player)sender;
            final String name = p.getName();
            final File Perk = new File("plugins/GrieferGames/Data/Perk.yml");
            final YamlConfiguration yPerk = YamlConfiguration.loadConfiguration(Perk);
            if (p.hasPermission("system.mutep.time.bypass")) {
                if (yPerk.getString(p.getUniqueId() + ".Mutep") != null) {
                    if (args.length == 1) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.mutep.bypass")) {
                                    int Mutep = 0;
                                    Mutep = Integer.parseInt(this.plugin.getConfig().getString("MutepTime"));
                                    p.sendMessage("§2Du hast " + tar.getDisplayName() + " §2für " + Mutep + " Minuten gemutet.");
                                    tar.sendMessage("§4Du wurdest von " + p.getDisplayName() + " §4für " + Mutep + " Minuten gemutet!");
                                    this.SetMutep(tar, Mutep * 60);
                                    this.SetMutepPlayer(tar, p);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diesen Spieler nicht muten!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst nicht dich selbst muten!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Mutep <Spieler>");
                    }
                }
                else if (!p.hasPermission("system.perk.mutep.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else if (args.length == 1) {
                    if (!args[0].equalsIgnoreCase(p.getName())) {
                        final String target = args[0];
                        final Player tar = Bukkit.getPlayer(target);
                        if (tar != null) {
                            if (!tar.hasPermission("system.mutep.bypass")) {
                                int Mutep = 0;
                                Mutep = Integer.parseInt(this.plugin.getConfig().getString("MutepTime"));
                                p.sendMessage("§2Du hast " + tar.getDisplayName() + " §2für " + Mutep + " Minuten gemutet.");
                                tar.sendMessage("§4Du wurdest von " + p.getDisplayName() + " §4für " + Mutep + " Minuten gemutet!");
                                this.SetMutep(tar, Mutep * 60);
                                this.SetMutepPlayer(tar, p);
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diesen Spieler nicht muten!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu kannst nicht dich selbst muten!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Mutep <Spieler>");
                }
            }
            else if (MutepCMD.MPTime_cfg.get(name) == null) {
                if (yPerk.getString(p.getUniqueId() + ".Mutep") != null) {
                    if (args.length == 1) {
                        if (!args[0].equalsIgnoreCase(p.getName())) {
                            final String target = args[0];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                if (!tar.hasPermission("system.mutep.bypass")) {
                                    int Mutep = 0;
                                    Mutep = Integer.parseInt(this.plugin.getConfig().getString("MutepTime"));
                                    p.sendMessage("§2Du hast " + tar.getDisplayName() + " §2für " + Mutep + " Minuten gemutet.");
                                    tar.sendMessage("§4Du wurdest von " + p.getDisplayName() + " §4für " + Mutep + " Minuten gemutet!");
                                    int time = 0;
                                    time = Integer.parseInt(this.plugin.getConfig().getString("MutepWaitTimeInMinutes"));
                                    this.SetMPTime(p, time * 60);
                                    this.SetMutep(tar, Mutep * 60);
                                    this.SetMutepPlayer(tar, p);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diesen Spieler nicht muten!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu kannst nicht dich selbst muten!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Mutep <Spieler>");
                    }
                }
                else if (!p.hasPermission("system.perk.mutep.bypass")) {
                    p.sendMessage(String.valueOf(Prefix) + "§aKaufe dir diesen Befehl bei §d/perk§a.");
                }
                else if (args.length == 1) {
                    if (!args[0].equalsIgnoreCase(p.getName())) {
                        final String target = args[0];
                        final Player tar = Bukkit.getPlayer(target);
                        if (tar != null) {
                            if (!tar.hasPermission("system.mutep.bypass")) {
                                int Mutep = 0;
                                Mutep = Integer.parseInt(this.plugin.getConfig().getString("MutepTime"));
                                p.sendMessage("§2Du hast " + tar.getDisplayName() + " §2für " + Mutep + " Minuten gemutet.");
                                tar.sendMessage("§4Du wurdest von " + p.getDisplayName() + " §4für " + Mutep + " Minuten gemutet!");
                                int time = 0;
                                time = Integer.parseInt(this.plugin.getConfig().getString("MutepWaitTimeInMinutes"));
                                this.SetMPTime(p, time * 60);
                                this.SetMutep(tar, Mutep * 60);
                                this.SetMutepPlayer(tar, p);
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDu kannst diesen Spieler nicht muten!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu kannst nicht dich selbst muten!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Mutep <Spieler>");
                }
            }
            else if (MutepCMD.MPTime_cfg.getLong(name) < System.currentTimeMillis()) {
                MutepCMD.MPTime_cfg.set(name, (Object)null);
                p.sendMessage(String.valueOf(Prefix) + "§eDas System musste sich erstmal updaten!");
                p.sendMessage(String.valueOf(Prefix) + "§cGib den Command bitte nocheinmal ein!");
                try {
                    MutepCMD.MPTime_cfg.save(MutepCMD.MPTime);
                }
                catch (IOException e) {
                    e.printStackTrace();
                }
            }
            else {
                final Date date = new Date(MutepCMD.MPTime_cfg.getLong(name));
                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                p.sendMessage(String.valueOf(Prefix) + "§c§lDu kannst diesen Befehl erst wieder am" + "\n" + " §a§l" + mm_dd_yyyy + " §c§lum §a§l" + hour_min + " §c§lUhr benutzen.");
            }
        }
        else {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            if (args.length == 1) {
                final String target2 = args[0];
                final Player tar2 = Bukkit.getPlayer(target2);
                if (tar2 != null) {
                    int Mutep2 = 0;
                    Mutep2 = Integer.parseInt(this.plugin.getConfig().getString("MutepTime"));
                    Bukkit.getConsoleSender().sendMessage("§2Du hast " + tar2.getDisplayName() + " §2für " + Mutep2 + " Minuten gemutet.");
                    tar2.sendMessage("§4Du wurdest von der §4§lConsole §4für " + Mutep2 + " Minuten gemutet!");
                    this.SetMutep(tar2, Mutep2 * 60);
                    this.SetMutepConsole(tar2);
                }
                else {
                    Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                }
            }
            else {
                Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Mutep <Spieler>");
            }
        }
        return true;
    }
    
    public void SetMPTime(final Player p, final int time) {
        MutepCMD.MPTime_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            MutepCMD.MPTime_cfg.save(MutepCMD.MPTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void SetMutep(final Player p, final int MutepTime) {
        MutepCMD.Mutep_cfg.set(p.getName(), (Object)(System.currentTimeMillis() + MutepTime * 1000));
        try {
            MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void SetMutepPlayer(final Player p, final Player p2) {
        MutepCMD.Mutep_cfg.set(String.valueOf(p.getName()) + " mutet by", (Object)p2.getName());
        try {
            MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public void SetMutepConsole(final Player p) {
        MutepCMD.Mutep_cfg.set(String.valueOf(p.getName()) + " mutet by", (Object)"Console");
        try {
            MutepCMD.Mutep_cfg.save(MutepCMD.Mutep);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
