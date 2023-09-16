package uk.rythefirst.hcgames.listeners;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.GameState;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent e) {

		Player p = e.getPlayer();
		Block b = e.getBlock();

		if (b.getType() == Material.CHEST) {
			if (Main.ChestCache.isCreating(e.getPlayer())) {
				if (Main.ChestCache.chestSet(b)) {
					e.getPlayer().sendMessage(ChatColor.DARK_RED + "Chest is already set.");
					e.setCancelled(true);
					return;
				}

				Main.ChestCache.addChest(b.getLocation(), Main.ChestCache.getTier(p));
				p.sendMessage(ChatColor.GREEN + "Chest at X:" + b.getX() + " Y:" + b.getY() + " Z:" + b.getZ()
						+ " added as tier: " + Main.ChestCache.getTier(p).toString());
				e.setCancelled(true);
			}

			if (Main.ChestCache.chestSet(b)) {
				e.getPlayer().sendMessage(ChatColor.DARK_RED + "DON'T BREAK MY CHESTS!");
				e.setCancelled(true);
			}

		}

		if (!(Main.settings.getState() == GameState.PLAYING) && !(p.hasPermission("hc.break"))) {
			e.setCancelled(true);
			return;
		}

	}

}
