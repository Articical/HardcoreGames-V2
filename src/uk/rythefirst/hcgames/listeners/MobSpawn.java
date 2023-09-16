package uk.rythefirst.hcgames.listeners;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.world.ChunkLoadEvent;

import uk.rythefirst.hcgames.Main;

public class MobSpawn implements Listener {

	@EventHandler
	public void MobSpawning(EntitySpawnEvent e) {
		if (Main.settings.getMobSpawn()) {
			return;
		}

		List<EntityType> removeme = new ArrayList<EntityType>();
		removeme.add(EntityType.BAT);
		removeme.add(EntityType.BEE);
		removeme.add(EntityType.BLAZE);
		removeme.add(EntityType.CAT);
		removeme.add(EntityType.CAVE_SPIDER);
		removeme.add(EntityType.CHICKEN);
		removeme.add(EntityType.COD);
		removeme.add(EntityType.COW);
		removeme.add(EntityType.CREEPER);
		removeme.add(EntityType.DOLPHIN);
		removeme.add(EntityType.DONKEY);
		removeme.add(EntityType.DROWNED);
		removeme.add(EntityType.ELDER_GUARDIAN);
		removeme.add(EntityType.ENDER_DRAGON);
		removeme.add(EntityType.ENDERMAN);
		removeme.add(EntityType.ENDERMITE);
		removeme.add(EntityType.SILVERFISH);
		removeme.add(EntityType.PIG);
		removeme.add(EntityType.EVOKER);
		removeme.add(EntityType.FOX);
		removeme.add(EntityType.GHAST);
		removeme.add(EntityType.GUARDIAN);
		removeme.add(EntityType.HORSE);
		removeme.add(EntityType.SKELETON_HORSE);
		removeme.add(EntityType.SKELETON);
		removeme.add(EntityType.SPIDER);
		removeme.add(EntityType.ZOMBIE_HORSE);
		removeme.add(EntityType.VILLAGER);
		removeme.add(EntityType.ZOMBIE_VILLAGER);
		removeme.add(EntityType.SHEEP);
		removeme.add(EntityType.LLAMA);
		removeme.add(EntityType.PILLAGER);
		removeme.add(EntityType.WITCH);

		if (removeme.contains(e.getEntityType())) {
			e.setCancelled(true);
		}
		;
	}

	@EventHandler
	public void chunkLoad(ChunkLoadEvent e) {
		if (Main.settings.getMobSpawn()) {
			return;
		}

		List<EntityType> removeme = new ArrayList<EntityType>();
		removeme.add(EntityType.BAT);
		removeme.add(EntityType.BEE);
		removeme.add(EntityType.BLAZE);
		removeme.add(EntityType.CAT);
		removeme.add(EntityType.CAVE_SPIDER);
		removeme.add(EntityType.CHICKEN);
		removeme.add(EntityType.COD);
		removeme.add(EntityType.COW);
		removeme.add(EntityType.CREEPER);
		removeme.add(EntityType.DOLPHIN);
		removeme.add(EntityType.DONKEY);
		removeme.add(EntityType.DROWNED);
		removeme.add(EntityType.ELDER_GUARDIAN);
		removeme.add(EntityType.ENDER_DRAGON);
		removeme.add(EntityType.ENDERMAN);
		removeme.add(EntityType.ENDERMITE);
		removeme.add(EntityType.SILVERFISH);
		removeme.add(EntityType.PIG);
		removeme.add(EntityType.EVOKER);
		removeme.add(EntityType.FOX);
		removeme.add(EntityType.GHAST);
		removeme.add(EntityType.GUARDIAN);
		removeme.add(EntityType.HORSE);
		removeme.add(EntityType.SKELETON_HORSE);
		removeme.add(EntityType.SKELETON);
		removeme.add(EntityType.SPIDER);
		removeme.add(EntityType.ZOMBIE_HORSE);
		removeme.add(EntityType.VILLAGER);
		removeme.add(EntityType.ZOMBIE_VILLAGER);
		removeme.add(EntityType.SHEEP);
		removeme.add(EntityType.LLAMA);
		removeme.add(EntityType.PILLAGER);
		removeme.add(EntityType.WITCH);

		for (Entity entity : e.getChunk().getEntities()) {
			if (removeme.contains(entity.getType())) {
				entity.remove();
			}
		}

	}

}
