package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Bukkit;
import java.util.List;
import java.util.Arrays;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class SignCMD implements CommandExecutor
{
    private Main plugin;
    
    public SignCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("sign").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.sign")) {
                if (args.length >= 1) {
                    if (p.getItemInHand() != null && p.getItemInHand().getType() != Material.AIR) {
                        final ItemStack itemnew = p.getItemInHand();
                        final ItemMeta itemnewmeta = itemnew.getItemMeta();
                        try {
                            if (itemnewmeta.hasLore()) {
                                if (!itemnewmeta.getLore().get(2).contains("§7Signiert")) {
                                    String Message = "";
                                    for (int i = 0; i < args.length; ++i) {
                                        Message = String.valueOf(Message) + args[i] + " ";
                                    }
                                    p.sendMessage(String.valueOf(Prefix) + "§aDein Item wurde signiert.");
                                    final Date date = new Date(System.currentTimeMillis());
                                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                    itemnewmeta.setLore((List)Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName() + " §7am §e" + mm_dd_yyyy));
                                    itemnew.setItemMeta(itemnewmeta);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDas Item ist bereits signiert.");
                                }
                            }
                            else {
                                String Message = "";
                                for (int i = 0; i < args.length; ++i) {
                                    Message = String.valueOf(Message) + args[i] + " ";
                                }
                                p.sendMessage(String.valueOf(Prefix) + "§aDein Item wurde signiert.");
                                final Date date = new Date(System.currentTimeMillis());
                                final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                                itemnewmeta.setLore((List)Arrays.asList(" ", Message.replaceAll("&", "§"), "§7Signiert von §a" + p.getName() + " §7am §e" + mm_dd_yyyy));
                                itemnew.setItemMeta(itemnewmeta);
                                p.setItemInHand(itemnew);
                            }
                        }
                        catch (Exception ex) {}
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst ein Item in der Hand haben!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Sign <Signierung>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann nichts signieren!");
        }
        return true;
    }
}
