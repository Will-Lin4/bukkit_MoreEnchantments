package me.Xeroun.MoreEnchantments;

import java.util.ArrayList;

import me.Xeroun.MoreEnchantments.Helpers.Util;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.inventory.AnvilInventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Events implements Listener {
	
	@EventHandler
	public void onOpenInventory(InventoryOpenEvent event){
		if(event.getInventory() instanceof AnvilInventory){
			Player player = (Player) event.getPlayer();
			if(player.getInventory().getContents() != null){
				for(final ItemStack stack : player.getInventory().getContents()){
					if(stack != null){
						if(stack.getEnchantments() != null){
							String enchants = "MoreEnchantments;";
							ArrayList<String> lore = new ArrayList<String>();
							ItemMeta meta = stack.getItemMeta();		
							if(meta.getLore() != null){
								lore = (ArrayList<String>) meta.getLore();
							}
							
							for(final Enchantment enchant : stack.getEnchantments().keySet()){
								if(MoreEnchantments.getInstance().getHandler().getCustomEnchantment(enchant.getName()) != null){
									enchants = enchants + enchant.getName() + ":" + stack.getEnchantmentLevel(enchant) + ";";
									Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(MoreEnchantments.getInstance(), new Runnable() {
										public void run() {
											stack.removeEnchantment(enchant);
										}
									}, 1L);
								}
							}
							
							if(!enchants.equalsIgnoreCase("MoreEnchantments;")) lore.add(Util.invisible(enchants) + ChatColor.RED + "Can't be placed in the anvil");
							meta.setLore(lore);
							stack.setItemMeta(meta);
													
						}
					}
					
				}
			}
			
		}
	}
	
	@EventHandler
	public void onClose(InventoryCloseEvent event){
		Player player = (Player) event.getPlayer();
		for(ItemStack stack : player.getInventory().getContents()){
			MoreEnchantments.getInstance().getHandler().update(stack);
		}
	}
	
	@EventHandler
	public void onClick(InventoryClickEvent event){
		if(event.getInventory() instanceof AnvilInventory){
			if(event.getCurrentItem() != null){
				if(event.getCurrentItem().getItemMeta() != null){
					if(event.getCurrentItem().getItemMeta().getLore() != null){
						for(String string : event.getCurrentItem().getItemMeta().getLore()){
							if(Util.visable(string).contains("MoreEnchantments")){
								event.setCancelled(true);
								return;
							}
						}
					}
				}
			}
			
		}
	}
	
	@EventHandler
	public void removeOnLand(ProjectileHitEvent event) {
		
		if (Util.getMetadata(event.getEntity(), "RemoveOnLand", MoreEnchantments.getInstance()) != null) {
			Boolean remove = (Boolean) Util.getMetadata(event.getEntity(), "RemoveOnLand", MoreEnchantments.getInstance());
			if (remove == true) {
				event.getEntity().remove();
			}

		}
	}

}
