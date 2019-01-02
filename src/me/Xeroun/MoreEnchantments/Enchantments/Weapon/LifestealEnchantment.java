package me.Xeroun.MoreEnchantments.Enchantments.Weapon;

import me.Xeroun.MoreEnchantments.MoreEnchantments;
import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

public class LifestealEnchantment extends CustomEnchantment {

	public LifestealEnchantment(int id) {
		super(id);
	}

	@Override
	public String getName() {
		return "Lifesteal";
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
			
			if (entity.getNoDamageTicks() < entity.getMaximumNoDamageTicks() / 2) {
				if (damager.getEquipment() != null) {
					if (damager.getEquipment().getItemInHand() != null) {
						ItemStack hand = damager.getEquipment().getItemInHand();
						if (hand.containsEnchantment(MoreEnchantments.getInstance().getHandler().getCustomEnchantment("lifesteal"))) {
							Enchantment enchantment = MoreEnchantments.getInstance().getHandler().getCustomEnchantment("lifesteal");
							if (damager.getHealth() + event.getDamage() * (0.1 * hand.getEnchantmentLevel(enchantment)) < damager.getMaxHealth()) {
								damager.setHealth(damager.getHealth() + event.getDamage() * (0.1 * hand.getEnchantmentLevel(enchantment)));
							} else {
								damager.setHealth(damager.getMaxHealth());
							}

						}
					}
				}

			}
			
		}
	}
}
