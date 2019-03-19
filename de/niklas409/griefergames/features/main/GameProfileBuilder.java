package de.niklas409.griefergames.features.main;

import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonParseException;
import java.util.Iterator;
import java.util.Map;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;
import java.util.List;
import com.mojang.authlib.properties.Property;
import org.yaml.snakeyaml.external.biz.base64Coder.Base64Coder;
import java.util.ArrayList;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;
import java.io.Reader;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.HttpURLConnection;
import java.io.IOException;
import com.mojang.authlib.properties.PropertyMap;
import com.mojang.authlib.GameProfile;
import java.lang.reflect.Type;
import com.mojang.util.UUIDTypeAdapter;
import com.google.gson.GsonBuilder;
import java.util.UUID;
import java.util.HashMap;
import com.google.gson.Gson;

public class GameProfileBuilder
{
    private static final String SERVICE_URL = "https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false";
    private static final String JSON_SKIN = "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"}}}";
    private static final String JSON_CAPE = "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"},\"CAPE\":{\"url\":\"%s\"}}}";
    private static Gson gson;
    private static HashMap<UUID, CachedProfile> cache;
    private static long cacheTime;
    
    static {
        GameProfileBuilder.gson = new GsonBuilder().disableHtmlEscaping().registerTypeAdapter((Type)UUID.class, (Object)new UUIDTypeAdapter()).registerTypeAdapter((Type)GameProfile.class, (Object)new GameProfileSerializer(null)).registerTypeAdapter((Type)PropertyMap.class, (Object)new PropertyMap.Serializer()).create();
        GameProfileBuilder.cache = new HashMap<UUID, CachedProfile>();
        GameProfileBuilder.cacheTime = -1L;
    }
    
    public GameProfileBuilder() {
        super();
    }
    
    public static GameProfile fetch(final UUID uuid) throws IOException {
        return fetch(uuid, false);
    }
    
    public static GameProfile fetch(final UUID uuid, final boolean forceNew) throws IOException {
        if (!forceNew && GameProfileBuilder.cache.containsKey(uuid) && GameProfileBuilder.cache.get(uuid).isValid()) {
            return GameProfileBuilder.cache.get(uuid).profile;
        }
        final HttpURLConnection connection = (HttpURLConnection)new URL(String.format("https://sessionserver.mojang.com/session/minecraft/profile/%s?unsigned=false", UUIDTypeAdapter.fromUUID(uuid))).openConnection();
        connection.setReadTimeout(5000);
        if (connection.getResponseCode() == 200) {
            final String json = new BufferedReader(new InputStreamReader(connection.getInputStream())).readLine();
            final GameProfile result = (GameProfile)GameProfileBuilder.gson.fromJson(json, (Class)GameProfile.class);
            GameProfileBuilder.cache.put(uuid, new CachedProfile(result));
            return result;
        }
        if (!forceNew && GameProfileBuilder.cache.containsKey(uuid)) {
            return GameProfileBuilder.cache.get(uuid).profile;
        }
        final JsonObject error = (JsonObject)new JsonParser().parse(new BufferedReader(new InputStreamReader(connection.getErrorStream())).readLine());
        throw new IOException(String.valueOf(error.get("error").getAsString()) + ": " + error.get("errorMessage").getAsString());
    }
    
    public static GameProfile getProfile(final UUID uuid, final String name, final String skin) {
        return getProfile(uuid, name, skin, null);
    }
    
    public static GameProfile getProfile(final UUID uuid, final String name, final String skinUrl, final String capeUrl) {
        final GameProfile profile = new GameProfile(uuid, name);
        final boolean cape = capeUrl != null && !capeUrl.isEmpty();
        final List<Object> args = new ArrayList<Object>();
        args.add(System.currentTimeMillis());
        args.add(UUIDTypeAdapter.fromUUID(uuid));
        args.add(name);
        args.add(skinUrl);
        if (cape) {
            args.add(capeUrl);
        }
        profile.getProperties().put((Object)"textures", (Object)new Property("textures", Base64Coder.encodeString(String.format(cape ? "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"},\"CAPE\":{\"url\":\"%s\"}}}" : "{\"timestamp\":%d,\"profileId\":\"%s\",\"profileName\":\"%s\",\"isPublic\":true,\"textures\":{\"SKIN\":{\"url\":\"%s\"}}}", args.toArray(new Object[args.size()])))));
        return profile;
    }
    
    public static void setCacheTime(final long time) {
        GameProfileBuilder.cacheTime = time;
    }
    
    static /* synthetic */ long access$0() {
        return GameProfileBuilder.cacheTime;
    }
}
