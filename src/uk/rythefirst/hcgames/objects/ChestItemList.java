package uk.rythefirst.hcgames.objects;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.bukkit.Material;

public class ChestItemList {

	private Integer getRandomAmount() {
		Random rand = new Random();
		return rand.nextInt(4);
	}

	public List<ChestItem> tier1Item() {

		List<ChestItem> tier1Mat = new ArrayList<ChestItem>();

		tier1Mat.add(new ChestItem(Material.IRON_NUGGET, getRandomAmount()));
		tier1Mat.add(new ChestItem(Material.APPLE, getRandomAmount()));
		tier1Mat.add(new ChestItem(Material.LEATHER_HELMET, 1));
		tier1Mat.add(new ChestItem(Material.LEATHER_CHESTPLATE, 1));
		tier1Mat.add(new ChestItem(Material.LEATHER_LEGGINGS, 1));
		tier1Mat.add(new ChestItem(Material.LEATHER_BOOTS, 1));
		tier1Mat.add(new ChestItem(Material.BREAD, getRandomAmount()));

		return tier1Mat;
	}

	public List<ChestItem> tier2Item() {

		List<ChestItem> tier2Mat = new ArrayList<ChestItem>();

		tier2Mat.add(new ChestItem(Material.IRON_INGOT, 2));
		tier2Mat.add(new ChestItem(Material.COOKED_BEEF, 6));
		tier2Mat.add(new ChestItem(Material.COAL, 10));
		tier2Mat.add(new ChestItem(Material.CRAFTING_TABLE, 1));
		tier2Mat.add(new ChestItem(Material.FURNACE, 1));

		return tier2Mat;

	}

}
