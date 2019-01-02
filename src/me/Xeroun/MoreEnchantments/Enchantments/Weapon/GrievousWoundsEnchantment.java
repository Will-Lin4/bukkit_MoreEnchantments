package me.Xeroun.MoreEnchantments.Enchantments.Weapon;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.potion.PotionEffectType;

import me.Xeroun.MoreEnchantments.Enchantments.CustomEnchantment;
import me.Xeroun.MoreEnchantments.Helpers.NMSReflection;
import me.Xeroun.MoreEnchantments.Helpers.ParticleEffects;
import me.Xeroun.MoreEnchantments.Helpers.Util;

public class GrievousWoundsEnchantment extends CustomEnchantment {

	private HashMap<String, Integer> inflicted = new HashMap<String, Integer>();
	public GrievousWoundsEnchantment(int id) {
		super(id);
	}

	@Override
	public void init(){
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@Override
			public void run() {
				for(Player player : Bukkit.getOnlinePlayers()){
					if(inflicted.containsKey(player.getName())){
						if(Util.getMetadata(player, "Health", getPlugin()) == null){
							Util.setMetadata(player, "Health", player.getHealth(), getPlugin());
						}else{
							if((double) Util.getMetadata(player, "Health", getPlugin()) != player.getHealth()){
								if((double) Util.getMetadata(player, "Health", getPlugin()) < player.getHealth()){
									double difference = player.getHealth() - (double) Util.getMetadata(player, "Health", getPlugin());
									player.setHealth(player.getHealth() - (difference * (0.33)));
								}
								Util.setMetadata(player, "Health", player.getHealth(), getPlugin());
							}
						}
					}
				}
			}
		}, 0, 1L);
		
		Bukkit.getServer().getScheduler().scheduleSyncRepeatingTask(getPlugin(), new Runnable() {
			@SuppressWarnings("deprecation")
			@Override
			public void run() {
				ArrayList<String> remove = new ArrayList<String>();
				for(String string : inflicted.keySet()){
					if(inflicted.get(string) != null){
						Random rn = new Random();
						int random = rn.nextInt(100) + 1;
						if(random <= 30){
							Player player = Bukkit.getPlayer(string);
							player.damage(1);
						}
						inflicted.put(string, inflicted.get(string) - 1);
						if(inflicted.get(string) <= 0){
							remove.add(string);
						}
					}
					
				}
				
				for(String string : remove){
					Object packet = NMSReflection.getObject("PacketPlayOutRemoveEntityEffect");
					NMSReflection.setField(packet, "a", Bukkit.getPlayer(string).getEntityId());
					NMSReflection.setField(packet, "b", PotionEffectType.HARM.getId());
					NMSReflection.sendPacket(Bukkit.getPlayer(string), packet);
					inflicted.remove(string);
					Bukkit.getPlayer(string).damage(1);
				}
				
			}
		}, 0, 20L);
		
	}
	
	@Override
	public String getName() {
		return "Grievous_Wounds";
	}

	@Override
	public EnchantmentTarget getItemTarget() {
		return EnchantmentTarget.WEAPON;
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@EventHandler
	public void onQuit(PlayerQuitEvent event){
		if(inflicted.containsKey(event.getPlayer().getName())){
			inflicted.remove(event.getPlayer().getName());
		}
	}
	
	@EventHandler
	public void onKilled(PlayerDeathEvent event){
		if(inflicted.containsKey(event.getEntity().getName())){
			inflicted.remove(event.getEntity().getName());
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler (priority = EventPriority.HIGHEST)
	public void onDamage(EntityDamageByEntityEvent event){
		if(event.isCancelled()) return;
		if(event.getDamager() instanceof LivingEntity && event.getEntity() instanceof Player){
			LivingEntity damager = (LivingEntity) event.getDamager();
			Player player = (Player) event.getEntity();
			if(this.hasEnchantmentInHand(damager)){
				if (player.getNoDamageTicks() < player.getMaximumNoDamageTicks() / 2) {
					int level = damager.getEquipment().getItemInHand().getEnchantmentLevel(this);
					Random rn = new Random();
					int random = rn.nextInt(100) + 1;
					if(random <= 20 * level){
						Object packet = NMSReflection.getObject("PacketPlayOutEntityEffect");
						NMSReflection.setField(packet, "a", player.getEntityId());
						NMSReflection.setField(packet, "b", (byte) PotionEffectType.HARM.getId());
						NMSReflection.setField(packet, "c", (byte) 0);
						NMSReflection.setField(packet, "d", (short) (20 * (10 + (5 * level))));
						
						if(inflicted.containsKey(player.getName())){
							if(inflicted.get(player.getName()) < 10 + 5 * level){
								inflicted.put(player.getName(), 10 + 5 * level);
								NMSReflection.sendPacket(player, packet);
								try {
									ParticleEffects.playEffect(ParticleEffects.CRIT.getName(), player.getLocation().add(0, 1, 0), 0.5F, 0, 0.5F, 0.05F, 10);
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}else{
							inflicted.put(player.getName(), 10 + 5 * level);
							NMSReflection.sendPacket(player, packet);
						}
					}
				}
			}
		}
	}
	
}
