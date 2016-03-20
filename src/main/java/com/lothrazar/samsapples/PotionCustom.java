package com.lothrazar.samsapples;

import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class PotionCustom extends Potion
{ 
	ResourceLocation location;
	protected PotionCustom(int potionID, ResourceLocation loc,	boolean badEffect, int potionColor) 
	{
		super(false,potionColor); 
		location = loc;
		Potion.potionRegistry.register(potionID, location, this);
	} 
	/*
	@Override
	public Potion setIconIndex(int par1, int par2) 
    {
        super.setIconIndex(par1, par2);
        return this;
    }
	*/
    @SideOnly(Side.CLIENT)
    public boolean hasStatusIcon()
    {
        return true;//false to block it from looking for one of the vanilla textures in the default way.
    }
    
    
	@Override
    @SideOnly(Side.CLIENT)
    public void renderInventoryEffect(int x, int y, PotionEffect effect, net.minecraft.client.Minecraft mc) 
	{ 
		UtilItemRenderer.renderItemAt(location,x+6,y+6);
		
		super.renderInventoryEffect(x, y, effect, mc);
	}
	
}
