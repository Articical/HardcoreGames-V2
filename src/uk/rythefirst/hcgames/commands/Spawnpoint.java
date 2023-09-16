package uk.rythefirst.hcgames.commands;

import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.objects.SpawnPoint;

public class Spawnpoint implements CommandExecutor {

	public static TreeMap<UUID, SpawnPoint> creatingMap;

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (!(sender instanceof Player)) {
			return true;
		}

		Player player = (Player) sender;

		if (!(player.hasPermission("hc.spawns"))) {
			return true;
		}

		if (args.length == 0) {
			sender.sendMessage(ChatColor.GOLD + "Usage:");
			sender.sendMessage(ChatColor.GOLD + "/spawnpoint new - Add a new spawn point group.");
			sender.sendMessage(
					ChatColor.GOLD + "/spawnpoint add - Add a location to the spawnpoint you're currently creating.");
			sender.sendMessage(ChatColor.GOLD + "/spawnpoint remove <num> - Remove the listed spawn point.");
			sender.sendMessage(ChatColor.GOLD
					+ "/spawnpoint save - Stop adding points to the currently selected spawn point and save it.");
			return true;
		}

		if (args[0].equalsIgnoreCase("new")) {
			creatingMap = Main.spawnCache.creatingMap;
			if (creatingMap.containsKey(player.getUniqueId())) {
				player.sendMessage(ChatColor.DARK_RED + "You're currently creating a spawn point!");
				return true;
			}
			creatingMap.put(player.getUniqueId(), new SpawnPoint());
			player.sendMessage(ChatColor.GOLD + "New spawnpoint created!");
		}

		if (args[0].equalsIgnoreCase("add")) {
			creatingMap = Main.spawnCache.creatingMap;
			if (!(creatingMap.containsKey(player.getUniqueId()))) {
				player.sendMessage(ChatColor.DARK_RED + "Pleas use /spawnpoint new first!");
				return true;
			}
			SpawnPoint point = creatingMap.get(player.getUniqueId());
			if (point.positionUsed(player.getLocation())) {
				player.sendMessage(ChatColor.DARK_RED + "Location already set!");
				return true;
			}
			point.addLocation(player.getLocation());
			creatingMap.replace(player.getUniqueId(), point);
			Main.spawnCache.creatingMap = creatingMap;
			player.sendMessage("Spawn location added!");
		}

		if (args[0].equalsIgnoreCase("save")) {
			creatingMap = Main.spawnCache.creatingMap;
			if (!(creatingMap.containsKey(player.getUniqueId()))) {
				player.sendMessage(ChatColor.DARK_RED + "Pleas use /spawnpoint new first!");
				return true;
			}
			Main.spawnManager.addPoint(creatingMap.get(player.getUniqueId()));
			Main.spawnCache.creatingMap.remove(player.getUniqueId());
			player.sendMessage(ChatColor.GREEN + "Spawn saved!");
			return true;
		}

		return true;
	}

}
