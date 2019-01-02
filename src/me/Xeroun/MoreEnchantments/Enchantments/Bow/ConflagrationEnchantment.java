package me.Xeroun.MoreEnchantments.Enchantments.Bow;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.ParticleEffects;
import me.Xeroun.MoreEnchantments.Helpers.SoundEffects;

public class ConflagrationEnchantment extends CustomEnchantment {

	private HashMap<Projectile, Integer> projectiles = new HashMap<Projectile, Integer>();

	public ConflagrationEnchantment(int id) {
		super(id);
	}

	@Override
	public void init() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@Override
			public void run() {

				for (Projectile proj : projectiles.keySet()) {
					if(proj.getTicksLived() > 0){
						Location loc = proj.getLocation();
						try {
							ParticleEffects.playEffect(ParticleEffects.RED_DUST.getName(), loc, 0, 0, 0, 0.05F, 3);
							ParticleEffects.playEffect(ParticleEffects.FLAME.getName(), loc, 0, 0, 0, 0.05F, 3);
						} catch (Exception e) {e.printStackTrace();}
					}

				}

			}
		}, 0, 1L);
	}

	@Override
	public String getName() {
		return "Conflagration";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@EventHandler
	public void onShoot(EntityShootBowEvent event) {
		if (event.getBow().containsEnchantment(this)) {
			projectiles.put((Projectile) event.getProjectile(), event.getBow().getEnchantmentLevel(this));
		}
	}

	@EventHandler
	public void onLand(ProjectileHitEvent event) throws Exception {
		if(projectiles.containsKey(event.getEntity())){
			Location loc = event.getEntity().getLocation();
			int level = projectiles.get(event.getEntity());

			ParticleEffects.playEffect(ParticleEffects.LAVA.getName(), loc, level, level, level, 0.1F, 50 * level);
			ParticleEffects.playEffect(ParticleEffects.FLAME.getName(), loc, level, level, level, 0.1F, 50 * level);
			SoundEffects.playSound(SoundEffects.RandomFizz, loc, 2 + (0.2F * level), 1);

			if (event.getEntity().getNearbyEntities(level * 1.5, level * 1.5, level * 1.5) != null) {
				for (Entity entity : event.getEntity().getNearbyEntities(level * 1.5, level * 1.5, level * 1.5)) {
					if (entity instanceof LivingEntity) {
						LivingEntity e = (LivingEntity) entity;
						if (entity != event.getEntity().getShooter()) {
							if (20 * (4 + 5 * (level - 1)) > e.getFireTicks()) {
								e.setFireTicks(20 * (4 + 5 * (level - 1)));
							}
						}

					}
				}
			}

			projectiles.remove(event.getEntity());
		}

	}

}
