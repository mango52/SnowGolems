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
		if (((plugin.permission.has(sender, "snowgolems.use")) || (sender.isOp())) && (sender instanceof Player)) {
			if (commandName.equalsIgnoreCase("sg")) {
				if (!plugin.snowPlayers.contains((Player) sender)) {
					plugin.snowPlayers.add((Player) sender);
					sender.sendMessage(ChatColor.GREEN + "You have enabled snow golem mode!");
					return true;
				} else {
					if (plugin.snowPlayers.contains((Player) sender)) {
						plugin.snowPlayers.remove((Player) sender);
						sender.sendMessage(ChatColor.DARK_RED + "You have disabled snow golem mode.");
						return true;
					} else {
						sender.sendMessage("You already have snow golem mode!");
					}
				}
				if (commandName.equalsIgnoreCase("sgdisable")) {
					if (plugin.snowPlayers.contains((Player) sender)) {
						plugin.snowPlayers.remove((Player) sender);
						sender.sendMessage(ChatColor.DARK_RED + "You have disabled snow golem mode.");
						return true;
					} else {
						sender.sendMessage("You already have snow golem mode disabled!");
					}
					if (commandName.equalsIgnoreCase("sghelp")) {
						sender.sendMessage(ChatColor.GOLD + "SnowGolems Help:");
						sender.sendMessage(ChatColor.GREEN + "/sg - Toggles snow golem mode. (Requires OP or permission)");
						sender.sendMessage(ChatColor.GREEN + "/sghelp - Displays this help message.");
						return true;
					}
				}
			} else {
				sender.sendMessage(ChatColor.RED + "You don't have permission to do that!");
				return true;
			}
			return false;
		}
		return false;
	}
}
