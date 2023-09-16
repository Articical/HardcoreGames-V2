package uk.rythefirst.hcgames;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.enums.LifeStatus;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.objects.HCPlayer;

public class Game {

	private Integer playersRemaining = 0;
	private Integer teamsRemaining = 0;
	private UUID winningTeam;
	public List<UUID> teamsRemainingLst = new ArrayList<UUID>();
	public boolean lastZone = false;

	public void Start() {
		if (!(Main.settings.getState() == GameState.LOBBY)) {
			return;
		}
		Main.settings.setState(GameState.STARTING);
		Bukkit.getWorld("world").getWorldBorder().setSize(3000);
		Bukkit.getWorld("world").getWorldBorder().setDamageAmount(5);
		Bukkit.getWorld("world").getWorldBorder().setWarningDistance(10);
		Main.spawnManager.SpawnPlayers();
		Main.cm.populateChests();
		Main.sCountdown.Broadcast(false);

		for (Player player : Bukkit.getOnlinePlayers()) {
			HCPlayer hcplayer = Main.pm.getPlayer(player.getUniqueId().toString());
			hcplayer.setLiving(LifeStatus.ALIVE);
			if (hcplayer.getType() == PlayerType.SPECTATOR) {
				player.setGameMode(GameMode.SPECTATOR);
			}
		}

	}

	public Integer getPlayersRemaining() {
		return playersRemaining;
	}

	public void setPlayersRemaining(Integer playersRemaining) {
		this.playersRemaining = playersRemaining;
	}

	public Integer getTeamsRemaining() {
		return teamsRemaining;
	}

	public void setTeamsRemaining(Integer teamsRemaining) {
		this.teamsRemaining = teamsRemaining;
	}

	public UUID getWinningTeam() {
		return winningTeam;
	}

	public void setWinningTeam(UUID winningTeam) {
		this.winningTeam = winningTeam;
	}

}
