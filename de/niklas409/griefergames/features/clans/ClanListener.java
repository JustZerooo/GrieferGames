package de.niklas409.griefergames.features.clans;

import org.bukkit.event.EventHandler;
import org.bukkit.plugin.PluginManager;
import java.util.Iterator;
import net.ess3.api.MaxMoneyException;
import java.math.BigDecimal;
import com.earth2me.essentials.Essentials;
import java.io.IOException;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.entity.Player;
import java.util.HashMap;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.event.Listener;

public class ClanListener implements Listener
{
    private Main plugin;
    public static File Clans;
    public static YamlConfiguration yClans;
    public static HashMap<Player, String> clanname;
    public static HashMap<Player, String> clankürzel;
    public static HashMap<String, String> anfragen;
    public static HashMap<String, String> anfragen2;
    public static HashMap<Player, String> Promotion;
    
    static {
        ClanListener.Clans = new File("plugins/GrieferGames/Clans/Clan.yml");
        ClanListener.yClans = YamlConfiguration.loadConfiguration(ClanListener.Clans);
        ClanListener.clanname = new HashMap<Player, String>();
        ClanListener.clankürzel = new HashMap<Player, String>();
        ClanListener.anfragen = new HashMap<String, String>();
        ClanListener.anfragen2 = new HashMap<String, String>();
        ClanListener.Promotion = new HashMap<Player, String>();
    }
    
    public ClanListener(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents((Listener)this, (Plugin)plugin);
    }
    
