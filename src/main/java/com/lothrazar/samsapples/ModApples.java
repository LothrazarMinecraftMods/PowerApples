package com.lothrazar.samsapples;
import org.apache.logging.log4j.Logger;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.SidedProxy;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ModApples.MODID, useMetadata = true, updateJSON = "https://raw.githubusercontent.com/LothrazarMinecraftMods/PowerApples/master/update.json")
public class ModApples {
  public static final String MODID = "samsapples";
  @Instance(value = MODID)
  public static ModApples instance;
  @SidedProxy(clientSide = "com.lothrazar.samsapples.ClientProxy", serverSide = "com.lothrazar.samsapples.CommonProxy")
  public static CommonProxy proxy;
  public static Logger logger;
  public static CreativeTabs tabSamsContent = new CreativeTabs("tabSamsApples") {
    @Override
    public Item getTabIconItem() {
      return ItemRegistry.apple_chocolate;
    }
  };
  @EventHandler
  public void onPreInit(FMLPreInitializationEvent event) {
    logger = event.getModLog();
    ModConfig.loadConfig(new Configuration(event.getSuggestedConfigurationFile()));
    PotionRegistry.registerPotionEffects();
    ItemRegistry.registerItems();
    MinecraftForge.EVENT_BUS.register(new ModHandler());
  }
  @EventHandler
  public void onInit(FMLInitializationEvent event) {
    proxy.registerRenderers();
  }
}
