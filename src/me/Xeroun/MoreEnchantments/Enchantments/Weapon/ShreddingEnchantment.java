package me.Xeroun.MoreEnchantments.Enchantments.Weapon;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;

public class ShreddingEnchantment extends CustomEnchantment {

	public ShreddingEnchantment(int id) {
		super(id);
	}

	@Override
	public String getName() {
		return "Shredding";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 0;
	}

	@EventHandler (priority = EventPriority.HIGHEST)
	public void onHit(EntityDamageByEntityEvent event){
		if(event.isCancelled()) return;
		if(this.isLiving(event.getDamager(), event.getEntity())){
			LivingEntity damager = (LivingEntity) event.getDamager();
			LivingEntity damaged = (LivingEntity) event.getEntity();
			if(this.hasEnchantmentInHand(damager)){
				if (damaged.getNoDamageTicks() < damaged.getMaximumNoDamageTicks() / 2) {
					if(damaged.getEquipment().getArmorContents() != null){
						for(ItemStack stack : damaged.getEquipment().getArmorContents()){
							if(stack != null){
								stack.setDurability((short) (stack.getDurability() + 2 * damager.getEquipment().getItemInHand().getEnchantmentLevel(this)));
							}
						}
						
					}
				}
			}
		}
	}
	
}
