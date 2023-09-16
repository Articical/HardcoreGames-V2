package uk.rythefirst.hcgames.guis.items;

import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;

public class SetupItems {

	public ItemStack chestSelectorItem(List<String> lore) {
		ItemStack is = new ItemStack(Material.CHEST, 1);
		ItemMeta im = is.getItemMeta();
		im.setDisplayName(ChatColor.GOLD + "Chest Selector");
		im.setLore(lore);
		is.setItemMeta(im);
		return is;
	}

}
