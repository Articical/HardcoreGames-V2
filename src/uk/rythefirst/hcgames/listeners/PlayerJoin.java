package uk.rythefirst.hcgames.listeners;

import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.objects.HCTeam;
import uk.rythefirst.hcgames.tools.PlayerTools;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {

		e.setJoinMessage(null);
		e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED)
				.setBaseValue(e.getPlayer().getAttribute(Attribute.GENERIC_ATTACK_SPEED).getDefaultValue());

		if (Main.playerCache.quitPlayers.containsKey(e.getPlayer().getUniqueId())) {
			Main.playerCache.quitPlayers.remove(e.getPlayer().getUniqueId());
		}

		if (!(Main.pm.isRegistered(e.getPlayer().getUniqueId().toString()))) {
			Main.pm.registerPlayer(e.getPlayer());
			// Main.game.setPlayersRemaining(Main.game.getPlayersRemaining() + 1);
		}
		HCPlayer player = Main.pm.getPlayer(e.getPlayer().getUniqueId().toString());
		if (player.getType() == PlayerType.PLAYER) {
			HCTeam team = Main.tm.getTeam(player.getTeam());
			e.getPlayer().setPlayerListName(
					team.getTeamColor() + team.getName() + ChatColor.RESET + " " + e.getPlayer().getName());

		} else if (player.getType() == PlayerType.SPECTATOR) {
			e.getPlayer().setPlayerListName(ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "S" + ChatColor.GOLD + "] "
					+ ChatColor.RESET + e.getPlayer().getName());
		}
		PlayerTools.hideFromPlayer(e.getPlayer());
	}

}
