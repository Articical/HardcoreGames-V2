package uk.rythefirst.hcgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerPortalEvent;

public class PlayerPortal implements Listener {

	@EventHandler
	public void onPortal(PlayerPortalEvent e) {
		e.setCancelled(true);
	}

}
