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

	public static int hunger = 4; 
	public static int time = 8 * 60;// 8:00
   
	 
	public static ItemFoodAppleMagic apple_emerald;
	public static ItemFoodAppleMagic apple_diamond; 
	public static ItemFoodAppleMagic apple_ender; 
	public static ItemFoodAppleMagic apple_frost; 
	public static ItemFoodAppleMagic apple_lapis; 
	public static ItemFoodAppleMagic apple_chocolate;
	public static ItemFoodAppleMagic apple_netherwart; 
  

	public static final int dye_cocoa = 3;
	public static final int dye_lapis = 4;

	public static void registerItems()
	{   
		//TODO: config file for each item
		ItemRegistry.apple_ender = new ItemFoodAppleMagic(hunger, false);
		ItemRegistry.apple_ender.addEffect(PotionRegistry.ender.id, time, I);
		ItemRegistry.registerItem(ItemRegistry.apple_ender, "apple_ender");

		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_ender,new ItemStack(Items.ender_pearl));
	 
		ItemRegistry.apple_emerald = new ItemFoodAppleMagic(hunger, false);
		ItemRegistry.apple_emerald.addEffect(Potion.moveSpeed.id, time, II);  
		ItemRegistry.apple_emerald.addEffect(Potion.absorption.id, time, I);  
		ItemRegistry.apple_emerald.addEffect(Potion.saturation.id, time, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_emerald, "apple_emerald");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_emerald,new ItemStack(Items.emerald));

		ItemRegistry.apple_chocolate = new ItemFoodAppleMagic(hunger, false); 
		ItemRegistry.apple_chocolate.addEffect(Potion.weakness.id, time, I);
		ItemRegistry.apple_chocolate.addEffect(Potion.moveSpeed.id, time, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_chocolate, "apple_chocolate");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_chocolate, new ItemStack(Items.dye, 1, dye_cocoa) );

		ItemRegistry.apple_lapis = new ItemFoodAppleMagic(hunger, false); 
		ItemRegistry.apple_lapis.addEffect(Potion.digSpeed.id, time, II); 
		ItemRegistry.registerItem(ItemRegistry.apple_lapis, "apple_lapis");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_lapis, new ItemStack(Items.dye, 1, dye_lapis) );
		
		ItemRegistry.apple_diamond = new ItemFoodAppleMagic(hunger, false);
		ItemRegistry.registerItem(ItemRegistry.apple_diamond, "apple_diamond");
		ItemRegistry.apple_diamond.addEffect(Potion.resistance.id, time, I); 
		ItemRegistry.apple_diamond.addEffect(Potion.healthBoost.id, time, V); 
		ItemRegistry.apple_diamond.addEffect(Potion.saturation.id, time, I); 
		GameRegistry.addShapelessRecipe(new ItemStack(ItemRegistry.apple_diamond)
			, new ItemStack(Items.diamond)
			, new ItemStack(Items.apple));
		GameRegistry.addSmelting(apple_diamond, new ItemStack(Items.diamond, 1),	0);

		apple_frost = new ItemFoodAppleMagic(hunger, false);
		ItemRegistry.registerItem(ItemRegistry.apple_frost, "apple_frost");
		ItemRegistry.apple_frost.addEffect(PotionRegistry.nav.id, time, I); 
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_frost,new ItemStack(Items.bone));
		
		apple_netherwart = new ItemFoodAppleMagic(hunger, false);
		ItemRegistry.registerItem(ItemRegistry.apple_netherwart, "apple_netherwart");
		ItemRegistry.apple_netherwart.addEffect(Potion.digSlowdown.id, time, I); 
		ItemRegistry.apple_netherwart.addEffect(Potion.waterBreathing.id, time, I);  
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_netherwart,new ItemStack(Items.nether_wart));
		
		//TODO: cant think of anything for the purple apple...
	}
	
	public static void registerItem(Item item, String name)
	{ 
		 item.setUnlocalizedName(name);
		 
		 GameRegistry.registerItem(item, name);
		 
		 items.add(item);
	}
}
