package diamondgunrevived.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diamondgunrevived.DiamondGunRevived;

public class ItemFiftyCal extends Item {

	public ItemFiftyCal(int i) {
		super(i);
		setCreativeTab(DiamondGunRevived.gunTab);
		setUnlocalizedName(ItemInfo.CalUnlocal);
		setMaxStackSize(64);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.CalIcon);
	}

}
