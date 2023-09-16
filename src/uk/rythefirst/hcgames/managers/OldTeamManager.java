package uk.rythefirst.hcgames.managers;

import java.util.TreeMap;
import java.util.UUID;

import uk.rythefirst.hcgames.objects.HCTeam;

public class OldTeamManager {

	private TreeMap<UUID, HCTeam> teams = new TreeMap<UUID, HCTeam>();

	public HCTeam createTeam(String name, String leaderID) {
		// if (Main.pm.inTeam(leaderID)) {
		// leader.sendMessage(ChatColor.DARK_RED + "You're currently in a team!");
		// }
		// HCTeam newTeam = new HCTeam(leaderID, name);
		// Main.pm.setTeam(Bukkit.getPlayer(UUID.fromString(leaderID)),
		// newTeam.getUUID());
		// teams.put(newTeam.getUUID(), newTeam);
		return null;
	}

	public HCTeam getTeam(UUID TeamID) {
		return teams.get(TeamID);
	}

}
