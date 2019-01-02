package me.Xeroun.MoreEnchantments.Helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public enum ParticleEffects {

	HUGE_EXPLOSION("hugeexplosion"), LARGE_EXPLODE("largeexplode"), FIREWORKS_SPARK("fireworksSpark"), BUBBLE("bubble"), SUSPEND("suspend"), DEPTH_SUSPEND("depthSuspend"), TOWN_AURA("townaura"), CRIT("crit"), MAGIC_CRIT("magicCrit"), MOB_SPELL("mobSpell"), MOB_SPELL_AMBIENT("mobSpellAmbient"), SPELL("spell"), INSTANT_SPELL("instantSpell"), WITCH_MAGIC("witchMagic"), NOTE("note"), PORTAL("portal"), ENCHANTMENT_TABLE("enchantmenttable"), EXPLODE("explode"), FLAME("flame"), LAVA("lava"), FOOTSTEP("footstep"), SPLASH("splash"), LARGE_SMOKE("largesmoke"), CLOUD("cloud"), RED_DUST("reddust"), SNOWBALL_POOF("snowballpoof"), DRIP_WATER("dripWater"), DRIP_LAVA("dripLava"), SNOW_SHOVEL("snowshovel"), SLIME("slime"), HEART("heart"), ANGRY_VILLAGER("angryVillager"), HAPPY_VILLAGER("happerVillager"), ICONCRACK("iconcrack_"), TILECRACK("tilecrack_");

	private String name;

	ParticleEffects(String particleName) {
		this.name = particleName;
	}

	public String getName() {
		return name;
	}

	public static void playEffect(String particleName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count) throws Exception {

		Object packet = NMSReflection.getObject("PacketPlayOutWorldParticles");
		NMSReflection.setField(packet, "a", particleName);
		NMSReflection.setField(packet, "b", (float) location.getX());
		NMSReflection.setField(packet, "c", (float) location.getY());
		NMSReflection.setField(packet, "d", (float) location.getZ());
		NMSReflection.setField(packet, "e", offsetX);
		NMSReflection.setField(packet, "f", offsetY);
		NMSReflection.setField(packet, "g", offsetZ);
		NMSReflection.setField(packet, "h", speed);
		NMSReflection.setField(packet, "i", count);

		if (Bukkit.getOnlinePlayers() != null) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player != null) {
					NMSReflection.sendPacket(player, packet);
				}
			}
		}

	}

	public static void playEffect(String particleName, Location location, float offsetX, float offsetY, float offsetZ, float speed, int count, Player player) throws Exception {

		Object packet = NMSReflection.getObject("PacketPlayOutWorldParticles");
		NMSReflection.setField(packet, "a", particleName);
		NMSReflection.setField(packet, "b", (float) location.getX());
		NMSReflection.setField(packet, "c", (float) location.getY());
		NMSReflection.setField(packet, "d", (float) location.getZ());
		NMSReflection.setField(packet, "e", offsetX);
		NMSReflection.setField(packet, "f", offsetY);
		NMSReflection.setField(packet, "g", offsetZ);
		NMSReflection.setField(packet, "h", speed);
		NMSReflection.setField(packet, "i", count);

		if (player != null) {
			NMSReflection.sendPacket(player, packet);
		}

	}

}
