package uk.rythefirst.hcgames.objects;

import org.bukkit.Material;

public class ChestItem {

	Integer amount;
	private Material material;

	public ChestItem(Material item, Integer amount) {
		this.amount = amount;
		material = item;
	}

	public Integer getAmount() {
		return amount;
	}

	public Material getMaterial() {
		return material;
	}

}
