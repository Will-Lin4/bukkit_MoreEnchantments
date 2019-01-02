package me.Xeroun.MoreEnchantments.Enchantments.Bow;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.event.entity.ProjectileHitEvent;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.NMSReflection;
import me.Xeroun.MoreEnchantments.Helpers.ParticleEffects;

public class PhantomEnchantment extends CustomEnchantment {

	ArrayList<Projectile> smoke = new ArrayList<Projectile>();

	public PhantomEnchantment(int id) {
		super(id);
	}

	@Override
	public void init() {
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@Override
			public void run() {
				for (Projectile proj : smoke) {
					if(proj.getTicksLived() > 1){
						Location loc = proj.getLocation();
						if(proj.getShooter() instanceof Player){
							Player player = (Player) proj.getShooter();
							try {
								ParticleEffects.playEffect(ParticleEffects.LARGE_SMOKE.getName(), loc, 0, 0, 0, 0.05F, 3, player);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}

				}

			}
		}, 0, 1L);
	}
	
	@Override
	public String getName() {
		return "Phantom";
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
			Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(getPlugin(), new Runnable() {
				public void run() {
					Object packet = NMSReflection.getObject("PacketPlayOutEntityDestroy");
					int id[] = { event.getProjectile().getEntityId() };
					NMSReflection.setField(packet, "a", id);

					if (Bukkit.getOnlinePlayers() != null) {
						for (Player player : Bukkit.getOnlinePlayers()) {
							if (player != null) {
								NMSReflection.sendPacket(player, packet);
								
							}
						}
					}
					smoke.add((Projectile) event.getProjectile());

				}
			}, 1L);

		}
	}

	@EventHandler
	public void onLand(ProjectileHitEvent event) {
		if (smoke.contains(event.getEntity())) {
			smoke.remove(event.getEntity());
		}
	}

}
