package me.Xeroun.MoreEnchantments.Enchantments.Bow;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.util.Vector;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.Util;

public class BullseyeEnchantment extends CustomEnchantment {

	private List<Projectile> bullseye = new ArrayList<Projectile>();

	public BullseyeEnchantment(int id) {
		super(id);
	}

	@Override
	public void init() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@Override
			public void run() {
				Iterator<Projectile> itr = bullseye.iterator();
				while (itr.hasNext()) {
					Projectile proj = itr.next();
					if (Util.getMetadata(proj, "Bullseye-Vector", getPlugin()) != null && Util.getMetadata(proj, "Bullseye-Level", getPlugin()) != null) {
						Integer level = (Integer) Util.getMetadata(proj, "Bullseye-Level", getPlugin());
						if (proj.getTicksLived() < 20 * (10 + level * 5)) {
							proj.setVelocity((Vector) Util.getMetadata(proj, "Bullseye-Vector", getPlugin()));
							
						} else {
							itr.remove();
						}
					} else {
						itr.remove();
					}
				}
			}
		}, 0, 1L);
	}

	@Override
	public String getName() {
		return "Bullseye";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@EventHandler
	public void onLaunch(EntityShootBowEvent event) {
		if (event.getEntity() instanceof LivingEntity) {
			if (event.getBow().containsEnchantment(this)) {
				if(event.getForce() > 0.85F){
					Util.setMetadata((Projectile) event.getProjectile(), "Bullseye-Vector", event.getProjectile().getVelocity(), getPlugin());
					Util.setMetadata((Projectile) event.getProjectile(), "Bullseye-Level", event.getBow().getEnchantmentLevel(this), getPlugin());
					bullseye.add((Projectile) event.getProjectile());
				}
				
			}
		}
	}

	@EventHandler
	public void onLand(ProjectileHitEvent event) {
		if (bullseye.contains(event.getEntity())) {
			bullseye.remove(event.getEntity());
		}
	}

}
