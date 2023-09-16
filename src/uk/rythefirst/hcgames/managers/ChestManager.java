package uk.rythefirst.hcgames.managers;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import uk.rythefirst.hcgames.Main;
import uk.rythefirst.hcgames.enums.ChestTier;
import uk.rythefirst.hcgames.objects.ChestItem;
import uk.rythefirst.hcgames.objects.ChestItemList;

public class ChestManager {

	File chestSaveFile = new File(Main.instance.getDataFolder() + "/chests.yml");
	YamlConfiguration FileConfig;

	public ChestManager() {
		if (!(Main.instance.getDataFolder().exists())) {
			Main.instance.getDataFolder().mkdirs();
		}

		if (!(chestSaveFile.exists())) {
			try {
				chestSaveFile.createNewFile();
				FileConfig = YamlConfiguration.loadConfiguration(chestSaveFile);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			FileConfig = YamlConfiguration.loadConfiguration(chestSaveFile);
		}
	}

	public void saveChests() {
		TreeMap<String, ChestTier> cacheMap = new TreeMap<String, ChestTier>();
		cacheMap = Main.ChestCache.getChests();
		List<String> chestList = new ArrayList<String>();
		for (Entry<String, ChestTier> entry : cacheMap.entrySet()) {
			chestList.add(entry.getKey() + "#@#" + entry.getValue().toString());
		}
		FileConfig.set("Chests", chestList);
		try {
			FileConfig.save(chestSaveFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void loadChests() {
		TreeMap<String, ChestTier> cacheMap = new TreeMap<String, ChestTier>();
		List<String> chestList = new ArrayList<String>();
		if (!(Main.instance.getDataFolder().exists())) {
			Main.instance.getDataFolder().mkdirs();
		}
		if (!(chestSaveFile.exists())) {
			try {
				chestSaveFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		FileConfig = YamlConfiguration.loadConfiguration(chestSaveFile);
		if (FileConfig.isSet("Chests")) {
			chestList = FileConfig.getStringList("Chests");
			for (String str : chestList) {
				String[] split = str.split("#@#");
				cacheMap.put(split[0], ChestTier.fromString(split[1]));
				Main.ChestCache.setChests(cacheMap);
			}
		} else {
			return;
		}
	}

	public void populateChests() {

		TreeMap<String, ChestTier> cacheMap = new TreeMap<String, ChestTier>();

		cacheMap = Main.ChestCache.getChests();

		for (Entry<String, ChestTier> entry : cacheMap.entrySet()) {
			populate(Bukkit.getWorld("world").getBlockAt(Main.ChestCache.locationFromString(entry.getKey())),
					entry.getValue());
		}

	}

	public void clearChests() {
		TreeMap<String, ChestTier> cacheMap = new TreeMap<String, ChestTier>();

		cacheMap = Main.ChestCache.getChests();

		for (Entry<String, ChestTier> entry : cacheMap.entrySet()) {
			if (Bukkit.getWorld("world").getBlockAt(Main.ChestCache.locationFromString(entry.getKey())) != null) {
				Block block = Bukkit.getWorld("world").getBlockAt(Main.ChestCache.locationFromString(entry.getKey()));
				Chest chest = (Chest) block.getState();
				chest.getInventory().clear();
			}
		}
	}

	public void populate(Block block, ChestTier tier) {

		if (!(block.getType() == Material.CHEST)) {
			Bukkit.getLogger().log(Level.SEVERE,
					"Error populating chest @ " + block.getLocation().toString() + " this block isn't a chest!");
			return;
		}

		if (tier == ChestTier.ONE) {
			Chest chest = (Chest) block.getState();
			Inventory chestInv = chest.getInventory();
			Integer maxItems = 5;
			Integer items = 0;
			for (int i = 0; i < chestInv.getSize(); i++) {
				Random random = new Random();
				if (items < maxItems && random.nextInt(10) < 3) {
					chestInv.setItem(i, getRandomItem(tier));
					items++;
				}
			}
		}
	}

	public ItemStack getRandomItem(ChestTier tier) {

		List<ChestItem> itemList = new ArrayList<ChestItem>();
		ChestItemList cItemList = new ChestItemList();
		ItemStack is = null;

		if (tier == ChestTier.ONE) {
			itemList = cItemList.tier1Item();
			Random r = new Random();
			ChestItem citem = itemList.get(r.nextInt(itemList.size()));
			Material mat = citem.getMaterial();
			Integer amount = citem.getAmount();
			is = new ItemStack(mat, amount);
		}

		return is;
	}

}
