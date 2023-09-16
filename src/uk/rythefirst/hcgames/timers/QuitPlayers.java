package uk.rythefirst.hcgames.timers;

import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerEliminatedEvent;
import uk.rythefirst.hcgames.objects.HCPlayer;

public class QuitPlayers {

	BukkitTask quitTask;

	public void start() {
		quitTask = new BukkitRunnable() {

			@Override
			public void run() {
				TreeMap<UUID, Location> map;
				map = Main.playerCache.quitPlayers;
				if (map.size() == 0) {
					stopTimer();
				}
				for (Entry<UUID, Location> entry : map.entrySet()) {
					World world = Bukkit.getWorld("world");
					if (!(world.getWorldBorder().isInside(entry.getValue()))) {
						HCPlayer hcplayer = Main.pm.getPlayer(entry.getKey().toString());
						PlayerEliminatedEvent event = new PlayerEliminatedEvent(hcplayer,
								Bukkit.getOfflinePlayer(entry.getKey()));
						Bukkit.getServer().getPluginManager().callEvent(event);
					}
				}
			}
		}.runTaskTimer(Main.instance, 0, 100);
	}

	public void stopTimer() {
		if (!(quitTask == null)) {
			quitTask.cancel();
			quitTask = null;
		}
	}

	public boolean timerRunning() {
		return quitTask == null;
	}

}
