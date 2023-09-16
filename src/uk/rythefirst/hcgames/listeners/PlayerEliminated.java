package uk.rythefirst.hcgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerEliminatedEvent;
import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.objects.HCTeam;

public class PlayerEliminated implements Listener {

	@EventHandler
	public void onEliminated(PlayerEliminatedEvent e) {
		Bukkit.broadcastMessage("" + ChatColor.DARK_RED + ChatColor.BOLD + "Player Eliminated!");
		Bukkit.broadcastMessage("");
		if (e.getKiller() == null) {
			Bukkit.broadcastMessage(ChatColor.DARK_RED + e.getMcPlayer().getName() + " Died of natural causes!");
		} else {
			Bukkit.broadcastMessage(ChatColor.GOLD + e.getKiller().getName() + " dominated " + ChatColor.DARK_RED
					+ e.getMcPlayer().getName());
		}
		Bukkit.broadcastMessage(ChatColor.GOLD + Main.game.getPlayersRemaining().toString() + " players remain!");

		HCPlayer hcPlayer = e.getPlayer();
		hcPlayer.setLiving(LifeStatus.DEAD);
		HCTeam pTeam = Main.tm.getTeam(hcPlayer.getTeam());
		pTeam.PlayerDied();
		Main.game.setPlayersRemaining(Main.game.getPlayersRemaining() - 1);
		Main.playerCache.addElimination(e.getMcPlayer());
		((Player) e.getMcPlayer())
				.kickPlayer(ChatColor.DARK_RED + "You were Eliminated! Watch Now @ " + Main.settings.getStreamLink());
	}

}
