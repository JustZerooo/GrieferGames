package de.niklas409.griefergames.features.cmds;

import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.plugin.Plugin;
import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import java.util.ArrayList;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class FeuerwerkCMD implements CommandExecutor
{
    private static Main plugin;
    ArrayList<String> wait;
    
    public FeuerwerkCMD(final Main plugin) {
        super();
        this.wait = new ArrayList<String>();
        FeuerwerkCMD.plugin = plugin;
        plugin.getCommand("Feuerwerk").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = FeuerwerkCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + FeuerwerkCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.feuerwerk")) {
                if (!this.wait.contains(p.getName())) {
                    final Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
                    final FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                    final FireworkMeta meta = firework.getFireworkMeta();
                    meta.addEffect(effect);
                    meta.setPower(1);
                    firework.setFireworkMeta(meta);
                    final Firework firework2 = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
                    final FireworkEffect effect2 = FireworkEffect.builder().withColor(Color.ORANGE).flicker(true).trail(true).withFade(Color.PURPLE).with(FireworkEffect.Type.STAR).build();
                    final FireworkMeta meta2 = firework2.getFireworkMeta();
                    meta2.addEffect(effect2);
                    meta2.setPower(1);
                    firework2.setFireworkMeta(meta2);
                    this.wait.add(p.getName());
                    Bukkit.getScheduler().runTaskLater((Plugin)FeuerwerkCMD.plugin, (Runnable)new Runnable() {
                        final /* synthetic */ FeuerwerkCMD this$0;
                        private final /* synthetic */ Player val$p;
                        
                        FeuerwerkCMD$1() {
                            this.this$0 = this$0;
                            super();
                        }
                        
                        @Override
                        public void run() {
                            this.this$0.wait.remove(p.getName());
                        }
                    }, 60L);
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cBitte warte 3 Sekunden!");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Feuerwerke erstellen!");
        }
        return true;
    }
}
