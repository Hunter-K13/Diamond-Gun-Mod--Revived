package diamondgunrevived.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.ArrowLooseEvent;
import net.minecraftforge.event.entity.player.ArrowNockEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diamondgunrevived.DiamondGunRevived;
import diamondgunrevived.entities.BulletType;
import diamondgunrevived.entities.EntityBullet;

public class ItemDiamondShotgun extends Item implements IGun {
	
	public static final String[] bowPullIconNameArray = new String[] {"pulling_0", "pulling_1", "pulling_2"};
    @SideOnly(Side.CLIENT)
    private Icon[] iconArray;
	
	public ItemDiamondShotgun(int par1) {
	super(par1);
	setCreativeTab(DiamondGunRevived.gunTab);
	setMaxStackSize(1);
    setMaxDamage(384);
	setUnlocalizedName(ItemInfo.ShotgunUnlocal);
	}
	
	public void onPlayerStoppedUsing(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer, int par4)
	{
	    int j = this.getMaxItemUseDuration(par1ItemStack) - par4;
	
	    ArrowLooseEvent event = new ArrowLooseEvent(par3EntityPlayer, par1ItemStack, j);
	    MinecraftForge.EVENT_BUS.post(event);
	    
	    if (event.isCanceled())
	    {
	        return;
	    }
	    
	    j = event.charge;
	
	    boolean flag = par3EntityPlayer.capabilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantment.infinity.effectId, par1ItemStack) > 0;
	
	    if (flag || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
	    {
	        float f = (float)j / 20.0F;
	        f = 1.0F;//(f * f + f * 2.0F) / 3.0F;
	
	        if ((double)f < 0.1D)
	        {
	            return;
	        }
	
	        if (f > 1.0F)
	        {
	            f = 1.0F;
	        }
	
	        EntityBullet entityarrow = new EntityBullet(par2World, par3EntityPlayer, f * 2.0F);
	        entityarrow.setBulletType(BulletType.SHOTGUNSHELL);
	
	        if (f == 1.0F)
	        {
	            entityarrow.setIsCritical(true);
	        }
	
	        int k = EnchantmentHelper.getEnchantmentLevel(Enchantment.power.effectId, par1ItemStack);
	
	        if (k > 0)
	        {
	            entityarrow.setDamage(entityarrow.getDamage() + (double)k * 0.5D + 0.5D);
	        }
	
	        int l = EnchantmentHelper.getEnchantmentLevel(Enchantment.punch.effectId, par1ItemStack);
	
	        if (l > 0)
	        {
	            entityarrow.setKnockbackStrength(l);
	        }
	
	        if (EnchantmentHelper.getEnchantmentLevel(Enchantment.flame.effectId, par1ItemStack) > 0)
	        {
	            entityarrow.setFire(100);
	        }
	
	        par1ItemStack.damageItem(1, par3EntityPlayer);
	        par2World.playSoundAtEntity(par3EntityPlayer, "random.bow", 1.0F, 1.0F / (itemRand.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
	
	        if (flag)
	        {
	            entityarrow.canBePickedUp = 2;
	        }
	        else
	        {
	            par3EntityPlayer.inventory.consumeInventoryItem(Item.arrow.itemID);
	        }
	
	        if (!par2World.isRemote)
	        {
	            par2World.spawnEntityInWorld(entityarrow);
	        }
	    }
	}
	
	public ItemStack onEaten(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
	    return par1ItemStack;
	}
	
	public int getMaxItemUseDuration(ItemStack par1ItemStack)
	{
	    return 72000;
	}
	
	public EnumAction getItemUseAction(ItemStack par1ItemStack)
	{
	    return EnumAction.bow;
	}
	
	public ItemStack onItemRightClick(ItemStack par1ItemStack, World par2World, EntityPlayer par3EntityPlayer)
	{
	    ArrowNockEvent event = new ArrowNockEvent(par3EntityPlayer, par1ItemStack);
	    MinecraftForge.EVENT_BUS.post(event);
	    if (event.isCanceled())
	    {
	        return event.result;
	    }
	
	    if (par3EntityPlayer.capabilities.isCreativeMode || par3EntityPlayer.inventory.hasItem(Item.arrow.itemID))
	    {
	        par3EntityPlayer.setItemInUse(par1ItemStack, this.getMaxItemUseDuration(par1ItemStack));
	    }
	
	    return par1ItemStack;
	}
	
	/**
	 * Return the enchantability factor of the item, most of the time is based on material.
	 */
	public int getItemEnchantability()
	{
	    return 1;
	}
	
	/**
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
	}**/
	
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