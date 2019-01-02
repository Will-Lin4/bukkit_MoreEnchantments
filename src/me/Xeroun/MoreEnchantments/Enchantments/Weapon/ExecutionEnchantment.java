package me.Xeroun.MoreEnchantments.Enchantments.Weapon;
import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class ExecutionEnchantment extends CustomEnchantment {
	
	public ExecutionEnchantment(int id) {
		super(id);
	}
	
	@Override
	public String getName() {
		return "Execution";
	}
	
	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.isCancelled()) return;
		if(isLiving(event.getDamager(), event.getEntity())){
			LivingEntity damager = (LivingEntity) event.getDamager();
			LivingEntity entity = (LivingEntity) event.getEntity();
			if(hasEnchantmentInHand(damager)){
				event.setDamage(event.getDamage() + (1 - (entity.getHealth() / entity.getMaxHealth())) * (2 * damager.getEquipment().getItemInHand().getEnchantmentLevel(this)));
			}
		}

	}
	
	
}
