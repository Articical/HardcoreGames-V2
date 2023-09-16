package uk.rythefirst.hcgames;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import uk.rythefirst.hcgames.enums.GameState;

public class TabList {

	public void custom() {
		new BukkitRunnable() {

			@Override
			public void run() {
				String spacer = "§6  ------  ";
				String header;
				if (Main.settings.getState() == GameState.LOBBY) {
					header = (spacer + "§5NihachuMC §6Hardcore Event" + spacer + "\n§6Current Status: Lobby");
				} else if (Main.settings.getState() == GameState.STARTING) {
					header = (spacer + "§5NihachuMC §6Hardcore Event" + spacer
							+ "\n§6Current Status: Starting\nBorder Size: ");
				} else if (Main.settings.getState() == GameState.PLAYING) {
					header = (spacer + "§5NihachuMC §6Hardcore Event" + spacer
							+ "\n§6Current Status: Playing\nPlayers Alive: " + Main.game.getPlayersRemaining()
							+ "\nTeams Remaining: " + Main.game.getTeamsRemaining() + "\n"
							+ +Bukkit.getWorld("world").getWorldBorder().getSize() / 2);
				} else if (Main.settings.getState() == GameState.FINISHED) {
					header = (spacer + "§5NihachuMC §6Hardcore Event" + spacer
							+ "\n§6Current Status: Finished\nWinning Team: "
							+ Main.tm.getTeam(Main.game.getWinningTeam()).getName());
				} else {
					header = (spacer + "§5NihachuMC §6Hardcore Event" + spacer);
				}
				String footer = ("§6Developed by: §4RyTheFirst\n" + spacer + "§6www.rythefirst.uk" + spacer);

				if (Bukkit.getOnlinePlayers().size() == 0) {
					return;
				}
				for (Player player : Bukkit.getOnlinePlayers()) {
					player.setPlayerListHeaderFooter(header, footer);
				}
			}
		}.runTaskTimer(Main.instance, 0, 10);
	}
}
