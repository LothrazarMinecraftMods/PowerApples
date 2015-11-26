package com.lothrazar.samsapples;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;   

import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionRegistry 
{ 
	//public static Potion tired;//http://www.minecraftforge.net/wiki/Potion_Tutorial
	public static Potion ender;
	//public static Potion nav;
	public static Potion waterwalk;
	public static Potion slowfall; 
	//public static Potion lavawalk;
	//public static Potion frost;
	
	public final static int I = 0; 
	public final static int II = 1;
	public final static int III = 2;
	public final static int IV = 3;
	public final static int V = 4;
	public static int ender_id = 50;
	//public static int nav_id = 51;
	public static int waterwalk_id = 52;
	public static int slowfall_id = 53;
//	public static int lavawalk_id = 54;
//	public static int frost_id = 55;
	
	public static float slowfallSpeed = 0.41f;

	
	public static void registerPotionEffects()
	{ 
		initPotionTypesReflection();
	     
	    registerNewPotionEffects(); 
	}
 
	private static void registerNewPotionEffects() 
	{  
		//http://www.minecraftforge.net/forum/index.php?topic=11024.0
		//???http://www.minecraftforge.net/forum/index.php?topic=12358.0
		//
		PotionRegistry.ender = (new PotionCustom(ender_id, new ResourceLocation("ender"), false, 0, new ItemStack(Items.ender_pearl))).setPotionName("potion.ender");	  
		//PotionRegistry.nav = (new PotionCustom(nav_id, new ResourceLocation("nav"), false, 0, new ItemStack(Items.map))).setPotionName("potion.nav");	  
		PotionRegistry.waterwalk = (new PotionCustom(waterwalk_id, new ResourceLocation("waterwalk") , false, 0, new ItemStack(Items.quartz))).setPotionName("potion.waterwalk");
		PotionRegistry.slowfall = (new PotionCustom(slowfall_id,   new ResourceLocation("slowfall"), false, 0, new ItemStack(Items.feather))).setPotionName("potion.slowfall");
	 
	}

	private static void initPotionTypesReflection() 
	{
		//because it is final, and because forge does not natively do this, we must change it this way
		Potion[] potionTypes = null;  //  public static final Potion[] potionTypes = new Potion[32];
	    for (Field f : Potion.class.getDeclaredFields()) 
	    {
	        f.setAccessible(true);
	        try 
	        { 
	            if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) 
	            {
	                Field modfield = Field.class.getDeclaredField("modifiers");
	                modfield.setAccessible(true);
	                modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

	                potionTypes = (Potion[])f.get(null);
	                final Potion[] newPotionTypes = new Potion[256];
	               
	                System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
	                f.set(null, newPotionTypes);
	                
	            }
	        }
	        catch (Exception e) 
	        {
	            System.err.println("Severe error, please report this to the mod author:");
	            System.err.println(e);
	        }
	    }
	}
	
	

/*
	public static void tickFrost(LivingUpdateEvent event) 
	{ 
		if(event.entityLiving.isPotionActive(PotionRegistry.frost)) 
	    { 
			World world = event.entityLiving.worldObj;
			BlockPos pos = event.entityLiving.getPosition();

			if(world.rand.nextDouble() < 0.5)
				doPotionParticle(world,event.entityLiving,EnumParticleTypes.SNOWBALL);

			if(world.rand.nextDouble() < 0.3 && 
				//world.getBlockState(pos).getBlock().isReplaceable(world, pos) &&
				world.getBlockState(pos.down()).getBlock() != Blocks.snow_layer && 
				//world.getBlockState(pos).getBlock().isReplaceable(world, pos.down()) == false &&//dont place above torches/snow/grass
				
				world.isAirBlock(pos.down()) == false)//dont place above air
			{
				world.setBlockState( pos, Blocks.snow_layer.getDefaultState());
			}
	    } 
	}*/
	
/*
	public static void tickLavawalk(LivingUpdateEvent event) 
	{
		if(event.entityLiving.isPotionActive(PotionRegistry.lavawalk)) 
	    {
			tickLiquidWalk(event,Blocks.lava);
	    }
	}*/

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
    	 EntityLivingBase e = event.entityLiving;
    	 
    	 if( (world.getBlock((int)e.posX, (int)e.posY-2,(int)e.posZ) == liquid || 
    			 world.getBlock((int)e.posX, (int)e.posY-1,(int)e.posZ) == liquid) && 
    			 //world.isAirBlock((int)e.posX, (int)e.posY,(int)e.posZ) && 
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
