package com.lothrazar.samsapples;

import java.util.ArrayList;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.common.registry.GameRegistry;
   
public class ItemRegistry 
{ 
	public static ArrayList<Item> items = new ArrayList<Item>();
	public static final String TEXTURE_LOCATION = ModApples.MODID + ":";

	public final static int I = 0; 
	public final static int II = 1;
	public final static int III = 2;
	public final static int IV = 3;
	public final static int V = 4;

	public static int hunger = 4; 
	public static int time = 8 * 60;// 8:00

	public static boolean apple_emerald_enabled;
	public static boolean apple_diamond_enabled; 
	public static boolean apple_ender_enabled; 
	public static boolean apple_bone_enabled; 
	public static boolean apple_lapis_enabled; 
	public static boolean apple_chocolate_enabled;
	public static boolean apple_netherwart_enabled; 
	public static boolean apple_prismarine_enabled;
	public static boolean apple_clownfish_enabled;

	public static boolean apple_emerald_expensive;
	public static boolean apple_diamond_expensive; 
	public static boolean apple_ender_expensive; 
	public static boolean apple_bone_expensive; 
	public static boolean apple_lapis_expensive; 
	public static boolean apple_chocolate_expensive;
	public static boolean apple_netherwart_expensive; 
	public static boolean apple_prismarine_expensive;
	public static boolean apple_clownfish_expensive;
	
	public static ItemFoodAppleMagic apple_emerald;
	public static ItemFoodAppleMagic apple_diamond; 
	public static ItemFoodAppleMagic apple_ender; 
	public static ItemFoodAppleMagic apple_bone; 
	public static ItemFoodAppleMagic apple_lapis; 
	public static ItemFoodAppleMagic apple_chocolate;
	public static ItemFoodAppleMagic apple_netherwart; 
	public static ItemFoodAppleMagic apple_prismarine;
	public static ItemFoodAppleMagic apple_slowfall;
	
	private static final int clownfish = 2;

	public static final int dye_cocoa = 3;
	public static final int dye_lapis = 4;

	public static void registerItems()
	{   
		if(apple_ender_enabled)
		{
			ItemRegistry.apple_ender = new ItemFoodAppleMagic(hunger, false);
		 
			ItemRegistry.apple_ender.addEffect(PotionRegistry.getPotionID(PotionRegistry.ender), time, I);
			ItemRegistry.registerItem(ItemRegistry.apple_ender, "apple_ender");
	
			ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_ender,new ItemStack(Items.ender_pearl),apple_ender_expensive);
		}
		
		if(apple_emerald_enabled)
		{ 
			ItemRegistry.apple_emerald = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.apple_emerald.addEffect(PotionRegistry.getPotionID(MobEffects.moveSpeed), time, II);  
			ItemRegistry.apple_emerald.addEffect(PotionRegistry.getPotionID(MobEffects.absorption), time, I);  
			ItemRegistry.apple_emerald.addEffect(PotionRegistry.getPotionID(MobEffects.saturation), time, I); 
			ItemRegistry.registerItem(ItemRegistry.apple_emerald, "apple_emerald");
			ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_emerald,new ItemStack(Items.emerald),apple_emerald_expensive);
		}
		
		if(apple_chocolate_enabled)
		{
			ItemRegistry.apple_chocolate = new ItemFoodAppleMagic(hunger, false); 
			ItemRegistry.apple_chocolate.addEffect(PotionRegistry.getPotionID(MobEffects.weakness), time, I);
			ItemRegistry.apple_chocolate.addEffect(PotionRegistry.getPotionID(MobEffects.moveSpeed), time, I); 
			ItemRegistry.registerItem(ItemRegistry.apple_chocolate, "apple_chocolate");
			ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_chocolate, new ItemStack(Items.dye, 1, dye_cocoa),apple_chocolate_expensive );
		}
		
		if(apple_lapis_enabled)
		{
			ItemRegistry.apple_lapis = new ItemFoodAppleMagic(hunger, false); 
			ItemRegistry.apple_lapis.addEffect(PotionRegistry.getPotionID(MobEffects.digSpeed), time, II); //Haste
			ItemRegistry.registerItem(ItemRegistry.apple_lapis, "apple_lapis");
			ItemFoodAppleMagic.addRecipe(ItemRegistry.apple_lapis, new ItemStack(Items.dye, 1, dye_lapis),apple_lapis_expensive );
		}
		if(apple_diamond_enabled)
		{
			ItemRegistry.apple_diamond = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.registerItem(ItemRegistry.apple_diamond, "apple_diamond");
			ItemRegistry.apple_diamond.addEffect(PotionRegistry.getPotionID(MobEffects.resistance), time, I); 
			ItemRegistry.apple_diamond.addEffect(PotionRegistry.getPotionID(MobEffects.healthBoost), time, V); 
			ItemRegistry.apple_diamond.addEffect(PotionRegistry.getPotionID(MobEffects.saturation), time, I); 	
			
			ItemFoodAppleMagic.addRecipe(apple_diamond,new ItemStack(Items.diamond),apple_diamond_expensive);
		 
		}
		
		if(apple_bone_enabled)
		{
			apple_bone = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.registerItem(ItemRegistry.apple_bone, "apple_bone");
			ItemRegistry.apple_bone.addEffect(PotionRegistry.getPotionID(PotionRegistry.nav), time, I); 
			ItemFoodAppleMagic.addRecipe(apple_bone,new ItemStack(Items.bone),apple_bone_expensive);
		}
		
		if(apple_netherwart_enabled)
		{
			apple_netherwart = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.registerItem(ItemRegistry.apple_netherwart, "apple_netherwart");
			ItemRegistry.apple_netherwart.addEffect(PotionRegistry.getPotionID(MobEffects.digSlowdown), time, I); //Mining Fatigue
			ItemRegistry.apple_netherwart.addEffect(PotionRegistry.getPotionID(MobEffects.waterBreathing), time, I);  
			ItemFoodAppleMagic.addRecipe(apple_netherwart,new ItemStack(Items.nether_wart),apple_netherwart_expensive);
		}
		
		if(apple_prismarine_enabled)
		{
			apple_prismarine = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.registerItem(ItemRegistry.apple_prismarine, "apple_prismarine");
			ItemRegistry.apple_prismarine.addEffect(PotionRegistry.getPotionID(PotionRegistry.waterwalk), time, I); 
			ItemFoodAppleMagic.addRecipe(apple_prismarine,new ItemStack(Items.prismarine_shard),apple_prismarine_expensive);
		}

		if(apple_clownfish_enabled)
		{
			apple_slowfall = new ItemFoodAppleMagic(hunger, false);
			ItemRegistry.registerItem(ItemRegistry.apple_slowfall, "apple_slowfall");
			ItemRegistry.apple_slowfall.addEffect(PotionRegistry.getPotionID(PotionRegistry.slowfall), time, I); 
			ItemFoodAppleMagic.addRecipe(apple_slowfall,new ItemStack(Items.fish,1,clownfish),apple_clownfish_expensive);
			 
		}
	}
	
	public static void registerItem(Item item, String name)
	{ 
		 item.setUnlocalizedName(name);
		 
		 GameRegistry.registerItem(item, name);
		 
		 items.add(item);
	}
}
