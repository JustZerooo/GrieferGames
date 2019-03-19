package de.niklas409.griefergames.features.blocklog;

import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class BlockLogCMD implements CommandExecutor
{
    private Main plugin;
    
    public BlockLogCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("blocklog").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.blocklog.admin")) {
                p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.DIAMOND_PICKAXE, "§4§lBlockLog=Gebaut", 1, new String[] { "§4§lGG Features" }) });
                p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.BEDROCK, "§4§lBlockLog=Abgebaut", 1, new String[] { "§4§lGG Features" }) });
                p.sendMessage(String.valueOf(Prefix) + "§7Du hast die Tools erhalten.");
                p.sendMessage(String.valueOf(Prefix) + "§4§lErklärung: §7Gebaut: Rechtsklicke mit der Spitzhacke auf einem gebauten Block, um zu sehen von wem er gesetzt wurde und wann.");
                p.sendMessage(String.valueOf(Prefix) + "§4§lErklärung: §7Abgebaut: Setze den Grundstein in einen Loch, um zu sehen von wem er abgebaut wurde und wann.");
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann das nicht :(");
        }
        return true;
    }
}
