package uk.rythefirst.hcgames.listeners;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerEliminatedEvent;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.objects.HCTeam;

public class PlayerDeath implements Listener {

	@EventHandler
	public void onPlayerDeath(PlayerDeathEvent e) {
		e.setDeathMessage(null);
		if (!(Main.settings.getState() == GameState.PLAYING)) {
			return;
		}

		HCPlayer hcPlayer = Main.pm.getPlayer(e.getEntity().getUniqueId().toString());
		if (hcPlayer.getType() == PlayerType.PLAYER && hcPlayer.getLiving() == LifeStatus.ALIVE) {
			e.setDeathMessage(null);
			if (e.getEntity().getKiller() != null) {
				PlayerEliminatedEvent event = new PlayerEliminatedEvent(hcPlayer, e.getEntity(),
						e.getEntity().getKiller());
				Bukkit.getServer().getPluginManager().callEvent(event);
			} else {
				PlayerEliminatedEvent event = new PlayerEliminatedEvent(hcPlayer, e.getEntity());
				Bukkit.getServer().getPluginManager().callEvent(event);
			}

			if (Main.game.getPlayersRemaining() <= 3) {
				List<UUID> teamslst = new ArrayList<UUID>();
				for (Player player : Bukkit.getOnlinePlayers()) {
					HCPlayer p = Main.pm.getPlayer(player.getUniqueId().toString());
					if (p.getType() == PlayerType.PLAYER) {
						if (!(teamslst.contains(p.getTeam()))) {
							teamslst.add(p.getTeam());
						}
					}
				}
				if (teamslst.size() == 1) {
					Main.game.setWinningTeam(teamslst.get(0));
					HCTeam team = Main.tm.getTeam(Main.game.getWinningTeam());
					Main.game.setTeamsRemaining(1);
					Main.settings.setState(GameState.FINISHED);
					for (Player player : Bukkit.getOnlinePlayers()) {
						HCPlayer hcplayer = Main.pm.getPlayer(player.getUniqueId().toString());
						if (hcplayer.getType() == PlayerType.PLAYER) {
							player.sendTitle(ChatColor.GREEN + "Game Over!",
									ChatColor.GOLD + "Congratulations! You won!", 20, 200, 20);
						} else {
							player.sendTitle(ChatColor.GREEN + "Game Over!",
									ChatColor.GOLD + "Team " + team.getName() + " Won!", 20, 200, 20);
						}
					}
					Bukkit.broadcastMessage(ChatColor.GOLD + "Game Over!");
					Bukkit.broadcastMessage(ChatColor.GOLD + "Winning Team: " + ChatColor.DARK_PURPLE + team.getName());
					StringBuilder builder = new StringBuilder();
					for (String string : team.getMembers()) {
						OfflinePlayer player = Bukkit.getOfflinePlayer(UUID.fromString(string));
						builder.append(player.getName() + " ");
					}
					String name1 = Bukkit.getOfflinePlayer(UUID.fromString(team.getMembers().get(0))).getName();
					String name2 = Bukkit.getOfflinePlayer(UUID.fromString(team.getMembers().get(1))).getName();
					String name3 = Bukkit.getOfflinePlayer(UUID.fromString(team.getMembers().get(0))).getName();
					Bukkit.broadcastMessage(ChatColor.GOLD + "Members: " + name1 + ", " + name2 + ", " + name3);
				}
			}

			return;
		}

		if (hcPlayer.getType() == PlayerType.SPECTATOR || e.getEntity().hasPermission("hc.hidden")) {
			e.setDeathMessage(null);
			return;
		}

	}

}
