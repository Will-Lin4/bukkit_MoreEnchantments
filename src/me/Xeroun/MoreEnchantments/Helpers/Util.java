package me.Xeroun.MoreEnchantments.Helpers;

import java.util.List;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;
import org.bukkit.metadata.Metadatable;
import org.bukkit.plugin.Plugin;

public class Util {

	// Roman Number Convertor
	private final static TreeMap<Integer, String> map = new TreeMap<Integer, String>();
	static {
		map.put(1000, "M");
		map.put(900, "CM");
		map.put(500, "D");
		map.put(400, "CD");
		map.put(100, "C");
		map.put(90, "XC");
		map.put(50, "L");
		map.put(40, "XL");
		map.put(10, "X");
		map.put(9, "X");
		map.put(5, "V");
		map.put(4, "IV");
		map.put(1, "I");
	}
	
	public final static String toRoman(int number) {
		int l = map.floorKey(number);
		if (number == l) {
			return map.get(number);
		}
		return map.get(l) + toRoman(number - l);
	}

	// Easily set an item's metadata
	public static void setMetadata(Metadatable object, String key, Object value, Plugin plugin) {
		object.setMetadata(key, new FixedMetadataValue(plugin, value));
	}

	public static Object getMetadata(Metadatable object, String key, Plugin plugin) {
		List<MetadataValue> values = object.getMetadata(key);
		for (MetadataValue value : values) {
			if (value.getOwningPlugin() == plugin) {
				return value.value();
			}
		}
		return null;
	}
	
	// Hide text from players
	public static String invisible(String string){

		String newString = "";
		for (char c: string.toCharArray()) { 
		    newString = newString + ChatColor.COLOR_CHAR + c;
		} 
		
		return newString;
	}
	
	public static String visable(String string){
		return string.replaceAll(ChatColor.COLOR_CHAR + "", "");
	}
	
}