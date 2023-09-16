package uk.rythefirst.hcgames.listeners;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.ChatStatus;
import uk.rythefirst.hcgames.enums.PlayerType;
import uk.rythefirst.hcgames.managers.PlayerManager;

public class PlayerChat implements Listener {

	@EventHandler
	public void onPlayerChat(AsyncPlayerChatEvent e) {
		PlayerManager pm = Main.pm;

		String format = e.getPlayer().getName() + ChatColor.GOLD + ChatColor.BOLD + " » " + ChatColor.RESET
				+ e.getMessage();

		if (pm.getPlayer(e.getPlayer().getUniqueId().toString()).inTeam()) {
			format = ChatColor.DARK_PURPLE
					+ Main.tm.getTeam(pm.getPlayer(e.getPlayer().getUniqueId().toString()).getTeam()).getName()
					+ ChatColor.RESET + " " + format;
		} else if (pm.getPlayer(e.getPlayer().getUniqueId().toString()).getType() == PlayerType.SPECTATOR
				|| e.getPlayer().hasPermission("hc.hidden")) {
			format = "" + ChatColor.GOLD + ChatColor.BOLD + "[" + ChatColor.BLUE + "Staff" + ChatColor.GOLD
					+ ChatColor.BOLD + "] " + ChatColor.RESET + format;
		}
		e.setFormat(format);
		if (Main.settings.getChatStatus() == ChatStatus.GLOBAL) {
			return;
		} else if (Main.settings.getChatStatus() == ChatStatus.NONE
				&& !(pm.getPlayer(e.getPlayer().getUniqueId().toString()).getType() == PlayerType.SPECTATOR)) {
			e.setCancelled(true);
		} else if (Main.settings.getChatStatus() == ChatStatus.TEAM
				&& pm.getPlayer(e.getPlayer().getUniqueId().toString()).inTeam()) {
			e.getRecipients().clear();
			for (String id : Main.tm.getTeam(pm.getPlayer(e.getPlayer().getUniqueId().toString()).getTeam())
					.getMembers()) {
				OfflinePlayer op = Bukkit.getOfflinePlayer(UUID.fromString(id));
				if (op.isOnline()) {
					Player p = Bukkit.getPlayer(UUID.fromString(id));
					e.getRecipients().add(p);
				}
			}
		}

	}

}
