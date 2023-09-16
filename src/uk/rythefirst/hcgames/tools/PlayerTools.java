package uk.rythefirst.hcgames.tools;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.PlayerType;

public class PlayerTools {

	public static void hidePlayers() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				if (player2 == player) {
					Bukkit.getLogger().log(Level.INFO, "Could not hide " + player.getName() + " from themself!");
				}
				if (player.hasPermission("hc.stream") && !(player2.hasPermission("hc.stream"))) {
					player2.hidePlayer(Main.instance, player);
					Bukkit.getLogger().log(Level.INFO,
							"Player " + player.getName() + " hidden from: " + player2.getName());
				}
				if (player.hasPermission("hc.hidden") && !(player2.hasPermission("hc.hidden"))) {
					player2.hidePlayer(Main.instance, player);
					Bukkit.getLogger().log(Level.INFO,
							"Player " + player.getName() + " hidden from: " + player2.getName());
				}
			}
		}
		Main.settings.setPlayersHidden(true);
	}

	public static void showPlayers() {

		for (Player player : Bukkit.getOnlinePlayers()) {
			for (Player player2 : Bukkit.getOnlinePlayers()) {
				if (player2 == player) {
				}
				if (player2.hasPermission("hc.stream")) {
					player.showPlayer(Main.instance, player2);
				}
				if (player.hasPermission("hc.hidden")
						|| Main.pm.getPlayer(player.getUniqueId().toString()).getType() == PlayerType.SPECTATOR) {
					if (!player2.hasPermission("hc.hidden") && !(Main.pm.getPlayer(player2.getUniqueId().toString())
							.getType() == PlayerType.SPECTATOR)) {
						player.showPlayer(Main.instance, player2);
					}

				}
			}
		}
		Main.settings.setPlayersHidden(false);
	}

	public static void hideFromPlayer(Player player) {
		for (Player player2 : Bukkit.getOnlinePlayers()) {
			if (player2 == player) {
				Bukkit.getLogger().log(Level.INFO, "Could not hide " + player.getName() + " from themself!");
			}
			if (player.hasPermission("hc.stream") && !(player2.hasPermission("hc.stream"))) {
				player2.hidePlayer(Main.instance, player);
				Bukkit.getLogger().log(Level.INFO, "Player " + player.getName() + " hidden from: " + player2.getName());
			}
			if (player.hasPermission("hc.hidden") && !(player2.hasPermission("hc.hidden"))) {
				player2.hidePlayer(Main.instance, player);
				Bukkit.getLogger().log(Level.INFO, "Player " + player.getName() + " hidden from: " + player2.getName());
			}
		}
	}

}
