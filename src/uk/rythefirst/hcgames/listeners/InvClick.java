package uk.rythefirst.hcgames.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.cache.GUI;
import uk.rythefirst.hcgames.enums.ChatStatus;
import uk.rythefirst.hcgames.enums.ChestTier;
import uk.rythefirst.hcgames.enums.GameState;
import uk.rythefirst.hcgames.guis.items.MasterItems;
import uk.rythefirst.hcgames.guis.items.SetupItems;
import uk.rythefirst.hcgames.tools.PlayerTools;

public class InvClick implements Listener {

	@EventHandler
	public void IClicked(InventoryClickEvent e) {

		MasterItems items = new MasterItems();

		if (!(e.getCurrentItem() == null)) {

			if (!(e.getCurrentItem().hasItemMeta())) {
				return;
			}

			if (e.getCurrentItem().getItemMeta().getDisplayName().contains(ChatColor.DARK_PURPLE + "Chat")) {

				if (e.getClick() == ClickType.LEFT) {
					if (Main.settings.getChatStatus() == ChatStatus.GLOBAL) {
						Main.settings.setChatStatus(ChatStatus.TEAM);
					} else if (Main.settings.getChatStatus() == ChatStatus.TEAM) {
						Main.settings.setChatStatus(ChatStatus.NONE);
					} else if (Main.settings.getChatStatus() == ChatStatus.NONE) {
						Main.settings.setChatStatus(ChatStatus.GLOBAL);
					}
				} else if (e.getClick() == ClickType.RIGHT) {
					if (Main.settings.getChatStatus() == ChatStatus.GLOBAL) {
						Main.settings.setChatStatus(ChatStatus.NONE);
					} else if (Main.settings.getChatStatus() == ChatStatus.TEAM) {
						Main.settings.setChatStatus(ChatStatus.GLOBAL);
					} else if (Main.settings.getChatStatus() == ChatStatus.NONE) {
						Main.settings.setChatStatus(ChatStatus.TEAM);
					}
				}

				e.getInventory().setItem(e.getSlot(), items.chatStateItem());
				if (e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
					p.updateInventory();
				}

			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.mobSpawnItem().getItemMeta().getDisplayName())) {
				Main.settings.setMobSpawn(!(Main.settings.getMobSpawn()));
				e.getInventory().setItem(e.getSlot(), items.mobSpawnItem());
				if (e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
					p.updateInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.gameStateItem().getItemMeta().getDisplayName())) {
				if (e.getClick() == ClickType.LEFT) {
					if (Main.settings.getState() == GameState.LOBBY) {
						Main.settings.setState(GameState.STARTING);
					} else if (Main.settings.getState() == GameState.STARTING) {
						Main.settings.setState(GameState.PLAYING);
					} else if (Main.settings.getState() == GameState.PLAYING) {
						Main.settings.setState(GameState.FINISHED);
					} else if (Main.settings.getState() == GameState.FINISHED) {
						Main.settings.setState(GameState.LOBBY);
					}
				} else if (e.getClick() == ClickType.RIGHT) {
					if (Main.settings.getState() == GameState.LOBBY) {
						Main.settings.setState(GameState.FINISHED);
					} else if (Main.settings.getState() == GameState.STARTING) {
						Main.settings.setState(GameState.LOBBY);
					} else if (Main.settings.getState() == GameState.PLAYING) {
						Main.settings.setState(GameState.STARTING);
					} else if (Main.settings.getState() == GameState.FINISHED) {
						Main.settings.setState(GameState.PLAYING);
					}
				}

				e.getInventory().setItem(e.getSlot(), items.gameStateItem());
				if (e.getWhoClicked() instanceof Player) {
					Player p = (Player) e.getWhoClicked();
					p.updateInventory();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.startTimerItem().getItemMeta().getDisplayName())) {
				if (e.getClick() == ClickType.LEFT) {
					Main.sCountdown.Broadcast(true);
				} else if (e.getClick() == ClickType.RIGHT) {
					Main.sCountdown.Stop();
				}
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.populateChestsItem().getItemMeta().getDisplayName())) {
				Main.cm.populateChests();
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.clearChestsItem().getItemMeta().getDisplayName())) {
				Main.cm.clearChests();
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.startGameItem().getItemMeta().getDisplayName())) {
				Main.game.Start();
			} else if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(items.hidePlayersItem().getItemMeta().getDisplayName())) {
				if (Main.settings.getPlayersHidden()) {
					PlayerTools.showPlayers();
				} else {
					PlayerTools.hidePlayers();
				}
			}

			SetupItems Sitems = new SetupItems();

			if (e.getCurrentItem().getItemMeta().getDisplayName()
					.equals(Sitems.chestSelectorItem(new ArrayList<String>()).getItemMeta().getDisplayName())) {
				List<String> lorelst = new ArrayList<String>();
				if (!(Main.ChestCache.isCreating((Player) e.getWhoClicked()))) {
					Main.ChestCache.setCreating((Player) e.getWhoClicked(), ChestTier.ONE);
					lorelst.add(ChatColor.DARK_RED + "Currently: Selecting");
				} else {
					Main.ChestCache.stopCreating((Player) e.getWhoClicked());
					lorelst.add(ChatColor.DARK_RED + "Currently: Not Selecting");
				}
				e.getInventory().setItem(e.getSlot(), Sitems.chestSelectorItem(lorelst));
			}

		}

		if (GUI.invs.contains(e.getInventory())) {
			e.setCancelled(true);
		}
	}

}
