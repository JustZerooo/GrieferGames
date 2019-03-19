package de.niklas409.griefergames.features.main;

import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;

public class Booster
{
    public Booster() {
        super();
    }
    
    public static void AddBooster(final Player p, final int anzahl) {
        final File file = new File("plugins/GrieferGames/Data/Booster.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            cfg.set(p.getUniqueId() + ".Booster", (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Booster");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", (Object)total);
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
            final int i = cfg.getInt(p.getUniqueId() + ".Booster");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
    
    public static int getBooster(final Player p) {
        final File file = new File("plugins/GrieferGames/Data/Booster.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int i = 0;
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            i = 0;
        }
        else {
            i = cfg.getInt(p.getUniqueId() + ".Booster");
        }
        return i;
    }
    
    public static void RemoveBooster(final Player p, final int anzahl) {
        final File file = new File("plugins/GrieferGames/Data/Booster.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Booster") == null) {
            cfg.set(p.getUniqueId() + ".Booster", (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Booster");
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Booster", (Object)total);
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
            final int i = cfg.getInt(p.getUniqueId() + ".Booster");
            final int total = i - anzahl;
            cfg.set(p.getUniqueId() + ".Booster", (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
}
