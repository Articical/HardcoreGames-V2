package uk.rythefirst.hcgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.PlayerType;

public class EntityTarget implements Listener {

	@EventHandler
	public void onTarget(EntityTargetLivingEntityEvent e) {
		if (!(e.getTarget() instanceof Player)) {
			return;
		}

		Player player = (Player) e.getTarget();

		if (player.hasPermission("hc.hidden")
				|| Main.pm.getPlayer(player.getUniqueId().toString()).getType() == PlayerType.SPECTATOR) {
			e.setCancelled(true);
		}

	}

}
