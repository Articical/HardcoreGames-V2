package uk.rythefirst.hcgames.timers;

import java.util.Map.Entry;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerEliminatedEvent;
import uk.rythefirst.hcgames.objects.HCPlayer;

public class GameTimer {

	BukkitTask timer;

	public boolean gracePeriod = true;

	public void runTimer() {
		timer = new BukkitRunnable() {

			Integer elapsed = 0;

			@Override
			public void run() {
				if (elapsed == 1500) {
					Bukkit.getWorld("world").getWorldBorder().setSize(50, 5700);
					gracePeriod = false;
					Main.settings.setPvp(true);
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.sendTitle(ChatColor.DARK_PURPLE + "Border Closing",
								ChatColor.GOLD + "The border has begun closing. Pvp is now enabled!", 15, 40, 15);
					}
				}
				if (elapsed == 7500) {
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.sendTitle(ChatColor.DARK_PURPLE + "Border Shrinking",
								ChatColor.GOLD + "The border has begun shrinking to a 5 block radius!", 15, 40, 15);
					}
					Bukkit.getWorld("world").getWorldBorder().setSize(5, 300);
					for (Entry<UUID, Location> entry : Main.playerCache.quitPlayers.entrySet()) {
						HCPlayer hcplayer = Main.pm.getPlayer(entry.getKey().toString());
						PlayerEliminatedEvent event = new PlayerEliminatedEvent(hcplayer,
								Bukkit.getOfflinePlayer(entry.getKey()));
						Bukkit.getServer().getPluginManager().callEvent(event);
						if (Main.quitTimer.timerRunning()) {
							Main.quitTimer.stopTimer();
						}
					}
				}
				elapsed++;
			}
		}.runTaskTimer(Main.instance, 0, 20);
	}

	public void StopTimer() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

}
