package me.Xeroun.MoreEnchantments.Enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public abstract class CustomEnchantment extends Enchantment implements Listener {
	
	private Plugin plugin = null;
	public abstract String getName();
	public abstract EnchantmentTarget getItemTarget();
	public abstract int getMaxLevel();
	
	public CustomEnchantment(int id) {
		super(id);	
	}

	public void init(){}
	
	public void setPlugin(Plugin plugin){
		this.plugin = plugin;
	}
	
	public Plugin getPlugin(){
		return plugin;
	}
	
	public boolean hasEnchantmentInHand(LivingEntity entity){
		if(entity.getEquipment() != null){
			if(entity.getEquipment().getItemInHand() != null){
				if(entity.getEquipment().getItemInHand().containsEnchantment(this)){
					return true;
				}
				
			}
		}
		return false;
	}
	
	public boolean isLiving(Entity entity1, Entity entity2){
		if(entity1 instanceof LivingEntity && entity2 instanceof LivingEntity){
			return true;
		}
		return false;
	}
	
	public boolean canEnchantItem(ItemStack arg0){
		return true;
	}
	
	public boolean conflictsWith(Enchantment arg0){
		return false;
	}
	
	public int getStartLevel(){
		return 1;
	}
	
}
