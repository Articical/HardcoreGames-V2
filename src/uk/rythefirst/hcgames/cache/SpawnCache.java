package uk.rythefirst.hcgames.cache;

import java.util.TreeMap;
import java.util.UUID;

import org.bukkit.Bukkit;

import uk.rythefirst.hcgames.objects.SpawnPoint;

public class SpawnCache {

	public TreeMap<UUID, SpawnPoint> creatingMap;

	public SpawnCache() {
		creatingMap = new TreeMap<UUID, SpawnPoint>();
		Bukkit.getLogger().info("Spawn Cache Loaded");
	}

}
