package me.Xeroun.MoreEnchantments.Enchantments.Bow;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.Util;

import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.util.Vector;

public class VolleyEnchantment extends CustomEnchantment {

	public VolleyEnchantment(int id) {
		super(id);
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.BOW;
	}

	@Override
	public String getName() {
		return "Volley";
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@EventHandler
	public void projectileLaunch(ProjectileLaunchEvent event) {
		
		if (event.getEntity().getShooter() instanceof LivingEntity) {
			LivingEntity shooter = (LivingEntity) event.getEntity().getShooter();

			if(hasEnchantmentInHand(shooter)){
				Vector velocity = event.getEntity().getVelocity();
				double speed = velocity.length();
				Vector direction = new Vector(velocity.getX() / speed, velocity.getY() / speed, velocity.getZ() / speed);
				double spray = 3.5D;
				final Location startLoc = event.getEntity().getLocation().clone();

				for (int i = 0; i < 3* shooter.getEquipment().getItemInHand().getEnchantmentLevel(this); i++) {
					Projectile proj = shooter.getWorld().spawn(startLoc, event.getEntity().getClass());
					proj.setBounce(event.getEntity().doesBounce());
					proj.setFireTicks(event.getEntity().getFireTicks());
					proj.setFallDistance(event.getEntity().getFallDistance());
					proj.setShooter(event.getEntity().getShooter());
					Util.setMetadata(proj, "RemoveOnLand", true, getPlugin());
					proj.setVelocity(new Vector(direction.getX() + (Math.random() - 0.5) / spray, direction.getY(), direction.getZ() + (Math.random() - 0.5) / spray).normalize().multiply(speed));
				}
			}
		}
	}

}
