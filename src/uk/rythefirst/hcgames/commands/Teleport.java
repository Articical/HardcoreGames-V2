package uk.rythefirst.hcgames.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;

public class Teleport implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return false;
		}
		Player player = (Player) sender;

		if (!(player.hasPermission("hc.hidden"))) {
			return true;
		}

		if (!(args.length == 1)) {
			player.sendMessage(ChatColor.DARK_RED + "Correct Usage: /tp <player>");
			return true;
		}

		String pname = args[0];

		if (Bukkit.getPlayer(pname).isOnline() == false) {
			player.sendMessage(ChatColor.DARK_RED + "Invalid Player");
		}

		if (Bukkit.getPlayer(pname) != null || !(Bukkit.getPlayer(pname).isOnline())) {
			Player target = Bukkit.getPlayer(pname);
			player.teleport(target.getLocation());
		} else {
			player.sendMessage(ChatColor.DARK_RED + "That player doesn't exist!");
		}
		return true;
	}

}
