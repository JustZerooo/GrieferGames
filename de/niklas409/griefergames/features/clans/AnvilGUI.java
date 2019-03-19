package de.niklas409.griefergames.features.clans;

import net.minecraft.server.v1_8_R3.Container;
import net.minecraft.server.v1_8_R3.BlockPosition;
import net.minecraft.server.v1_8_R3.ContainerAnvil;
import org.bukkit.event.HandlerList;
import java.util.Iterator;
import net.minecraft.server.v1_8_R3.EntityPlayer;
import net.minecraft.server.v1_8_R3.ICrafting;
import net.minecraft.server.v1_8_R3.Packet;
import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutOpenWindow;
import net.minecraft.server.v1_8_R3.ChatMessage;
import net.minecraft.server.v1_8_R3.EntityHuman;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.plugin.Plugin;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import java.util.HashMap;
import org.bukkit.entity.Player;

public class AnvilGUI
{
    private Player player;
    private AnvilClickEventHandler handler;
    private HashMap<AnvilSlot, ItemStack> items;
    private Inventory inv;
    private Listener listener;
    
    public AnvilGUI(final Player player, final AnvilClickEventHandler handler) {
        super();
        this.items = new HashMap<AnvilSlot, ItemStack>();
        this.player = player;
        this.handler = handler;
        this.listener = (Listener)new Listener() {
            final /* synthetic */ AnvilGUI this$0;
            private final /* synthetic */ AnvilClickEventHandler val$handler;
            
            AnvilGUI$1() {
                this.this$0 = this$0;
                super();
            }
            
            @EventHandler
            public void onInventoryClick(final InventoryClickEvent event) {
                if (event.getWhoClicked() instanceof Player && event.getInventory().equals(this.this$0.inv)) {
                    event.setCancelled(true);
                    final ItemStack item = event.getCurrentItem();
                    final int slot = event.getRawSlot();
                    String name = "";
                    if (item != null && item.hasItemMeta()) {
                        final ItemMeta meta = item.getItemMeta();
                        if (meta.hasDisplayName()) {
                            name = meta.getDisplayName();
                        }
                    }
                    final AnvilClickEvent clickEvent = this.this$0.new AnvilClickEvent(AnvilSlot.bySlot(slot), name);
                    handler.onAnvilClick(clickEvent);
                    if (clickEvent.getWillClose()) {
                        event.getWhoClicked().closeInventory();
                    }
                    if (clickEvent.getWillDestroy()) {
                        this.this$0.destroy();
                    }
                }
            }
            
            @EventHandler
            public void onInventoryClose(final InventoryCloseEvent event) {
                if (event.getPlayer() instanceof Player) {
                    final Inventory inv = event.getInventory();
                    if (inv.equals(this.this$0.inv)) {
                        inv.clear();
                        this.this$0.destroy();
                    }
                }
            }
            
            @EventHandler
            public void onPlayerQuit(final PlayerQuitEvent event) {
                if (event.getPlayer().equals(this.this$0.getPlayer())) {
                    this.this$0.destroy();
                }
            }
        };
        Bukkit.getPluginManager().registerEvents(this.listener, (Plugin)Main.getInstance());
    }
    
    public Player getPlayer() {
        return this.player;
    }
    
    public void setSlot(final AnvilSlot slot, final ItemStack item) {
        this.items.put(slot, item);
    }
    
    public void open() {
        final EntityPlayer p = ((CraftPlayer)this.player).getHandle();
        final AnvilContainer container = new AnvilContainer((EntityHuman)p);
        this.inv = container.getBukkitView().getTopInventory();
        for (final AnvilSlot slot : this.items.keySet()) {
            this.inv.setItem(slot.getSlot(), (ItemStack)this.items.get(slot));
        }
        final int c = p.nextContainerCounter();
        p.playerConnection.sendPacket((Packet)new PacketPlayOutOpenWindow(c, "minecraft:anvil", (IChatBaseComponent)new ChatMessage("Repairing", new Object[0]), 0));
        p.activeContainer = (Container)container;
        p.activeContainer.windowId = c;
        p.activeContainer.addSlotListener((ICrafting)p);
    }
    
    public void destroy() {
        this.player = null;
        this.handler = null;
        this.items = null;
        HandlerList.unregisterAll(this.listener);
        this.listener = null;
    }
    
    static /* synthetic */ Inventory access$0(final AnvilGUI anvilGUI) {
        return anvilGUI.inv;
    }
}
