package uk.rythefirst.hcgames.managers;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.entity.Player;

import uk.rythefirst.hcgames.enums.PlayerType;

public class OldPlayerManager {

	private List<String> players = new ArrayList<String>();
	private TreeMap<String, PlayerType> ptype = new TreeMap<String, PlayerType>();

	private TreeMap<String, UUID> teams = new TreeMap<String, UUID>();

	public boolean inTeam(String uID) {
		return teams.containsKey(uID);
	}

	public void setTeam(Player p, UUID TeamID) {
		if (teams.containsKey(p.getUniqueId().toString())) {
			teams.replace(p.getUniqueId().toString(), TeamID);
		}
	}

	public UUID getTeam(String PID) {
		if (inTeam(PID)) {
			return teams.get(PID);
		} else {
			return null;
		}
	}

	public void registerPlayer(Player p) {
		players.add(p.getUniqueId().toString());
	}

	public boolean playerKnown(Player p) {
		return players.contains(p.getUniqueId().toString());
	}

	public boolean isSpectator(Player p) {
		if (ptype.get(p.getUniqueId().toString()) == PlayerType.SPECTATOR) {
			return true;
		}
		return false;
	}

}
