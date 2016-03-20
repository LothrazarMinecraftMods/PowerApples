package com.lothrazar.samsapples;

import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionRegistry 
{ 
	//public static Potion tired;//http://www.minecraftforge.net/wiki/Potion_Tutorial
	public static Potion ender; 
	public static Potion waterwalk;
	public static Potion slowfall;  
	
	public final static int I = 0; 
	public final static int II = 1;
	public final static int III = 2;
	public final static int IV = 3;
	public final static int V = 4;
	public static int ender_id = 50; 
	public static int waterwalk_id = 52;
	public static int slowfall_id = 53; 
	
	public static float slowfallSpeed = 0.41f;
	
	public static int getPotionID(PotionEffect pot){
		return Potion.potionRegistry.getIDForObject(pot.getPotion());
	}
	public static int getPotionID(Potion pot){
		return Potion.potionRegistry.getIDForObject(pot);
	}
	
	public static void registerPotionEffects()
	{ 
	    registerNewPotionEffects(); 
	}
 
	private static void registerNewPotionEffects() 
	{  
		//http://www.minecraftforge.net/forum/index.php?topic=11024.0
		//???http://www.minecraftforge.net/forum/index.php?topic=12358.0
		//resource locations still dont work >< TODO: use them intead of itemstack override
		PotionRegistry.ender = (new PotionCustom(ender_id, new ResourceLocation(ModApples.MODID,"textures/items/apple_ender.png"), false, 0)).setPotionName("potion.ender");	  
		//PotionRegistry.nav = (new PotionCustom(nav_id, new ResourceLocation("minecraft","textures/items/map_empty.png"), false, 0)).setPotionName("potion.nav");	  
		PotionRegistry.waterwalk = (new PotionCustom(waterwalk_id, new ResourceLocation("minecraft","textures/items/prismarine_shard.png") , false, 0)).setPotionName("potion.waterwalk");
		PotionRegistry.slowfall = (new PotionCustom(slowfall_id,   new ResourceLocation("minecraft","textures/items/feather.png"), false, 0)).setPotionName("potion.slowfall");
	 
	} 
 
	public static void tickWaterwalk(LivingUpdateEvent event) 
	{
		if(event.entityLiving.isPotionActive(PotionRegistry.waterwalk)) 
	    {
			tickLiquidWalk(event,Blocks.water);
	    }
	}
 
	private static void tickLiquidWalk(LivingUpdateEvent event, Block liquid)
	{
    	 World world = event.entityLiving.worldObj;
    	 
    	 if(world.getBlockState(event.entityLiving.getPosition().down()).getBlock() == liquid && 
    			 world.isAirBlock(event.entityLiving.getPosition()) && 
    			 event.entityLiving.motionY < 0)
    	 { 
    		 if(event.entityLiving instanceof EntityPlayer)  //now wait here, since if we are a sneaking player we cancel it
    		 {
    			 EntityPlayer p = (EntityPlayer)event.entityLiving;
    			 if(p.isSneaking())
    				 return;//let them slip down into it
    		 }
    		 
    		 event.entityLiving.motionY  = 0;//stop falling
    		 event.entityLiving.onGround = true; //act as if on solid ground
    		 event.entityLiving.setAIMoveSpeed(0.1F);//walking and not sprinting is this speed
    	 }  
	}
	
	public static void tickSlowfall(LivingUpdateEvent event) 
	{
		 if(event.entityLiving.isPotionActive(PotionRegistry.slowfall)) 
	     { 
			 if(event.entityLiving instanceof EntityPlayer)  //now wait here, since if we are a sneaking player we cancel
			 {
    			 EntityPlayer p = (EntityPlayer)event.entityLiving;
    			 if(p.isSneaking())
    			 {
    				 return;//so fall normally for now
    			 }
    		 }
			 //else: so we are either a non-sneaking player, or a non player entity
			  
	    	 //a normal fall seems to go up to 0, -1.2, -1.4, -1.6, then flattens out at -0.078 
	    	 if(event.entityLiving.motionY < 0)
	    	 { 
				event.entityLiving.motionY *= slowfallSpeed;
				  
				event.entityLiving.fallDistance = 0f; //for no fall damage
	    	 } 
	     }
	}	 
}
