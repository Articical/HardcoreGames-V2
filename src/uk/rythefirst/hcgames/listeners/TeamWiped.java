package uk.rythefirst.hcgames.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.TeamWipeEvent;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.objects.HCTeam;

public class TeamWiped implements Listener {

	@EventHandler
	public void onWipe(TeamWipeEvent e) {
		Main.game.setTeamsRemaining(Main.game.getTeamsRemaining() - 1);
		Main.game.teamsRemainingLst.remove(e.getTeam().getUUID());
		if (Main.game.getTeamsRemaining() == 1) {
			Main.game.setWinningTeam(Main.game.teamsRemainingLst.get(0));
			HCTeam team = Main.tm.getTeam(Main.game.getWinningTeam());
			for (Player player : Bukkit.getOnlinePlayers()) {
				HCPlayer hcplayer = Main.pm.getPlayer(player.getUniqueId().toString());
				if (hcplayer.getType() == PlayerType.PLAYER) {
					player.sendTitle(ChatColor.GREEN + "Game Over!", ChatColor.GOLD + "Congratulations! You won!", 20,
							200, 20);
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
			Bukkit.broadcastMessage(ChatColor.GOLD + "Members: " + builder.toString());
		}
	}

}
