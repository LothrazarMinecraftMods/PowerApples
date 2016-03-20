package com.lothrazar.samsapples;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.init.Items;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundCategory;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ModHandler{

	@SubscribeEvent
	public void onEnderTeleportEvent(EnderTeleportEvent event){

		if(event.entityLiving != null && event.entityLiving.isPotionActive(PotionRegistry.ender)){
			event.attackDamage = 0; // starts at exactly 5.0 which is 2.5hearts

			if(event.entityLiving.worldObj.isRemote == false)// do not spawn a second 'ghost' one
																// on client side
			{
				EntityItem entityItem = new EntityItem(event.entityLiving.worldObj, event.targetX, event.targetY, event.targetZ, new ItemStack(Items.ender_pearl));
				event.entityLiving.worldObj.spawnEntityInWorld(entityItem);
			}
		}
	}
 
	@SubscribeEvent
	public void entityInteractEvent(EntityInteractEvent event){

		if(event.getTarget() == null || event.entityPlayer == null){
			return;
		}// dont think this ever happens

		if(event.entityPlayer.getHeldItemMainhand() != null && event.entityPlayer.getHeldItemMainhand().getItem() instanceof ItemFoodAppleMagic && event.getTarget() instanceof EntityLivingBase){
			ItemFoodAppleMagic item = (ItemFoodAppleMagic) event.entityPlayer.getHeldItemMainhand().getItem();

			EntityLivingBase mob = (EntityLivingBase) event.getTarget();

			item.addAllEffects(event.entity.worldObj, mob);

			if(event.entityPlayer.capabilities.isCreativeMode == false){
				event.entityPlayer.inventory.decrStackSize(event.entityPlayer.inventory.currentItem, 1);
			}

			event.entityPlayer.worldObj.playSound(mob.getPosition().getX(), mob.getPosition().getY(), mob.getPosition().getZ(), SoundEvents.entity_horse_eat, SoundCategory.PLAYERS, 1.0F, 1.0F, false);
			// event.entityLiving .setea

			// mob.setEating(true); //makes horse animate and bend down to eat
		}
	}

	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event){

		if(event.entityLiving == null){
			return;
		}

		PotionRegistry.tickSlowfall(event);

		PotionRegistry.tickWaterwalk(event);
	}
}
