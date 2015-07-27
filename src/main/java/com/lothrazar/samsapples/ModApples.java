package com.lothrazar.samsapples;

import java.util.Random;

import org.apache.logging.log4j.Logger;   

import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.WorldRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.client.resources.model.IBakedModel;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.Entity;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.MinecraftServer;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
  
@Mod(modid = ModApples.MODID, useMetadata=true)  
public class ModApples
{
	public static final String MODID = "samsapples";
	public static final String TEXTURE_LOCATION = MODID + ":";

	@Instance(value = MODID)
	public static ModApples instance;
	@SidedProxy(clientSide="com.lothrazar.samsapples.ClientProxy", serverSide="com.lothrazar.samsapples.CommonProxy")
	public static CommonProxy proxy;   
	public static Logger logger; 

	public static CreativeTabs tabSamsContent = new CreativeTabs("tabSamsApples") 
	{ 
		@Override
		public Item getTabIconItem() 
		{ 
			return ItemRegistry.apple_chocolate;
		}
	};    

	@EventHandler
	public void onPreInit(FMLPreInitializationEvent event)
	{ 
		logger = event.getModLog();  
		String category = Configuration.CATEGORY_GENERAL;
		
		Configuration cfg = new Configuration(event.getSuggestedConfigurationFile());
		cfg.load();
		

		ItemRegistry.apple_bone_enabled = cfg.get( category, "apple_bone_enabled",true).getBoolean();
		ItemRegistry.apple_emerald_enabled = cfg.get( category, "apple_emerald_enabled",true).getBoolean();
		ItemRegistry.apple_diamond_enabled = cfg.get( category, "apple_diamond_enabled",true).getBoolean();
		ItemRegistry.apple_ender_enabled = cfg.get( category, "apple_ender_enabled",true).getBoolean();
		ItemRegistry.apple_lapis_enabled = cfg.get( category, "apple_lapis_enabled",true).getBoolean();
		ItemRegistry.apple_chocolate_enabled = cfg.get( category, "apple_chocolate_enabled",true).getBoolean();
		ItemRegistry.apple_netherwart_enabled = cfg.get( category, "apple_netherwart_enabled",true).getBoolean();
		ItemRegistry.apple_prismarine_enabled = cfg.get( category, "apple_prismarine_enabled",true).getBoolean();
		ItemRegistry.apple_clownfish_enabled = cfg.get( category, "apple_clownfish_enabled",true).getBoolean();
		

		category = "recipes";
		cfg.addCustomCategoryComment(category, 
				"True means you have to fully surround the apple with 8 items, false means only a single item will craft with the red apple.");

		ItemRegistry.apple_bone_expensive = cfg.get( category, "apple_bone_expensive",true).getBoolean();
		ItemRegistry.apple_emerald_expensive = cfg.get( category, "apple_emerald_expensive",true).getBoolean();
		ItemRegistry.apple_diamond_expensive = cfg.get( category, "apple_diamond_expensive",false).getBoolean();
		ItemRegistry.apple_ender_expensive = cfg.get( category, "apple_ender_expensive",true).getBoolean();
		ItemRegistry.apple_lapis_expensive = cfg.get( category, "apple_lapis_expensive",true).getBoolean();
		ItemRegistry.apple_chocolate_expensive = cfg.get( category, "apple_chocolate_expensive",true).getBoolean();
		ItemRegistry.apple_netherwart_expensive = cfg.get( category, "apple_netherwart_expensive",true).getBoolean();
		ItemRegistry.apple_prismarine_expensive = cfg.get( category, "apple_prismarine_expensive",true).getBoolean();
		ItemRegistry.apple_clownfish_expensive = cfg.get( category, "apple_clownfish_expensive",false).getBoolean();
		
		
		
		category = "effect_tweaks";
		
		PotionRegistry.slowfallSpeed = cfg.getFloat("slowfall_speed",category, 0.41F,0.1F,1F,
				"This factor affects how much the slowfall effect slows down the entity.");

		category = "effect_ids";

		cfg.addCustomCategoryComment(category, 
				"IDs are only exposed to avoid conflicts with other mods.  Messing with these might break the game.   ");

		
		
		PotionRegistry.ender_id = cfg.get(category,"ender_id", 50).getInt();
		PotionRegistry.nav_id = cfg.get(category,"nav_id", 51).getInt();
		PotionRegistry.waterwalk_id = cfg.get(category,"waterwalk_id", 52).getInt(); 
		PotionRegistry.slowfall_id = cfg.get(category,"slowfall_id", 53).getInt();
		
		if(cfg.hasChanged()){cfg.save();}
		
    	//network = NetworkRegistry.INSTANCE.newSimpleChannel( Reference.MODID );     	
    	
    	//network.registerMessage(MessageKeyPressed.class, MessageKeyPressed.class, MessageKeyPressed.ID, Side.SERVER);
    	//network.registerMessage(MessagePotion.class, MessagePotion.class, MessagePotion.ID, Side.CLIENT);
 	

		PotionRegistry.registerPotionEffects();
		ItemRegistry.registerItems();
 
  	    FMLCommonHandler.instance().bus().register(instance); 
  	    MinecraftForge.EVENT_BUS.register(instance); 
  	   
	}
	
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

