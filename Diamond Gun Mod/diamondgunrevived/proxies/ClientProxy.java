package diamondgunrevived.proxies;

import cpw.mods.fml.client.registry.RenderingRegistry;
import diamondgunrevived.client.renderer.entity.RendeBullet;
import diamondgunrevived.entities.EntityBullet;

public class ClientProxy extends CommonProxy {

	@Override
	public void initSounds() {
		
	}

	@Override
	public void initRenderers() {
		RenderingRegistry.registerEntityRenderingHandler(EntityBullet.class, new RendeBullet());
	}
}
