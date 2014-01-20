package diamondgunrevived.items;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

public abstract interface IGun
{
  public abstract void shoot(EntityPlayer paramEntityPlayer, ItemStack paramItemStack, World paramWorld);
  
  
}