package me.Xeroun.MoreEnchantments;

import me.Xeroun.MoreEnchantments.Enchantments.Armor.ToughnessEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Bow.BullseyeEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Bow.ConflagrationEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Bow.PhantomEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Bow.VenomEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Bow.VolleyEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Weapon.ExecutionEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Weapon.GrievousWoundsEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Weapon.LifestealEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Weapon.PoisonAspectEnchantment;
import me.Xeroun.MoreEnchantments.Enchantments.Weapon.ShreddingEnchantment;

import org.bukkit.plugin.java.JavaPlugin;

public class MoreEnchantments extends JavaPlugin {

	private static MoreEnchantments instance;
	public static MoreEnchantments getInstance(){
		return instance;
	}
	
	private Events events = new Events();
	private Commands commands = new Commands();
	
	// Completed Enchantments
	private ExecutionEnchantment execution = new ExecutionEnchantment(100);
	private LifestealEnchantment lifesteal = new LifestealEnchantment(101);
	private PoisonAspectEnchantment poisonAspect = new PoisonAspectEnchantment(102);
	private BullseyeEnchantment bullseye = new BullseyeEnchantment(103);
	private ShreddingEnchantment shredding = new ShreddingEnchantment(104);
	private ToughnessEnchantment toughness = new ToughnessEnchantment(105);
	private ConflagrationEnchantment conflagration = new ConflagrationEnchantment(106);
	private PhantomEnchantment phantom = new PhantomEnchantment(107);
	private VenomEnchantment venom = new VenomEnchantment(108);
	private GrievousWoundsEnchantment grievousWounds = new GrievousWoundsEnchantment(109);
	private VolleyEnchantment volley = new VolleyEnchantment(110);
	
	private EnchantmentHandler handler;
	
	public void onEnable() {
		MoreEnchantments.instance = this;
		getLogger().info(this + " has been enabled.");
		getServer().getPluginManager().registerEvents(events, this);
		getCommand("enchantplus").setExecutor(commands);
		
		handler = new EnchantmentHandler();
		handler.init();
		handler.register(execution);
		handler.register(lifesteal);
		handler.register(poisonAspect);
		handler.register(bullseye);
		handler.register(shredding);
		handler.register(toughness);
		handler.register(conflagration);
		handler.register(phantom);
		handler.register(venom);
		handler.register(grievousWounds);
		handler.register(volley);
	}

	public void onDisable() {
		getLogger().info(this + " has been disabled.");
		
	}

	public EnchantmentHandler getHandler(){
		return handler;
	}

}
