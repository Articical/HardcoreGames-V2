package uk.rythefirst.hcgames.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;
import java.util.UUID;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.customevents.PlayerRegisteredEvent;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.result.TeamSetResult;

public class PlayerManager {

	private File pendingTeams = new File(Main.instance.getDataFolder() + "/teams.yml");
	private YamlConfiguration teamsCfg;

	private static TreeMap<UUID, HCPlayer> registeredPlayers = new TreeMap<UUID, HCPlayer>();
	// Player, Team
	private static TreeMap<UUID, UUID> pendingTeam = new TreeMap<UUID, UUID>();

	public PlayerManager() {
		if (!(pendingTeams.exists())) {
			try {
				pendingTeams.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		teamsCfg = YamlConfiguration.loadConfiguration(pendingTeams);
	}

	public Boolean isRegistered(String PID) {
		return registeredPlayers.containsKey(UUID.fromString(PID));
	}

	public void addFromPending(String PID) {
		if (hasPendingTeam(PID)) {
			setTeam(UUID.fromString(PID), pendingTeam.get(UUID.fromString(PID)));
			pendingTeam.remove(UUID.fromString(PID));
			getPlayer(PID).setType(PlayerType.PLAYER);
		}
	}

	public Boolean registerPlayer(Player p) {
		if (isRegistered(p.getUniqueId().toString())) {
			return false;
		}
		HCPlayer newPlayer = new HCPlayer(p.getUniqueId().toString(), p.getName());
		registeredPlayers.put(p.getUniqueId(), newPlayer);
		PlayerRegisteredEvent event = new PlayerRegisteredEvent(Bukkit.getPlayer(p.getUniqueId()), newPlayer);
		Bukkit.getServer().getPluginManager().callEvent(event);
		if (hasPendingTeam(p.getUniqueId().toString())) {
			Main.pm.addFromPending(p.getUniqueId().toString());
			Bukkit.getLogger().log(Level.INFO, "Player " + p.getName() + " has been added to their team!");
			for (Entry<UUID, UUID> entry : pendingTeam.entrySet()) {
				Bukkit.getLogger().log(Level.INFO, entry.getKey().toString() + " : " + entry.getValue().toString());
			}
		}
		return true;
	}

	public void addPending(UUID PlayerID, UUID TeamID) {
		pendingTeam.put(PlayerID, TeamID);
	}

	public TeamSetResult setTeam(UUID playerID, UUID TeamID) {
		if (isRegistered(playerID.toString())) {
			if (registeredPlayers.get(playerID).inTeam()) {
				return TeamSetResult.INTEAM;
			} else {
				registeredPlayers.get(playerID).setTeam(TeamID);
				return TeamSetResult.ADDED;
			}
		} else {
			pendingTeam.put(playerID, TeamID);
			Bukkit.getLogger().info((playerID.toString() + " added pendng "));
			return TeamSetResult.ADDEDPENDING;
		}

	}

	public HCPlayer getPlayer(String PID) {
		if (!isRegistered(PID)) {
			return null;
		}
		return registeredPlayers.get(UUID.fromString(PID));
	}

	public boolean hasPendingTeam(String PID) {
		return pendingTeam.containsKey(UUID.fromString(PID));
	}

	public void savePendingTeams() {
		String divide = "#@#";
		List<String> lst = new ArrayList<String>();
		for (Entry<UUID, UUID> pending : pendingTeam.entrySet()) {
			UUID playerUuid = pending.getKey();
			UUID teamID = pending.getValue();
			String saveStr = playerUuid.toString() + divide + teamID;
			lst.add(saveStr);
		}
		teamsCfg.set("teams", lst);
		try {
			teamsCfg.save(pendingTeams);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void loadPendingTeams() {
		String divide = "#@#";
		List<String> lst = teamsCfg.getStringList("teams");
		for (String string : lst) {
			String[] splitStr = string.split(divide);
			pendingTeam.put(UUID.fromString(splitStr[0]), UUID.fromString(splitStr[1]));
			Bukkit.getLogger().info(string);
		}
		Bukkit.getLogger().log(Level.INFO, "Pending Teams Loaded!");
	}

}
