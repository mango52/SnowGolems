package me.mango.snowgolems;

import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SGListener implements Listener {

	private SnowGolems plugin;

	public SGListener(SnowGolems plugin) {
		plugin.getServer().getPluginManager().registerEvents(this, plugin);
		this.plugin = plugin;
	}
	
	@EventHandler(priority = EventPriority.MONITOR, ignoreCancelled = true)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player player = event.getPlayer();
		if (plugin.snowPlayers.contains((Player) player)) {
			org.bukkit.Location loc = event.getPlayer().getLocation();
			World w = loc.getWorld();
			loc.setY(loc.getY() + 0);
			Block b = w.getBlockAt(loc);
			b.setTypeId(78); // snow = 78
		}
	}
}
