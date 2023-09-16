package uk.rythefirst.hcgames.objects;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Location;

public class SpawnPoint {

	private List<Location> spawnLocations = new ArrayList<Location>();
	private List<Location> usedLocations = new ArrayList<Location>();
	private String divide = "#~~~#";
	private Integer locInteger = 0;

	public Location getRandomLocation() {
		Location loc;
		loc = spawnLocations.get(locInteger);
		usedLocations.add(loc);
		locInteger++;
		return loc;
	}

	public void addLocation(Location loc) {
		if (!(spawnLocations.contains(loc))) {
			spawnLocations.add(loc);
		}
	}

	public Boolean used() {
		return usedLocations.size() == spawnLocations.size();
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		Integer count = 0;
		for (Location loc : spawnLocations) {
			if (!(count == 0)) {
				builder.append(divide);
			}
			count++;
			builder.append(locationToString(loc));
		}
		return builder.toString();
	}

	public String locationToString(Location loc) {
		return loc.getWorld().getName() + ":#:" + loc.getBlockX() + ":#:" + loc.getBlockY() + ":#:" + loc.getBlockZ()
				+ ":#:" + loc.getYaw() + ":#:" + loc.getPitch();
	}

	public Boolean positionUsed(Location loc) {
		return spawnLocations.contains(loc);
	}

}
