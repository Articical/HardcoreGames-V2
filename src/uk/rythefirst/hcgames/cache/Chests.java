package uk.rythefirst.hcgames.cache;

import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;

import uk.rythefirst.hcgames.enums.ChestTier;

public class Chests {

	private TreeMap<UUID, ChestTier> players;
	private TreeMap<String, ChestTier> chests;

	public Chests() {
		players = new TreeMap<UUID, ChestTier>();
		chests = new TreeMap<String, ChestTier>();
	}

	public void setChests(TreeMap<String, ChestTier> map) {
		chests = map;
	}

	public boolean isCreating(Player p) {
		return players.containsKey(p.getUniqueId());
	}

	public void setCreating(Player player, ChestTier tier) {
		if (players.containsKey(player.getUniqueId())) {
			players.replace(player.getUniqueId(), tier);
			return;
		}

		players.put(player.getUniqueId(), tier);
	}

	public void stopCreating(Player player) {
		if (players.containsKey(player.getUniqueId())) {
			players.remove(player.getUniqueId());
		}
	}

	public TreeMap<String, ChestTier> getChests() {
		return chests;
	}

	public Boolean chestSet(Block b) {
		if (chests.containsKey(locationToString(b.getLocation()))) {
			return true;
		} else {
			return false;
		}
	}

	public void addChest(Location loc, ChestTier tier) {
		chests.put(locationToString(loc), tier);
	}

	public ChestTier getTier(Player p) {
		return players.get(p.getUniqueId());
	}

	public String locationToString(Location loc) {
		return loc.getWorld().getName() + ":#:" + loc.getBlockX() + ":#:" + loc.getBlockY() + ":#:" + loc.getBlockZ();
	}

	public Location locationFromString(String string) {
		String[] splitStr = string.split(":#:");
		Location loc = new Location(Bukkit.getWorld(splitStr[0]), Double.parseDouble(splitStr[1]),
				Double.parseDouble(splitStr[2]), Double.parseDouble(splitStr[3]));
		return loc;
	}

}
