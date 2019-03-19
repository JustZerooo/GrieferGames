package de.niklas409.griefergames.features.only.api.cmds;

import org.bukkit.Bukkit;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Constructor;
import org.bukkit.entity.Player;

public class Reflect
{
    public static final Class<?> NBTTagCompound;
    public static final Class<?> CraftItemStack;
    public static final Class<?> ItemStack;
    
    static {
        NBTTagCompound = getNMSClass("NBTTagCompound");
        CraftItemStack = getCBClass("inventory", "CraftItemStack");
        ItemStack = getNMSClass("ItemStack");
    }
    
    public Reflect() {
        super();
    }
    
    public static Version getServerVersion() {
        return Version.fromString(getVersion());
    }
    
    public static Version getProtocolVersion(final Player player) {
        return Version.UNKNOWN;
    }
    
    public static Constructor<?> getConstructor(final Class<?> clazz, final Class<?>[] params) {
        try {
            return clazz.getConstructor(params);
        }
        catch (NoSuchMethodException ex) {
            return null;
        }
    }
    
    public static boolean isCastable(final Object object, final Class<?> clazz) {
        try {
            clazz.cast(object);
            return true;
        }
        catch (ClassCastException ex) {
            return false;
        }
    }
    
    public static Object getNMSPlayer(final Player player) {
        try {
            final Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            return getHandle.invoke(player, new Object[0]);
        }
        catch (Exception ex) {
            return null;
        }
    }
    
    public static Method getMethod(final Method method) {
        method.setAccessible(true);
        return method;
    }
    
    public static void sendPacket(final Player player, final Object packet) {
        try {
            final Method getHandle = player.getClass().getMethod("getHandle", (Class<?>[])new Class[0]);
            final Object nmsPlayer = getHandle.invoke(player, new Object[0]);
            final Field pConnectionField = nmsPlayer.getClass().getField("playerConnection");
            final Object pConnection = pConnectionField.get(nmsPlayer);
            final Method sendMethod = pConnection.getClass().getMethod("sendPacket", getNMSClass("Packet"));
            sendMethod.invoke(pConnection, packet);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public static Field getField(final Field field) {
        field.setAccessible(true);
        return field;
    }
    
    public static String getVersion() {
        return String.valueOf(Bukkit.getServer().getClass().getPackage().getName().substring(23)) + ".";
    }
    
    public static Class<?> getNMSClass(final String nmsName) {
        final String name = "net.minecraft.server." + getVersion() + nmsName;
        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
    
    public static Class<?> getCBClass(final String cbPackage, final String cbName) {
        final String name = "org.bukkit.craftbukkit." + getVersion() + cbPackage + "." + cbName;
        try {
            return Class.forName(name);
        }
        catch (ClassNotFoundException ex) {
            return null;
        }
    }
}
