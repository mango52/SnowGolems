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

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class SnowGolems extends JavaPlugin {

	public final ArrayList<Player> snowPlayers = new ArrayList<Player>();
	
	@Override
	public void onEnable() {
		new SGListener(this);
		getCommand("sg").setExecutor(new SGCommandExecutor(this));
		getCommand("sghelp").setExecutor(new SGCommandExecutor(this));
		Bukkit.getLogger().info(getDescription().getName() + " " + getDescription().getVersion() + " by Mango enabled.");
	}

	@Override
	public void onDisable() {
		Bukkit.getLogger().info(getDescription().getName() + " " + getDescription().getVersion() + " by Mango disabled.");
	}
}
