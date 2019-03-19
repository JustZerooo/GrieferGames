package de.niklas409.griefergames.features.listeners;

import java.io.IOException;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.List;
import java.util.Arrays;
import de.niklas409.griefergames.features.cmds.CreateCaseItemCMD;
import org.bukkit.event.inventory.InventoryClickEvent;
import java.util.Random;
import java.util.ArrayList;
import org.bukkit.inventory.ItemStack;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.Inventory;
import org.bukkit.entity.Player;
import org.bukkit.Sound;
import org.bukkit.GameMode;
import de.niklas409.griefergames.features.main.ItemBuilder;
import de.niklas409.griefergames.features.main.Cases;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.event.block.Action;
import org.bukkit.Location;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.plugin.Plugin;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.event.Listener;

public class CaseOpeningBETA implements Listener
{
    private Main plugin;
    public static Boolean using;
    Integer cd1;
    
    public CaseOpeningBETA(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onInteract(final PlayerInteractEvent e) {
        final Player p = e.getPlayer();
        if (e.getClickedBlock() != null && e.getClickedBlock().getType() == Material.CHEST) {
            try {
                final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
                final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
                final String Welt = yCO.getString("Truhe.Loc.Welt");
                final double X = yCO.getDouble("Truhe.Loc.X");
                final double Y = yCO.getDouble("Truhe.Loc.Y");
                final double Z = yCO.getDouble("Truhe.Loc.Z");
                final Location truhe = new Location(Bukkit.getWorld(Welt), X, Y, Z);
                if (e.getClickedBlock().getLocation().equals((Object)truhe)) {
                    if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                        e.setCancelled(true);
                        final Inventory cases = Bukkit.createInventory((InventoryHolder)null, 27, "§6" + p.getName() + "'s §bKisten");
                        cases.setItem(12, ItemBuilder.createItem(Material.CHEST, "§5Epische §aKisten", 1, new String[] { "§7Du hast §a" + Cases.getCase(p, "E") + " Kisten§7." }));
                        cases.setItem(14, ItemBuilder.createItem(Material.ENDER_PORTAL_FRAME, "§d§lSUPREME Kisten", 1, new String[] { "§7Du hast §d§l" + Cases.getCase(p, "S") + " Supreme Kisten§7." }));
                        p.openInventory(cases);
                    }
                    else if (e.getAction() == Action.LEFT_CLICK_BLOCK) {
                        if (e.getPlayer().getGameMode() == GameMode.CREATIVE) {
                            if (e.getPlayer().isSneaking()) {
                                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                                p.sendMessage(String.valueOf(Prefix) + "§aDu hast den CaseBlock abgebaut!");
                                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                                yCO.set("Truhe.Loc", (Object)null);
                                yCO.save(CO);
                            }
                            else {
                                e.setCancelled(true);
                                final Inventory cases = Bukkit.createInventory((InventoryHolder)null, 27, "§6" + p.getName() + "'s §bKisten");
                                cases.setItem(12, ItemBuilder.createItem(Material.CHEST, "§5Epische §aKisten", 1, new String[] { "§7Du hast §a" + Cases.getCase(p, "E") + " Kisten§7." }));
                                cases.setItem(14, ItemBuilder.createItem(Material.ENDER_PORTAL_FRAME, "§d§lSUPREME Kisten", 1, new String[] { "§7Du hast §d§l" + Cases.getCase(p, "S") + " Supreme Kisten§7." }));
                                p.openInventory(cases);
                            }
                        }
                        else {
                            e.setCancelled(true);
                            final Inventory cases = Bukkit.createInventory((InventoryHolder)null, 27, "§6" + p.getName() + "'s §bKisten");
                            cases.setItem(12, ItemBuilder.createItem(Material.CHEST, "§5Epische §aKisten", 1, new String[] { "§7Du hast §a" + Cases.getCase(p, "E") + " Kisten§7." }));
                            cases.setItem(14, ItemBuilder.createItem(Material.ENDER_PORTAL_FRAME, "§d§lSUPREME Kisten", 1, new String[] { "§7Du hast §d§l" + Cases.getCase(p, "S") + " Supreme Kisten§7." }));
                            p.openInventory(cases);
                        }
                    }
                    else {
                        e.setCancelled(true);
                        final Inventory cases = Bukkit.createInventory((InventoryHolder)null, 27, "§6" + p.getName() + "'s §bKisten");
                        cases.setItem(12, ItemBuilder.createItem(Material.CHEST, "§5Epische §aKisten", 1, new String[] { "§7Du hast §a" + Cases.getCase(p, "E") + " Kisten§7." }));
                        cases.setItem(14, ItemBuilder.createItem(Material.ENDER_PORTAL_FRAME, "§d§lSUPREME Kisten", 1, new String[] { "§7Du hast §d§l" + Cases.getCase(p, "S") + " Supreme Kisten§7." }));
                        p.openInventory(cases);
                    }
                }
            }
            catch (Exception ex) {}
        }
    }
    
