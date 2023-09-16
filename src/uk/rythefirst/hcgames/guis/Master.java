package uk.rythefirst.hcgames.guis;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.guis.items.MasterItems;

public class Master {

	MasterItems items;

	public Inventory buildGUI() {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Game Master Menu");

		items = new MasterItems();
		inv.addItem(items.mobSpawnItem());
		inv.addItem(items.chatStateItem());
		inv.addItem(items.gameStateItem());
		inv.addItem(items.startTimerItem());
		inv.addItem(items.populateChestsItem());
		inv.addItem(items.clearChestsItem());
		inv.addItem(items.startGameItem());
		inv.addItem(items.hidePlayersItem());

		return inv;
	}

}
