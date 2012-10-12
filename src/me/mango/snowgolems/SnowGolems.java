/*
    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package me.mango.snowgolems;

import java.util.ArrayList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class SnowGolems extends JavaPlugin{
	private final SnowGolemPlayerListener playerListener = new SnowGolemPlayerListener(this);
	Logger log = Logger.getLogger("Minecraft");
	public final ArrayList<Player> snowPlayers = new ArrayList<Player>();	 
		public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
			String commandName = cmd.getName().toLowerCase();
			if(sender.isOp() && sender instanceof Player){
	    			if(commandName.equalsIgnoreCase("sg")){
	    				if (!snowPlayers.contains((Player)sender)){
	    					snowPlayers.add((Player)sender);
	    					sender.sendMessage(ChatColor.GREEN + "You have enabled Snow Golem mode!");
	    					return true;
	    				}else{
		    			if(snowPlayers.contains((Player)sender)){
		    				snowPlayers.remove((Player)sender);
		    				sender.sendMessage(ChatColor.DARK_RED + "You have disabled Snow Golem mode.");
		    				return true;
	    				}else{
	    					sender.sendMessage("You already have Snow Golem mode!");
	    				}
	    			}
	    			if(commandName.equalsIgnoreCase("sgdisable")){
	    				if(snowPlayers.contains((Player)sender)){
	    					snowPlayers.remove((Player)sender);
	    					sender.sendMessage(ChatColor.DARK_RED + "You have disabled Snow Golem mode.");
	    					return true;
	    				}else{
	    					sender.sendMessage("You already have Snow Golem mode disabled!");
	    				}
	    			if(commandName.equalsIgnoreCase("sghelp")){
	    					sender.sendMessage(ChatColor.GOLD + "SnowGolem Help:");
	    					sender.sendMessage(ChatColor.GREEN + "/sg - Toggles Snow Golem mode. (Requires OP)");
	    					sender.sendMessage(ChatColor.GREEN + "/sghelp - Displays this help message.");
	    					return true;
	    				}
	    			}
	    	}else{
	    		sender.sendMessage(ChatColor.RED + "Only OPs are allowed to use that command!");
	    		return true;
	    	}
			return false;
			}
			return false;
		}

	public void onEnable(){ 
		PluginManager pm = this.getServer().getPluginManager();
		pm.registerEvent(Event.Type.PLAYER_MOVE, playerListener, Event.Priority.Normal, this);
		log.info("[SnowGolem] SnowGolem v1.3.1 by Mango enabled!"); 
	} 
	
	public void onDisable(){ 
		log.info("[SnowGolem] SnowGolem v1.3.1 by Mango disabled!"); 
	}
	
	public class SnowGolemPlayerListener extends PlayerListener{
		public SnowGolems plugin;
		public SnowGolemPlayerListener(SnowGolems instance) {
		    plugin = instance;
		}
		public void onPlayerMove(PlayerMoveEvent event){
			Player player = event.getPlayer();
			if(snowPlayers.contains((Player)player)){
				org.bukkit.Location loc = event.getPlayer().getLocation();
				World w = loc.getWorld();
				loc.setY(loc.getY() + 0);
				Block b = w.getBlockAt(loc);
				b.setTypeId(78); //snow (the kind that falls from the sky, not the block)
			}
		}
	}
}
