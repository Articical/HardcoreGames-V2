package uk.rythefirst.hcgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.PlayerType;

public class PlayerDamagePlayer implements Listener {

	@EventHandler
	public void onDamage(EntityDamageByEntityEvent e) {
		if (!(e.getEntity() instanceof Player && e.getDamager() instanceof Player)) {
			return;
		}

		Player victim = (Player) e.getEntity();
		Player culprit = (Player) e.getDamager();

		if (Main.settings.getPvp() == false) {
			e.setCancelled(true);
		}

		if (!(Main.settings.getState() == GameState.PLAYING)) {
			e.setCancelled(true);
			return;
		}

		if (Main.pm.getPlayer(victim.getUniqueId().toString()).inTeam()
				&& Main.pm.getPlayer(culprit.getUniqueId().toString()).inTeam()
				&& Main.pm.getPlayer(victim.getUniqueId().toString()).getTeam() == Main.pm
						.getPlayer(culprit.getUniqueId().toString()).getTeam()) {
			e.setCancelled(true);
		}

		if (Main.pm.getPlayer(culprit.getUniqueId().toString()).getType() == PlayerType.SPECTATOR) {
			e.setCancelled(true);
		}

	}

}