	public static String lang(String name)
	{
		return StatCollector.translateToLocal(name);
	}

	@EventHandler
	public void onInit(FMLInitializationEvent event)
	{       
		proxy.registerRenderers();
	}
	
	@SideOnly(Side.CLIENT)
	@SubscribeEvent
	public void onRenderTextOverlay(RenderGameOverlayEvent.Text event)
	{
		EntityPlayerSP player = Minecraft.getMinecraft().thePlayer;  
		//World world = player.worldObj;//Minecraft.getMinecraft().getIntegratedServer().getEntityWorld();
	
		if(player.isPotionActive(PotionRegistry.nav_id) && 
				Minecraft.getMinecraft().gameSettings.showDebugInfo == false && 
				player.worldObj.isRemote == true)//client side only
		{
			int size = 16;
			
			int xLeft = 20;
			int xRight = Minecraft.getMinecraft().displayWidth/2 - size*2;
			int yBottom = Minecraft.getMinecraft().displayHeight/2 - size*2;

			renderItemAt(new ItemStack(Items.clock),xLeft,yBottom,size);
			renderItemAt(new ItemStack(Items.compass),xRight,yBottom,size);

			World world = null;
		
			if(MinecraftServer.getServer().worldServers.length > 0)
			{
				world = MinecraftServer.getServer().getEntityWorld();
			}
			else System.out.println("worldServers do not exist");
			
			if(isSlimeChunk(world,player.getPosition()))
			{
				renderItemAt(new ItemStack(Items.slime_ball),xLeft + size+1,yBottom,size);
			}
		}
	}	
	
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event) 
	{  
		if(event.entityLiving == null){return;}
		
		if(event.entityLiving instanceof EntityPlayer)
		{
			//SpellGhost.onPlayerUpdate(event); 
			
			//SpellRegistry.tickSpellTimer((EntityPlayer)event.entityLiving);
		}

		PotionRegistry.tickSlowfall(event);
	     
		PotionRegistry.tickWaterwalk(event);
	     
		//PotionRegistry.tickLavawalk(event);
 
		//PotionRegistry.tickFrost(event); 
	}
	
	private boolean isSlimeChunk(World world, BlockPos pos)
	{
		if(world == null){return false;}//shouldnt happen anymore since i check worldServers.length above now
		long seed =  world.getSeed();
		if(seed == 0){return false;}//on a server where seed is hidden
		
		Chunk in = world.getChunkFromBlockCoords(pos);

		//formula source : http://minecraft.gamepedia.com/Slime
		Random rnd = new Random(seed +
		        (long) (in.xPosition * in.xPosition * 0x4c1906) +
		        (long) (in.xPosition * 0x5ac0db) + 
		        (long) (in.zPosition * in.zPosition) * 0x4307a7L +
		        (long) (in.zPosition * 0x5f24f) ^ 0x3ad8025f);
		
		boolean isSlimeChunk = (rnd.nextInt(10) == 0);
    
		return isSlimeChunk;
	}
	@SideOnly(Side.CLIENT)
	private static void renderItemAt(ItemStack stack, int x, int y, int dim)
	{
		@SuppressWarnings("deprecation")
		IBakedModel iBakedModel = Minecraft.getMinecraft().getRenderItem().getItemModelMesher().getItemModel(stack);
		@SuppressWarnings("deprecation")
		TextureAtlasSprite textureAtlasSprite = Minecraft.getMinecraft().getTextureMapBlocks().getAtlasSprite(iBakedModel.getTexture().getIconName());
		
		renderTexture( textureAtlasSprite, x, y, dim);
	}
	@SideOnly(Side.CLIENT)
	public static void renderTexture( TextureAtlasSprite textureAtlasSprite , int x, int y, int dim)
	{	
		//special thanks to http://www.minecraftforge.net/forum/index.php?topic=26613.0
		
        Minecraft.getMinecraft().getTextureManager().bindTexture(TextureMap.locationBlocksTexture);
		Tessellator tessellator = Tessellator.getInstance();

		int height = dim, width = dim;
		WorldRenderer worldrenderer = tessellator.getWorldRenderer();
		worldrenderer.startDrawingQuads();
		worldrenderer.addVertexWithUV((double)(x),          (double)(y + height),  0.0, (double)textureAtlasSprite.getMinU(), (double)textureAtlasSprite.getMaxV());
		worldrenderer.addVertexWithUV((double)(x + width),  (double)(y + height),  0.0, (double)textureAtlasSprite.getMaxU(), (double)textureAtlasSprite.getMaxV());
		worldrenderer.addVertexWithUV((double)(x + width),  (double)(y),           0.0, (double)textureAtlasSprite.getMaxU(), (double)textureAtlasSprite.getMinV());
		worldrenderer.addVertexWithUV((double)(x),          (double)(y),           0.0, (double)textureAtlasSprite.getMinU(), (double)textureAtlasSprite.getMinV());
		tessellator.draw();
	}
}
