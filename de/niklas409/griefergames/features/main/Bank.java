package de.niklas409.griefergames.features.main;

import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;

public class Bank
{
    public Bank() {
        super();
    }
    
    public static void AddBank(final Player p, final int anzahl) {
        final File file = new File("plugins/GrieferGames/Data/Bank.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Bank") == null) {
            cfg.set(p.getUniqueId() + ".Bank", (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Bank");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Bank", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex2) {}
        }
        else {
            try {
                cfg.save(file);
            }
            catch (IOException ex3) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Bank");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Bank", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
    
    public static int getBank(final Player p) {
        final File file = new File("plugins/GrieferGames/Data/Bank.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int i = 0;
        if (cfg.getString(p.getUniqueId() + ".Bank") == null) {
            i = 0;
        }
        else {
            i = cfg.getInt(p.getUniqueId() + ".Bank");
        }
        return i;
    }
    
    public static void RemoveBank(final Player p, final int anzahl) {
        final File file = new File("plugins/GrieferGames/Data/Bank.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Bank") == null) {
            cfg.set(p.getUniqueId() + ".Bank", (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Bank");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Bank", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex2) {}
        }
        else {
            try {
                cfg.save(file);
            }
            catch (IOException ex3) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Bank");
            final int total = i - anzahl;
            cfg.set(p.getUniqueId() + ".Bank", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
}
