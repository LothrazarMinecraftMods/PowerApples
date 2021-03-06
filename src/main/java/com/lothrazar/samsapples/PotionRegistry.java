package com.lothrazar.samsapples;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionRegistry {
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
  public static void registerPotionEffects() {
    registerNewPotionEffects();
  }
  private static void registerNewPotionEffects() {
    //http://www.minecraftforge.net/forum/index.php?topic=11024.0
    //???http://www.minecraftforge.net/forum/index.php?topic=12358.0
    //resource locations still dont work >< TODO: use them intead of itemstack override
    PotionRegistry.ender = (new PotionCustom(ender_id, new ResourceLocation(ModApples.MODID, "textures/items/apple_ender.png"), false, 0)).setPotionName("potion.ender");
    //PotionRegistry.nav = (new PotionCustom(nav_id, new ResourceLocation("minecraft","textures/items/map_empty.png"), false, 0)).setPotionName("potion.nav");	  
    PotionRegistry.waterwalk = (new PotionCustom(waterwalk_id, new ResourceLocation("minecraft", "textures/items/prismarine_shard.png"), false, 0)).setPotionName("potion.waterwalk");
    PotionRegistry.slowfall = (new PotionCustom(slowfall_id, new ResourceLocation("minecraft", "textures/items/feather.png"), false, 0)).setPotionName("potion.slowfall");
  }
  public static void tickWaterwalk(EntityLivingBase entityLiving) {
    if (entityLiving.isPotionActive(PotionRegistry.waterwalk)) {
      tickLiquidWalk(entityLiving, Blocks.WATER);
    }
  }
  private static void tickLiquidWalk(EntityLivingBase entityLiving, Block liquid) {
    World world = entityLiving.worldObj;
    if (world.getBlockState(entityLiving.getPosition().down()).getBlock() == liquid &&
        world.isAirBlock(entityLiving.getPosition()) &&
        entityLiving.motionY < 0) {
      if (entityLiving instanceof EntityPlayer) //now wait here, since if we are a sneaking player we cancel it
      {
        EntityPlayer p = (EntityPlayer) entityLiving;
        if (p.isSneaking())
          return;//let them slip down into it
      }
      entityLiving.motionY = 0;//stop falling
      entityLiving.onGround = true; //act as if on solid ground
      entityLiving.setAIMoveSpeed(0.1F);//walking and not sprinting is this speed
    }
  }
  public static void tickSlowfall(EntityLivingBase entityLiving) {
    if (entityLiving.isPotionActive(PotionRegistry.slowfall)) {
      if (entityLiving instanceof EntityPlayer) //now wait here, since if we are a sneaking player we cancel
      {
        EntityPlayer p = (EntityPlayer) entityLiving;
        if (p.isSneaking()) { return;//so fall normally for now
        }
      }
      //else: so we are either a non-sneaking player, or a non player entity
      //a normal fall seems to go up to 0, -1.2, -1.4, -1.6, then flattens out at -0.078 
      if (entityLiving.motionY < 0) {
        entityLiving.motionY *= slowfallSpeed;
        entityLiving.fallDistance = 0f; //for no fall damage
      }
    }
  }
}
