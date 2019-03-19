package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.Sound;
import net.ess3.api.MaxMoneyException;
import com.intellectualcrafters.plot.api.PlotAPI;
import java.math.BigDecimal;
import org.bukkit.Bukkit;
import com.earth2me.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import java.util.ArrayList;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class MergeCMD implements CommandExecutor
{
    private Main plugin;
    public static ArrayList<Player> merge;
    
    static {
        MergeCMD.merge = new ArrayList<Player>();
    }
    
    public MergeCMD(final Main plugin) {
        super();
        this.plugin = plugin;
        plugin.getCommand("merge").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = this.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (!p.hasPermission("system.merge.admin")) {
                if (args.length == 0) {
                    Integer i = 0;
                    while (i < 100) {
                        ++i;
                        p.sendMessage(" ");
                    }
                    p.sendMessage("§e§lACHTUNG: §fSchaue in die Richtung in der du deine Grundstücke mergen möchtest.");
                    p.sendMessage("§c§lALLE Grundstücke §fmüssen dir gehören, ansonsten funktioniert es nicht!");
                    p.sendMessage("§fNach dem Kauf wird dein Grundstück gemerged. Sollte etwas §b§lverbuggt sein §foder es nicht funktionieren, kann dir keiner helfen!");
                    p.sendMessage("§fDu hast §edie Verantwortung §ffür dein Grundstück!");
                    p.sendMessage("§cMöchtest du dieses Plot für §450.000§2$ §cwirklich Mergen?");
                    p.sendMessage("§fDann gib §c/merge confirm §fein.");
                }
                else if (args[0].equalsIgnoreCase("confirm")) {
                    final Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
                    final Integer money = ess.getUser(p).getMoney().intValue();
                    final Integer betrag = 50000;
                    final BigDecimal neuesgeld = BigDecimal.valueOf(money - betrag);
                    if (money >= betrag) {
                        final PlotAPI papi = new PlotAPI();
                        if (papi.isInPlot(p)) {
                            if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                                MergeCMD.merge.add(p);
                                p.chat("/p merge");
                                try {
                                    ess.getUser(p).setMoney(neuesgeld);
                                }
                                catch (MaxMoneyException e) {
                                    e.printStackTrace();
                                }
                                p.playSound(p.getLocation(), Sound.ENDERDRAGON_GROWL, 1.0f, 1.0f);
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§cDas ist nicht dein Plot!");
                            }
                        }
                        else {
                            p.sendMessage(String.valueOf(Prefix) + "§cDu stehst auf kein Plot!");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu hast leider nicht genügend Geld!");
                    }
                }
                else {
                    Integer i = 0;
                    while (i < 100) {
                        ++i;
                        p.sendMessage(" ");
                    }
                    p.sendMessage("§e§lACHTUNG: §fSchaue in die Richtung in der du deine Grundstücke mergen möchtest.");
                    p.sendMessage("§c§lALLE Grundstücke §fmüssen dir gehören, ansonsten funktioniert es nicht!");
                    p.sendMessage("§fNach dem Kauf wird dein Grundstück gemerged. Sollte etwas §b§lverbuggt sein §foder es nicht funktionieren, kann dir keiner helfen!");
                    p.sendMessage("§fDu hast §edie Verantwortung §ffür dein Grundstück!");
                    p.sendMessage("§cMöchtest du dieses Plot für §450.000§2$ §cwirklich Mergen?");
                    p.sendMessage("§fDann gib §c/merge confirm §fein.");
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§aDu hast die Permission §b\"System.Merge.Admin\" §adu kannst mit §b\"/p merge\" §akostenlos mergen!");
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console kann nicht mergen!");
        }
        return true;
    }
}
