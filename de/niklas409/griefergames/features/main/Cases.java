package de.niklas409.griefergames.features.main;

import java.io.IOException;
import org.bukkit.configuration.file.YamlConfiguration;
import java.io.File;
import org.bukkit.entity.Player;

public class Cases
{
    public Cases() {
        super();
    }
    
    public static void AddCase(final Player p, final int anzahl, final String cases) {
        final File file = new File("plugins/GrieferGames/Data/Cases.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Case" + "." + cases) == null) {
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Case" + "." + cases);
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)total);
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
            final int i = cfg.getInt(p.getUniqueId() + ".Case" + "." + cases);
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
    
    public static int getCase(final Player p, final String cases) {
        final File file = new File("plugins/GrieferGames/Data/Cases.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        int i = 0;
        if (cfg.getString(p.getUniqueId() + ".Case" + "." + cases) == null) {
            i = 0;
        }
        else {
            i = cfg.getInt(p.getUniqueId() + ".Case" + "." + cases);
        }
        return i;
    }
    
    public static void RemoveCase(final Player p, final int anzahl, final String cases) {
        final File file = new File("plugins/GrieferGames/Data/Cases.yml");
        final YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);
        if (cfg.getString(p.getUniqueId() + ".Case" + "." + cases) == null) {
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)0);
            try {
                cfg.save(file);
            }
            catch (IOException ex) {}
            final int i = cfg.getInt(p.getUniqueId() + ".Case" + "." + cases);
            final int total = i + anzahl;
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)total);
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
            final int i = cfg.getInt(p.getUniqueId() + ".Case" + "." + cases);
            final int total = i - anzahl;
            cfg.set(p.getUniqueId() + ".Case" + "." + cases, (Object)total);
            try {
                cfg.save(file);
            }
            catch (IOException ex4) {}
        }
    }
}
