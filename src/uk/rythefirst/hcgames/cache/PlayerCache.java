package uk.rythefirst.hcgames.cache;

import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

public class PlayerCache {

	public List<String> EliminatedPlayers = new ArrayList<String>();

	public TreeMap<UUID, Location> quitPlayers = new TreeMap<UUID, Location>();

	public boolean isEliminated(Player p) {
		return EliminatedPlayers.contains(p.getUniqueId().toString());
	}

	public void addElimination(OfflinePlayer offlinePlayer) {
		if (!(EliminatedPlayers.contains(offlinePlayer.getUniqueId().toString()))) {
			EliminatedPlayers.add(offlinePlayer.getUniqueId().toString());
		}
	}

}
