package uk.rythefirst.hcgames.guis.items;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;
import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.ChatStatus;
import uk.rythefirst.hcgames.enums.GameState;

public class MasterItems {

	public ItemStack gameStateItem() {
		List<String> lore = new ArrayList<String>();
		GameState gState = Main.settings.getState();
		ItemStack is = new ItemStack(org.bukkit.Material.NOTE_BLOCK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Game State");
		lore.add(ChatColor.GOLD + gState.toString());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack mobSpawnItem() {
		List<String> lore = new ArrayList<String>();
		Boolean mobs = Main.settings.getMobSpawn();
		ItemStack is = new ItemStack(Material.SPAWNER, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + "Mob Spawning");
		lore.add(ChatColor.GOLD + "Currently Set to: " + mobs.toString());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack chatStateItem() {
		List<String> lore = new ArrayList<String>();
		ChatStatus state = Main.settings.getChatStatus();
		ItemStack is = new ItemStack(Material.WRITABLE_BOOK, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + "Chat");
		lore.add(ChatColor.GOLD + "Currently: " + org.bukkit.ChatColor.RED + state.toString());
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack startTimerItem() {
		List<String> lore = new ArrayList<String>();
		ItemStack is = new ItemStack(Material.PAPER, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.GREEN + "Test Start Timer");
		lore.add(ChatColor.RED + "Debug only");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack populateChestsItem() {
		List<String> lore = new ArrayList<String>();
		ItemStack is = new ItemStack(Material.CHEST, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_RED + "Populate Chests");
		lore.add("Populates chests");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack clearChestsItem() {
		List<String> lore = new ArrayList<String>();
		ItemStack is = new ItemStack(Material.ENDER_CHEST, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.DARK_PURPLE + "Empty Chests");
		lore.add("Empties chests");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack startGameItem() {
		List<String> lore = new ArrayList<String>();
		ItemStack is = new ItemStack(Material.GREEN_CONCRETE, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("" + ChatColor.BOLD + ChatColor.GREEN + "Start The Game");
		lore.add("Start the game!");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

	public ItemStack hidePlayersItem() {
		List<String> lore = new ArrayList<String>();
		ItemStack is = new ItemStack(Material.NAME_TAG, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName("" + ChatColor.BOLD + ChatColor.GREEN + "Hide Players");
		lore.add("Toggle whether or not players are visible.");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

}
