package me.Xeroun.MoreEnchantments.Enchantments.Weapon;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.ParticleEffects;

import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class PoisonAspectEnchantment extends CustomEnchantment {

	public PoisonAspectEnchantment(int id) {
		super(id);
	}

	@Override
	public String getName() {
		return "Poison_Aspect";
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
			if(hasEnchantmentInHand(damager)){
				LivingEntity entity = (LivingEntity) event.getEntity();
				if (entity.getNoDamageTicks() < entity.getMaximumNoDamageTicks() / 2) {
					entity.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * (1 + 5 * damager.getEquipment().getItemInHand().getEnchantmentLevel(this)), 0));
					try {
						ParticleEffects.playEffect(ParticleEffects.MOB_SPELL.getName(), entity.getLocation().add(0, 1, 0), 0.5F, 0, 0.5F, 0.05F, 5);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
	
}
