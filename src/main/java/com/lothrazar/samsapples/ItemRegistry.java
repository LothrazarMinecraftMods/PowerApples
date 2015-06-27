package com.lothrazar.samsapples;

import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.fml.common.registry.GameRegistry;
   
public class ItemRegistry 
{ 
	public static ArrayList<Item> items = new ArrayList<Item>();

	public final static int I = 0; 
	public final static int II = 1;
	public final static int III = 2;
	public final static int IV = 3;
	public final static int V = 4;
	 
	public static ItemFoodAppleMagic apple_emerald;
	public static ItemFoodAppleMagic apple_diamond; 
	public static ItemFoodAppleMagic apple_ender; 
	//public static ItemFoodAppleMagic apple_frost; 
	public static ItemFoodAppleMagic apple_lapis; 
 
	public static ItemFoodAppleMagic apple_chocolate;
	//public static ItemFoodAppleMagic apple_netherwart; 
  
	//public static int timePotionShort = 90; // 1:30
	public static int timePotionLong = 8 * 60;// 8:00

	public static final int dye_cocoa = 3;
	public static final int dye_lapis = 4;

	public static void registerItems()
	{   
		//TODO: config file for each item
		ItemRegistry.apple_ender = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerLarge, false);
		ItemRegistry.apple_ender.addEffect(PotionRegistry.ender.id, timePotionLong, I);
		ItemRegistry.registerItem(ItemRegistry.apple_ender, "apple_ender");

		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_ender,new ItemStack(Items.ender_pearl));
	 
		ItemRegistry.apple_emerald = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerLarge, false);
		ItemRegistry.apple_emerald.addEffect(Potion.moveSpeed.id, timePotionLong, II);  
		ItemRegistry.apple_emerald.addEffect(Potion.absorption.id, timePotionLong, I);  
		ItemRegistry.apple_emerald.addEffect(Potion.saturation.id, timePotionLong, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_emerald, "apple_emerald");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_emerald,new ItemStack(Items.emerald));

		ItemRegistry.apple_chocolate = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerSmall, false); 
		ItemRegistry.apple_chocolate.addEffect(Potion.weakness.id, timePotionLong, I);
		ItemRegistry.apple_chocolate.addEffect(Potion.moveSpeed.id, timePotionLong, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_chocolate, "apple_chocolate");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_chocolate, new ItemStack(Items.dye, 1, dye_cocoa) );

		ItemRegistry.apple_lapis = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerSmall, false); 
		ItemRegistry.apple_lapis.addEffect(Potion.digSpeed.id, timePotionLong, II); 
		ItemRegistry.registerItem(ItemRegistry.apple_lapis, "apple_lapis");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_lapis, new ItemStack(Items.dye, 1, dye_lapis) );
		
		ItemRegistry.apple_diamond = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerSmall, false);
		ItemRegistry.registerItem(ItemRegistry.apple_diamond, "apple_diamond");
		ItemRegistry.apple_diamond.addEffect(Potion.resistance.id, timePotionLong, I); 
		ItemRegistry.apple_diamond.addEffect(Potion.healthBoost.id, timePotionLong, V); 
		ItemRegistry.apple_diamond.addEffect(Potion.saturation.id, timePotionLong, I); 
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.apple_diamond)
			, new ItemStack(Items.diamond)
			, new ItemStack(Items.apple));
		GameRegistry.addSmelting(apple_diamond, new ItemStack(Items.diamond, 1),	0);

		//TODO: something with digSlowdown (mining fatigue) as tradeoff for..?
 
	}
	
	public static void registerItem(Item item, String name)
	{ 
		 item.setUnlocalizedName(name);
		 
		 GameRegistry.registerItem(item, name);
		 
		 items.add(item);
	}
}
