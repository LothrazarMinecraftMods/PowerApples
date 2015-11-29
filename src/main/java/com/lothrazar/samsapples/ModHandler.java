package com.lothrazar.samsapples;

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class ModHandler
{
	@SubscribeEvent
	public void onEnderTeleportEvent(EnderTeleportEvent event)
	{  
		if(event.entityLiving != null && event.entityLiving.isPotionActive(PotionRegistry.ender))
		{
			event.attackDamage = 0;  //starts at exactly  5.0 which is 2.5hearts

			if(event.entityLiving.worldObj.isRemote == false)//do not spawn a second 'ghost' one on client side
			{
				EntityItem entityItem = new EntityItem(event.entityLiving.worldObj, event.targetX,event.targetY,event.targetZ, new ItemStack(Items.ender_pearl)); 
				event.entityLiving.worldObj.spawnEntityInWorld(entityItem);
			}
		}
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTextOverlay(RenderGameOverlayEvent.Text event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;  
	
		if(player.isPotionActive(PotionRegistry.nav_id) && 
				Minecraft.getMinecraft().gameSettings.showDebugInfo == false && 
				player.worldObj.isRemote == true)//client side only
		{
			int size = 16;
			
			int xLeft = 20;
			int xRight = Minecraft.getMinecraft().displayWidth/2 - size*2;
			int yBottom = Minecraft.getMinecraft().displayHeight/2 - size*2;

			UtilItemRenderer.renderItemAt(new ItemStack(Items.clock),xLeft,yBottom,size);
			UtilItemRenderer.renderItemAt(new ItemStack(Items.compass),xRight,yBottom,size);

			World world = null;
		
			if(MinecraftServer.getServer().worldServers.length > 0)
			{
				world = MinecraftServer.getServer().getEntityWorld();
			}
			else System.out.println("worldServers do not exist");
			
			if(UtilSlimeChunk.isSlimeChunk(world,player.getPosition()))
			{
				UtilItemRenderer.renderItemAt(new ItemStack(Items.slime_ball),xLeft + size+1,yBottom,size);
			}
		}
	}	
	
	@SubscribeEvent
	public void entityInteractEvent(EntityInteractEvent event)
    {
		if(event.target == null || event.entityPlayer == null){return;}//dont think this ever happens
		
		if(event.entityPlayer.getHeldItem() != null && 
				event.entityPlayer.getHeldItem().getItem() instanceof ItemFoodAppleMagic
				&& event.target instanceof EntityLivingBase)
		{
			ItemFoodAppleMagic item = (ItemFoodAppleMagic)event.entityPlayer.getHeldItem().getItem();
			
			EntityLivingBase mob = (EntityLivingBase)event.target;
			
			item.addAllEffects(event.entity.worldObj, mob);
		
			if (event.entityPlayer.capabilities.isCreativeMode == false)
	        {
				event.entityPlayer.inventory.decrStackSize(event.entityPlayer.inventory.currentItem, 1);
	        }
	 
			event.entityPlayer.worldObj.playSoundAtEntity(mob, "random.eat", 1.0F, 1.0F);
			//event.entityLiving .setea
			mob.setEating(true); //makes horse animate and bend down to eat
		}
    }
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) 
	{  
		if(event.entityLiving == null){return;}
	
		PotionRegistry.tickSlowfall(event);
	     
		PotionRegistry.tickWaterwalk(event);
	}
}