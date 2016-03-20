package com.lothrazar.samsapples;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public class UtilItemRenderer
{
	@SideOnly(Side.CLIENT)
	public static void renderItemAt(ItemStack stack, int x, int y, int dim)
	{
		//first get texture from item stack
		IBakedModel iBakedModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
	 
		TextureAtlasSprite textureAtlasSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(iBakedModel.getParticleTexture().getIconName());
	 
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
       
        if(Minecraft.getMinecraft().currentScreen != null){
        	Minecraft.getMinecraft().currentScreen.drawTexturedModalRect(x,y,textureAtlasSprite, dim,dim);
        }
	}
}
