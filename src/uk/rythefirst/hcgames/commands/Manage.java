package uk.rythefirst.hcgames.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import uk.rythefirst.hcgames.cache.GUI;
import uk.rythefirst.hcgames.guis.Master;

public class Manage implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Master ManagerInv = new Master();

		Player player = (Player) sender;

		// player.sendMessage(player.getUniqueId().toString());

		if (player.getUniqueId().toString().equals("d05d95d5-92ed-45cd-9320-ee2e2e491d78")) {

			Inventory inv = ManagerInv.buildGUI();

			GUI.invs.add(inv);

			player.openInventory(inv);
		}

		return false;
	}

}
