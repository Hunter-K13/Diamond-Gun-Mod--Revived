package diamondgunrevived.entities;

import cpw.mods.fml.common.registry.EntityRegistry;
import diamondgunrevived.DiamondGunRevived;

public class Entities {
	
	public static void init() {
		EntityRegistry.registerModEntity(EntityBullet.class, "EntityBullet", 0, DiamondGunRevived.instance, 80, 3, true);
	}
	
}
