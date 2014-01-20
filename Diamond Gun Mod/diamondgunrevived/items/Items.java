package diamondgunrevived.items;

import cpw.mods.fml.common.registry.LanguageRegistry;
import net.minecraft.item.Item;


public class Items {
	
	public static Item shotgun;
	public static Item m4;
	public static Item eagle;
	public static Item sshell;
	public static Item ashell;
	public static Item cal;
	
	public static void init() {
		shotgun = new ItemDiamondShotgun(ItemInfo.ShotgunID);
		m4 = new ItemDiamondM4(ItemInfo.M4ID);
		eagle = new ItemDiamondDesertEagle(ItemInfo.EagleID);
		sshell = new ItemShotgunShell(ItemInfo.SShellID);
		ashell = new ItemAssaultShell(ItemInfo.AShellID);
		cal = new ItemFiftyCal(ItemInfo.FiftyCalID);
	}
	
	public static void addNames() {
		LanguageRegistry.addName(shotgun, ItemInfo.ShotgunName);
		LanguageRegistry.addName(m4, ItemInfo.M4Name);
		LanguageRegistry.addName(eagle, ItemInfo.EagleName);
		LanguageRegistry.addName(sshell, ItemInfo.SShellName);
		LanguageRegistry.addName(ashell, ItemInfo.AShellName);
		LanguageRegistry.addName(cal, ItemInfo.CalName);
	}
	
	public static void registerRecipes() {
		
	}

}
