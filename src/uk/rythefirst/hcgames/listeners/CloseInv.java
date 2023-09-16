package uk.rythefirst.hcgames.listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryCloseEvent;

import uk.rythefirst.hcgames.cache.GUI;

public class CloseInv implements Listener {

	@EventHandler
	public void onInvClose(InventoryCloseEvent e) {
		if (GUI.invs.contains(e.getInventory())) {
			GUI.invs.remove(e.getInventory());
		}
	}

}
