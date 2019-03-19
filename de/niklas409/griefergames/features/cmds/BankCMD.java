package de.niklas409.griefergames.features.cmds;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import net.ess3.api.MaxMoneyException;
import java.math.BigDecimal;
import de.niklas409.griefergames.features.main.Bank;
import org.bukkit.entity.Player;
import org.bukkit.Bukkit;
import com.earth2me.essentials.Essentials;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import de.niklas409.griefergames.features.main.Main;
import org.bukkit.command.CommandExecutor;

public class BankCMD implements CommandExecutor
{
    private static Main plugin;
    
    public BankCMD(final Main plugin) {
        super();
        BankCMD.plugin = plugin;
        plugin.getCommand("bank").setExecutor((CommandExecutor)this);
    }
    
    public boolean onCommand(final CommandSender sender, final Command cmd, final String label, final String[] args) {
        final Essentials ess = (Essentials)Bukkit.getPluginManager().getPlugin("Essentials");
        final String Prefix = BankCMD.plugin.getConfig().getString("Prefix").replace("&", "§");
        if (sender instanceof Player) {
            final Player p = (Player)sender;
            if (args.length == 1) {
                if (args[0].equalsIgnoreCase("guthaben")) {
                    if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("MySQL")) {
                        p.sendMessage(String.valueOf(Prefix) + "§3Dein Kontostand: §e" + getBankGuthaben(p.getUniqueId()));
                    }
                    else if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File")) {
                        p.sendMessage(String.valueOf(Prefix) + "§3Dein Kontostand: §e" + Bank.getBank(p));
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank guthaben §7- zeigt dein Guthaben auf der Bank an.");
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank einzahlen <Betrag> §7- zahlt den Betrag auf die Bank ein.");
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank abheben <Betrag> §7- hebt den Betrag von der Bank ab.");
                    p.sendMessage(String.valueOf(Prefix) + "§7Die Bank sichert dein Geld vor Verlust durch Serverfehler oder einem Reset.");
                }
            }
            else if (args.length == 2) {
                if (args[0].equalsIgnoreCase("einzahlen")) {
                    if (args[1].matches("[0-9]+")) {
                        if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("MySQL")) {
                            final Integer betrag = Integer.valueOf(args[1]);
                            final Integer money = ess.getUser(p).getMoney().intValue();
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - betrag);
                            final Integer abmoney = Integer.valueOf(getBankGuthaben(p.getUniqueId()));
                            final Integer nbmoney = abmoney + betrag;
                            if (betrag > 1999) {
                                if (money >= betrag) {
                                    try {
                                        ess.getUser(p).setMoney(neuesgeld);
                                    }
                                    catch (MaxMoneyException e) {
                                        e.printStackTrace();
                                    }
                                    Main.mysql.update("DELETE FROM Bank WHERE UUID= '" + p.getUniqueId() + "'");
                                    Main.mysql.update("INSERT INTO Bank(UUID, Geld) VALUES ('" + p.getUniqueId() + "', '" + nbmoney + "');");
                                    p.sendMessage(String.valueOf(Prefix) + "§2Du hast " + betrag + " auf dein Bankkonto eingezahlt.");
                                    p.sendMessage(String.valueOf(Prefix) + "§aKontostand: §e" + getBankGuthaben(p.getUniqueId()));
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                            }
                        }
                        else if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File")) {
                            final Integer betrag = Integer.valueOf(args[1]);
                            final Integer money = ess.getUser(p).getMoney().intValue();
                            final BigDecimal neuesgeld = BigDecimal.valueOf(money - betrag);
                            if (betrag > 1999) {
                                if (money >= betrag) {
                                    try {
                                        ess.getUser(p).setMoney(neuesgeld);
                                    }
                                    catch (MaxMoneyException e2) {
                                        e2.printStackTrace();
                                    }
                                    Bank.AddBank(p, betrag);
                                    p.sendMessage(String.valueOf(Prefix) + "§2Du hast " + betrag + " auf dein Bankkonto eingezahlt.");
                                    p.sendMessage(String.valueOf(Prefix) + "§aKontostand: §e" + Bank.getBank(p));
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                            }
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§4" + args[1] + " §7ist keine Zahl.");
                    }
                }
                else if (args[0].equalsIgnoreCase("abheben")) {
                    if (args[1].matches("[0-9]+")) {
                        if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("MySQL")) {
                            final Integer betrag = Integer.valueOf(args[1]);
                            final Integer bank = Integer.valueOf(getBankGuthaben(p.getUniqueId()));
                            final Integer money2 = ess.getUser(p).getMoney().intValue();
                            final BigDecimal neuesgeld2 = BigDecimal.valueOf(money2 + betrag);
                            final Integer abmoney2 = Integer.valueOf(getBankGuthaben(p.getUniqueId()));
                            final Integer nbmoney2 = abmoney2 - betrag;
                            if (betrag > 1999) {
                                if (bank >= betrag) {
                                    try {
                                        ess.getUser(p).setMoney(neuesgeld2);
                                    }
                                    catch (MaxMoneyException e3) {
                                        e3.printStackTrace();
                                    }
                                    Main.mysql.update("DELETE FROM Bank WHERE UUID= '" + p.getUniqueId() + "'");
                                    Main.mysql.update("INSERT INTO Bank(UUID, Geld) VALUES ('" + p.getUniqueId() + "', '" + nbmoney2 + "');");
                                    p.sendMessage(String.valueOf(Prefix) + "§2Du hast " + betrag + " von deinen Bankkonto abgehoben.");
                                    p.sendMessage(String.valueOf(Prefix) + "§aKontostand: §e" + getBankGuthaben(p.getUniqueId()));
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§eDu willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                            }
                        }
                        else if (BankCMD.plugin.getConfig().getString("BankSettings false/File/MySQL").equalsIgnoreCase("File")) {
                            final Integer betrag = Integer.valueOf(args[1]);
                            final Integer bank = Bank.getBank(p);
                            final Integer money2 = ess.getUser(p).getMoney().intValue();
                            final BigDecimal neuesgeld2 = BigDecimal.valueOf(money2 + betrag);
                            if (betrag > 1999) {
                                if (bank >= betrag) {
                                    try {
                                        ess.getUser(p).setMoney(neuesgeld2);
                                    }
                                    catch (MaxMoneyException e4) {
                                        e4.printStackTrace();
                                    }
                                    Bank.RemoveBank(p, betrag);
                                    p.sendMessage(String.valueOf(Prefix) + "§2Du hast " + betrag + " von deinen Bankkonto abgehoben.");
                                    p.sendMessage(String.valueOf(Prefix) + "§aKontostand: §e" + Bank.getBank(p));
                                }
                                else {
                                    p.sendMessage(String.valueOf(Prefix) + "§eDu hast nicht genug Guthaben oder willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                                }
                            }
                            else {
                                p.sendMessage(String.valueOf(Prefix) + "§eDu willst einen zu geringen Betrag abheben. Mindesein- und auszahlungsbetrag: 2000");
                            }
                        }
                    }
                    else {
                        p.sendMessage(String.valueOf(Prefix) + "§4" + args[1] + " §7ist keine Zahl.");
                    }
                }
                else {
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank guthaben §7- zeigt dein Guthaben auf der Bank an.");
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank einzahlen <Betrag> §7- zahlt den Betrag auf die Bank ein.");
                    p.sendMessage(String.valueOf(Prefix) + "§e/bank abheben <Betrag> §7- hebt den Betrag von der Bank ab.");
                    p.sendMessage(String.valueOf(Prefix) + "§7Die Bank sichert dein Geld vor Verlust durch Serverfehler oder einem Reset.");
                }
            }
            else {
                p.sendMessage(String.valueOf(Prefix) + "§e/bank guthaben §7- zeigt dein Guthaben auf der Bank an.");
                p.sendMessage(String.valueOf(Prefix) + "§e/bank einzahlen <Betrag> §7- zahlt den Betrag auf die Bank ein.");
                p.sendMessage(String.valueOf(Prefix) + "§e/bank abheben <Betrag> §7- hebt den Betrag von der Bank ab.");
                p.sendMessage(String.valueOf(Prefix) + "§7Die Bank sichert dein Geld vor Verlust durch Serverfehler oder einem Reset.");
            }
        }
        else {
            Bukkit.getConsoleSender().sendMessage(String.valueOf(Prefix) + "§cDie Console hat kein Geld!");
        }
        return true;
    }
    
    public static String getBankGuthaben(final UUID uuid) {
        String i = "0";
        if (MoneyExists(uuid)) {
            try {
                final ResultSet rs = Main.mysql.query("SELECT * FROM Bank WHERE UUID= '" + uuid + "'");
                if (!rs.next() || String.valueOf(rs.getString("UUID")) == null) {}
                i = rs.getString("Geld");
            }
            catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return i;
    }
    
    public static boolean MoneyExists(final UUID uuid) {
        try {
            final ResultSet rs = Main.mysql.query("SELECT * FROM Bank WHERE UUID= '" + uuid + "'");
            return rs.next() && rs.getString("Geld") != null;
        }
        catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
