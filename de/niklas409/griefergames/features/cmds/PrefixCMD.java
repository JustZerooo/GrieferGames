package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.main.Main;
import java.util.ArrayList;
import org.bukkit.inventory.Inventory;
import org.bukkit.command.CommandExecutor;

public class PrefixCMD implements CommandExecutor
{
    public static Inventory inv;
    public static ArrayList<String> used;
    private Main plugin;
    
    static {
        PrefixCMD.inv = Bukkit.createInventory((InventoryHolder)null, 9, "§bPrefix Auswahl");
        PrefixCMD.used = new ArrayList<String>();
    }
    
    public PrefixCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("prefix").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        if (sender instanceof Player) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
            final Player p = (Player)sender;
            if (p.hasPermission("system.prefix")) {
                if (!PrefixCMD.used.contains(p.getName())) {
                    PrefixCMD.used.add(p.getName());
                    final ItemStack istack = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)1);
                    final ItemMeta istackMeta = istack.getItemMeta();
                    istackMeta.setDisplayName("§6Goldener Name");
                    istack.setItemMeta(istackMeta);
                    final ItemStack istack2 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                    final ItemMeta istackMeta2 = istack2.getItemMeta();
                    istackMeta2.setDisplayName("§cRoter Name");
                    istack2.setItemMeta(istackMeta2);
                    final ItemStack istack3 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)3);
                    final ItemMeta istackMeta3 = istack3.getItemMeta();
                    istackMeta3.setDisplayName("§bBlauer Name");
                    istack3.setItemMeta(istackMeta3);
                    final ItemStack istack4 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)5);
                    final ItemMeta istackMeta4 = istack4.getItemMeta();
                    istackMeta4.setDisplayName("§aGrüner Name");
                    istack4.setItemMeta(istackMeta4);
                    final ItemStack istack5 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)14);
                    final ItemMeta istackMeta5 = istack5.getItemMeta();
                    istackMeta5.setDisplayName("§c§kMagischer Name");
                    istack5.setItemMeta(istackMeta5);
                    final ItemStack istack6 = new ItemStack(Material.NETHER_STAR);
                    final ItemMeta istackMeta6 = istack6.getItemMeta();
                    istackMeta6.setDisplayName("§6Zum Shop");
                    istack6.setItemMeta(istackMeta6);
                    final ItemStack istack7 = new ItemStack(Material.STAINED_GLASS_PANE, 1, (short)0);
                    final ItemMeta istackMeta7 = istack7.getItemMeta();
                    istackMeta7.setDisplayName("§7Farbe entfernen");
                    istack7.setItemMeta(istackMeta7);
                    PrefixCMD.inv.setItem(0, istack);
                    PrefixCMD.inv.setItem(1, istack2);
                    PrefixCMD.inv.setItem(2, istack3);
                    PrefixCMD.inv.setItem(3, istack4);
                    PrefixCMD.inv.setItem(4, istack5);
                    PrefixCMD.inv.setItem(7, istack6);
                    PrefixCMD.inv.setItem(8, istack7);
                    p.openInventory(PrefixCMD.inv);
                    Bukkit.getScheduler().scheduleSyncDelayedTask((Plugin)this.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ PrefixCMD this$0;
                        private final /* synthetic */ Player val$p;
                        
                        PrefixCMD$1() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            PrefixCMD.used.remove(p.getName());
                        }
                    }, 900L);
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§7Du kannst den Befehl nur alle §445 Sekunden §7benutzen.");
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
