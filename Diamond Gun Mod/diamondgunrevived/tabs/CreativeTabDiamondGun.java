package diamondgunrevived.tabs;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diamondgunrevived.items.Items;

public class CreativeTabDiamondGun extends CreativeTabs {
    
	
    public CreativeTabDiamondGun(int tabId, String label) {
        super(tabId, label);
        //this.setBackgroundImageName("diamondgunrevived:guntab");
    }
    
    @Override
    @SideOnly(Side.CLIENT)
    public Item getTabIconItem() {
        return Items.eagle;
    }
}
