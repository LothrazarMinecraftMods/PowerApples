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
	public static ItemFoodAppleHeart apple_diamond; 
	public static ItemFoodAppleMagic apple_ender; 
	public static ItemFoodAppleMagic apple_frost; 
 
	public static ItemFoodAppleMagic apple_chocolate;
	public static ItemFoodAppleMagic apple_netherwart; 
  
	public static int timePotionShort = 90; // 1:30
	public static int timePotionLong = 8 * 60;// 8:00

	public static final int dye_cocoa = 3;

	public static void registerItems()
	{   
		ItemRegistry.apple_ender = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerLarge, false);
		ItemRegistry.apple_ender.addEffect(PotionRegistry.ender.id, timePotionLong, I);
		ItemRegistry.registerItem(ItemRegistry.apple_ender, "apple_ender");

		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_ender,new ItemStack(Items.ender_eye));
	 
		ItemRegistry.apple_emerald = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerLarge, false);
		ItemRegistry.apple_emerald.addEffect(Potion.digSpeed.id, timePotionLong, II);
		ItemRegistry.apple_emerald.addEffect(Potion.moveSpeed.id, timePotionLong, I);  
		ItemRegistry.apple_emerald.addEffect(Potion.absorption.id, timePotionLong, I);  
		ItemRegistry.apple_emerald.addEffect(Potion.resistance.id, timePotionLong, I); 
		ItemRegistry.apple_emerald.addEffect(Potion.jump.id, timePotionLong, I); 
	//	ItemRegistry.apple_emerald.addEffect(PotionRegistry.slowfall.id, timePotionLong, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_emerald, "apple_emerald");
		
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_emerald,new ItemStack(Items.emerald));

		
	 
		ItemRegistry.apple_chocolate = new ItemFoodAppleMagic(ItemFoodAppleMagic.hungerSmall, false); 
		ItemRegistry.apple_chocolate.addEffect(Potion.weakness.id, timePotionLong, I);
		ItemRegistry.apple_chocolate.addEffect(Potion.moveSpeed.id, timePotionLong, I);
		ItemRegistry.apple_chocolate.addEffect(Potion.digSpeed.id, timePotionLong, I); 
		ItemRegistry.registerItem(ItemRegistry.apple_chocolate, "apple_chocolate");
		ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_chocolate, new ItemStack(Items.dye, 1, dye_cocoa) );
	
		  
		ItemRegistry.apple_diamond = new ItemFoodAppleHeart();
		ItemRegistry.registerItem(ItemRegistry.apple_diamond, "apple_diamond");
		ItemFoodAppleHeart.addRecipe(ItemRegistry.apple_diamond);

		//TODO:remove diamond apple or at least make it to resistance isntaed of hearts
		//hearts are buggy with nether portals//save quit.
		
		
		//TODO: ender apple back in since spells were removed
		//1- on use , refund
		//2- no damage
		
	 
 
	}
	
	public static void registerItem(Item item, String name)
	{ 
		 item.setUnlocalizedName(name);
		 
		 GameRegistry.registerItem(item, name);
		 
		 items.add(item);
	}
}
