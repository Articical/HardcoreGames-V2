package uk.rythefirst.hcgames.customevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

import uk.rythefirst.hcgames.objects.HCTeam;

public final class TeamWipeEvent extends Event {

	public TeamWipeEvent(HCTeam Team) {
		team = Team;
	}

	private static final HandlerList handlers = new HandlerList();

	private HCTeam team;

	public HCTeam getTeam() {
		return team;
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
