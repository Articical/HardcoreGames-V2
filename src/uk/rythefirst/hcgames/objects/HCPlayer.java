package uk.rythefirst.hcgames.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.enums.PlayerType;

public class HCPlayer {

	private UUID team;
	private Boolean inTeam;
	private UUID PlayerID;
	private String name;
	private LifeStatus Living;
	private Integer kills;
	private UUID killer;
	private List<UUID> killed;
	private PlayerType type;

	public HCPlayer(String PlayerUUID, String name) {
		PlayerID = UUID.fromString(PlayerUUID);
		killed = new ArrayList<UUID>();
		this.name = name;
		type = PlayerType.SPECTATOR;
		inTeam = false;
	}

	public boolean inTeam() {
		return inTeam;
	}

	public void addKill(UUID victim) {
		killed.add(victim);
	}

	public UUID getTeam() {
		return team;
	}

	public void setTeam(UUID team) {
		if (team == null) {
			inTeam = false;
			return;
		}
		inTeam = true;
		this.team = team;
	}

	public UUID getPlayerID() {
		return PlayerID;
	}

	public void setPlayerID(UUID playerID) {
		PlayerID = playerID;
	}

	public Integer getKills() {
		return kills;
	}

	public void setKills(Integer kills) {
		this.kills = kills;
	}

	public UUID getKiller() {
		return killer;
	}

	public void setKiller(UUID killer) {
		this.killer = killer;
	}

	public List<UUID> getKilled() {
		return killed;
	}

	public void setKilled(List<UUID> killed) {
		this.killed = killed;
	}

	public LifeStatus getLiving() {
		return Living;
	}

	public void setLiving(LifeStatus living) {
		Living = living;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public PlayerType getType() {
		return type;
	}

	public void setType(PlayerType type) {
		this.type = type;
	}

}
