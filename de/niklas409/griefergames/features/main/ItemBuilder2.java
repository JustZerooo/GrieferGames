package de.niklas409.griefergames.features.main;

import com.mojang.authlib.properties.Property;
import com.mojang.authlib.GameProfile;
import java.util.UUID;
import java.lang.reflect.Field;
import org.bukkit.Bukkit;
import org.bukkit.entity.EntityType;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.Color;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.enchantments.Enchantment;
import java.util.List;
import java.util.Arrays;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public class ItemBuilder2
{
    private ItemStack item;
    
    public ItemBuilder2(final Material material, final int amount) {
        super();
        this.item = new ItemStack(material, amount);
    }
    
    public ItemBuilder2(final Material material, final int amount, final int data) {
        super();
        this.item = new ItemStack(material, amount, (short)data);
    }
    
    public ItemBuilder2(final ItemStack item) {
        super();
        this.item = item;
    }
    
    public ItemBuilder2 setData(final int data) {
        this.item.setDurability((short)data);
        return this;
    }
    
    public ItemBuilder2 setMaterial(final Material m) {
        this.item.setType(m);
        return this;
    }
    
    public ItemBuilder2 setAmount(final int amount) {
        this.item.setAmount(amount);
        return this;
    }
    
    public ItemBuilder2 setName(final String name) {
        final ItemMeta m = this.item.getItemMeta();
        m.setDisplayName(name);
        this.item.setItemMeta(m);
        return this;
    }
    
    public ItemBuilder2 setLore(final String... lore) {
        final ItemMeta m = this.item.getItemMeta();
        m.setLore((List)Arrays.asList(lore));
        this.item.setItemMeta(m);
        return this;
    }
    
    public ItemBuilder2 enchant(final Enchantment ench, final int lvl) {
        this.item.addUnsafeEnchantment(ench, lvl);
        return this;
    }
    
    public ItemBuilder2 addFlags(final ItemFlag... flag) {
        final ItemMeta m = this.item.getItemMeta();
        m.addItemFlags(flag);
        this.item.setItemMeta(m);
        return this;
    }
    
    public ItemBuilder2 setLeatherColor(final Color color) {
        final LeatherArmorMeta m = (LeatherArmorMeta)this.item.getItemMeta();
        m.setColor(color);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setSkullOwner(final String owner) {
        final SkullMeta m = (SkullMeta)this.item.getItemMeta();
        m.setOwner(owner);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setPotionType(final PotionEffectType type) {
        final PotionMeta m = (PotionMeta)this.item.getItemMeta();
        m.setMainEffect(type);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setBookAuthor(final String author) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setAuthor(author);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setBookContent(final String... pages) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setPages(pages);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setBookTitle(final String title) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setTitle(title);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setBookMeta(final String title, final String author, final String... pages) {
        final BookMeta m = (BookMeta)this.item.getItemMeta();
        m.setTitle(title);
        m.setAuthor(author);
        m.setPages(pages);
        this.item.setItemMeta((ItemMeta)m);
        return this;
    }
    
    public ItemBuilder2 setEggType(final EntityType type) {
        if (this.item != null && this.item.getType() == Material.MONSTER_EGG && type != null && type.getName() != null) {
            try {
                final String version = Bukkit.getServer().getClass().toString().split("\\.")[3];
                final Class<?> craftItemStack = Class.forName("org.bukkit.craftbukkit." + version + ".inventory.CraftItemStack");
                final Object nmsItemStack = craftItemStack.getDeclaredMethod("asNMSCopy", ItemStack.class).invoke(null, this.item);
                final Object nbtTagCompound = Class.forName("net.minecraft.server." + version + ".NBTTagCompound").newInstance();
                final Field nbtTagCompoundField = nmsItemStack.getClass().getDeclaredField("tag");
                nbtTagCompoundField.setAccessible(true);
                nbtTagCompound.getClass().getMethod("setString", String.class, String.class).invoke(nbtTagCompound, "id", type.getName());
                nbtTagCompound.getClass().getMethod("set", String.class, Class.forName("net.minecraft.server." + version + ".NBTBase")).invoke(nbtTagCompoundField.get(nmsItemStack), "EntityTag", nbtTagCompound);
                this.item = (ItemStack)craftItemStack.getDeclaredMethod("asCraftMirror", nmsItemStack.getClass()).invoke(null, nmsItemStack);
            }
            catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return this;
    }
    
    public ItemBuilder2 setSkullTexture(final String base64) {
        final ItemMeta m = this.item.getItemMeta();
        final GameProfile profile = new GameProfile(UUID.randomUUID(), (String)null);
        profile.getProperties().put((Object)"textures", (Object)new Property("textures", base64));
        Field profileField = null;
        try {
            profileField = m.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(m, profile);
        }
        catch (NoSuchFieldException | IllegalArgumentException | IllegalAccessException ex2) {
            final Exception ex;
            final Exception e1 = ex;
            e1.printStackTrace();
        }
        this.item.setItemMeta(m);
        return this;
    }
    
    public ItemStack build() {
        return this.item;
    }
}
