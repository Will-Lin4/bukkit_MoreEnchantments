package me.Xeroun.MoreEnchantments;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Commands implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		if (label.equalsIgnoreCase("enchantplus")) {
			if (sender instanceof Player) {
				Player player = (Player) sender;
				if (args.length == 2) {
					if (args[0] != null && args[1] != null) {
						if (player.getInventory().getItemInHand() != null) {
							if (MoreEnchantments.getInstance().getHandler().getCustomEnchantment(args[0]) != null) {
								try {
									int level = Integer.parseInt(args[1]);
									if(level == 0){
										player.getInventory().getItemInHand().removeEnchantment(MoreEnchantments.getInstance().getHandler().getCustomEnchantment(args[0]));
										MoreEnchantments.getInstance().getHandler().updateLore(player.getInventory().getItemInHand());
										player.sendMessage(ChatColor.AQUA + "Removed enchantment " + ChatColor.GOLD + args[0].toUpperCase() + ChatColor.AQUA + " from " + ChatColor.GOLD + player.getInventory().getItemInHand().getType().toString());
									}else{
										MoreEnchantments.getInstance().getHandler().addCustomEnchantment(player.getInventory().getItemInHand(), MoreEnchantments.getInstance().getHandler().getCustomEnchantment(args[0]), level);
										player.sendMessage(ChatColor.AQUA + "Added enchantment " + ChatColor.GOLD + args[0].toUpperCase() + ChatColor.AQUA + " to " + ChatColor.GOLD + player.getInventory().getItemInHand().getType().toString());
									}
								} catch (NumberFormatException e) {
									player.sendMessage(ChatColor.RED + "Correct Format: " + ChatColor.GOLD + "/enchantplus (Enchant) (Level)");
								} catch(NullPointerException e){
									player.sendMessage(ChatColor.RED + "You dont have any items in your hand!");
								}
							} else {
								sender.sendMessage(ChatColor.RED + "Invalid Enchantment: " + ChatColor.GOLD + args[0]);
							}
						} else {
							player.sendMessage(ChatColor.RED + "You dont have any items in your hand!");
						}
					} else {
						player.sendMessage(ChatColor.RED + "Correct Format: " + ChatColor.GOLD + "/enchantplus (Enchant) (Level)");
					}
				} else {
					player.sendMessage(ChatColor.RED + "Correct Format: " + ChatColor.GOLD + "/enchantplus (Enchant) (Level)");

				}
			}else{
				sender.sendMessage(ChatColor.RED + "Only players may use this command.");

			}
		}

		return true;
	}

}
