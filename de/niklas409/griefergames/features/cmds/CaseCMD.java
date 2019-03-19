package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.Cases;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class CaseCMD implements CommandExecutor
{
    private static Main plugin;
    
    public CaseCMD(final Main plugin) {
        super();
        CaseCMD.plugin = plugin;
        plugin.getCommand("case").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = CaseCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + CaseCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
        final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.case")) {
                if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("edit")) {
                        if (args[1].equalsIgnoreCase("Episch")) {
                            final Inventory editepisch = Bukkit.createInventory((InventoryHolder)null, 45, "§6Case §eedit §5§lEpisch");
                            Integer ii = 0;
                            while (ii != 100) {
                                ++ii;
                                if (yCO.getString("Truhe.Episch.Preise." + ii) != null) {
                                    editepisch.setItem(ii - 1, yCO.getItemStack("Truhe.Episch.Preise." + ii));
                                }
                            }
                            p.openInventory(editepisch);
                        }
                        else if (args[1].equalsIgnoreCase("Supreme")) {
                            final Inventory editSupreme = Bukkit.createInventory((InventoryHolder)null, 45, "§6Case §eedit §d§lSupreme");
                            Integer ii = 0;
                            while (ii != 100) {
                                ++ii;
                                if (yCO.getString("Truhe.Supreme.Preise." + ii) != null) {
                                    editSupreme.setItem(ii - 1, yCO.getItemStack("Truhe.Supreme.Preise." + ii));
                                }
                            }
                            p.openInventory(editSupreme);
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Case <edit(add)> <Episch/Supreme> (<Spieler>) (<Anzahl>)");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Case <edit(add)> <Episch/Supreme> (<Spieler>) (<Anzahl>)");
                    }
                }
                else if (args.length == 4) {
                    if (args[0].equalsIgnoreCase("add")) {
                        if (args[1].equalsIgnoreCase("episch")) {
                            final String target = args[2];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                final String AnzahlS = args[3];
                                if (AnzahlS.matches("[0-9]+")) {
                                    final Integer AnzahlI = Integer.valueOf(AnzahlS);
                                    p.sendMessage(String.valueOf(Prefix) + "§7Du hast dem Spieler §a" + tar.getName() + " §7" + AnzahlI + " §5epische Truhe(n) §7gegeben.");
                                    tar.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7hat dir " + AnzahlI + " §5epische Truhe(n) §7gegeben.");
                                    Cases.AddCase(tar, AnzahlI, "E");
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§a" + AnzahlS + " §cist keine Zahl!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht online!");
                            }
                        }
                        else if (args[1].equalsIgnoreCase("supreme")) {
                            final String target = args[2];
                            final Player tar = Bukkit.getPlayer(target);
                            if (tar != null) {
                                final String AnzahlS = args[3];
                                if (AnzahlS.matches("[0-9]+")) {
                                    final Integer AnzahlI = Integer.valueOf(AnzahlS);
                                    p.sendMessage(String.valueOf(Prefix) + "§7Du hast dem Spieler §a" + tar.getName() + " §7" + AnzahlI + " §d§lSupreme Truhe(n) §7gegeben.");
                                    tar.sendMessage(String.valueOf(Prefix) + "§7Der Spieler §a" + p.getName() + " §7hat dir " + AnzahlI + " §d§lSupreme Truhe(n) §7gegeben.");
                                    Cases.AddCase(tar, AnzahlI, "S");
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§a" + AnzahlS + " §cist keine Zahl!");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht online!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Case <edit(add)> <Episch/Supreme> (<Spieler>) (<Anzahl>)");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Case <edit(add)> <Episch/Supreme> (<Spieler>) (<Anzahl>)");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Case <edit(add)> <Episch/Supreme> (<Spieler>) (<Anzahl>)");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDen Command kann nur ein Spieler ausführen!");
        }
        return true;
    }
}
