package me.Xeroun.MoreEnchantments.Enchantments.Bow;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.ParticleEffects;

public class VenomEnchantment extends CustomEnchantment {

	private HashMap<Projectile, Integer> projectiles = new HashMap<Projectile, Integer>();

	public VenomEnchantment(int id) {
		super(id);
	}

	@Override
	public void init() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@Override
			public void run() {
				for (Projectile proj : projectiles.keySet()) {
					
					if(proj.getTicksLived() > 0){
						try {
							Location loc = proj.getLocation();
							ParticleEffects.playEffect(ParticleEffects.MOB_SPELL.getName(), loc, 0.1F, 0, 0.1F, 0.05F, 3);
						} catch (Exception e) {e.printStackTrace();}
					}
				}
			}
		}, 0, 1L);
	}

	@Override
	public String getName() {
		return "Venom";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public int getMaxLevel() {
		return 1;
	}

	@EventHandler
	public void onShoot(final EntityShootBowEvent event) {
		if (event.getBow().containsEnchantment(this)) {
			projectiles.put((Projectile) event.getProjectile(), event.getBow().getEnchantmentLevel(this));
		}
	}

	@EventHandler
	public void onHit(EntityDamageByEntityEvent event) {
		if (event.getDamager() instanceof Projectile && event.getEntity() instanceof LivingEntity) {
			Projectile proj = (Projectile) event.getDamager();
			LivingEntity damaged = (LivingEntity) event.getEntity();
			if (projectiles.containsKey(proj)) {
				int level = projectiles.get(proj);
				damaged.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 20 * (1 + 5 * level), 0));
			}
		}
	}

	@EventHandler
	public void onLand(final ProjectileHitEvent event) {
		if (projectiles.containsKey(event.getEntity())) {
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
				@Override
				public void run() {
					projectiles.remove(event.getEntity());
				}
			}, 1L);
		}
	}
}
