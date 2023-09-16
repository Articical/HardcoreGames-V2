package uk.rythefirst.hcgames.tools;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;

public class Mobs {

	public static void Remove() {
		for (World world : Bukkit.getWorlds()) {
			for (Entity e : world.getEntities()) {
				if (!(e instanceof Item) && !(e instanceof Player)) {
					e.remove();
				}
			}
		}
	}

}
