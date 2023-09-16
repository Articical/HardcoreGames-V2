package uk.rythefirst.hcgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.PlayerType;

public class PlayerDamage implements Listener {

	@EventHandler
	public void onDamage(EntityDamageEvent e) {
		if (!(e.getEntity() instanceof Player)) {
			return;
		}

		Player p = (Player) e.getEntity();

		if (Main.settings.getState() == GameState.LOBBY || Main.settings.getState() == GameState.STARTING
				|| Main.pm.getPlayer(p.getUniqueId().toString()).getType() == PlayerType.SPECTATOR) {
			e.setCancelled(true);
		}

	}

}
