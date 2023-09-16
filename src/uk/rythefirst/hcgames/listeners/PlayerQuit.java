package uk.rythefirst.hcgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerEliminatedEvent;
import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;

public class PlayerQuit implements Listener {

	@EventHandler
	public void onQuit(PlayerQuitEvent e) {
		e.setQuitMessage(null);
		if (Main.game.lastZone) {
			HCPlayer hcplayer = Main.pm.getPlayer(e.getPlayer().getUniqueId().toString());
			PlayerEliminatedEvent event = new PlayerEliminatedEvent(hcplayer, e.getPlayer());
			Bukkit.getServer().getPluginManager().callEvent(event);
		}
		if (!(Main.pm.getPlayer(e.getPlayer().getUniqueId().toString()).getLiving() == LifeStatus.DEAD)
				&& Main.pm.getPlayer(e.getPlayer().getUniqueId().toString()).getType() == PlayerType.PLAYER) {
			Main.playerCache.quitPlayers.put(e.getPlayer().getUniqueId(), e.getPlayer().getLocation());
			if (!(Main.quitTimer.timerRunning())) {
				Main.quitTimer.start();
			}
		}
	}

}