    @EventHandler
    public void onSet(final BlockPlaceEvent e) {
        final Player p = e.getPlayer();
        try {
            if (p.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lCaseOpening") && p.getItemInHand().getItemMeta().getLore().get(0).equalsIgnoreCase("§4§lGG Features")) {
                final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
                final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
                final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
                p.setItemInHand(new ItemStack(Material.AIR));
                p.sendMessage(String.valueOf(Prefix) + "§aDu hast den CaseBlock gesetzt!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                yCO.set("Truhe.Loc.Welt", (Object)e.getBlock().getLocation().getWorld().getName());
                yCO.set("Truhe.Loc.X", (Object)e.getBlock().getLocation().getX());
                yCO.set("Truhe.Loc.Y", (Object)e.getBlock().getLocation().getY());
                yCO.set("Truhe.Loc.Z", (Object)e.getBlock().getLocation().getZ());
                yCO.save(CO);
            }
        }
        catch (Exception ex) {}
    }
    
    public void openco(final Player p, final String Truhe) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (!CaseOpeningBETA.using) {
            if (Truhe.equalsIgnoreCase("Episch")) {
                Cases.RemoveCase(p, 1, "E");
            }
            else if (Truhe.equalsIgnoreCase("Supreme")) {
                Cases.RemoveCase(p, 1, "S");
            }
            CaseOpeningBETA.using = true;
            final Inventory inv = Bukkit.createInventory((InventoryHolder)null, 27, "§6CaseOpening");
            inv.setItem(4, ItemBuilder.createItemON(Material.HOPPER));
            inv.setItem(9, this.PicRNDItems(Truhe));
            inv.setItem(10, this.PicRNDItems(Truhe));
            inv.setItem(11, this.PicRNDItems(Truhe));
            inv.setItem(12, this.PicRNDItems(Truhe));
            inv.setItem(13, this.PicRNDItems(Truhe));
            inv.setItem(14, this.PicRNDItems(Truhe));
            inv.setItem(15, this.PicRNDItems(Truhe));
            inv.setItem(16, this.PicRNDItems(Truhe));
            inv.setItem(17, this.PicRNDItems(Truhe));
            p.openInventory(inv);
            this.cd1 = Bukkit.getScheduler().scheduleSyncRepeatingTask((Plugin)this.plugin, (Runnable)new Runnable() {
                Integer i = 0;
                Integer max = this$0.zufallszahl(12, 20);
                final /* synthetic */ CaseOpeningBETA this$0;
                private final /* synthetic */ Player val$p;
                private final /* synthetic */ Inventory val$inv;
                private final /* synthetic */ String val$Truhe;
                private final /* synthetic */ String val$Prefix;
                
                CaseOpeningBETA$1() {
                    this.this$0 = this$0;
                    super();
                }
                
                @Override
                public void run() {
                    if (this.i != this.max) {
                        ++this.i;
                        p.playSound(p.getLocation(), Sound.BAT_TAKEOFF, 1.0f, 1.0f);
                        inv.setItem(17, inv.getItem(16));
                        inv.setItem(16, inv.getItem(15));
                        inv.setItem(15, inv.getItem(14));
                        inv.setItem(14, inv.getItem(13));
                        inv.setItem(13, inv.getItem(12));
                        inv.setItem(12, inv.getItem(11));
                        inv.setItem(11, inv.getItem(10));
                        inv.setItem(10, inv.getItem(9));
                        inv.setItem(9, this.this$0.PicRNDItems(Truhe));
                        p.openInventory(inv);
                    }
                    else {
                        inv.setItem(17, inv.getItem(16));
                        inv.setItem(16, inv.getItem(15));
                        inv.setItem(15, inv.getItem(14));
                        inv.setItem(14, inv.getItem(13));
                        inv.setItem(13, inv.getItem(12));
                        inv.setItem(12, inv.getItem(11));
                        inv.setItem(11, inv.getItem(10));
                        inv.setItem(10, inv.getItem(9));
                        inv.setItem(9, this.this$0.PicRNDItems(Truhe));
                        p.openInventory(inv);
                        Bukkit.getScheduler().cancelTask((int)this.this$0.cd1);
                        p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast " + inv.getItem(13).getItemMeta().getDisplayName() + " §agewonnen!");
                        p.getInventory().addItem(new ItemStack[] { inv.getItem(13) });
                        CaseOpeningBETA.using = false;
                    }
                }
            }, 10L, 10L);
        }
        else {
            p.closeInventory();
            p.sendMessage(String.valueOf(Prefix) + "§cBitte warte einen Moment. Ein anderer Spieler macht gerade das CaseOpening!");
        }
    }
    
