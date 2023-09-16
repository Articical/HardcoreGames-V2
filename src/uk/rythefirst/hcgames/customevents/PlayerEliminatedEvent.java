package uk.rythefirst.hcgames.customevents;

import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import uk.rythefirst.hcgames.objects.HCPlayer;

public final class PlayerEliminatedEvent extends Event {

	private HCPlayer player;
	private OfflinePlayer MCPlayer;
	private OfflinePlayer Killer;

	public PlayerEliminatedEvent(HCPlayer Player, Player mcPlayer, Player killer) {
		player = Player;
		MCPlayer = mcPlayer;
		Killer = killer;
	}

	public PlayerEliminatedEvent(HCPlayer Player, Player mcPlayer) {
		player = Player;
		MCPlayer = mcPlayer;
		Killer = null;
	}

	public PlayerEliminatedEvent(HCPlayer hcplayer, OfflinePlayer Player) {
		MCPlayer = Player;
		player = hcplayer;
	}

	private static final HandlerList handlers = new HandlerList();

	public HCPlayer getPlayer() {
		return player;
	}

	public OfflinePlayer getMcPlayer() {
		return MCPlayer;
	}

	public OfflinePlayer getKiller() {
		return Killer;
	}

	// Required Crap
	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		return handlers;
	}

}