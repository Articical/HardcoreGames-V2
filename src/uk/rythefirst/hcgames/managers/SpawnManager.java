package uk.rythefirst.hcgames.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerTeleportEvent.TeleportCause;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;
import uk.rythefirst.hcgames.objects.SpawnPoint;

public class SpawnManager {

	private List<SpawnPoint> spawns = new ArrayList<SpawnPoint>();
	private List<SpawnPoint> usedSpawns = new ArrayList<SpawnPoint>();
	private File spawnsFile = new File(Main.instance.getDataFolder() + "/spawns.yml");
	private YamlConfiguration spawnsCfg;
	private HashMap<UUID, SpawnPoint> teamSpawns = new HashMap<UUID, SpawnPoint>();

	public void SpawnPlayers() {
		for (Player player : Bukkit.getOnlinePlayers()) {
			HCPlayer hcPlayer = Main.pm.getPlayer(player.getUniqueId().toString());
			if (hcPlayer.getType() == PlayerType.PLAYER) {
				if (teamSpawns.containsKey(hcPlayer.getTeam())) {
					SpawnPoint point = teamSpawns.get(hcPlayer.getTeam());
					Location loc = point.getRandomLocation();
					player.teleport(loc, TeleportCause.PLUGIN);
				} else {
					teamSpawns.put(hcPlayer.getTeam(), Main.spawnManager.getUnusedSpawnPoint());
					SpawnPoint point = teamSpawns.get(hcPlayer.getTeam());
					Location loc = point.getRandomLocation();
					player.teleport(loc, TeleportCause.PLUGIN);
				}
				Main.game.setPlayersRemaining(Main.game.getPlayersRemaining() + 1);
			}
		}
	}

	public SpawnPoint getUnusedSpawnPoint() {
		SpawnPoint spoint = null;
		for (SpawnPoint point : spawns) {
			if (!(usedSpawns.contains(point))) {
				usedSpawns.add(point);
				return point;
			}

		}
		return spoint;
	}

	public SpawnManager() {
		if (!(spawnsFile.exists())) {
			try {
				spawnsFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
			spawnsCfg = YamlConfiguration.loadConfiguration(spawnsFile);
			spawnsCfg.set("spawns", new ArrayList<String>());
			try {
				spawnsCfg.save(spawnsFile);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			loadPoints();
		} else {
			spawnsCfg = YamlConfiguration.loadConfiguration(spawnsFile);
		}
	}

	public void loadPoints() {
		List<String> Lst = spawnsCfg.getStringList("spawns");
		if (Lst.size() == 0) {
			return;
		}
		for (String string : Lst) {
			SpawnPoint point;
			point = fromString(string);
			spawns.add(point);
		}
	}

	public void savePoints() {
		List<String> Lst = new ArrayList<String>();
		for (SpawnPoint point : spawns) {
			Lst.add(point.toString());
		}
		spawnsCfg.set("spawns", Lst);
		try {
			spawnsCfg.save(spawnsFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addPoint(SpawnPoint point) {
		if (!(spawns.contains(point))) {
			spawns.add(point);
		}
	}

	public SpawnPoint fromString(String string) {
		String[] stringlst = string.split("#~~~#");
		SpawnPoint point = new SpawnPoint();
		for (String str : stringlst) {
			Location loc = locationFromString(str);
			point.addLocation(loc);
		}
		return point;
	}

	public String locationToString(Location loc) {
		return loc.getWorld().getName() + ":#:" + loc.getBlockX() + ":#:" + loc.getBlockY() + ":#:" + loc.getBlockZ()
				+ ":#:" + loc.getYaw() + ":#:" + loc.getPitch();
	}

	public Location locationFromString(String string) {
		String[] splitStr = string.split(":#:");
		Location loc = new Location(Bukkit.getWorld(splitStr[0]), Double.parseDouble(splitStr[1]),
				Double.parseDouble(splitStr[2]), Double.parseDouble(splitStr[3]));
		loc.setYaw(Float.parseFloat(splitStr[4]));
		loc.setPitch(Float.parseFloat(splitStr[5]));
		return loc;
	}

}
