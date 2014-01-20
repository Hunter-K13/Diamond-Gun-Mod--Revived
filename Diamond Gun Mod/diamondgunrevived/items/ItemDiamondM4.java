package diamondgunrevived.items;

import java.util.HashMap;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import diamondgunrevived.DiamondGunRevived;
import diamondgunrevived.entities.BulletType;
import diamondgunrevived.entities.EntityBullet;

public class ItemDiamondM4 extends Item implements IGun {
	
	public HashMap machineGunMap = new HashMap();
	public HashMap shootersMap = new HashMap();
	
	public ItemDiamondM4(int par1) {
		super(par1);
		setCreativeTab(DiamondGunRevived.gunTab);
		setMaxStackSize(1);
		setUnlocalizedName(ItemInfo.M4Unlocal);
	}
	
	public ItemStack onItemRightClick(ItemStack itemstack, World world, EntityPlayer entityplayer){
		if ((entityplayer.capabilities.isCreativeMode) || (entityplayer.inventory.hasItem(ItemInfo.AShellID))) {
			entityplayer.setItemInUse(itemstack, 1000);
			if (!world.isRemote) {
				scheduleShot(5, entityplayer, itemstack);
			}
		}
		
		return itemstack;
	}
	
	@Override
	public void onPlayerStoppedUsing(ItemStack itemstack, World world, EntityPlayer entityplayer, int durcapabilitiesion) {
		stopShooting(entityplayer, itemstack);
	}
	
	public void shoot(EntityPlayer player, ItemStack gun, World world) {
		EntityBullet entityarrow = new EntityBullet(world, player, 5.0F);
		entityarrow.setBulletType(BulletType.ASSAULTSHELL);
		gun.damageItem(1, player);
		world.playSoundAtEntity(player, "random.explode", 0.1F, 0.1F);
		player.inventory.consumeInventoryItem(ItemInfo.AShellID);
		if (!world.isRemote)
		world.spawnEntityInWorld(entityarrow);
	}
	
	public void scheduleShot(int ticks, EntityPlayer player, ItemStack gun) {
		  if (this.shootersMap.containsKey(player)) {
			  Boolean isShooting;
			  if (((HashMap)this.shootersMap.get(player)).containsKey(gun)) {
				  isShooting = (Boolean)((HashMap)this.shootersMap.get(player)).put(gun, Boolean.valueOf(true));
			  }else {
				  ((HashMap)this.shootersMap.get(player)).put(gun, Boolean.valueOf(true));
			  }
			  
		  }else {
			  HashMap gunMap = new HashMap();
			  gunMap.put(gun, Boolean.valueOf(true));
			  this.shootersMap.put(player, gunMap);
		  }
		  
		  if (this.machineGunMap.containsKey(player)) {
			  if (((HashMap)this.machineGunMap.get(player)).containsKey(gun)) {
				  ((HashMap)this.machineGunMap.get(player)).put(gun, Integer.valueOf(ticks));
			  }else {
				  ((HashMap)this.machineGunMap.get(player)).put(gun, Integer.valueOf(ticks));
			  }
			  
		  }else {
			  HashMap gunMap = new HashMap();
			  gunMap.put(gun, Integer.valueOf(ticks));
			  this.machineGunMap.put(player, gunMap);
		  }
		  
		  //ModLoader.setInGameHook(this, true, true);
	  }
	
	public void stopShooting(EntityPlayer player, ItemStack gun) {
		((HashMap)this.shootersMap.get(player)).put(gun, Boolean.valueOf(false));
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister register) {
		itemIcon = register.registerIcon(ItemInfo.TEXTURE_LOCATION + ":" + ItemInfo.M4Icon);
	}
}