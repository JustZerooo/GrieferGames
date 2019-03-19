package de.niklas409.griefergames.features.cmds;

import java.io.IOException;
import org.bukkit.Bukkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import de.niklas409.griefergames.features.main.Cases;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class FreeKisteCMD implements CommandExecutor
{
    private static Main plugin;
    public static File FKTime;
    public static YamlConfiguration yFKTime;
    
    static {
        FreeKisteCMD.FKTime = new File("plugins/GrieferGames/Data/Cases.yml");
        FreeKisteCMD.yFKTime = YamlConfiguration.loadConfiguration(FreeKisteCMD.FKTime);
    }
    
    public FreeKisteCMD(final Main plugin) {
        super();
        FreeKisteCMD.plugin = plugin;
        plugin.getCommand("freekiste").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = FreeKisteCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + FreeKisteCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (p.hasPermission("system.freekiste.griefer")) {
                if (FreeKisteCMD.yFKTime.get("FreeKiste." + p.getUniqueId()) == null) {
                    this.SetFKTime(p, 1209600);
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast §62 Kisten §aerhalten.");
                    Cases.AddCase(p, 2, "E");
                }
                else if (FreeKisteCMD.yFKTime.getLong("FreeKiste." + p.getUniqueId()) < System.currentTimeMillis()) {
                    this.SetFKTime(p, 1209600);
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast §62 Kisten §aerhalten.");
                    Cases.AddCase(p, 2, "E");
                }
                else {
                    final Date date = new Date(FreeKisteCMD.yFKTime.getLong("FreeKiste." + p.getUniqueId()));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst erst am §e" + mm_dd_yyyy + " um " + hour_min + " §cwieder §b§lFree-Kisten §cabholen.");
                }
            }
            else if (p.hasPermission("system.freekiste.premium")) {
                if (FreeKisteCMD.yFKTime.get("FreeKiste." + p.getUniqueId()) == null) {
                    this.SetFKTime(p, 1209600);
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast §61 Kiste §aerhalten.");
                    Cases.AddCase(p, 1, "E");
                }
                else if (FreeKisteCMD.yFKTime.getLong("FreeKiste." + p.getUniqueId()) < System.currentTimeMillis()) {
                    this.SetFKTime(p, 1209600);
                    p.sendMessage(String.valueOf(Prefix) + "§aDu hast §61 Kiste §aerhalten.");
                    Cases.AddCase(p, 1, "E");
                }
                else {
                    final Date date = new Date(FreeKisteCMD.yFKTime.getLong("FreeKiste." + p.getUniqueId()));
                    final String mm_dd_yyyy = new SimpleDateFormat("dd.MM.yyyy").format(date);
                    final String hour_min = new SimpleDateFormat("HH:mm").format(date);
                    p.sendMessage(String.valueOf(Prefix) + "§cDu kannst erst am §e" + mm_dd_yyyy + " um " + hour_min + " §cwieder §b§lFree-Kisten §cabholen.");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann keine Kisten bekommen!");
        }
        return true;
    }
    
    public void SetFKTime(final Player p, final int time) {
        FreeKisteCMD.yFKTime.set("FreeKiste." + p.getUniqueId(), (Object)(System.currentTimeMillis() + time * 1000));
        try {
            FreeKisteCMD.yFKTime.save(FreeKisteCMD.FKTime);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
}
