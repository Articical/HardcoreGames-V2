package uk.rythefirst.hcgames.timers;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;

public class StartCountdown {

	BukkitTask timer;

	public void Broadcast(Boolean testing) {

		timer = new BukkitRunnable() {

			Integer counterMAX = Main.settings.getStartingCountdownTime();
			Integer countercrnt = 0;

			@Override
			public void run() {
				if (!(countercrnt == counterMAX)) {
					int remainingTime = counterMAX - countercrnt;
					countercrnt++;
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_CHIME, 4, 1);
						player.sendTitle(ChatColor.DARK_PURPLE + "Game Starting in...",
								"" + ChatColor.GOLD + remainingTime + " Seconds", 0, 21, 0);
					}
					Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + "Game Starting in:" + " " + ChatColor.GOLD
							+ remainingTime + " Seconds");
				} else {
					for (Player player : Bukkit.getOnlinePlayers()) {
						player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_PLING, 4, 1);
						player.sendTitle(ChatColor.DARK_PURPLE + "BEGIN!", ChatColor.GOLD + "May the best team win.", 0,
								40, 0);
					}
					Bukkit.broadcastMessage("" + ChatColor.DARK_PURPLE + "Game Starts" + " " + ChatColor.GOLD
							+ "May the best team win.");

					Main.settings.setMobSpawn(true);
					if (!testing) {
						Main.bTimer.runTimer();
						Main.settings.setState(GameState.PLAYING);
					}

					new BukkitRunnable() {

						@Override
						public void run() {

							for (Player player : Bukkit.getOnlinePlayers()) {
								player.playSound(player.getLocation(), Sound.UI_TOAST_IN, 4, 1);
								player.sendTitle(ChatColor.GOLD + "Grace Period",
										ChatColor.GOLD
												+ "Border is at 1,500 blocks, you have 25 minutes of peace time.",
										20, 40, 20);
							}

						}
					}.runTaskLater(Main.instance, 60);

					Stop();
				}
			}
		}.runTaskTimer(Main.instance, 0, 20);
	}

	public void Stop() {
		if (timer != null) {
			timer.cancel();
			timer = null;
		}
	}

}