    @EventHandler
    public void onClick(final InventoryClickEvent e) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (e.getWhoClicked() instanceof Player) {
            final Player p = (Player)e.getWhoClicked();
            if (e.getInventory().getName().equalsIgnoreCase("§6§lClan-Menü")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClan erstellen")) {
                        p.closeInventory();
                        final AnvilGUI clannameauswahl = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                            final /* synthetic */ ClanListener this$0;
                            private final /* synthetic */ Player val$p;
                            private final /* synthetic */ String val$Prefix;
                            
                            ClanListener$1() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                    e.setWillClose(true);
                                    e.setWillDestroy(true);
                                    final String name = e.getName().replaceAll("Clanname:", "");
                                    if (name.length() >= 4 && name.length() <= 15) {
                                        final List<String> c = (List<String>)ClanListener.yClans.getStringList("Clan");
                                        if (!this.this$0.containsIgnoreCase(c, name)) {
                                            ClanListener.clanname.put(p, name);
                                            p.closeInventory();
                                            Bukkit.getScheduler().runTaskLater((Plugin)this.this$0.plugin, (Runnable)new Runnable() {
                                                final /* synthetic */ ClanListener$1 this$1;
                                                private final /* synthetic */ Player val$p;
                                                private final /* synthetic */ String val$Prefix;
                                                
                                                ClanListener$1$1() {
                                                    this.this$1 = this$1;
                                                    super();
                                                }
                                                
                                                @Override
                                                public void run() {
                                                    final AnvilGUI clankürzelauswahl = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                                                        final /* synthetic */ ClanListener$1$1 this$2;
                                                        private final /* synthetic */ Player val$p;
                                                        private final /* synthetic */ String val$Prefix;
                                                        
                                                        ClanListener$1$1$1() {
                                                            this.this$2 = this$2;
                                                            super();
                                                        }
                                                        
                                                        @Override
                                                        public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                                            if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                                                e.setWillClose(true);
                                                                e.setWillDestroy(true);
                                                                final String name = e.getName().replaceAll("Clankürzel:", "");
                                                                if (name.length() >= 2 && name.length() <= 4) {
                                                                    final List<String> k = (List<String>)ClanListener.yClans.getStringList("Kuerzel");
                                                                    if (!this.this$2.this$1.this$0.containsIgnoreCase(k, name)) {
                                                                        ClanListener.clankürzel.put(p, name);
                                                                        p.closeInventory();
                                                                        Bukkit.getScheduler().runTaskLater((Plugin)this.this$2.this$1.this$0.plugin, (Runnable)new Runnable() {
                                                                            final /* synthetic */ ClanListener$1$1$1 this$3;
                                                                            private final /* synthetic */ Player val$p;
                                                                            
                                                                            ClanListener$1$1$1$1() {
                                                                                this.this$3 = this$3;
                                                                                super();
                                                                            }
                                                                            
                                                                            @Override
                                                                            public void run() {
                                                                                final Inventory ClanAccept = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lClanAccept");
                                                                                ClanAccept.setItem(3, ItemBuilder.createItem(Material.EMERALD_BLOCK, "§2Akzeptieren", 1, new String[] { "§c§lClan erstellen." }));
                                                                                ClanAccept.setItem(5, ItemBuilder.createItem(Material.BARRIER, "§4Nicht §cAkzeptieren", 1, new String[] { "§c§lClan §4§lNICHT §c§lerstellen." }));
                                                                                p.openInventory(ClanAccept);
                                                                            }
                                                                        }, 20L);
                                                                    }
                                                                    else {
                                                                        ClanListener.clanname.remove(p);
                                                                        ClanListener.clankürzel.remove(p);
                                                                        p.sendMessage(String.valueOf(Prefix) + "§cDen Clankürzel gibt es schon!");
                                                                    }
                                                                }
                                                                else {
                                                                    ClanListener.clanname.remove(p);
                                                                    ClanListener.clankürzel.remove(p);
                                                                    p.sendMessage(String.valueOf(Prefix) + "§cDu musst zwischen 1-5 Zeichen verwenden.");
                                                                }
                                                            }
                                                            else {
                                                                e.setWillClose(false);
                                                                e.setWillDestroy(false);
                                                            }
                                                        }
                                                    });
                                                    clankürzelauswahl.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Clankürzel:", 1));
                                                    clankürzelauswahl.open();
                                                }
                                                
                                                static /* synthetic */ ClanListener$1 access$0(final ClanListener$1$1 runnable) {
                                                    return runnable.this$1;
                                                }
                                            }, 20L);
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDen Clannamen gibt es schon.");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst zwischen 3-16 Zeichen verwenden.");
                                    }
                                }
                                else {
                                    e.setWillClose(false);
                                    e.setWillDestroy(false);
                                }
                            }
                            
                            static /* synthetic */ ClanListener access$0(final ClanListener$1 anvilClickEventHandler) {
                                return anvilClickEventHandler.this$0;
                            }
                        });
                        clannameauswahl.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Clanname:", 1));
                        clannameauswahl.open();
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClankonto Optionen")) {
                        final Inventory ClanKontoOptions = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lKontostand-Optionen");
                        ClanKontoOptions.setItem(1, ItemBuilder.createItem(Material.IRON_INGOT, "§6§lEinzahlen", 1, new String[] { "§c§lBetrag einzahlen." }));
                        ClanKontoOptions.setItem(4, ItemBuilder.createItem(Material.GOLD_INGOT, "§6§lAbheben", 1, new String[] { "§c§lBetrag Abheben.(nur als Mod möglich)" }));
                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        final Integer ClanKasse = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Kasse"));
                        ClanKontoOptions.setItem(7, ItemBuilder.createItem(Material.GOLD_BLOCK, "§6§lKontostand", 1, new String[] { "§c§l" + ClanKasse + "§2§l$" }));
                        p.openInventory(ClanKontoOptions);
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClan verlassen")) {
                        final Inventory ClanLeaveMenü = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lLeaveClan-Menü");
                        ClanLeaveMenü.setItem(3, ItemBuilder.createItem(Material.EMERALD_BLOCK, "§2Akzeptieren", 1, new String[] { "§c§lClan verlassen." }));
                        ClanLeaveMenü.setItem(5, ItemBuilder.createItem(Material.BARRIER, "§4Nicht §cAkzeptieren", 1, new String[] { "§c§lClan §4§lNICHT §c§lverlassen." }));
                        p.openInventory(ClanLeaveMenü);
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lSpieler einladen")) {
                        final AnvilGUI clanspieleranfragesenden = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                            final /* synthetic */ ClanListener this$0;
                            private final /* synthetic */ Player val$p;
                            private final /* synthetic */ String val$Prefix;
                            
                            ClanListener$2() {
                                this.this$0 = this$0;
                                super();
                            }
                            
                            @Override
                            public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                    e.setWillClose(true);
                                    e.setWillDestroy(true);
                                    final String name = e.getName().replaceAll("Spieler:", "");
                                    final Player tar = Bukkit.getPlayer(name);
                                    if (tar != null) {
                                        if (ClanListener.yClans.getString("Spieler." + tar.getName() + ".Clan") == null) {
                                            final String owner = p.getName();
                                            final String Clanname = ClanListener.yClans.getString("Spieler." + owner + ".Clan");
                                            final Integer ClanAnzahl = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Anzahl"));
                                            if (ClanAnzahl != 54) {
                                                if (ClanListener.anfragen.get(tar.getName()) != null) {
                                                    if (!ClanListener.anfragen.get(tar.getName()).equalsIgnoreCase(p.getName())) {
                                                        tar.sendMessage(String.valueOf(Prefix) + "§6Der Spieler " + p.getName() + " hat dich in den Clan " + ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") + " eingeladen.");
                                                        p.sendMessage(String.valueOf(Prefix) + "§7Du hast den Spieler " + tar.getName() + " eingeladen.");
                                                        ClanListener.anfragen.put(tar.getName(), p.getName());
                                                    }
                                                    else {
                                                        p.sendMessage(String.valueOf(Prefix) + "§cDu hast den Spieler bereits eingeladen.");
                                                    }
                                                }
                                                else {
                                                    tar.sendMessage(String.valueOf(Prefix) + "§6Der Spieler " + p.getName() + " hat dich in den Clan " + ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan") + " eingeladen.");
                                                    p.sendMessage(String.valueOf(Prefix) + "§7Du hast den Spieler " + tar.getName() + " eingeladen.");
                                                    ClanListener.anfragen.put(tar.getName(), p.getName());
                                                }
                                            }
                                            else {
                                                ClanListener.anfragen.remove(p.getName());
                                                ClanListener.anfragen2.remove(p.getName());
                                                p.closeInventory();
                                                p.sendMessage(String.valueOf(Prefix) + "§cDein Clan ist bereits voll!");
                                            }
                                        }
                                        else {
                                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler hat schon ein Clan!");
                                        }
                                    }
                                    else {
                                        p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist nicht online!");
                                    }
                                }
                                else {
                                    e.setWillClose(false);
                                    e.setWillDestroy(false);
                                }
                            }
                        });
                        clanspieleranfragesenden.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Spieler:", 1));
                        clanspieleranfragesenden.open();
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAusstehende Anfragen")) {
                        if (ClanListener.anfragen.get(p.getName()) != null) {
                            final Inventory InviteMenüSee = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lInvite-Menü");
                            final ItemStack i = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            final SkullMeta im = (SkullMeta)i.getItemMeta();
                            im.setOwner((String)ClanListener.anfragen.get(p.getName()));
                            im.setDisplayName("§6§l" + ClanListener.anfragen.get(p.getName()));
                            i.setItemMeta((ItemMeta)im);
                            InviteMenüSee.addItem(new ItemStack[] { i });
                            p.openInventory(InviteMenüSee);
                        }
                        else {
                            final Inventory InviteMenüSee = Bukkit.createInventory((InventoryHolder)null, 27, "§6§lInvite-Menü");
                            p.openInventory(InviteMenüSee);
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lClanmitglieder anzeigen")) {
                        final String Clanname2 = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        final Inventory Clanmitglieder = Bukkit.createInventory((InventoryHolder)null, 54, "§6§lClanmitglieder");
                        for (final String clanmitglied : ClanListener.yClans.getStringList("Mitglieder von Clan " + Clanname2)) {
                            final ItemStack item = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
                            final SkullMeta meta = (SkullMeta)item.getItemMeta();
                            meta.setDisplayName("§6§l" + clanmitglied);
                            meta.setOwner(clanmitglied);
                            item.setItemMeta((ItemMeta)meta);
                            Clanmitglieder.addItem(new ItemStack[] { item });
                        }
                        p.openInventory(Clanmitglieder);
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lClanAccept")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Akzeptieren")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§7Du hast den Clan §a" + ClanListener.clanname.get(p) + " §7mit dem Clankürzel §a" + ClanListener.clankürzel.get(p) + " §7erstellt.");
                        ClanListener.yClans.set("Clans." + ClanListener.clanname.get(p) + ".Name", (Object)ClanListener.clanname.get(p));
                        ClanListener.yClans.set("Clans." + ClanListener.clanname.get(p) + ".Kuerzel", (Object)ClanListener.clankürzel.get(p));
                        ClanListener.yClans.set("Clans." + ClanListener.clanname.get(p) + ".Anzahl", (Object)"1");
                        ClanListener.yClans.set("Clans." + ClanListener.clanname.get(p) + ".Kasse", (Object)"0");
                        ClanListener.yClans.set("Clans." + ClanListener.clanname.get(p) + ".Owner", (Object)p.getName());
                        ClanListener.yClans.set("Spieler." + p.getName() + ".Clan", (Object)ClanListener.clanname.get(p));
                        final List<String> mitglieder = (List<String>)ClanListener.yClans.getStringList("Mitglieder von Clan " + ClanListener.clanname.get(p));
                        mitglieder.add(p.getName());
                        ClanListener.yClans.set("Mitglieder von Clan " + ClanListener.clanname.get(p), (Object)mitglieder);
                        final List<String> Kuerzel = (List<String>)ClanListener.yClans.getStringList("Kuerzel");
                        Kuerzel.add(ClanListener.clankürzel.get(p));
                        ClanListener.yClans.set("Kuerzel", (Object)Kuerzel);
                        final List<String> Clan = (List<String>)ClanListener.yClans.getStringList("Clan");
                        Clan.add(ClanListener.clanname.get(p));
                        ClanListener.yClans.set("Clan", (Object)Clan);
                        try {
                            ClanListener.yClans.save(ClanListener.Clans);
                        }
                        catch (IOException e2) {
                            e2.printStackTrace();
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Nicht §cAkzeptieren")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§cDu hast den Clan §4NICHT §cerstellt.");
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lKontostand-Optionen")) {
                final PluginManager manager = Bukkit.getServer().getPluginManager();
                final Plugin Essentials = manager.getPlugin("Essentials");
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lEinzahlen")) {
                        if (Essentials != null && Essentials.isEnabled()) {
                            final AnvilGUI einzahlen = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                                Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                                final /* synthetic */ ClanListener this$0;
                                private final /* synthetic */ Player val$p;
                                private final /* synthetic */ String val$Prefix;
                                
                                ClanListener$3() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                        e.setWillClose(true);
                                        e.setWillDestroy(true);
                                        final String betrag = e.getName().replaceAll("Einzahlen:", "");
                                        final Integer money = this.ess.getUser(p).getMoney().intValue();
                                        if (betrag.matches("[0-9]+")) {
                                            if (money >= Integer.valueOf(betrag)) {
                                                final BigDecimal neuesgeld = BigDecimal.valueOf(money - Integer.valueOf(betrag));
                                                try {
                                                    this.ess.getUser(p).setMoney(neuesgeld);
                                                }
                                                catch (MaxMoneyException e2) {
                                                    e2.printStackTrace();
                                                }
                                                final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                                                final Integer ClanKasse = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Kasse"));
                                                ClanListener.yClans.set("Clans." + Clanname + ".Kasse", (Object)(ClanKasse + Integer.valueOf(betrag)));
                                                try {
                                                    ClanListener.yClans.save(ClanListener.Clans);
                                                }
                                                catch (IOException e3) {
                                                    e3.printStackTrace();
                                                }
                                                p.closeInventory();
                                                p.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + betrag + "$ §7in die Clankasse gespendet.");
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDu hast nicht genug Geld!");
                                            }
                                        }
                                        else {
                                            ClanListener.clanname.remove(p);
                                            ClanListener.clankürzel.remove(p);
                                            p.sendMessage(String.valueOf(Prefix) + "§cDu musst eine Zahl verwenden.");
                                        }
                                    }
                                    else {
                                        e.setWillClose(false);
                                        e.setWillDestroy(false);
                                    }
                                }
                            });
                            einzahlen.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Einzahlen:", 1));
                            einzahlen.open();
                        }
                        else {
                            p.closeInventory();
                            if (p.hasPermission("system.essentials.error.see")) {
                                p.sendMessage(String.valueOf(Prefix) + "§4Diese Funktion konnte nicht aktiviert werden!");
                                p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials!");
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDiese Funktion ist deaktiviert, bitte frage einen Admin für mehr Informationen!");
                            }
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lAbheben")) {
                        if (Essentials != null && Essentials.isEnabled()) {
                            final AnvilGUI abheben = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                                Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                                final /* synthetic */ ClanListener this$0;
                                private final /* synthetic */ Player val$p;
                                private final /* synthetic */ String val$Prefix;
                                
                                ClanListener$4() {
                                    this.this$0 = this$0;
                                    super();
                                }
                                
                                @Override
                                public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                        e.setWillClose(true);
                                        e.setWillDestroy(true);
                                        final String betrag = e.getName().replaceAll("Abheben:", "");
                                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                                        final String ClanOwner = ClanListener.yClans.getString("Clans." + Clanname + ".Owner");
                                        final Integer ClanKasse = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Kasse"));
                                        final Integer money = this.ess.getUser(p).getMoney().intValue();
                                        if (!p.getName().equalsIgnoreCase(ClanOwner)) {
                                            final String ClanMod = ClanListener.yClans.getString("Clans." + Clanname + ".Mod");
                                            if (ClanMod != null) {
                                                if (ClanMod.equalsIgnoreCase(p.getName())) {
                                                    if (betrag.matches("[0-9]+")) {
                                                        if (ClanKasse >= Integer.valueOf(betrag)) {
                                                            final BigDecimal neuesgeld = BigDecimal.valueOf(money + Integer.valueOf(betrag));
                                                            try {
                                                                this.ess.getUser(p).setMoney(neuesgeld);
                                                            }
                                                            catch (MaxMoneyException e2) {
                                                                e2.printStackTrace();
                                                            }
                                                            ClanListener.yClans.set("Clans." + Clanname + ".Kasse", (Object)(ClanKasse - Integer.valueOf(betrag)));
                                                            try {
                                                                ClanListener.yClans.save(ClanListener.Clans);
                                                            }
                                                            catch (IOException e3) {
                                                                e3.printStackTrace();
                                                            }
                                                            p.closeInventory();
                                                            p.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + betrag + "$ §7von der Clankasse abgehoben.");
                                                        }
                                                        else {
                                                            p.sendMessage(String.valueOf(Prefix) + "§cSo viel Geld hat euer Clan nicht!");
                                                        }
                                                    }
                                                    else {
                                                        ClanListener.clanname.remove(p);
                                                        ClanListener.clankürzel.remove(p);
                                                        p.sendMessage(String.valueOf(Prefix) + "§cDu musst eine Zahl verwenden.");
                                                    }
                                                }
                                                else {
                                                    p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Clan-Mod!");
                                                }
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Clan-Mod!");
                                            }
                                        }
                                        else if (betrag.matches("[0-9]+")) {
                                            if (ClanKasse >= Integer.valueOf(betrag)) {
                                                final BigDecimal neuesgeld2 = BigDecimal.valueOf(money + Integer.valueOf(betrag));
                                                try {
                                                    this.ess.getUser(p).setMoney(neuesgeld2);
                                                }
                                                catch (MaxMoneyException e4) {
                                                    e4.printStackTrace();
                                                }
                                                ClanListener.yClans.set("Clans." + Clanname + ".Kasse", (Object)(ClanKasse - Integer.valueOf(betrag)));
                                                try {
                                                    ClanListener.yClans.save(ClanListener.Clans);
                                                }
                                                catch (IOException e5) {
                                                    e5.printStackTrace();
                                                }
                                                p.closeInventory();
                                                p.sendMessage(String.valueOf(Prefix) + "§7Du hast §a" + betrag + "$ §7von der Clankasse abgehoben.");
                                            }
                                            else {
                                                p.sendMessage(String.valueOf(Prefix) + "§cSo viel Geld hat euer Clan nicht!");
                                            }
                                        }
                                        else {
                                            ClanListener.clanname.remove(p);
                                            ClanListener.clankürzel.remove(p);
                                            p.sendMessage(String.valueOf(Prefix) + "§cDu musst eine Zahl verwenden.");
                                        }
                                    }
                                    else {
                                        e.setWillClose(false);
                                        e.setWillDestroy(false);
                                    }
                                }
                            });
                            abheben.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Abheben:", 1));
                            abheben.open();
                        }
                        else {
                            p.closeInventory();
                            if (p.hasPermission("system.essentials.error.see")) {
                                p.sendMessage(String.valueOf(Prefix) + "§4Diese Funktion konnte nicht aktiviert werden!");
                                p.sendMessage(String.valueOf(Prefix) + "§4Du benötigst das Plugin Essentials!");
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDiese Funktion ist deaktiviert, bitte frage einen Admin für mehr Informationen!");
                            }
                        }
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lLeaveClan-Menü")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Akzeptieren")) {
                        final String Clanname2 = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        if (ClanListener.yClans.getString("Clans." + Clanname2 + ".Owner").equalsIgnoreCase(p.getName())) {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§7Du hast dein Clan verlassen.");
                            ClanListener.yClans.set("Clans." + Clanname2, (Object)null);
                            ClanListener.yClans.set("Spieler." + p.getName(), (Object)null);
                            ClanListener.yClans.set("Mitglieder von Clan " + ClanListener.clanname.get(p), (Object)null);
                            ClanListener.yClans.set("Mitglieder von Clan " + Clanname2, (Object)null);
                            final List<String> Kuerzel = (List<String>)ClanListener.yClans.getStringList("Kuerzel");
                            Kuerzel.remove(ClanListener.clankürzel.get(p));
                            ClanListener.yClans.set("Kuerzel", (Object)Kuerzel);
                            final List<String> Clan = (List<String>)ClanListener.yClans.getStringList("Clan");
                            Clan.remove(ClanListener.clanname.get(p));
                            ClanListener.yClans.set("Clan", (Object)Clan);
                            try {
                                ClanListener.yClans.save(ClanListener.Clans);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§7Du hast dein Clan verlassen.");
                            final String ClanMod = ClanListener.yClans.getString("Clans." + Clanname2 + ".Mod");
                            if (ClanMod != null && ClanMod.equalsIgnoreCase(p.getName())) {
                                ClanListener.yClans.set("Clans." + Clanname2 + ".Mod", (Object)null);
                            }
                            ClanListener.yClans.set("Spieler." + p.getName(), (Object)null);
                            final Integer ClanAnzahl = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname2 + ".Anzahl"));
                            ClanListener.yClans.set("Clans." + Clanname2 + ".Anzahl", (Object)(ClanAnzahl - 1));
                            final List<String> mitglieder2 = (List<String>)ClanListener.yClans.getStringList("Mitglieder von Clan " + Clanname2);
                            mitglieder2.remove(p.getName());
                            ClanListener.yClans.set("Mitglieder von Clan " + Clanname2, (Object)mitglieder2);
                            try {
                                ClanListener.yClans.save(ClanListener.Clans);
                            }
                            catch (IOException e3) {
                                e3.printStackTrace();
                            }
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Nicht §cAkzeptieren")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§cDu hast dein Clan nicht verlassen.");
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lInvite-Menü")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    ClanListener.anfragen2.put(p.getName(), ClanListener.anfragen.get(p.getName()));
                    final Inventory InviteAnnehmen = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lEinladung");
                    InviteAnnehmen.setItem(3, ItemBuilder.createItem(Material.EMERALD_BLOCK, "§2Akzeptieren", 1, new String[] { "§c§lEinladung annehmen." }));
                    InviteAnnehmen.setItem(5, ItemBuilder.createItem(Material.BARRIER, "§4Nicht §cAkzeptieren", 1, new String[] { "§c§lEinladung ablehnen." }));
                    p.openInventory(InviteAnnehmen);
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lEinladung")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§2Akzeptieren")) {
                        final String owner = ClanListener.anfragen2.get(p.getName());
                        final String Clanname = ClanListener.yClans.getString("Spieler." + owner + ".Clan");
                        final Integer ClanAnzahl = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Anzahl"));
                        if (ClanAnzahl != 54) {
                            ClanListener.yClans.set("Spieler." + p.getName() + ".Clan", (Object)Clanname);
                            ClanListener.yClans.set("Clans." + Clanname + ".Anzahl", (Object)(ClanAnzahl + 1));
                            final List<String> mitglieder2 = (List<String>)ClanListener.yClans.getStringList("Mitglieder von Clan " + Clanname);
                            mitglieder2.add(p.getName());
                            ClanListener.yClans.set("Mitglieder von Clan " + Clanname, (Object)mitglieder2);
                            try {
                                ClanListener.yClans.save(ClanListener.Clans);
                            }
                            catch (IOException e3) {
                                e3.printStackTrace();
                            }
                            ClanListener.anfragen.remove(p.getName());
                            ClanListener.anfragen2.remove(p.getName());
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§2Du hast die Einladung erfolgreich akzeptiert!");
                        }
                        else {
                            ClanListener.anfragen.remove(p.getName());
                            ClanListener.anfragen2.remove(p.getName());
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Clan ist bereits voll!");
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§4Nicht §cAkzeptieren")) {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§cDu hast die Einladung abgelehnt.");
                        ClanListener.anfragen.remove(p.getName());
                        ClanListener.anfragen2.remove(p.getName());
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lClanmitglieder")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    final String Clanname2 = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                    final String ClanOwner = ClanListener.yClans.getString("Clans." + Clanname2 + ".Owner");
                    if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l" + ClanOwner)) {
                        if (p.getName().equalsIgnoreCase(ClanOwner)) {
                            final Inventory Promotion = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lPromotion");
                            Promotion.setItem(1, ItemBuilder.createItem(Material.GOLDEN_APPLE, "§6§lMod", 1, new String[] { "§c§lSetzt " + e.getCurrentItem().getItemMeta().getDisplayName() + " §c§lin die Gruppe Moderator." }));
                            Promotion.setItem(4, ItemBuilder.createItem(Material.APPLE, "§6§lMember", 1, new String[] { "§c§lSetzt " + e.getCurrentItem().getItemMeta().getDisplayName() + " §c§lin die Gruppe Spieler." }));
                            Promotion.setItem(7, ItemBuilder.createItem(Material.BARRIER, "§6§lKick Spieler", 1, new String[] { "§c§lSpieler aus dem Clan werfen." }));
                            ClanListener.Promotion.put(p, e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§6§l", ""));
                            p.openInventory(Promotion);
                        }
                        else if (ClanListener.yClans.getString("Clans." + Clanname2 + ".Mod") != null) {
                            final String ClanMod2 = ClanListener.yClans.getString("Clans." + Clanname2 + ".Mod");
                            if (p.getName().equalsIgnoreCase(ClanMod2)) {
                                if (!e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§l" + p.getName())) {
                                    final Inventory Promotion2 = Bukkit.createInventory((InventoryHolder)null, 9, "§6§lPromotion");
                                    Promotion2.setItem(1, ItemBuilder.createItem(Material.GOLDEN_APPLE, "§6§lMod", 1, new String[] { "§c§lSetzt " + e.getCurrentItem().getItemMeta().getDisplayName() + " §c§lin die Gruppe Moderator." }));
                                    Promotion2.setItem(4, ItemBuilder.createItem(Material.APPLE, "§6§lMember", 1, new String[] { "§c§lSetzt " + e.getCurrentItem().getItemMeta().getDisplayName() + " §c§lin die Gruppe Spieler." }));
                                    Promotion2.setItem(7, ItemBuilder.createItem(Material.BARRIER, "§6§lKick Spieler", 1, new String[] { "§c§lSpieler aus dem Clan werfen." }));
                                    ClanListener.Promotion.put(p, e.getCurrentItem().getItemMeta().getDisplayName().replaceAll("§6§l", ""));
                                    p.openInventory(Promotion2);
                                }
                                else {
                                    p.closeInventory();
                                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst nicht deine eigenen Rechte ändern.");
                                }
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Clan-Mod");
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Clan-Mod");
                        }
                    }
                    else {
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§cDu kannst die Rechte vom Clan-Owner nicht ändern.");
                    }
                }
            }
            else if (e.getInventory().getName().equalsIgnoreCase("§6§lPromotion")) {
                e.setCancelled(true);
                if (e.getCurrentItem() != null && e.getCurrentItem().getType() != Material.AIR && e.getCurrentItem().hasItemMeta()) {
                    final String Playername = ClanListener.Promotion.get(p);
                    if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lMod")) {
                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        final String ClanMod2 = ClanListener.yClans.getString("Clans." + Clanname + ".Mod");
                        if (ClanMod2 == null) {
                            ClanListener.yClans.set("Clans." + Clanname + ".Mod", (Object)Playername);
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§6§lDu hast den Spieler " + Playername + " zum Clan-Mod gemacht!");
                            try {
                                ClanListener.yClans.save(ClanListener.Clans);
                            }
                            catch (IOException e2) {
                                e2.printStackTrace();
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler " + ClanMod2 + " ist schon Mod!");
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lMember")) {
                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        final String ClanMod2 = ClanListener.yClans.getString("Clans." + Clanname + ".Mod");
                        if (ClanMod2 != null) {
                            if (ClanMod2.equalsIgnoreCase(Playername)) {
                                ClanListener.yClans.set("Clans." + Clanname + ".Mod", (Object)null);
                                try {
                                    ClanListener.yClans.save(ClanListener.Clans);
                                }
                                catch (IOException e2) {
                                    e2.printStackTrace();
                                }
                                p.closeInventory();
                                p.sendMessage(String.valueOf(Prefix) + "§6§lDu hast den Spieler " + Playername + " zum Member gemacht!");
                            }
                            else {
                                p.closeInventory();
                                p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits Member.");
                            }
                        }
                        else {
                            p.closeInventory();
                            p.sendMessage(String.valueOf(Prefix) + "§cDer Spieler ist bereits Member.");
                        }
                    }
                    else if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§6§lKick Spieler")) {
                        final String Clanname = ClanListener.yClans.getString("Spieler." + p.getName() + ".Clan");
                        p.closeInventory();
                        p.sendMessage(String.valueOf(Prefix) + "§6§lDu hast den Spieler " + Playername + " §6§laus den Clan geworfen!");
                        final String ClanMod2 = ClanListener.yClans.getString("Clans." + Clanname + ".Mod");
                        if (ClanMod2 != null && ClanMod2.equalsIgnoreCase(Playername)) {
                            ClanListener.yClans.set("Clans." + Clanname + ".Mod", (Object)null);
                        }
                        ClanListener.yClans.set("Spieler." + Playername, (Object)null);
                        final Integer ClanAnzahl2 = Integer.valueOf(ClanListener.yClans.getString("Clans." + Clanname + ".Anzahl"));
                        ClanListener.yClans.set("Clans." + Clanname + ".Anzahl", (Object)(ClanAnzahl2 - 1));
                        final List<String> mitglieder3 = (List<String>)ClanListener.yClans.getStringList("Mitglieder von Clan " + Clanname);
                        mitglieder3.remove(Playername);
                        ClanListener.yClans.set("Mitglieder von Clan " + Clanname, (Object)mitglieder3);
                        try {
                            ClanListener.yClans.save(ClanListener.Clans);
                        }
                        catch (IOException e4) {
                            e4.printStackTrace();
                        }
                    }
                }
            }
        }
    }
    
    public boolean containsIgnoreCase(final List<String> l, final String s) {
        final Iterator<String> it = l.iterator();
        while (it.hasNext()) {
            if (it.next().equalsIgnoreCase(s)) {
                return true;
            }
        }
        return false;
    }
    
    static /* synthetic */ Main access$0(final ClanListener clanListener) {
        return clanListener.plugin;
    }
}
