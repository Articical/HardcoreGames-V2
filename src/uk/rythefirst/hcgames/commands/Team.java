package uk.rythefirst.hcgames.commands;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.objects.HCTeam;

public class Team implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

		if (args.length == 0) {
			sender.sendMessage(ChatColor.DARK_RED + "Invalid use.");
			return true;
		}

		if (args[0].equalsIgnoreCase("add")) {

			if (!(sender.hasPermission("hc.addteam"))) {
				sender.sendMessage(ChatColor.DARK_RED + "Unknown Command");
				return true;
			}

			if (args.length == 3) {
				String name = args[1];
				String ids = args[2];
				String[] uids = ids.split(",");
				HCTeam team = Main.tm.createTeam(name);
				Bukkit.getLogger().info(uids[0] + uids[1] + uids[2]);
				for (int i = 0; i < uids.length; i++) {

					Main.pm.setTeam(UUID.fromString(uids[i]), team.getUUID()).toString();
					Bukkit.getLogger()
							.info(Bukkit.getOfflinePlayer(UUID.fromString(uids[i])).getName() + " Added to team");
				}
			} else {
				sender.sendMessage(ChatColor.DARK_RED + "Correct Usage: /teams add <teamname> <id1>,<id2>,<id3>");
			}

		}

		if (args[0].equalsIgnoreCase("save")) {
			if (!(sender.hasPermission("hc.addteam"))) {
				sender.sendMessage(ChatColor.DARK_RED + "Unknown Command");
				return true;
			}

			Main.pm.savePendingTeams();
			sender.sendMessage(ChatColor.GREEN + "Teams Saved!");
		}

		return true;
	}

}
