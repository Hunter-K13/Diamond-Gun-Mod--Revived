package diamondgunrevived.entities;

import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.DamageSource;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;


public class EntityShotgunShell1 extends Entity implements EntityShot {
	private int xTile;
	private int yTile;
	private int zTile;
	private int inTile;
	private int inData;
	public boolean doesArrowBelongToPlayer;
	public int arrowShake;
	public Entity shootingEntity;
	private int ticksInGround;
	private int ticksInAir;
	public boolean arrowCritical;
	public EntityArrow entityarrow;
	
	public EntityShotgunShell1(World world, EntityPlayer entityplayer) {
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inData = 0;
		
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = false;
		setAngles(0.5F, 0.5F);
	}
	
	public EntityShotgunShell1(World world, double d, double d1, double d2) {
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inData = 0;
		
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = false;
		setAngles(0.5F, 0.5F);
		d(d, d1, d2);
		this.yOffset = 0.0F;
	}
	
	public EntityShotgunShell1(World world, EntityLivingBase entityliving, float f) {
		super(world);
		this.xTile = -1;
		this.yTile = -1;
		this.zTile = -1;
		this.inTile = 0;
		this.inData = 0;
		
		this.doesArrowBelongToPlayer = false;
		this.arrowShake = 0;
		this.ticksInAir = 0;
		this.arrowCritical = false;
		this.shootingEntity = entityliving;
		this.doesArrowBelongToPlayer = (entityliving instanceof EntityPlayer);
		setAngles(0.5F, 0.5F);
		setLocationAndAngles(entityliving.posX, entityliving.posY + entityliving.getEyeHeight(), entityliving.posZ, entityliving.rotationYaw, entityliving.rotationPitch);
		this.posX -= MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * 0.16F;
		this.posY -= 0.1000000014901161D;
		this.posZ -= MathHelper.sqrt_double(this.rotationYaw / 180.0F * 3.141593F) * 0.16F;
		d(this.posX, this.posY, this.posZ);
		this.yOffset = 0.0F;
		this.motionX = (-MathHelper.sqrt_double(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F));
		this.motionZ = (MathHelper.cos(this.rotationYaw / 180.0F * 3.141593F) * MathHelper.cos(this.rotationPitch / 180.0F * 3.141593F));
		this.motionY = (-MathHelper.sqrt_double(this.rotationPitch / 180.0F * 3.141593F));
		setArrowHeading(this.motionX, this.motionY, this.motionZ, f * 1.5F, 1.0F);
	}
	
	protected void entityInit() {
	}
	
