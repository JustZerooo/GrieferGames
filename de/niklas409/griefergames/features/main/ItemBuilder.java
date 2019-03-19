package de.niklas409.griefergames.features.main;

import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.Arrays;
import org.bukkit.inventory.ItemStack;
import org.bukkit.Material;

public class ItemBuilder
{
    public ItemBuilder() {
        super();
    }
    
    public static ItemStack createItem(final Material material, final String displayName, final Integer amount, final String[] lore) {
        final ItemStack itemStack = new ItemStack(material, (int)amount);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore((List)Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack createItemWD(final Material material, final String displayName, final Integer amount, final int Data, final String[] lore) {
        final ItemStack itemStack = new ItemStack(material, (int)amount, (short)(byte)Data);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemMeta.setLore((List)Arrays.asList(lore));
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack createItemWDOL(final Material material, final String displayName, final Integer amount, final int Data) {
        final ItemStack itemStack = new ItemStack(material, (int)amount, (short)(byte)Data);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack createItemOL(final Material material, final String displayName, final Integer amount) {
        final ItemStack itemStack = new ItemStack(material, (int)amount);
        final ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setDisplayName(displayName);
        itemStack.setItemMeta(itemMeta);
        return itemStack;
    }
    
    public static ItemStack createItemON(final Material material) {
        final ItemStack itemStack = new ItemStack(material);
        return itemStack;
    }
    
    public static Inventory InvFüller(final Inventory inv, final ItemStack item, final Integer Reihen) {
        Integer i = -1;
        while (i < Reihen * 9 - 1) {
            ++i;
            if (inv.getItem((int)i) == null) {
                inv.setItem((int)i, item);
            }
        }
        return inv;
    }
}
