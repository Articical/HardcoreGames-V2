package uk.rythefirst.hcgames;

import org.bukkit.Bukkit;

import uk.rythefirst.hcgames.customevents.MobSpawningChangeEvent;
import uk.rythefirst.hcgames.enums.ChatStatus;
import uk.rythefirst.hcgames.enums.GameState;

public class Settings {

	private GameState State = GameState.LOBBY;
	private Boolean mobSpawning = false;
	private ChatStatus chatStatus = ChatStatus.GLOBAL;
	private Integer startCountdownTime = 10;
	private Boolean playersHidden = true;
	private String streamLink;
	private Boolean Pvp;

	public GameState getState() {
		return State;
	}

	public void setState(GameState state) {
		State = state;
	}

	public Boolean getMobSpawn() {
		return mobSpawning;
	}

	public void setMobSpawn(Boolean mobSpawning) {
		MobSpawningChangeEvent event = new MobSpawningChangeEvent(!(mobSpawning), mobSpawning);
		Bukkit.getServer().getPluginManager().callEvent(event);
		this.mobSpawning = mobSpawning;
	}

	public ChatStatus getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(ChatStatus chatStatus) {
		this.chatStatus = chatStatus;
	}

	public Integer getStartingCountdownTime() {
		return startCountdownTime;
	}

	public Boolean getPlayersHidden() {
		return playersHidden;
	}

	public void setPlayersHidden(Boolean playersHidden) {
		this.playersHidden = playersHidden;
	}

	public String getStreamLink() {
		return streamLink;
	}

	public void setStreamLink(String streamLink) {
		this.streamLink = streamLink;
	}

	public Boolean getPvp() {
		return Pvp;
	}

	public void setPvp(Boolean pvp) {
		Pvp = pvp;
	}

}
