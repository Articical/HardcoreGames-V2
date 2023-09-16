package uk.rythefirst.hcgames.listeners;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerLoginEvent.Result;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;

public class PlayerLogin implements Listener {

	@EventHandler
	public void onLogin(PlayerLoginEvent e) {
		Player p = e.getPlayer();

		Bukkit.getLogger().info("" + Main.pm.isRegistered(e.getPlayer().getUniqueId().toString())
				+ Main.pm.hasPendingTeam(p.getUniqueId().toString()) + p.isWhitelisted());

		if (!(Main.pm.isRegistered(e.getPlayer().getUniqueId().toString()))) {
			if (Main.pm.hasPendingTeam(p.getUniqueId().toString()) == false) {
				if (p.isWhitelisted() == false) {
					e.disallow(Result.KICK_OTHER, ChatColor.DARK_RED + "You're not taking part!");
				}
			}
		}
		if (Main.playerCache.isEliminated(p)) {
			e.disallow(Result.KICK_OTHER,
					ChatColor.DARK_RED + "You were Eliminated! Watch now @ " + Main.settings.getStreamLink());
		}
	}

}
