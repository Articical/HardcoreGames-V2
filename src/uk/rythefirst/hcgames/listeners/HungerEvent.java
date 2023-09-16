package uk.rythefirst.hcgames.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.PlayerType;

public class HungerEvent implements Listener {

	@EventHandler
	public void onChange(FoodLevelChangeEvent e) {

		if (!(e.getEntity() instanceof Player)) {
			return;
		}

		Player p = (Player) e.getEntity();

		if (Main.settings.getState() == GameState.STARTING || Main.settings.getState() == GameState.FINISHED
				|| Main.settings.getState() == GameState.LOBBY
				|| Main.pm.getPlayer(p.getUniqueId().toString()).getType() == PlayerType.SPECTATOR) {
			e.setCancelled(true);
		}
	}

}
