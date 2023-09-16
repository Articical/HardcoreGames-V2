package uk.rythefirst.hcgames.customevents;

import org.bukkit.event.Event;
import org.bukkit.event.HandlerList;

public final class MobSpawningChangeEvent extends Event {

	private static final HandlerList handlers = new HandlerList();
	private Boolean oldVal;
	private Boolean newVal;

	public MobSpawningChangeEvent(Boolean oldValue, Boolean newValue) {
		oldVal = oldValue;
		newVal = newValue;
	}

	public Boolean getOldValue() {
		return oldVal;
	}

	public Boolean getNewValue() {
		return newVal;
	}

	public static HandlerList getHandlerList() {
		return handlers;
	}

	@Override
	public HandlerList getHandlers() {
		// TODO Auto-generated method stub
		return handlers;
	}

}