	public void setArrowHeading(double d, double d1, double d2, float f, float f1) {
		float f2 = MathHelper.sqrt_double(d * d + d1 * d1 + d2 * d2);
		d /= f2;
		d1 /= f2;
		d2 /= f2;
		d += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d1 += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d2 += this.rand.nextGaussian() * 0.007499999832361937D * f1;
		d *= f;
		d1 *= f;
		d2 *= f;
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		float f3 = MathHelper.sqrt_double(d * d + d2 * d2);
		this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / 3.141592741012573D));
		this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(d1, f3) * 180.0D / 3.141592741012573D));
		this.ticksInGround = 0;
	}
	
	public void d(double d, double d1, double d2) {
		this.motionX = d;
		this.motionY = d1;
		this.motionZ = d2;
		if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F)) {
			float f = MathHelper.sqrt_double(d * d + d2 * d2);
			this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(d, d2) * 180.0D / 3.141592741012573D));
			this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(d1, f) * 180.0D / 3.141592741012573D));
			this.prevRotationPitch = this.rotationPitch;
			this.prevRotationYaw = this.rotationYaw;
			setLocationAndAngles(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
			this.ticksInGround = 0;
		}
	}
	
	public void onUpdate() {
		super.onUpdate();
		if ((this.prevRotationPitch == 0.0F) && (this.prevRotationYaw == 0.0F)) {
			float f = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
			this.prevRotationYaw = (this.rotationYaw = (float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592741012573D));
			this.prevRotationPitch = (this.rotationPitch = (float)(Math.atan2(this.motionY, f) * 180.0D / 3.141592741012573D));
		}
		
		int i = this.worldObj.getBlockId(this.xTile, this.yTile, this.zTile);
		
		if (i > 0) {
			Block.blocksList[i].setBlockBoundsBasedOnState(this.worldObj, this.xTile, this.yTile, this.zTile);
			AxisAlignedBB axisalignedbb = Block.blocksList[i].getCollisionBoundingBoxFromPool(this.worldObj, this.xTile, this.yTile, this.zTile);
			if ((axisalignedbb != null) && (axisalignedbb.isVecInside(this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ)))) {
				setDead();
			}
			
		}
		
		this.ticksInAir += 1;
		Vec3 vec3d = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		Vec3 vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		MovingObjectPosition movingobjectposition = this.worldObj.rayTraceBlocks_do_do(vec3d, vec3d1, false, true);
		vec3d = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX, this.posY, this.posZ);
		vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(this.posX + this.motionX, this.posY + this.motionY, this.posZ + this.motionZ);
		if (movingobjectposition != null) {
			vec3d1 = this.worldObj.getWorldVec3Pool().getVecFromPool(movingobjectposition.hitVec.xCoord, movingobjectposition.hitVec.yCoord, movingobjectposition.hitVec.zCoord);
		}
		
		Entity entity = null;
		List list = this.worldObj.getEntitiesWithinAABBExcludingEntity(this, this.boundingBox.addCoord(this.motionX, this.motionY, this.motionZ).expand(1.0D, 1.0D, 1.0D));
		double d = 0.0D;
		for (int l = 0; l < list.size(); l++) {
			Entity entity1 = (Entity)list.get(l);
			
			if ((entity1.canBeCollidedWith()) && ((entity1 != this.shootingEntity) || (this.ticksInAir >= 5))) {
				float f5 = 0.3F;
				AxisAlignedBB axisalignedbb1 = entity1.boundingBox.expand(f5, f5, f5);
				MovingObjectPosition movingobjectposition1 = axisalignedbb1.calculateIntercept(vec3d, vec3d1);
				if (movingobjectposition1 != null) {
					double d1 = vec3d.distanceTo(movingobjectposition1.hitVec);
					if ((d1 < d) || (d == 0.0D)) {
						entity = entity1;
						d = d1;
					}
				}
			}
		}
		if (entity != null) {
			movingobjectposition = new MovingObjectPosition(entity);
		}
		if (movingobjectposition != null) {
			if (movingobjectposition.entityHit != null) {
				float f1 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionY * this.motionY + this.motionZ * this.motionZ);
				int j1 = (int)Math.ceil(f1 * 2.0D);
				if (this.arrowCritical) {
					j1 += this.rand.nextInt(j1 / 2 + 2);
				}
				DamageSource damagesource = null;
				if (this.shootingEntity == null) {
					damagesource = DamageSource.causeArrowDamage(this.entityarrow, this);
				}else {
					damagesource = DamageSource.causeArrowDamage(this.entityarrow, this.shootingEntity);
				}
				
				if (movingobjectposition.entityHit.attackEntityFrom(damagesource, j1)) {
					if ((movingobjectposition.entityHit instanceof EntityLivingBase));
					this.worldObj.playSoundAtEntity(this, "random.explode", 0.5F, 0.7F / (this.rand.nextFloat() * 0.2F + 0.9F));
					setDead();
				}
				else {
					setDead();
				}
				
			}
			
		}
		
		if (this.arrowCritical) {
			for (int i1 = 0; i1 < 4; i1++) {
				this.worldObj.spawnParticle("crit", this.posX + this.motionX * i1 / 4.0D, this.posY + this.motionY * i1 / 4.0D, this.posZ + this.motionZ * i1 / 4.0D, -this.motionX, -this.motionY + 0.2D, -this.motionZ);
			}
		}
		
		this.posX += this.motionX;
		this.posY += this.motionY;
		this.posZ += this.motionZ;
		float f3 = MathHelper.sqrt_double(this.motionX * this.motionX + this.motionZ * this.motionZ);
		this.rotationYaw = ((float)(Math.atan2(this.motionX, this.motionZ) * 180.0D / 3.141592741012573D));
		for (this.rotationPitch = ((float)(Math.atan2(this.motionY, f3) * 180.0D / 3.141592741012573D)); this.rotationPitch - this.prevRotationPitch < -180.0F; this.prevRotationPitch -= 360.0F);
		while (this.rotationPitch - this.prevRotationPitch >= 180.0F) this.prevRotationPitch += 360.0F;
		while (this.rotationYaw - this.prevRotationYaw < -180.0F) this.prevRotationYaw -= 360.0F;
		while (this.rotationYaw - this.prevRotationYaw >= 180.0F) this.prevRotationYaw += 360.0F;
		this.rotationPitch = (this.prevRotationPitch + (this.rotationPitch - this.prevRotationPitch) * 0.2F);
		this.rotationYaw = (this.prevRotationYaw + (this.rotationYaw - this.prevRotationYaw) * 0.2F);
		float f4 = 0.99F;
		float f6 = 0.05F;
		if (isInWater()) {
			for (int k1 = 0; k1 < 4; k1++) {
				float f7 = 0.25F;
				this.worldObj.spawnParticle("bubble", this.posX - this.motionX * f7, this.posY - this.motionY * f7, this.posZ - this.motionZ * f7, this.motionX, this.motionY, this.motionZ);
			}
			
			f4 = 0.8F;
		}
		this.motionX *= f4;
		this.motionY *= f4;
		this.motionZ *= f4;
		this.motionY -= f6;
		d(this.posX, this.posY, this.posZ);
	}
	
	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		nbttagcompound.setShort("xTile", (short)this.xTile);
		nbttagcompound.setShort("yTile", (short)this.yTile);
		nbttagcompound.setShort("zTile", (short)this.zTile);
		nbttagcompound.setByte("inTile", (byte)this.inTile);
		nbttagcompound.setByte("inData", (byte)this.inData);
		nbttagcompound.setByte("shake", (byte)this.arrowShake);
		
		nbttagcompound.setBoolean("player", this.doesArrowBelongToPlayer);
	}
	
	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		this.xTile = nbttagcompound.getShort("xTile");
		this.yTile = nbttagcompound.getShort("yTile");
		this.zTile = nbttagcompound.getShort("zTile");
		this.inTile = (nbttagcompound.getByte("inTile") & 0xFF);
		this.inData = (nbttagcompound.getByte("inData") & 0xFF);
		this.arrowShake = (nbttagcompound.getByte("shake") & 0xFF);
		
		this.doesArrowBelongToPlayer = nbttagcompound.getBoolean("player");
	}
	
	public float h_() {
		return 0.0F;
	}
	
	public void getSignature() {
	}
}