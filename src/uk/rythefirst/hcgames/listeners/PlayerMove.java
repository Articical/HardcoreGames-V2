package uk.rythefirst.hcgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.managers.PlayerManager;

public class PlayerMove implements Listener {

	@EventHandler
	public void onPlayerMove(PlayerMoveEvent e) {
		PlayerManager pm = Main.pm;
		if (pm.isRegistered(e.getPlayer().getUniqueId().toString())) {
			if (pm.getPlayer(e.getPlayer().getUniqueId().toString()).getType() == PlayerType.PLAYER
					&& Main.settings.getState() == GameState.STARTING) {
				e.setCancelled(true);
			}
		}
	}

}
