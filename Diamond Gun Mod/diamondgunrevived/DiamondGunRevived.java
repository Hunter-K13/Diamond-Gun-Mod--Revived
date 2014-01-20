package diamondgunrevived;

import net.minecraft.creativetab.CreativeTabs;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkMod;
import diamondgunrevived.configHandler.ConfigHandler;
import diamondgunrevived.entities.Entities;
import diamondgunrevived.items.Items;
import diamondgunrevived.network.PacketHandler;
import diamondgunrevived.proxies.CommonProxy;
import diamondgunrevived.tabs.CreativeTabDiamondGun;

@NetworkMod(channels = {DiamondGunRevivedInfo.CHANNEL}, clientSideRequired = true, serverSideRequired = false, packetHandler = PacketHandler.class)
@Mod(modid = DiamondGunRevivedInfo.ID, name = DiamondGunRevivedInfo.NAME, version = DiamondGunRevivedInfo.VERSION)
public class DiamondGunRevived {
	
	public static CreativeTabDiamondGun  gunTab = new CreativeTabDiamondGun(CreativeTabs.getNextID(), "guntab");
	
	@Instance(DiamondGunRevivedInfo.ID)
	public static DiamondGunRevived instance;
	
	@SidedProxy(clientSide = "diamondgunrevived.proxies.ClientProxy", serverSide = "diamondgunrevived.proxies.CommonProxy")
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {	
		ConfigHandler.init(event.getSuggestedConfigurationFile());
		Items.init();
		proxy.initSounds();
		proxy.initRenderers();
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
		Items.addNames();
		Items.registerRecipes();
		Entities.init();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {
		
	}
	
}
