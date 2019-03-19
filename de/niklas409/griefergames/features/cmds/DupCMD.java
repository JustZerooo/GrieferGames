package de.niklas409.griefergames.features.cmds;

import org.bukkit.Location;
import java.util.Iterator;
import java.util.List;
import java.io.IOException;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.TextComponent;
import de.niklas409.griefergames.features.listeners.MainListener;
import org.bukkit.Bukkit;
import java.util.UUID;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class DupCMD implements CommandExecutor
{
    private static Main plugin;
    
    public DupCMD(final Main plugin) {
        super();
        DupCMD.plugin = plugin;
        plugin.getCommand("dup").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = DupCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + DupCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.antidupp")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("list")) {
                        final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                        if (blocked.size() != 0) {
                            p.sendMessage("§8]=========================================[");
                            for (final String locs : blocked) {
                                final UUID uuid = UUID.fromString(Main.yAD.getString("DuppLocs.loc." + locs + ".Spieler"));
                                final String Spieler = Bukkit.getOfflinePlayer(uuid).getName();
                                final String Stufe = Main.yAD.getString("DuppLocs.loc." + locs + ".Stufe");
                                final String Welt = MainListener.stringToLoc(locs).getWorld().getName();
                                final TextComponent TPTxT = new TextComponent();
                                TPTxT.setText("§a[TP]");
                                TPTxT.setBold(Boolean.valueOf(true));
                                TPTxT.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dup tp " + locs));
                                TPTxT.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aTeleportiere dich.").create()));
                                TPTxT.addExtra(" §c" + Spieler + " §8| §4" + Stufe + " §8| §4" + Welt);
                                final TextComponent RM = new TextComponent();
                                RM.setText(" §e[RM]");
                                RM.setBold(Boolean.valueOf(true));
                                RM.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dup rm " + locs));
                                RM.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§cLösche die Truhe aus der Liste.").create()));
                                final TextComponent WL = new TextComponent();
                                WL.setText(" §f[WL]");
                                WL.setBold(Boolean.valueOf(true));
                                WL.setClickEvent(new ClickEvent(ClickEvent.Action.RUN_COMMAND, "/dup wl " + locs));
                                WL.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§7Whiteliste die Location.").create()));
                                TPTxT.addExtra((BaseComponent)RM);
                                RM.addExtra((BaseComponent)WL);
                                p.spigot().sendMessage((BaseComponent)TPTxT);
                            }
                            p.sendMessage("§8]=========================================[");
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDerzeit gibt es keine §4Dupper§c!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Dup list");
                    }
                }
                else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("tp")) {
                        final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                        final String loc = args[1];
                        if (blocked.contains(loc)) {
                            final Location Loc = MainListener.stringToLoc(args[1]);
                            p.teleport(Loc);
                            final UUID uuid = UUID.fromString(Main.yAD.getString("DuppLocs.loc." + loc + ".Spieler"));
                            final String Spieler = Bukkit.getOfflinePlayer(uuid).getName();
                            p.sendMessage(String.valueOf(Prefix) + "§2Du wurdest zu der Chest von §4" + Spieler + " §2teleportiert!");
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cEs wurde nichts gefunden!");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("rm")) {
                        final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                        final String loc = args[1];
                        if (blocked.contains(loc)) {
                            p.sendMessage(String.valueOf(Prefix) + "§2Du hast erfolgreich die Location §c" + loc + " \n §2aus der Liste entfernt!");
                            blocked.remove(loc);
                            Main.yAD.set("BlockLocs", (Object)blocked);
                            Main.yAD.set("DuppLocs.loc." + loc, (Object)null);
                            try {
                                Main.yAD.save(Main.AD);
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cEs wurde nichts gefunden!");
                        }
                    }
                    else if (args[0].equalsIgnoreCase("wl")) {
                        final List<String> blocked = (List<String>)Main.yAD.getStringList("BlockLocs");
                        final List<String> Whitelist = (List<String>)Main.yAD.getStringList("Whitelist");
                        final String loc2 = args[1];
                        if (blocked.contains(loc2)) {
                            p.sendMessage(String.valueOf(Prefix) + "§2Du hast erfolgreich die Location §c" + loc2 + " \n §2zur §fWhitelist §2hinzugefügt!");
                            blocked.remove(loc2);
                            Whitelist.add(loc2);
                            Main.yAD.set("BlockLocs", (Object)blocked);
                            Main.yAD.set("Whitelist", (Object)Whitelist);
                            Main.yAD.set("DuppLocs.loc." + loc2, (Object)null);
                            try {
                                Main.yAD.save(Main.AD);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cEs wurde nichts gefunden!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Dup list/(</TP/RM>) (<Loc>)");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Dup list/(</TP/RM>) (<Loc>)");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDas kann die Console leider nicht.");
        }
        return true;
    }
}
