package uk.rythefirst.hcgames.tools;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;

public class Visuals {

	private static Integer CCInteger = 0;

	public static void AnnounceTeamWipe(String TeamName) {
		for (Player player : Bukkit.getOnlinePlayers()) {
			player.sendTitle("" + ChatColor.BOLD + ChatColor.DARK_RED + "Team Wiped!",
					ChatColor.GOLD + TeamName + " is now out of the game", 10, 60, 10);
			player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE, 2f, 1f);
		}
		Bukkit.broadcastMessage("" + ChatColor.BOLD + ChatColor.DARK_RED + "Team wipe!");
		Bukkit.broadcastMessage(ChatColor.GOLD + TeamName + " were eliminated!");
		Bukkit.broadcastMessage(ChatColor.AQUA + Main.game.getTeamsRemaining().toString() + " Teams Remain!");
	}

	public static ChatColor NextTeamColor() {
		List<ChatColor> lst = new ArrayList<ChatColor>();
		lst.add(ChatColor.of(Color.red));
		lst.add(ChatColor.AQUA);
		lst.add(ChatColor.DARK_BLUE);
		lst.add(ChatColor.DARK_PURPLE);
		lst.add(ChatColor.DARK_GREEN);
		lst.add(ChatColor.GOLD);
		lst.add(ChatColor.WHITE);
		lst.add(ChatColor.BLUE);
		lst.add(ChatColor.YELLOW);
		lst.add(ChatColor.GREEN);
		lst.add(ChatColor.LIGHT_PURPLE);
		lst.add(ChatColor.GRAY);
		lst.add(ChatColor.DARK_RED);
		if (CCInteger == lst.size() - 1) {
			CCInteger = 0;
		}
		ChatColor cc = lst.get(CCInteger);
		CCInteger++;
		return cc;
	}

}
