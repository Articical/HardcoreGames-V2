package uk.rythefirst.hcgames.listeners;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.customevents.PlayerRegisteredEvent;

public class PlayerRegistered implements Listener {

	@EventHandler
	public void onRegister(PlayerRegisteredEvent e) {
		Bukkit.getLogger().log(Level.INFO, e.getPlayer().getName() + " Register event called");

		if (e.getPlayer().hasPermission("hc.hidden")) {
			e.getPlayer().setPlayerListName(ChatColor.GOLD + "[" + ChatColor.DARK_PURPLE + "S" + ChatColor.GOLD + "] "
					+ ChatColor.RESET + e.getPlayer().getName());
		}

		Bukkit.getLogger().log(Level.INFO, "Player " + e.getPlayer().getName() + " has been Registered!");
	}

}
