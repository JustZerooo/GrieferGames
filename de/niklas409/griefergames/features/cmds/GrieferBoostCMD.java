package de.niklas409.griefergames.features.cmds;

import java.io.IOException;
import java.util.UUID;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.Bukkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.entity.Firework;
import de.niklas409.griefergames.features.main.Booster;
import org.bukkit.entity.Player;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class GrieferBoostCMD implements CommandExecutor
{
    private Main plugin;
    
    public GrieferBoostCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("grieferboost").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + this.plugin.getConfig().getString("NoPerms").replace("&", "§");
        final File GB = new File("plugins/GrieferGames/Data/GrieferBoost.yml");
        final YamlConfiguration yGB = YamlConfiguration.loadConfiguration(GB);
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.grieferboost")) {
                if (yGB.contains(p.getUniqueId().toString())) {
                    if (yGB.get(p.getUniqueId().toString()) == null) {
                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast §b1 Booster §aerhalten. Danke für §bdeine Unterstützung §afür §bunser §aNetzwerk!");
                        Booster.AddBooster(p, 1);
                        this.SetGBTime(p.getUniqueId(), 1209600);
                        final Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
                        final FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                        final FireworkMeta meta = firework.getFireworkMeta();
                        meta.addEffect(effect);
                        meta.setPower(1);
                        firework.setFireworkMeta(meta);
                    }
                    else if (yGB.getLong(p.getUniqueId().toString()) < System.currentTimeMillis()) {
                        p.sendMessage(String.valueOf(Prefix) + "§aDu hast §b1 Booster §aerhalten. Danke für §bdeine Unterstützung §afür §bunser §aNetzwerk!");
                        Booster.AddBooster(p, 1);
                        this.SetGBTime(p.getUniqueId(), 1209600);
                        final Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
                        final FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                        final FireworkMeta meta = firework.getFireworkMeta();
                        meta.addEffect(effect);
                        meta.setPower(1);
                        firework.setFireworkMeta(meta);
                    }
                    else {
                        final Date date = new Date(yGB.getLong(p.getUniqueId().toString()));
                        final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                        final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                        p.sendMessage(String.valueOf(Prefix) + "§cDu kannst erst am §e" + mm_dd_yyyy + " um " + hour_min + " §cwieder einen §b§lFree-Booster abholen.");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast §b5 Booster §aerhalten. Danke für §bdeine Unterstützung §afür §bunser §aNetzwerk!");
                    Booster.AddBooster(p, 5);
                    this.SetGBTime(p.getUniqueId(), 1209600);
                    final Firework firework = (Firework)p.getWorld().spawn(p.getLocation(), (Class)Firework.class);
                    final FireworkEffect effect = FireworkEffect.builder().withColor(Color.GREEN).flicker(true).trail(true).withFade(Color.RED).with(FireworkEffect.Type.BALL_LARGE).build();
                    final FireworkMeta meta = firework.getFireworkMeta();
                    meta.addEffect(effect);
                    meta.setPower(1);
                    firework.setFireworkMeta(meta);
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console hat unendlich Booster!");
        }
        return true;
    }
    
    public void SetGBTime(final UUID uuid, final int time) {
        final File GB = new File("plugins/GrieferGames/Data/GrieferBoost.yml");
        final YamlConfiguration yGB = YamlConfiguration.loadConfiguration(GB);
        yGB.set(uuid.toString(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            yGB.save(GB);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
