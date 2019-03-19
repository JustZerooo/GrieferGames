package de.niklas409.griefergames.features.cmds;

import org.bukkit.Location;
import org.bukkit.util.Vector;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.entity.Creeper;
import org.bukkit.entity.Zombie;
import java.util.List;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.entity.ThrownExpBottle;
import org.bukkit.entity.Bat;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.plugin.Plugin;
import java.util.Iterator;
import org.bukkit.entity.Item;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.CreatureType;
import org.bukkit.entity.Pig;
import org.bukkit.enchantments.Enchantment;
import java.util.Random;
import org.bukkit.block.Block;
import org.bukkit.Bukkit;
import de.niklas409.griefergames.features.main.ItemBuilder;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import org.bukkit.command.CommandExecutor;

public class LuckyBlockCMD implements CommandExecutor
{
    public static ArrayList<Player> FakeDias;
    public static ArrayList<Player> ByeInv;
    private static Main plugin;
    
    static {
        LuckyBlockCMD.FakeDias = new ArrayList<Player>();
        LuckyBlockCMD.ByeInv = new ArrayList<Player>();
    }
    
    public LuckyBlockCMD(final Main plugin) {
        super();
        LuckyBlockCMD.plugin = plugin;
        plugin.getCommand("luckyblock").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = LuckyBlockCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + LuckyBlockCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.luckyblock.give")) {
                p.getInventory().addItem(new ItemStack[] { ItemBuilder.createItem(Material.SPONGE, "§6§lLuckyBlock", 1, new String[] { "§4§lby Niklas409" }) });
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie §4§lConsole §ckann keine LuckyBloecke annehmen.");
        }
        return true;
    }
    
    public static void onLuckyBlockEvents(final Player p, final Block b) {
        final World w = b.getWorld();
        final Random r = new Random();
        final int zufall = r.nextInt(18);
        switch (zufall) {
            case 0: {
                final ItemStack i1 = new ItemStack(Material.STICK);
                final ItemMeta im1 = i1.getItemMeta();
                im1.setDisplayName("§4§lOp-Stock");
                im1.addEnchant(Enchantment.KNOCKBACK, 10, true);
                i1.setItemMeta(im1);
                p.getWorld().dropItemNaturally(b.getLocation(), i1);
                break;
            }
            case 1: {
                final Pig pig = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                final Pig pig2 = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                final Pig pig3 = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                final Pig pig4 = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                final Pig pig5 = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                final Pig pig6 = (Pig)p.getWorld().spawnCreature(b.getLocation(), CreatureType.PIG);
                pig.setPassenger((Entity)pig2);
                pig2.setPassenger((Entity)pig3);
                pig3.setPassenger((Entity)pig4);
                pig4.setPassenger((Entity)pig5);
                pig5.setPassenger((Entity)pig6);
                break;
            }
            case 2: {
                final ItemStack i2 = new ItemStack(Material.DIAMOND_SWORD);
                final ItemMeta im2 = i2.getItemMeta();
                im2.setDisplayName("§4§lOp-Schwert");
                im2.addEnchant(Enchantment.SILK_TOUCH, 5, true);
                im2.addEnchant(Enchantment.KNOCKBACK, 5, true);
                im2.addEnchant(Enchantment.FIRE_ASPECT, 5, true);
                im2.addEnchant(Enchantment.LOOT_BONUS_MOBS, 5, true);
                i2.setItemMeta(im2);
                p.getWorld().dropItemNaturally(b.getLocation(), i2);
                break;
            }
            case 3: {
                final ItemStack i3 = new ItemStack(Material.DIAMOND, 8);
                final ItemMeta im3 = i3.getItemMeta();
                im3.setDisplayName("§5§lTrololololo");
                i3.setItemMeta(im3);
                w.dropItemNaturally(b.getLocation(), i3);
                LuckyBlockCMD.FakeDias.add(p);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ World val$w;
                    private final /* synthetic */ Block val$b;
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$1() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        for (final Entity e : w.getEntities()) {
                            if (e instanceof Item && e.getLocation().distance(b.getLocation()) < 3.0) {
                                e.remove();
                            }
                        }
                        LuckyBlockCMD.FakeDias.remove(p);
                    }
                }, 200L);
                break;
            }
            case 4: {
                final TNTPrimed tnt = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt.setFuseTicks(60);
                final TNTPrimed tnt2 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt2.setFuseTicks(60);
                final TNTPrimed tnt3 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt3.setFuseTicks(60);
                final TNTPrimed tnt4 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt4.setFuseTicks(60);
                final TNTPrimed tnt5 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt5.setFuseTicks(60);
                final TNTPrimed tnt6 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt6.setFuseTicks(60);
                final TNTPrimed tnt7 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt7.setFuseTicks(60);
                final TNTPrimed tnt8 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt8.setFuseTicks(60);
                final TNTPrimed tnt9 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt9.setFuseTicks(60);
                final TNTPrimed tnt10 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                tnt10.setFuseTicks(60);
                break;
            }
            case 5: {
                final Firework firework = (Firework)b.getWorld().spawn(b.getLocation().add(1.0, 0.0, 0.0), (Class)Firework.class);
                final Firework firework2 = (Firework)b.getWorld().spawn(b.getLocation().add(-1.0, 0.0, 0.0), (Class)Firework.class);
                final Firework firework3 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, 1.0), (Class)Firework.class);
                final Firework firework4 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, -1.0), (Class)Firework.class);
                final FireworkEffect effect4 = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                final FireworkMeta meta4 = firework4.getFireworkMeta();
                meta4.addEffect(effect4);
                meta4.setPower(1);
                firework.setFireworkMeta(meta4);
                firework2.setFireworkMeta(meta4);
                firework3.setFireworkMeta(meta4);
                firework4.setFireworkMeta(meta4);
                final ItemStack i4 = new ItemStack(Material.DIAMOND);
                b.getWorld().dropItemNaturally(b.getLocation().add(1.0, 0.0, 0.0), i4);
                b.getWorld().dropItemNaturally(b.getLocation().add(-1.0, 0.0, 0.0), i4);
                b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, 1.0), i4);
                b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, -1.0), i4);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Block val$b;
                    
                    LuckyBlockCMD$2() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        final Firework firework = (Firework)b.getWorld().spawn(b.getLocation().add(1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework2 = (Firework)b.getWorld().spawn(b.getLocation().add(-1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework3 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, 1.0), (Class)Firework.class);
                        final Firework firework4 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, -1.0), (Class)Firework.class);
                        final FireworkEffect effect4 = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                        final FireworkMeta meta4 = firework4.getFireworkMeta();
                        meta4.addEffect(effect4);
                        meta4.setPower(1);
                        firework.setFireworkMeta(meta4);
                        firework2.setFireworkMeta(meta4);
                        firework3.setFireworkMeta(meta4);
                        firework4.setFireworkMeta(meta4);
                        final ItemStack i4 = new ItemStack(Material.DIAMOND);
                        b.getWorld().dropItemNaturally(b.getLocation().add(1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(-1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, 1.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, -1.0), i4);
                    }
                }, 10L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Block val$b;
                    
                    LuckyBlockCMD$3() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        final Firework firework = (Firework)b.getWorld().spawn(b.getLocation().add(1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework2 = (Firework)b.getWorld().spawn(b.getLocation().add(-1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework3 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, 1.0), (Class)Firework.class);
                        final Firework firework4 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, -1.0), (Class)Firework.class);
                        final FireworkEffect effect4 = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                        final FireworkMeta meta4 = firework4.getFireworkMeta();
                        meta4.addEffect(effect4);
                        meta4.setPower(1);
                        firework.setFireworkMeta(meta4);
                        firework2.setFireworkMeta(meta4);
                        firework3.setFireworkMeta(meta4);
                        firework4.setFireworkMeta(meta4);
                        final ItemStack i4 = new ItemStack(Material.DIAMOND);
                        b.getWorld().dropItemNaturally(b.getLocation().add(1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(-1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, 1.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, -1.0), i4);
                    }
                }, 20L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Block val$b;
                    
                    LuckyBlockCMD$4() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        final Firework firework = (Firework)b.getWorld().spawn(b.getLocation().add(1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework2 = (Firework)b.getWorld().spawn(b.getLocation().add(-1.0, 0.0, 0.0), (Class)Firework.class);
                        final Firework firework3 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, 1.0), (Class)Firework.class);
                        final Firework firework4 = (Firework)b.getWorld().spawn(b.getLocation().add(0.0, 0.0, -1.0), (Class)Firework.class);
                        final FireworkEffect effect4 = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                        final FireworkMeta meta4 = firework4.getFireworkMeta();
                        meta4.addEffect(effect4);
                        meta4.setPower(1);
                        firework.setFireworkMeta(meta4);
                        firework2.setFireworkMeta(meta4);
                        firework3.setFireworkMeta(meta4);
                        firework4.setFireworkMeta(meta4);
                        final ItemStack i4 = new ItemStack(Material.DIAMOND);
                        b.getWorld().dropItemNaturally(b.getLocation().add(1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(-1.0, 0.0, 0.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, 1.0), i4);
                        b.getWorld().dropItemNaturally(b.getLocation().add(0.0, 0.0, -1.0), i4);
                    }
                }, 30L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ World val$w;
                    private final /* synthetic */ Block val$b;
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$5() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        final TNTPrimed tnt = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                        tnt.setFuseTicks(60);
                        final TNTPrimed tnt2 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                        tnt2.setFuseTicks(60);
                        final TNTPrimed tnt3 = (TNTPrimed)w.spawnEntity(b.getLocation(), EntityType.PRIMED_TNT);
                        tnt3.setFuseTicks(60);
                        p.sendMessage("§5§lTrolololo C:");
                    }
                }, 100L);
                break;
            }
            case 6: {
                final Vector v = p.getLocation().getDirection().setY(2.5);
                p.setVelocity(v);
                break;
            }
            case 7: {
                for (Integer j = 0; j < 21; ++j) {
                    final Bat bat = (Bat)p.getWorld().spawnEntity(b.getLocation(), EntityType.BAT);
                    final ThrownExpBottle xp = (ThrownExpBottle)p.getWorld().spawnEntity(b.getLocation(), EntityType.THROWN_EXP_BOTTLE);
                    bat.setPassenger((Entity)xp);
                }
                break;
            }
            case 8: {
                Integer i5 = 0;
                while (i5 < 270) {
                    ++i5;
                    final Location pl = p.getLocation();
                    pl.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl2 = p.getLocation().subtract(0.0, 0.0, 1.0);
                    pl2.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl3 = p.getLocation().subtract(0.0, 0.0, -1.0);
                    pl3.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl4 = p.getLocation().subtract(1.0, 0.0, 0.0);
                    pl4.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl5 = p.getLocation().subtract(-1.0, 0.0, 0.0);
                    pl5.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl6 = p.getLocation().subtract(1.0, 0.0, 1.0);
                    pl6.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl7 = p.getLocation().subtract(-1.0, 0.0, -1.0);
                    pl7.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl8 = p.getLocation().subtract(-1.0, 0.0, 1.0);
                    pl8.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                    final Location pl9 = p.getLocation().subtract(1.0, 0.0, -1.0);
                    pl9.subtract(0.0, (double)i5, 0.0).getBlock().setType(Material.AIR);
                }
                break;
            }
            case 9: {
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$6() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 10L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$7() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 20L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$8() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 30L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$9() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 40L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$10() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 50L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$11() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 60L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$12() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 70L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$13() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 80L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$14() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 90L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$15() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 100L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$16() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 110L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$17() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 120L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$18() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 130L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$19() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 140L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$20() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 150L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$21() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 160L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$22() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 170L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$23() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 180L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$24() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 160L);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$25() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        p.getLocation().getBlock().setType(Material.BEDROCK);
                    }
                }, 200L);
                break;
            }
            case 10: {
                p.getLocation().getBlock().setType(Material.BEACON);
                p.sendMessage("§aLoL wo kommt der jetzt her?!");
                break;
            }
            case 11: {
                p.getWorld().spawnEntity(b.getLocation(), EntityType.WITHER);
                p.sendMessage("§cViel Spaß mit deinem neuen Haustier! C:");
                break;
            }
            case 12: {
                p.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 12000, 9));
                p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 12000, 9));
                p.addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 12000, 9));
                p.addPotionEffect(new PotionEffect(PotionEffectType.WITHER, 12000, 9));
                p.addPotionEffect(new PotionEffect(PotionEffectType.HUNGER, 12000, 9));
                p.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 12000, 9));
                p.sendMessage("§cSchmecken die Tränke überhaupt?");
                break;
            }
            case 13: {
                ItemStack[] contents;
                for (int length = (contents = p.getInventory().getContents()).length, k = 0; k < length; ++k) {
                    final ItemStack in = contents[k];
                    if (in != null) {
                        p.getWorld().dropItemNaturally(p.getLocation(), in);
                        p.getInventory().remove(in);
                    }
                }
                ItemStack[] armorContents;
                for (int length2 = (armorContents = p.getInventory().getArmorContents()).length, l = 0; l < length2; ++l) {
                    final ItemStack in = armorContents[l];
                    if (in.getType() != Material.AIR) {
                        p.getWorld().dropItemNaturally(p.getLocation(), in);
                    }
                }
                final ItemStack ii = new ItemStack(Material.AIR);
                p.getInventory().setHelmet(ii);
                p.getInventory().setChestplate(ii);
                p.getInventory().setLeggings(ii);
                p.getInventory().setBoots(ii);
                LuckyBlockCMD.ByeInv.add(p);
                Bukkit.getScheduler().runTaskLater((Plugin)LuckyBlockCMD.plugin, (Runnable)new Runnable() {
                    private final /* synthetic */ Player val$p;
                    
                    LuckyBlockCMD$26() {
                        super();
                    }
                    
                    @Override
                    public void run() {
                        LuckyBlockCMD.ByeInv.remove(p);
                    }
                }, 600L);
                break;
            }
            case 14: {
                final ItemStack istack1 = new ItemStack(Material.COOKED_BEEF);
                final ItemMeta istackMeta1 = istack1.getItemMeta();
                istackMeta1.setDisplayName("§d§lScharfer Schinken");
                final ArrayList<String> Lore = new ArrayList<String>();
                Lore.add("§d§lScharfer Schinken");
                istackMeta1.setLore((List)Lore);
                istackMeta1.addEnchant(Enchantment.DAMAGE_ALL, 1000000, true);
                istackMeta1.addEnchant(Enchantment.FIRE_ASPECT, 1000000, true);
                istack1.setItemMeta(istackMeta1);
                p.getWorld().dropItemNaturally(b.getLocation(), istack1);
                break;
            }
            case 15: {
                final ItemStack istack2 = new ItemStack(Material.DIAMOND_SWORD);
                final ItemMeta istackMeta2 = istack2.getItemMeta();
                istackMeta2.spigot().setUnbreakable(true);
                istackMeta2.addEnchant(Enchantment.DAMAGE_ALL, 5, true);
                istack2.setItemMeta(istackMeta2);
                final ItemStack istack3 = new ItemStack(Material.DIAMOND_HELMET);
                final ItemMeta istackMeta3 = istack3.getItemMeta();
                istackMeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                istack3.setItemMeta(istackMeta3);
                final ItemStack istack4 = new ItemStack(Material.DIAMOND_CHESTPLATE);
                final ItemMeta istackMeta4 = istack4.getItemMeta();
                istackMeta4.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                istack4.setItemMeta(istackMeta4);
                final ItemStack istack5 = new ItemStack(Material.DIAMOND_LEGGINGS);
                final ItemMeta istackMeta5 = istack5.getItemMeta();
                istackMeta5.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                istack5.setItemMeta(istackMeta5);
                final ItemStack istack6 = new ItemStack(Material.DIAMOND_BOOTS);
                final ItemMeta istackMeta6 = istack6.getItemMeta();
                istackMeta6.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
                istack6.setItemMeta(istackMeta6);
                final Zombie zom = (Zombie)p.getWorld().spawnCreature(b.getLocation(), CreatureType.ZOMBIE);
                zom.setMaxHealth(200.0);
                zom.setHealth(200.0);
                zom.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 9999999, 1));
                zom.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 9999999, 2));
                zom.getEquipment().setHelmet(istack3);
                zom.getEquipment().setChestplate(istack4);
                zom.getEquipment().setLeggings(istack5);
                zom.getEquipment().setBoots(istack6);
                zom.getEquipment().setItemInHand(istack2);
                zom.getEquipment().setItemInHandDropChance(100.0f);
                zom.getEquipment().setBootsDropChance(0.0f);
                zom.getEquipment().setLeggingsDropChance(0.0f);
                zom.getEquipment().setChestplateDropChance(0.0f);
                zom.getEquipment().setHelmetDropChance(0.0f);
                zom.setCustomName("§4§lNiki");
                break;
            }
            case 16: {
                final ItemStack istack7 = new ItemStack(Material.DIAMOND_PICKAXE);
                final ItemMeta istackMeta7 = istack7.getItemMeta();
                istackMeta7.setDisplayName("§4§lBobs Spitzhacke");
                istackMeta7.addEnchant(Enchantment.DIG_SPEED, 5, true);
                istackMeta7.addEnchant(Enchantment.SILK_TOUCH, 5, true);
                istackMeta7.addEnchant(Enchantment.DURABILITY, 5, true);
                istack7.setItemMeta(istackMeta7);
                final ItemStack istack8 = new ItemStack(Material.DIAMOND_AXE);
                final ItemMeta istackMeta8 = istack8.getItemMeta();
                istackMeta8.setDisplayName("§4§lBobs Axt");
                istackMeta8.addEnchant(Enchantment.DIG_SPEED, 5, true);
                istackMeta8.addEnchant(Enchantment.SILK_TOUCH, 5, true);
                istackMeta8.addEnchant(Enchantment.DURABILITY, 5, true);
                istack8.setItemMeta(istackMeta8);
                p.getWorld().dropItemNaturally(b.getLocation(), istack7);
                p.getWorld().dropItemNaturally(b.getLocation(), istack8);
                p.sendMessage("§f§lDer Weihnachtsmann kam aber früh");
                break;
            }
            case 17: {
                final Creeper creep = (Creeper)p.getWorld().spawnCreature(b.getLocation(), CreatureType.CREEPER);
                creep.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 1));
                final Creeper creep2 = (Creeper)p.getWorld().spawnCreature(b.getLocation(), CreatureType.CREEPER);
                creep2.addPotionEffect(new PotionEffect(PotionEffectType.FIRE_RESISTANCE, 9999999, 1));
                p.getWorld().spawnEntity(b.getLocation(), EntityType.LIGHTNING);
                break;
            }
        }
    }
}
