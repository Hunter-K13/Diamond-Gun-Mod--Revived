package diamondgunrevived.items;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diamondgunrevived.DiamondGunRevived;

public class ISO extends Item implements IGun {
	public ISO(int par1) {
		super(par1);
		//setCreativeTab(DiamondGunRevived.gunTab);
		setMaxStackSize(1);
        this.setMaxDamage(384);
		setUnlocalizedName(ItemInfo.ShotgunUnlocal);
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int par4) {
		//if (!world.isRemote) {
		System.out.println("3: " + world.isRemote);
			if ((entityplayer.capabilities.isCreativeMode) || (entityplayer.inventory.hasItem(ItemInfo.SShellID))) {
				System.out.println("Well...");
			
				shoot(entityplayer, itemstack, world);
				System.out.println("Well.");
			}
		//}
	}
	
	@Override
	public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer entityplayer) {
		//System.out.println("1: " + world.isRemote);
		if ((entityplayer.capabilities.isCreativeMode) || (entityplayer.inventory.hasItem(ItemInfo.SShellID))) {
			entityplayer.setItemInUse(itemStack, itemStack.getMaxItemUseDuration());
		}
		
		return itemStack;
	}
	
	public void shoot(EntityPlayer player, ItemStack gun, World world) {
		//System.out.println("2: " + world.isRemote);
		System.out.println("Well well");
		EntityArrow entityarrow = new EntityArrow(world, player, 20.0F);
		gun.damageItem(1, player);
		world.playSoundAtEntity(player, "random.explode", 0.4F, 0.3F);
		player.inventory.consumeInventoryItem(ItemInfo.SShellID);
		world.spawnEntityInWorld(entityarrow);
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.ShotGunIcon);
	}
}