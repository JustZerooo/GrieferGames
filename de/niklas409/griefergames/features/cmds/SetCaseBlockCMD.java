package de.niklas409.griefergames.features.cmds;

import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class SetCaseBlockCMD implements CommandExecutor
{
    private static Main plugin;
    
    public SetCaseBlockCMD(final Main plugin) {
        super();
        SetCaseBlockCMD.plugin = plugin;
        plugin.getCommand("setcaseblock").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = SetCaseBlockCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + SetCaseBlockCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.case")) {
                p.sendMessage(String.valueOf(Prefix) + "§aDu hast die Truhe bekommen!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                p.getInventory().setItemInHand(ItemBuilder.createItem(Material.CHEST, "§6§lCaseOpening", 1, new String[] { "§4§lGG Features" }));
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDen Command kann die Console nicht ausfuehren!");
        }
        return true;
    }
}
