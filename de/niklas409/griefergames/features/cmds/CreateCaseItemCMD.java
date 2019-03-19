package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.inventory.Inventory;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.clans.AnvilGUI;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.inventory.ItemStack;
import org.bukkit.entity.Player;
import java.util.HashMap;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class CreateCaseItemCMD implements CommandExecutor
{
    private static Main plugin;
    public static HashMap<Player, ItemStack> item;
    
    static {
        CreateCaseItemCMD.item = new HashMap<Player, ItemStack>();
    }
    
    public CreateCaseItemCMD(final Main plugin) {
        super();
        CreateCaseItemCMD.plugin = plugin;
        plugin.getCommand("createcaseitem").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = CreateCaseItemCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + CreateCaseItemCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.case")) {
                if (p.getItemInHand().getType() != Material.AIR) {
                    final ItemStack item = new ItemStack(p.getItemInHand());
                    CreateCaseItemCMD.item.clear();
                    final AnvilGUI anzahlauswahl = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                        final /* synthetic */ CreateCaseItemCMD this$0;
                        private final /* synthetic */ ItemStack val$item;
                        private final /* synthetic */ Player val$p;
                        private final /* synthetic */ String val$Prefix;
                        
                        CreateCaseItemCMD$1() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                            if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                e.setWillClose(true);
                                e.setWillDestroy(true);
                                final String anzahl = e.getName().replaceAll("Anzahl von Item:", "");
                                if (anzahl.matches("[0-9]+")) {
                                    final Integer anzahli = Integer.valueOf(anzahl);
                                    item.setAmount((int)anzahli);
                                    Bukkit.getScheduler().runTaskLater((Plugin)CreateCaseItemCMD.plugin, (Runnable)new Runnable() {
                                        final /* synthetic */ CreateCaseItemCMD$1 this$1;
                                        private final /* synthetic */ Player val$p;
                                        private final /* synthetic */ ItemStack val$item;
                                        
                                        CreateCaseItemCMD$1$1() {
                                            this.this$1 = this$1;
                                            super();
                                        }
                                        
                                        @Override
                                        public void run() {
                                            final AnvilGUI nameauswahl = new AnvilGUI(p, new AnvilGUI.AnvilClickEventHandler() {
                                                final /* synthetic */ CreateCaseItemCMD$1$1 this$2;
                                                private final /* synthetic */ ItemStack val$item;
                                                private final /* synthetic */ Player val$p;
                                                
                                                CreateCaseItemCMD$1$1$1() {
                                                    this.this$2 = this$2;
                                                    super();
                                                }
                                                
                                                @Override
                                                public void onAnvilClick(final AnvilGUI.AnvilClickEvent e) {
                                                    if (e.getSlot() == AnvilGUI.AnvilSlot.OUTPUT) {
                                                        e.setWillClose(true);
                                                        e.setWillDestroy(true);
                                                        final String name = e.getName().replaceAll("Itemname:", "");
                                                        final ItemMeta itemm = item.getItemMeta();
                                                        itemm.setDisplayName(name);
                                                        item.setItemMeta(itemm);
                                                        CreateCaseItemCMD.item.put(p, item);
                                                        Bukkit.getScheduler().runTaskLater((Plugin)CreateCaseItemCMD.plugin, (Runnable)new Runnable() {
                                                            final /* synthetic */ CreateCaseItemCMD$1$1$1 this$3;
                                                            private final /* synthetic */ Player val$p;
                                                            
                                                            CreateCaseItemCMD$1$1$1$1() {
                                                                this.this$3 = this$3;
                                                                super();
                                                            }
                                                            
                                                            @Override
                                                            public void run() {
                                                                Bukkit.getScheduler().runTaskLater((Plugin)CreateCaseItemCMD.plugin, (Runnable)new Runnable() {
                                                                    final /* synthetic */ CreateCaseItemCMD$1$1$1$1 this$4;
                                                                    private final /* synthetic */ Player val$p;
                                                                    
                                                                    CreateCaseItemCMD$1$1$1$1$1() {
                                                                        this.this$4 = this$4;
                                                                        super();
                                                                    }
                                                                    
                                                                    @Override
                                                                    public void run() {
                                                                        final Inventory sets = Bukkit.createInventory((InventoryHolder)null, 9, "§7Seltenheit auswählen");
                                                                        sets.setItem(0, ItemBuilder.createItemOL(Material.STONE, "§aNormal", 1));
                                                                        sets.setItem(2, ItemBuilder.createItemOL(Material.DIAMOND, "§6Selten", 1));
                                                                        sets.setItem(4, ItemBuilder.createItemOL(Material.BEACON, "§bUltra", 1));
                                                                        sets.setItem(6, ItemBuilder.createItemOL(Material.DRAGON_EGG, "§5Episch", 1));
                                                                        sets.setItem(8, ItemBuilder.createItemOL(Material.COMMAND, "§6Legendaer", 1));
                                                                        p.openInventory(sets);
                                                                    }
                                                                }, 20L);
                                                            }
                                                        }, 20L);
                                                    }
                                                    else {
                                                        e.setWillClose(false);
                                                        e.setWillDestroy(false);
                                                    }
                                                }
                                            });
                                            nameauswahl.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Itemname:", 1));
                                            nameauswahl.open();
                                        }
                                    }, 20L);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§a" + anzahl + " §cist keine Zahl!");
                                }
                            }
                            else {
                                e.setWillClose(false);
                                e.setWillDestroy(false);
                            }
                        }
                    });
                    anzahlauswahl.setSlot(AnvilGUI.AnvilSlot.INPUT_LEFT, ItemBuilder.createItemOL(Material.NAME_TAG, "Anzahl von Item:", 1));
                    anzahlauswahl.open();
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDu musst ein Item in der Hand haben!");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDen Command kann nur ein Spieler ausführen!");
        }
        return true;
    }
    
    static /* synthetic */ Main access$0() {
        return CreateCaseItemCMD.plugin;
    }
}
