package me.Xeroun.MoreEnchantments;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.Util;

public class EnchantmentHandler {

	private ArrayList<CustomEnchantment> enchantments = new ArrayList<CustomEnchantment>();

	public void init() {
		try {
			Field f = Enchantment.class.getDeclaredField("acceptingNew");
			f.setAccessible(true);
			f.set(null, true);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@SuppressWarnings("deprecation")
	public void register(CustomEnchantment enchantment) {
		try {
			Field byIdField = Enchantment.class.getDeclaredField("byId");
			Field byNameField = Enchantment.class.getDeclaredField("byName");
			byIdField.setAccessible(true);
			byNameField.setAccessible(true);
			@SuppressWarnings("unchecked")
			HashMap<Integer, Enchantment> byId = (HashMap<Integer, Enchantment>) byIdField.get(null);
			@SuppressWarnings("unchecked")
			HashMap<String, Enchantment> byName = (HashMap<String, Enchantment>) byNameField.get(null);
			if (byId.containsKey(enchantment.getId())) byId.remove(enchantment.getId());
			if (byName.containsKey(enchantment.getName())) byName.remove(enchantment.getName());
		}

		catch (Exception ignored) {
		}
		enchantment.setPlugin(MoreEnchantments.getInstance());
		Enchantment.registerEnchantment(enchantment);
		enchantments.add(enchantment);
		MoreEnchantments.getInstance().getServer().getPluginManager().registerEvents(enchantment, MoreEnchantments.getInstance());
		MoreEnchantments.getInstance().getLogger().info("Enchantment Registered: " + enchantment.getName());
		enchantment.init();
	}

	public void addCustomEnchantment(ItemStack stack, CustomEnchantment enchant, Integer level) {
		stack.addUnsafeEnchantment(enchant, level);
		updateLore(stack);
	}

	public void update(ItemStack stack){
		if(stack != null){
			ItemMeta meta = stack.getItemMeta();
			ArrayList<String> lore = new ArrayList<String>();
			if (meta.getLore() != null) {
				lore = (ArrayList<String>) meta.getLore();
				Iterator<String> itr = lore.iterator();
				while (itr.hasNext()) {
					String next = Util.visable(itr.next());
					
					if(next.contains("MoreEnchantments")){
						String[] sections = next.split("\\;");
						for(String section : sections){
							String[] parts = section.split("\\:");
							if(parts.length == 2){
								if(parts[0] != null && parts[1] != null){
									if(getCustomEnchantment(parts[0]) != null){
										try{
											Integer level = Integer.parseInt(parts[1]);
											meta.addEnchant(getCustomEnchantment(parts[0]), level, true);
										}catch (Exception ignored){}
										
									}
								}
							}
						}
						
						itr.remove();
					}
				}
				meta.setLore(lore);
				stack.setItemMeta(meta);
				updateLore(stack);
				
			}
		}
	}
	
	public void updateLore(ItemStack stack){
		ItemMeta meta = stack.getItemMeta();
		ArrayList<String> lore = new ArrayList<String>();
		if (meta.getLore() != null) {
			lore = (ArrayList<String>) meta.getLore();
			Iterator<String> itr = lore.iterator();
			while (itr.hasNext()) {
				String next = Util.visable(itr.next());
				if (next.contains("~")) {
					itr.remove();
				}
				
			}
			meta.setLore(lore);
		}
		
		for (CustomEnchantment enchantment : enchantments) {
			if (stack.containsEnchantment(enchantment)) {
				lore.add(ChatColor.COLOR_CHAR + "~" + ChatColor.RESET + "" + ChatColor.GRAY + enchantment.getName().replaceAll("_", " ") + " " + Util.toRoman(stack.getEnchantmentLevel(enchantment)));
				meta.setLore(lore);
			}
		}

		stack.setItemMeta(meta);
	}
	
	public CustomEnchantment getCustomEnchantment(String enchant) {
		for (CustomEnchantment enchantment : enchantments) {
			if (enchantment.getName().equalsIgnoreCase(enchant)) {
				return enchantment;
			}
		}

		return null;
	}

}
