package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import com.intellectualcrafters.plot.api.PlotAPI;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class LeuchtenCMD implements CommandExecutor
{
    private static Main plugin;
    
    public LeuchtenCMD(final Main plugin) {
        super();
        LeuchtenCMD.plugin = plugin;
        plugin.getCommand("leuchten").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final String Prefix = LeuchtenCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        final String NoPerms = String.valueOf(Prefix) + LeuchtenCMD.plugin.getConfig().getString("NoPerms").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            final PlotAPI papi = new PlotAPI();
            if (p.hasPermission("system.leuchten")) {
                if (papi.isInPlot(p)) {
                    if (papi.getPlot(p.getLocation()).isOwner(p.getUniqueId())) {
                        if (args.length == 1) {
                            if (args[0].equalsIgnoreCase("aktivieren")) {
                                if (papi.getPlot(p.getLocation()).getConnectedPlots().size() == 1) {
                                    final File Settings = new File("plugins/PlotSquared/config/worlds.yml");
                                    final YamlConfiguration ySettings = YamlConfiguration.loadConfiguration(Settings);
                                    final Integer GrößeDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".size") + 2 + 3;
                                    final Integer HöheDerPlots = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".height");
                                    p.sendMessage(String.valueOf(Prefix) + "§aDer §cL§ee§bu§ac§9h§6t-Effek §awurde für dein Grundstück aktiviert!");
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.BEACON);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.BEACON);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), 3.0).getBlock().setType(Material.BEACON);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 3.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 4.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), 2.0).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots - 1), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.BEACON);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(3 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(4 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                    papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots), (double)(-HöheDerPlots), (double)(2 - GrößeDerPlots)).getBlock().setType(Material.DIAMOND_BLOCK);
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§cDas Plot darf nicht gemerged sein!");
                                }
                            }
                            else if (args[0].equalsIgnoreCase("deaktivieren")) {
                                p.sendMessage(String.valueOf(Prefix) + "§aDer §cL§ee§bu§ac§9h§6t-Effek §awurde für dein Grundstück deaktiviert!");
                                final File Settings = new File("plugins/PlotSquared/config/worlds.yml");
                                final YamlConfiguration ySettings = YamlConfiguration.loadConfiguration(Settings);
                                final String[] block = ySettings.getString("worlds." + p.getWorld().getName() + ".road" + ".block").split(":");
                                final Material m = Material.getMaterial((int)Integer.valueOf(block[0]));
                                final Byte d = Byte.valueOf(block[1]);
                                final Integer GrößeDerPlots2 = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".size") + 2 + 3;
                                final Integer HöheDerPlots2 = ySettings.getInt("worlds." + p.getWorld().getName() + ".plot" + ".height");
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2 - 1), 3.0).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2 - 1), (double)(3 - GrößeDerPlots2)).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2 - 1), 3.0).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2 - 1), (double)(3 - GrößeDerPlots2)).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setType(m);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2 - 1), 3.0).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2 - 1), (double)(3 - GrößeDerPlots2)).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(2.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(3.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract(4.0, (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2 - 1), 3.0).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 3.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 4.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), 2.0).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2 - 1), (double)(3 - GrößeDerPlots2)).getBlock().setType(Material.AIR);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(2 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(3 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(3 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(4 - GrößeDerPlots2)).getBlock().setData((byte)d);
                                papi.getBottomLocation(papi.getPlot(p.getLocation())).subtract((double)(4 - GrößeDerPlots2), (double)(-HöheDerPlots2), (double)(2 - GrößeDerPlots2)).getBlock().setData((byte)d);
                            }
                            else {
                                p.sendMessage("§f/leuchten aktivieren - §aAktiviert den §cL§ee§bu§ac§9h§6t-Effekt §fauf deinem\n Grundstück");
                                p.sendMessage("§f/leuchten deaktivieren - §cDeaktiviert den §cL§ee§bu§ac§9h§6t-Effekt §fauf\n deinem Grundstück");
                            }
                        }
                        else {
                            p.sendMessage("§f/leuchten aktivieren - §aAktiviert den §cL§ee§bu§ac§9h§6t-Effekt §fauf deinem\n Grundstück");
                            p.sendMessage("§f/leuchten deaktivieren - §cDeaktiviert den §cL§ee§bu§ac§9h§6t-Effekt §fauf\n deinem Grundstück");
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§cDu bist nicht der Besitzer dieses Plots!");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§cDu bist auf keinem Grundstück!");
                }
            }
            else {
                p.sendMessage(NoPerms);
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie §4§lConsole §ckann kein /Leuchten.");
        }
        return true;
    }
}
