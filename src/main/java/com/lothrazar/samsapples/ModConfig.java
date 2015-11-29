package com.lothrazar.samsapples;

import net.minecraftforge.common.config.Configuration;

public class ModConfig
{
	static void loadConfig(Configuration cfg)
	{
		String category = Configuration.CATEGORY_GENERAL;
		cfg.load();
		

		ItemRegistry.apple_bone_enabled = cfg.get( category, "apple_bone_enabled",true).getBoolean();
		ItemRegistry.apple_emerald_enabled = cfg.get( category, "apple_emerald_enabled",true).getBoolean();
		ItemRegistry.apple_diamond_enabled = cfg.get( category, "apple_diamond_enabled",true).getBoolean();
		ItemRegistry.apple_ender_enabled = cfg.get( category, "apple_ender_enabled",true).getBoolean();
		ItemRegistry.apple_lapis_enabled = cfg.get( category, "apple_lapis_enabled",true).getBoolean();
		ItemRegistry.apple_chocolate_enabled = cfg.get( category, "apple_chocolate_enabled",true).getBoolean();
		ItemRegistry.apple_netherwart_enabled = cfg.get( category, "apple_netherwart_enabled",true).getBoolean();
		ItemRegistry.apple_prismarine_enabled = cfg.get( category, "apple_prismarine_enabled",true).getBoolean();
		ItemRegistry.apple_clownfish_enabled = cfg.get( category, "apple_clownfish_enabled",true).getBoolean();
		

		category = "recipes";
		cfg.addCustomCategoryComment(category, 
				"True means you have to fully surround the apple with 8 items, false means only a single item will craft with the red apple.");

		ItemRegistry.apple_bone_expensive = cfg.get( category, "apple_bone_expensive",true).getBoolean();
		ItemRegistry.apple_emerald_expensive = cfg.get( category, "apple_emerald_expensive",true).getBoolean();
		ItemRegistry.apple_diamond_expensive = cfg.get( category, "apple_diamond_expensive",false).getBoolean();
		ItemRegistry.apple_ender_expensive = cfg.get( category, "apple_ender_expensive",true).getBoolean();
		ItemRegistry.apple_lapis_expensive = cfg.get( category, "apple_lapis_expensive",true).getBoolean();
		ItemRegistry.apple_chocolate_expensive = cfg.get( category, "apple_chocolate_expensive",true).getBoolean();
		ItemRegistry.apple_netherwart_expensive = cfg.get( category, "apple_netherwart_expensive",true).getBoolean();
		ItemRegistry.apple_prismarine_expensive = cfg.get( category, "apple_prismarine_expensive",true).getBoolean();
		ItemRegistry.apple_clownfish_expensive = cfg.get( category, "apple_clownfish_expensive",false).getBoolean();
		
		category = "effect_tweaks";
		
		PotionRegistry.slowfallSpeed = cfg.getFloat("slowfall_speed",category, 0.41F,0.1F,1F,
				"This factor affects how much the slowfall effect slows down the entity.");

		category = "effect_ids";

		cfg.addCustomCategoryComment(category, 
				"IDs are only exposed to avoid conflicts with other mods.  Messing with these might break the game.   ");

		
		
		PotionRegistry.ender_id = cfg.get(category,"ender_id", 50).getInt();
		PotionRegistry.nav_id = cfg.get(category,"nav_id", 51).getInt();
		PotionRegistry.waterwalk_id = cfg.get(category,"waterwalk_id", 52).getInt(); 
		PotionRegistry.slowfall_id = cfg.get(category,"slowfall_id", 53).getInt();
		
		if(cfg.hasChanged()){cfg.save();}
	}
}
