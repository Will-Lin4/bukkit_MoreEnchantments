package me.Xeroun.MoreEnchantments.Helpers;

import java.lang.reflect.Field;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

public class NMSReflection {

	// Class for reflection and retrieving NMS objects/classes
	
	private final static String packageName = Bukkit.getServer().getClass().getPackage().getName();
	private final static String version = packageName.substring(packageName.lastIndexOf(".") + 1);
	private final static String NMSPath = "net.minecraft.server." + version + ".";
		
	public static String getNMSPath(){
		return NMSPath;
	}
	
	public static Object getObject(String name){
		try{
			Class<?> objectClass = Class.forName(NMSPath + name);
			return objectClass.getConstructor(new Class[]{}).newInstance(new Object[]{});
		}catch(Exception e) {e.printStackTrace(); }
		
		return null;
	}
	
	public static Object getObject(String name, Object[] parameters){
		try{
			Class<?> objectClass = Class.forName(NMSPath + name);
			Class<?>[] parameterTypes = new Class[parameters.length];
			for(int i = 0 ; i < parameters.length ; i++){
				parameterTypes[i] = parameters[i].getClass();
			}
			
			return objectClass.getConstructor(parameterTypes).newInstance(parameters);
			
		}catch(Exception e) {e.printStackTrace(); }
		
		return null;
	}
	
	public static Object getObject(String name, Class<?>[] parameterTypes, Object[] parameters){
		try{
			Class<?> objectClass = Class.forName(NMSPath + name);
			return objectClass.getConstructor(parameterTypes).newInstance(parameters);
		}catch(Exception e) {e.printStackTrace(); }
		return null;
	}
	
	public static Class<?> getClass(String name){
		try {
			return Class.forName(NMSPath + name);
		} catch (Exception e) { e.printStackTrace(); }
		
		return null;
	}

	public static Object getCraftPlayer(Player player){
		try {
			Class<?> className = Class.forName("org.bukkit.craftbukkit." + version + ".entity.CraftPlayer");
			return className.cast(player);
		} catch (Exception e) {e.printStackTrace();}
		return null;
	}
	
	public static Object getField(Object object, String fieldName){
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			return field.get(object);
		} catch (Exception e) {e.printStackTrace();}
		
		return null;
	}
	
	public static void setField(Object object, String fieldName, Object value){
		try {
			Field field = object.getClass().getDeclaredField(fieldName);
			field.setAccessible(true);
			field.set(object, value);
			field.setAccessible(false);
		} catch (Exception e) {e.printStackTrace();}	
	}
	
	public static void sendPacket(Player player, Object object){
		Object craftPlayer = getCraftPlayer(player);
		try {
			Class<?> packetClass = Class.forName(NMSPath + "Packet");
			Object handle = craftPlayer.getClass().getMethod("getHandle", new Class[]{}).invoke(craftPlayer, new Object[]{});
			Object connection = handle.getClass().getField("playerConnection").get(handle);
			connection.getClass().getMethod("sendPacket", new Class[]{packetClass}).invoke(connection, object);
			
		} catch (Exception e) {e.printStackTrace();}
	}
	
}
