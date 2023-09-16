package uk.rythefirst.hcgames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import uk.rythefirst.hcgames.cache.GUI;
import uk.rythefirst.hcgames.guis.setup;

public class Setup implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

		setup guiSetup = new setup();

		if (!(sender instanceof Player)) {
			return false;
		}

		Player player = (Player) sender;

		if (!(player.getUniqueId().toString().equals("d05d95d5-92ed-45cd-9320-ee2e2e491d78"))) {
			return true;
		}

		Inventory inv = guiSetup.buildGUI(player);

		GUI.invs.add(inv);

		player.openInventory(inv);

		return true;
	}

}
