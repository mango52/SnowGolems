package me.mango.snowgolems;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SGCommandExecutor implements CommandExecutor {
	
	private SnowGolems plugin;
	
	public SGCommandExecutor(SnowGolems plugin) {
		this.plugin = plugin;
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		String commandName = cmd.getName().toLowerCase();
		if (sender.isOp() && (sender instanceof Player)) {
			if (commandName.equalsIgnoreCase("sg")) {
				if (!plugin.snowPlayers.contains((Player) sender)) {
					plugin.snowPlayers.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "You have enabled Snow Golem mode!");
					return true;
				} else {
					if (plugin.snowPlayers.contains((Player) sender)) {
						plugin.snowPlayers.remove((Player) sender);
						sender.sendMessage(ChatColor.DARK_RED + "You have disabled Snow Golem mode.");
						return true;
					} else {
						sender.sendMessage("You already have Snow Golem mode!");
					}
				}
				if (commandName.equalsIgnoreCase("sgdisable")) {
					if (plugin.snowPlayers.contains((Player) sender)) {
						plugin.snowPlayers.remove((Player) sender);
						sender.sendMessage(ChatColor.DARK_RED + "You have disabled Snow Golem mode.");
						return true;
					} else {
						sender.sendMessage("You already have Snow Golem mode disabled!");
					}
					if (commandName.equalsIgnoreCase("sghelp")) {
						sender.sendMessage(ChatColor.GOLD + "SnowGolem Help:");
						sender.sendMessage(ChatColor.GREEN + "/sg - Toggles Snow Golem mode. (Requires OP)");
						sender.sendMessage(ChatColor.GREEN + "/sghelp - Displays this help message.");
						return true;
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "Only OPs are allowed to use that command!");
				return true;
			}
			return false;
		}
		return false;
	}
}
