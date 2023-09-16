package uk.rythefirst.hcgames.guis;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.guis.items.SetupItems;

public class setup {

	SetupItems items = new SetupItems();

	public Inventory buildGUI(Player p) {
		Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_GREEN + "Game Master Menu");

		items = new SetupItems();
		List<String> lore = new ArrayList<String>();
		if (Main.ChestCache.isCreating(p)) {
			lore.add(ChatColor.GREEN + "You're currently selecting.");
		} else {
			lore.add(ChatColor.DARK_RED + "You're not selecting at the moment.");
		}
		inv.addItem(items.chestSelectorItem(lore));

		return inv;
	}

}
