package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import java.util.List;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class RandCMD implements CommandExecutor
{
    private Main plugin;
    
    public RandCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("rand").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final Inventory Rand = Bukkit.createInventory((InventoryHolder)null, 54, "§6Rand-Auswahl");
            final ItemStack istack1 = new ItemStack(44, 1, (short)3);
            final ItemMeta istackMeta1 = istack1.getItemMeta();
            istackMeta1.setDisplayName("§fBruchsteinstufe");
            final ArrayList<String> Lore = new ArrayList<String>();
            Lore.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta1.setLore((List)Lore);
            istack1.setItemMeta(istackMeta1);
            final ItemStack istack2 = new ItemStack(44, 1, (short)6);
            final ItemMeta istackMeta2 = istack2.getItemMeta();
            istackMeta2.setDisplayName("§fNetherziegelstufe");
            final ArrayList<String> Lore2 = new ArrayList<String>();
            Lore2.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta2.setLore((List)Lore2);
            istack2.setItemMeta(istackMeta2);
            final ItemStack istack3 = new ItemStack(Material.BARRIER);
            final ItemMeta istackMeta3 = istack3.getItemMeta();
            istackMeta3.setDisplayName("§fBarriere");
            final ArrayList<String> Lore3 = new ArrayList<String>();
            Lore3.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta3.setLore((List)Lore3);
            istack3.setItemMeta(istackMeta3);
            final ItemStack istack4 = new ItemStack(Material.GOLD_BLOCK);
            final ItemMeta istackMeta4 = istack4.getItemMeta();
            istackMeta4.setDisplayName("§fGoldblock");
            final ArrayList<String> Lore4 = new ArrayList<String>();
            Lore4.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta4.setLore((List)Lore4);
            istack4.setItemMeta(istackMeta4);
            final ItemStack istack5 = new ItemStack(44, 1, (short)4);
            final ItemMeta istackMeta5 = istack5.getItemMeta();
            istackMeta5.setDisplayName("§fZiegelstufe");
            final ArrayList<String> Lore5 = new ArrayList<String>();
            Lore5.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta5.setLore((List)Lore5);
            istack5.setItemMeta(istackMeta5);
            final ItemStack istack6 = new ItemStack(126);
            final ItemMeta istackMeta6 = istack6.getItemMeta();
            istackMeta6.setDisplayName("§fEichenholzstufe");
            final ArrayList<String> Lore6 = new ArrayList<String>();
            Lore6.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta6.setLore((List)Lore6);
            istack6.setItemMeta(istackMeta6);
            final ItemStack istack7 = new ItemStack(Material.DIAMOND_BLOCK);
            final ItemMeta istackMeta7 = istack7.getItemMeta();
            istackMeta7.setDisplayName("§fDiamantblock");
            final ArrayList<String> Lore7 = new ArrayList<String>();
            Lore7.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta7.setLore((List)Lore7);
            istack7.setItemMeta(istackMeta7);
            final ItemStack istack8 = new ItemStack(Material.OBSIDIAN);
            final ItemMeta istackMeta8 = istack8.getItemMeta();
            istackMeta8.setDisplayName("§fObsidian");
            final ArrayList<String> Lore8 = new ArrayList<String>();
            Lore8.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta8.setLore((List)Lore8);
            istack8.setItemMeta(istackMeta8);
            final ItemStack istack9 = new ItemStack(Material.DIAMOND_ORE);
            final ItemMeta istackMeta9 = istack9.getItemMeta();
            istackMeta9.setDisplayName("§fDiamanterz");
            final ArrayList<String> Lore9 = new ArrayList<String>();
            Lore9.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta9.setLore((List)Lore9);
            istack9.setItemMeta(istackMeta9);
            final ItemStack istack10 = new ItemStack(Material.BOOKSHELF);
            final ItemMeta istackMeta10 = istack10.getItemMeta();
            istackMeta10.setDisplayName("§fBücherregal");
            final ArrayList<String> Lore10 = new ArrayList<String>();
            Lore10.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta10.setLore((List)Lore10);
            istack10.setItemMeta(istackMeta10);
            final ItemStack istack11 = new ItemStack(Material.EMERALD_BLOCK);
            final ItemMeta istackMeta11 = istack11.getItemMeta();
            istackMeta11.setDisplayName("§fSmaragdblock");
            final ArrayList<String> Lore11 = new ArrayList<String>();
            Lore11.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta11.setLore((List)Lore11);
            istack11.setItemMeta(istackMeta11);
            final ItemStack istack12 = new ItemStack(Material.REDSTONE_LAMP_OFF);
            final ItemMeta istackMeta12 = istack12.getItemMeta();
            istackMeta12.setDisplayName("§fRedstone-Lampe");
            final ArrayList<String> Lore12 = new ArrayList<String>();
            Lore12.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta12.setLore((List)Lore12);
            istack12.setItemMeta(istackMeta12);
            final ItemStack istack13 = new ItemStack(Material.COAL_BLOCK);
            final ItemMeta istackMeta13 = istack13.getItemMeta();
            istackMeta13.setDisplayName("§fKohleblock");
            final ArrayList<String> Lore13 = new ArrayList<String>();
            Lore13.add("§fÄndert den Rand deines Grundstücks in diesen Block");
            istackMeta13.setLore((List)Lore13);
            istack13.setItemMeta(istackMeta13);
            Rand.setItem(0, istack1);
            Rand.setItem(1, istack2);
            Rand.setItem(2, ItemBuilder.createItem(Material.TORCH, "§fFackel", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(3, istack3);
            Rand.setItem(4, istack4);
            Rand.setItem(5, istack5);
            Rand.setItem(6, istack6);
            Rand.setItem(7, istack7);
            Rand.setItem(8, istack8);
            Rand.setItem(9, istack9);
            Rand.setItem(10, istack10);
            Rand.setItem(11, ItemBuilder.createItem(Material.RAILS, "§fSchiene", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(12, ItemBuilder.createItem(Material.REDSTONE_BLOCK, "§fRedstone-Block", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(13, ItemBuilder.createItem(Material.FENCE, "§fZaun", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(14, istack11);
            Rand.setItem(15, ItemBuilder.createItem(Material.BEACON, "§fLeuchtfeuer", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(16, istack12);
            Rand.setItem(17, istack13);
            Rand.setItem(18, ItemBuilder.createItem(Material.NETHERRACK, "§fNetherstein", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(19, ItemBuilder.createItem(Material.SEA_LANTERN, "§fSeelaterne", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(20, ItemBuilder.createItem(Material.BEDROCK, "§fGrundgestein", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(21, ItemBuilder.createItem(Material.ENDER_STONE, "§fEndstein", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(22, ItemBuilder.createItem(Material.ENDER_PORTAL_FRAME, "§fEndportalrahmen", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(23, ItemBuilder.createItem(Material.GLASS, "§fGlas", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(24, ItemBuilder.createItem(Material.PUMPKIN, "§fKürbis", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(25, ItemBuilder.createItem(Material.ENCHANTMENT_TABLE, "§fEnchantment Table", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(26, ItemBuilder.createItem(Material.LAVA_BUCKET, "§fLava", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            Rand.setItem(27, ItemBuilder.createItem(Material.WATER_BUCKET, "§fWasser", 1, new String[] { "§fÄndert den Rand deines Grundstücks in diesen Block" }));
            p.openInventory(Rand);
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie §4§lConsole §ckann kein /Rand.");
        }
        return true;
    }
}
