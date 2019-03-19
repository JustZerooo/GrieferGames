package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.command.CommandExecutor;

public class ViewarmorCMD implements CommandExecutor
{
    public static HashMap<Player, String> Name;
    private Main plugin;
    
    static {
        ViewarmorCMD.Name = new HashMap<Player, String>();
    }
    
    public ViewarmorCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("viewarmor").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final Player p = (Player)sender;
            if (p.hasPermission("system.viewarmor")) {
                if (args.length == 1) {
                    final String target = args[0];
                    final Player tar = Bukkit.getPlayer(target);
                    if (tar != null) {
                        final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 9, "§a" + tar.getName());
                        ViewarmorCMD.Name.put(p, "§a" + tar.getName());
                        final ItemStack istack = new ItemStack(Material.BARRIER);
                        if (tar.getInventory().getHelmet() == null) {
                            inv.setItem(0, istack);
                        }
                        else {
                            inv.setItem(0, tar.getInventory().getHelmet());
                        }
                        if (tar.getInventory().getChestplate() == null) {
                            inv.setItem(1, istack);
                        }
                        else {
                            inv.setItem(1, tar.getInventory().getChestplate());
                        }
                        if (tar.getInventory().getLeggings() == null) {
                            inv.setItem(2, istack);
                        }
                        else {
                            inv.setItem(2, tar.getInventory().getLeggings());
                        }
                        if (tar.getInventory().getBoots() == null) {
                            inv.setItem(3, istack);
                        }
                        else {
                            inv.setItem(3, tar.getInventory().getBoots());
                        }
                        p.openInventory(inv);
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler §a" + args[0] + " §cist nicht online!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBenutze: §a/Viewarmor <Spieler>");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDiesen Command kann nur ein Spieler ausführen!");
        }
        return true;
    }
}
