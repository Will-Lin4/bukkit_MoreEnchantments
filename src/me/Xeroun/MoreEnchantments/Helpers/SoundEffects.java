package me.Xeroun.MoreEnchantments.Helpers;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;

public class SoundEffects {
	public final static String AmbientCave = "ambient.cave.cave";
	public final static String AmbientWeatherRain = "ambient.weather.rain";
	public final static String AmbientWeatherThunder = "ambient.weather.thunder";
	public final static String DamageFallBig = "damage.fallbig";
	public final static String DamageFallSmall = "damage.fallsmall";
	public final static String FireActive = "fire.fire";
	public final static String FireIgnite = "fire.ignite";
	public final static String LiquidLava = "liquid.lava";
	public final static String LiquidLavaPop = "liquid.lavapop";
	public final static String LiquidSplash = "liquid.splash";
	public final static String LiquidWater = "liquid.water";
	public final static String MobBlazeBreathe = "mob.blaze.breathe";
	public final static String MobBlazeDeath = "mob.blaze.death";
	public final static String MobBlazeHit = "mob.blaze.hit";
	public final static String MobCatHiss = "mob.cat.hiss";
	public final static String MobCatHitt = "mob.cat.hitt";
	public final static String MobCatMeow = "mob.cat.meow";
	public final static String MobCatPurr = "mob.cat.purr";
	public final static String MobCatPurreow = "mob.cat.purreow";
	public final static String MobChickenHurt = "mob.chicken.hurt";
	public final static String MobChickenPlop = "mob.chicken.plop";
	public final static String MobCowHurt = "mob.cow.hurt";
	public final static String MobCreeper = "mob.creeper";
	public final static String MobCreeperDeath = "mob.creeperdeath";
	public final static String MobEndermenDeath = "mob.endermen.death";
	public final static String MobEndermenHit = "mob.endermen.hit";
	public final static String MobEndermenIdle = "mob.endermen.idle";
	public final static String MobEndermenPortal = "mob.endermen.portal";
	public final static String MobEndermenScream = "mob.endermen.scream";
	public final static String MobEndermenStare = "mob.endermen.stare";
	public final static String MobGhastAffectionateScream = "mob.ghast.affectionate scream";
	public final static String MobGhastCharge = "mob.ghast.charge";
	public final static String MobGhastDeath = "mob.ghast.death";
	public final static String MobGhastFireball = "mob.ghast.fireball";
	public final static String MobGhastMoan = "mob.ghast.moan";
	public final static String MobGhastScream = "mob.ghast.scream";
	public final static String MobIrongolemDeath = "mob.irongolem.death";
	public final static String MobIrongolemHit = "mob.irongolem.hit";
	public final static String MobIrongolemThrow = "mob.irongolem.throw";
	public final static String MobIrongolemWalk = "mob.irongolem.walk";
	public final static String MobMagmacubeBig = "mob.magmacube.big";
	public final static String MobMagmacubeJump = "mob.magmacube.jump";
	public final static String MobMagmacubeSmall = "mob.magmacube.small";
	public final static String MobPigdeath = "mob.pig.death";
	public final static String MobSilverfishHit = "mob.silverfish.hit";
	public final static String MobSilverfishKill = "mob.silverfish.kill";
	public final static String MobSilverfishSay = "mob.silverfish.say";
	public final static String MobSilverfishStep = "mob.silverfish.step";
	public final static String MobSkeletonDeath = "mob.skeleton.death";
	public final static String MobSkeletonHurt = "mob.skeleton.hurt";
	public final static String MobSlimeattack = "mob.slime.attack";
	public final static String MobSpiderDeath = "mob.spider.death";
	public final static String MobWolfBark = "mob.wolf.bark";
	public final static String MobWolfDeath = "mob.wolf.death";
	public final static String MobWolfGrowl = "mob.wolf.growl";
	public final static String MobWolfHowl = "mob.wolf.howl";
	public final static String MobWolfHurt = "mob.wolf.hurt";
	public final static String MobWolfPanting = "mob.wolf.panting";
	public final static String MobWolfShake = "mob.wolf.shake";
	public final static String MobWolfWhine = "mob.wolf.whine";
	public final static String MobZombieMetal = "mob.zombie.metal";
	public final static String MobZombieWood = "mob.zombie.wood";
	public final static String MobZombieWoodbreak = "mob.zombie.woodbreak";
	public final static String MobZombie = "mob.zombie";
	public final static String MobZombiedeath = "mob.zombie.death";
	public final static String MobZombiehurt = "mob.zombie.hurt";
	public final static String MobZombiepigZpig = "mob.zombiepig.zpig";
	public final static String MobZombiepigZpigangry = "mob.zombiepig.zpigangry";
	public final static String MobZombiepigZpigdeath = "mob.zombiepig.zpigdeath";
	public final static String MobZombiepigZpighurt = "mob.zombiepig.zpighurt";
	public final static String NoteBass = "note.bass";
	public final static String NoteBassAttack = "note.bassattack";
	public final static String NoteBd = "note.bd";
	public final static String NoteHarp = "note.harp";
	public final static String NoteHat = "note.hat";
	public final static String NotePling = "note.pling";
	public final static String NoteSnare = "note.snare";
	public final static String PortalNear = "portal.portal";
	public final static String PortalTravel = "portal.travel";
	public final static String PortalTrigger = "portal.trigger";
	public final static String RandomBow = "random.bow";
	public final static String RandomBowhit = "random.bowhit";
	public final static String RandomBreak = "random.break";
	public final static String RandomBreath = "random.breath";
	public final static String RandomBurp = "random.burp";
	public final static String RandomChestclosed = "random.chestclosed";
	public final static String RandomChestopen = "random.chestopen";
	public final static String RandomClick = "random.click";
	public final static String RandomDoorClose = "random.door_close";
	public final static String RandomDoorOpen = "random.door_open";
	public final static String RandomDrink = "random.drink";
	public final static String RandomEat = "random.eat";
	public final static String RandomExplode = "random.explode";
	public final static String RandomFizz = "random.fizz";
	public final static String RandomFuse = "random.fuse";
	public final static String RandomGlass = "random.glass";
	public final static String RandomLevelup = "random.levelup";
	public final static String RandomOldExplode = "random.old_explode";
	public final static String RandomOrb = "random.orb";
	public final static String RandomPop = "random.pop";
	public final static String RandomSplash = "random.splash";
	public final static String RandomWoodClick = "random.wood click";
	public final static String StepCloth = "step.cloth";
	public final static String StepGrass = "step.grass";
	public final static String StepGravel = "step.gravel";
	public final static String StepSand = "step.sand";
	public final static String StepSnow = "step.snow";
	public final static String StepStone = "step.stone";
	public final static String StepWood = "step.wood";
	public final static String TilePistonIn = "tile.piston.in";
	public final static String TilePistonOut = "tile.piston.out";
	public final static String DamageHit = "damage.hit";
	public final static String DigCloth = "dig.cloth";
	public final static String DigGrass = "dig.grass";
	public final static String DigGravel = "dig.gravel";
	public final static String DigSand = "dig.sand";
	public final static String DigSnow = "dig.snow";
	public final static String DigStone = "dig.stone";
	public final static String DigWood = "dig.wood";
	public final static String LiquidSwim = "liquid.swim";
	public final static String MinecartBase = "minecart.base";
	public final static String MinecartInside = "minecart.inside";
	public final static String MobChickenSay = "mob.chicken.say";
	public final static String MobChickenStep = "mob.chicken.step";
	public final static String MobCowSay = "mob.cow.say";
	public final static String MobCowStep = "mob.cow.step";
	public final static String MobCreeperSay = "mob.creeper.say";
	public final static String MobPigDeath = "mob.pig.death";
	public final static String MobPigSay = "mob.pig.say";
	public final static String MobPigStep = "mob.pig.step";
	public final static String MobSheepSay = "mob.sheep.say";
	public final static String MobSheepShear = "mob.sheep.shear";
	public final static String MobSheepStep = "mob.sheep.step";
	public final static String MobSkeletonSay = "mob.skeleton.say";
	public final static String MobSkeletonStep = "mob.skeleton.step";
	public final static String MobSlimeAttack = "mob.slime.attack";
	public final static String MobSlimeBig = "mob.slime.big";
	public final static String MobSlimeSmall = "mob.slime.small";
	public final static String MobSpiderSay = "mob.spider.say";
	public final static String MobSpiderStep = "mob.spider.step";
	public final static String MobWolfStep = "mob.wolf.step";
	public final static String MobZombieDeath = "mob.zombie.death";
	public final static String MobZombieHurt = "mob.zombie.hurt";
	public final static String MobZombieSay = "mob.zombie.say";
	public final static String MobZombieStep = "mob.zombie.step";
	public final static String RandomClassicHurt = "random.classic_hurt";
	public final static String StepLadder = "step.ladder";
	public final static String MobBatDeath = "mob.bat.death";
	public final static String MobBatHurt = "mob.bat.hurt";
	public final static String MobBatIdle = "mob.bat.idle";
	public final static String MobBatTakeoff = "mob.bat.takeoff";
	public final static String MobEnderdragonEnd = "mob.enderdragon.end";
	public final static String MobEnderdragonGrowl = "mob.enderdragon.growl";
	public final static String MobEnderdragonHit = "mob.enderdragon.hit";
	public final static String MobEnderdragonWings = "mob.enderdragon.wings";
	public final static String MobWitherDeath = "mob.wither.death";
	public final static String MobWitherHurt = "mob.wither.hurt";
	public final static String MobWitherIdle = "mob.wither.idle";
	public final static String MobWitherShoot = "mob.wither.shoot";
	public final static String MobWitherSpawn = "mob.wither.spawn";
	public final static String MobZombieInfect = "mob.zombie.infect";
	public final static String MobZombieRemedy = "mob.zombie.remedy";
	public final static String MobZombieUnfect = "mob.zombie.unfect";
	public final static String RandomAnvilBreak = "random.anvil_break";
	public final static String RandomAnvilLand = "random.anvil_land";
	public final static String RandomAnvilUse = "random.anvil_use";

