package uk.rythefirst.hcgames.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.configuration.file.YamlConfiguration;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.objects.HCTeam;

public class TeamManager {

	public TreeMap<UUID, HCTeam> teams = new TreeMap<UUID, HCTeam>();

	private File teamFiles = new File(Main.instance.getDataFolder() + "/actualteams.yml");
	private YamlConfiguration teamCfg;

	public TeamManager() {
		if (!(teamFiles.exists())) {
			try {
				teamFiles.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		teamCfg = YamlConfiguration.loadConfiguration(teamFiles);
		if (!(teamCfg.isSet("teams"))) {
			teamCfg.set("teams", new ArrayList<String>());
		}
		// loadTeams();
	}

	public void loadTeams() {
		List<String> lst = teamCfg.getStringList("teams");
		for (String string : lst) {
			fromString(string);
		}
	}

	public void saveTeams() {
		List<String> lst = new ArrayList<String>();
		for (Entry<UUID, HCTeam> entry : teams.entrySet()) {
			String str = toString(entry.getValue());
			lst.add(str);
		}
		teamCfg.set("teams", lst);
		try {
			teamCfg.save(teamFiles);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String toString(HCTeam team) {
		String divide = "#:::#";
		StringBuilder builder = new StringBuilder();
		builder.append(team.getName() + divide);
		builder.append(team.getUUID().toString());
		return builder.toString();

	}

	public HCTeam fromString(String string) {
		String divide = "#:::#";
		String[] splitStr = string.split(divide);
		UUID TID = UUID.fromString(splitStr[1]);
		HCTeam team = createTeam(splitStr[0], TID);
		return team;
	}

	public HCTeam createTeam(String name, UUID ID) {

		Boolean nameused = false;

		for (Entry<UUID, HCTeam> entry : teams.entrySet()) {
			if (entry.getValue().getName() == name) {
				nameused = true;
			}
		}

		if (nameused) {
			return null;
		}

		HCTeam newTeam = new HCTeam(name, ID);
		teams.put(newTeam.getUUID(), newTeam);
		Main.game.setTeamsRemaining(Main.game.getTeamsRemaining() + 1);
		Main.game.teamsRemainingLst.add(newTeam.getUUID());
		return newTeam;
	}

	public HCTeam createTeam(String name) {

		Boolean nameused = false;

		for (Entry<UUID, HCTeam> entry : teams.entrySet()) {
			if (entry.getValue().getName() == name) {
				nameused = true;
			}
		}

		if (nameused) {
			return null;
		}

		HCTeam newTeam = new HCTeam(name);
		teams.put(newTeam.getUUID(), newTeam);
		Main.game.setTeamsRemaining(Main.game.getTeamsRemaining() + 1);
		Main.game.teamsRemainingLst.add(newTeam.getUUID());
		return newTeam;
	}

	public Boolean teamExists(UUID TeamID) {
		return teams.containsKey(TeamID);
	}

	public HCTeam getTeam(UUID TeamID) {
		if (teams.containsKey(TeamID)) {
			return teams.get(TeamID);
		} else {
			return null;
		}
	}
}
