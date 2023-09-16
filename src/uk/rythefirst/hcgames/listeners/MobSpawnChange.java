package uk.rythefirst.hcgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import uk.rythefirst.hcgames.customevents.MobSpawningChangeEvent;
import uk.rythefirst.hcgames.tools.Mobs;

public class MobSpawnChange implements Listener {

	@SuppressWarnings("deprecation")
	@EventHandler
	public void MobSC(MobSpawningChangeEvent e) {

		for (World world : Bukkit.getServer().getWorlds()) {
			world.setGameRuleValue("doMobSpawning", e.getNewValue().toString());
		}

		if (e.getNewValue() == true) {
			return;
		}
		Mobs.Remove();
	}

}
