package uk.rythefirst.hcgames.customevents;

import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import uk.rythefirst.hcgames.objects.HCPlayer;

public final class PlayerRegisteredEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private HCPlayer account;
	private Player player;

	public PlayerRegisteredEvent(Player player, HCPlayer account) {
		this.account = account;
		this.player = player;
	}

	public HCPlayer getAccount() {
		return account;
	}

	public Player getPlayer() {
		return player;
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
