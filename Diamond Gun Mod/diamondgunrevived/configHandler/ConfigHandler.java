package diamondgunrevived.configHandler;

import java.io.File;

import net.minecraftforge.common.Configuration;
import diamondgunrevived.items.ItemInfo;

public class ConfigHandler {

	public static void init(File file) {

		Configuration config = new Configuration(file);
		config.load();

		ItemInfo.ShotgunID = config.getItem(ItemInfo.ShotgunKey, ItemInfo.ShotgunDefault).getInt() - 256;
		ItemInfo.M4ID = config.getItem(ItemInfo.M4Key, ItemInfo.M4Default).getInt() - 256;
		ItemInfo.EagleID = config.getItem(ItemInfo.EagleKey, ItemInfo.EagleDefault).getInt() - 256;
		ItemInfo.SShellID = config.getItem(ItemInfo.SShellKey, ItemInfo.SShellDefault).getInt() - 256;
		ItemInfo.AShellID = config.getItem(ItemInfo.AShellKey, ItemInfo.AShellDefault).getInt() - 256;
		ItemInfo.FiftyCalID = config.getItem(ItemInfo.CalKey, ItemInfo.CalDefault).getInt() - 256;
		
		config.save();
	}
	
}
