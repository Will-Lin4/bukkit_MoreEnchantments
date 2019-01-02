package me.Xeroun.MoreEnchantments.Enchantments.Armor;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class ToughnessEnchantment extends CustomEnchantment {

	public ToughnessEnchantment(int id) {
		super(id);
	}

	@Override
	public String getName() {
		return "Toughness";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.ARMOR;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event){
		if(this.isLiving(event.getDamager(), event.getEntity())){
			LivingEntity damager = (LivingEntity) event.getDamager();
			LivingEntity damaged = (LivingEntity) event.getEntity();
			
			if(damager.getEquipment() != null){
				if(damager.getEquipment().getItemInHand() != null){
					for(ItemStack stack : damaged.getEquipment().getArmorContents()){
						if(stack != null){
							if(stack.containsEnchantment(this)){
								if (damaged.getNoDamageTicks() < damaged.getMaximumNoDamageTicks() / 2) {
									damager.getEquipment().getItemInHand().setDurability((short) (damager.getEquipment().getItemInHand().getDurability() + stack.getEnchantmentLevel(this)));
								}
							}
						}
						
					}
				}
			}
			
			
		}
	}
	
}