    public ItemStack PicRNDItems(final String Truhe) {
        final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
        final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
        final ArrayList<ItemStack> items = new ArrayList<ItemStack>();
        ItemStack i = null;
        Integer ii = 0;
        while (ii != 100) {
            ++ii;
            if (yCO.getString("Truhe." + Truhe + ".Preise" + "." + ii) != null) {
                final ItemStack is = new ItemStack(yCO.getItemStack("Truhe." + Truhe + ".Preise" + "." + ii));
                if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§aNormal")) {
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                }
                if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Selten")) {
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                }
                if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§bUltra")) {
                    items.add(is);
                    items.add(is);
                    items.add(is);
                    items.add(is);
                }
                if (is.getItemMeta().getLore().get(0).equalsIgnoreCase("§5Episch")) {
                    items.add(is);
                    items.add(is);
                }
                if (!is.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Legendaer")) {
                    continue;
                }
                items.add(is);
            }
        }
        final int random = new Random().nextInt(items.size());
        i = items.get(random);
        return i;
    }
    
    public int zufallszahl(final int min, final int max) {
        final Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        if (e.getInventory().getName().equalsIgnoreCase("§6CaseOpening")) {
            e.setCancelled(true);
        }
        else if (e.getInventory().getName().equalsIgnoreCase("§6" + e.getWhoClicked().getName() + "'s §bKisten")) {
            e.setCancelled(true);
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Epische §aKisten")) {
                    if (Cases.getCase((Player)e.getWhoClicked(), "E") > 0) {
                        this.openco((Player)e.getWhoClicked(), "Episch");
                    }
                    else {
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(String.valueOf(Prefix) + "§cDu hast leider keine epischen Truhen übrig.");
                    }
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§d§lSUPREME Kisten")) {
                    if (Cases.getCase((Player)e.getWhoClicked(), "S") > 0) {
                        this.openco((Player)e.getWhoClicked(), "Supreme");
                    }
                    else {
                        e.getWhoClicked().closeInventory();
                        e.getWhoClicked().sendMessage(String.valueOf(Prefix) + "§cDu hast leider keine Supreme Truhen übrig.");
                    }
                }
            }
        }
        else if (e.getInventory().getName().equalsIgnoreCase("§7Seltenheit auswählen")) {
            e.setCancelled(true);
            if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()) {
                if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§aNormal")) {
                    e.getWhoClicked().closeInventory();
                    final ItemStack item = CreateCaseItemCMD.item.get(e.getWhoClicked());
                    final ItemMeta itemm = item.getItemMeta();
                    itemm.setLore((List)Arrays.asList("§aNormal"));
                    item.setItemMeta(itemm);
                    e.getWhoClicked().getInventory().setItemInHand(item);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Selten")) {
                    e.getWhoClicked().closeInventory();
                    final ItemStack item = CreateCaseItemCMD.item.get(e.getWhoClicked());
                    final ItemMeta itemm = item.getItemMeta();
                    itemm.setLore((List)Arrays.asList("§6Selten"));
                    item.setItemMeta(itemm);
                    e.getWhoClicked().getInventory().setItemInHand(item);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§bUltra")) {
                    e.getWhoClicked().closeInventory();
                    final ItemStack item = CreateCaseItemCMD.item.get(e.getWhoClicked());
                    final ItemMeta itemm = item.getItemMeta();
                    itemm.setLore((List)Arrays.asList("§bUltra"));
                    item.setItemMeta(itemm);
                    e.getWhoClicked().getInventory().setItemInHand(item);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§5Episch")) {
                    e.getWhoClicked().closeInventory();
                    final ItemStack item = CreateCaseItemCMD.item.get(e.getWhoClicked());
                    final ItemMeta itemm = item.getItemMeta();
                    itemm.setLore((List)Arrays.asList("§5Episch"));
                    item.setItemMeta(itemm);
                    e.getWhoClicked().getInventory().setItemInHand(item);
                }
                else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6Legendaer")) {
                    e.getWhoClicked().closeInventory();
                    final ItemStack item = CreateCaseItemCMD.item.get(e.getWhoClicked());
                    final ItemMeta itemm = item.getItemMeta();
                    itemm.setLore((List)Arrays.asList("§6Legendaer"));
                    item.setItemMeta(itemm);
                    e.getWhoClicked().getInventory().setItemInHand(item);
                }
            }
        }
    }
    
    @EventHandler
    public void onSave(final InventoryCloseEvent e) {
        if (e.getInventory().getName().equalsIgnoreCase("§6Case §eedit §5§lEpisch")) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = (Player)e.getPlayer();
            final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
            final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
            Integer i1 = 0;
            Integer i2 = 0;
            ItemStack[] contents;
            for (int length = (contents = e.getInventory().getContents()).length, l = 0; l < length; ++l) {
                final ItemStack j = contents[l];
                if (j != null) {
                    ++i1;
                    if (!j.hasItemMeta()) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    if (!j.getItemMeta().hasLore()) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    if (!j.getItemMeta().getLore().get(0).equalsIgnoreCase("§aNormal") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Selten") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§bUltra") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§5Episch") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Legendaer")) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    ++i2;
                }
            }
            if (i1 == i2) {
                e.getPlayer().sendMessage(String.valueOf(Prefix) + "§aInventar erfolgreich gespeichert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                Integer i3 = 0;
                ItemStack[] contents2;
                for (int length2 = (contents2 = e.getInventory().getContents()).length, n = 0; n < length2; ++n) {
                    final ItemStack k = contents2[n];
                    ++i3;
                    yCO.set("Truhe.Episch.Preise." + i3, (Object)k);
                    try {
                        yCO.save(CO);
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
        else if (e.getInventory().getName().equalsIgnoreCase("§6Case §eedit §d§lSupreme")) {
            final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
            final Player p = (Player)e.getPlayer();
            final File CO = new File("plugins/GrieferGames/Data/CaseOpening.yml");
            final YamlConfiguration yCO = YamlConfiguration.loadConfiguration(CO);
            Integer i1 = 0;
            Integer i2 = 0;
            ItemStack[] contents3;
            for (int length3 = (contents3 = e.getInventory().getContents()).length, n2 = 0; n2 < length3; ++n2) {
                final ItemStack j = contents3[n2];
                if (j != null) {
                    ++i1;
                    if (!j.hasItemMeta()) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    if (!j.getItemMeta().hasLore()) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    if (!j.getItemMeta().getLore().get(0).equalsIgnoreCase("§aNormal") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Selten") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§bUltra") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§5Episch") && !j.getItemMeta().getLore().get(0).equalsIgnoreCase("§6Legendaer")) {
                        e.getPlayer().sendMessage(String.valueOf(Prefix) + "§cDas Inventar wurde nicht gespeichert da nicht jedes Item im Inventar eine Seltenheit hat!" + "\n" + "§c/CreateCaseItem");
                        p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1.0f, 1.0f);
                        break;
                    }
                    ++i2;
                }
            }
            if (i1 == i2) {
                e.getPlayer().sendMessage(String.valueOf(Prefix) + "§aInventar erfolgreich gespeichert!");
                p.playSound(p.getLocation(), Sound.LEVEL_UP, 1.0f, 1.0f);
                Integer i3 = 0;
                ItemStack[] contents4;
                for (int length4 = (contents4 = e.getInventory().getContents()).length, n3 = 0; n3 < length4; ++n3) {
                    final ItemStack k = contents4[n3];
                    ++i3;
                    yCO.set("Truhe.Supreme.Preise." + i3, (Object)k);
                    try {
                        yCO.save(CO);
                    }
                    catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
