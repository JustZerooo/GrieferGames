package de.niklas409.griefergames.features.main;

import org.bukkit.Bukkit;
import java.lang.reflect.Field;

public class ReflectUtils
{
    public ReflectUtils() {
        super();
    }
    
    public static void setField(final Class<?> clazz, final String fieldName, final Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        final Field f = clazz.getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(clazz, value);
        f.setAccessible(false);
    }
    
    public static void setField(final Object obj, final String fieldName, final Object value) throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, SecurityException {
        final Field f = obj.getClass().getDeclaredField(fieldName);
        f.setAccessible(true);
        f.set(obj, value);
        f.setAccessible(false);
    }
    
    public static Class<?> getNMSClass(final String className) {
        try {
            return Class.forName("net.minecraft.server." + getVersion() + "." + className);
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
    
    public static String getVersion() {
        return Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3];
    }
}