	public static void playSound(String soundName, Location loc, float volume, float pitch) {

		Object packet = NMSReflection.getObject("PacketPlayOutNamedSoundEffect");
		NMSReflection.setField(packet, "a", soundName);
		NMSReflection.setField(packet, "b", (int) (loc.getX() * 8.0D));
		NMSReflection.setField(packet, "c", (int) (loc.getY() * 8.0D));
		NMSReflection.setField(packet, "d", (int) (loc.getZ() * 8.0D));
		NMSReflection.setField(packet, "e", volume);
		NMSReflection.setField(packet, "f", (int) (pitch * 63.0F));

		if (Bukkit.getOnlinePlayers() != null) {
			for (Player player : Bukkit.getOnlinePlayers()) {
				if (player != null) {
					NMSReflection.sendPacket(player, packet);
				}
			}
		}
	}

	public static void playSound(Player player, String soundName, Location loc, float volume, float pitch) {

		Object packet = NMSReflection.getObject("PacketPlayOutNamedSoundEffect");
		NMSReflection.setField(packet, "a", soundName);
		NMSReflection.setField(packet, "b", (int) (loc.getX() * 8.0D));
		NMSReflection.setField(packet, "c", (int) (loc.getY() * 8.0D));
		NMSReflection.setField(packet, "d", (int) (loc.getZ() * 8.0D));
		NMSReflection.setField(packet, "e", volume);
		NMSReflection.setField(packet, "f", (int) (pitch * 63.0F));

		if (player != null) {
			NMSReflection.sendPacket(player, packet);
		}

	}
}
