package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.{AntTraitRegistry, BlockRegistry, ItemRegistry}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ITickable

import scala.util.Random

/**
	* Created by samtebbs on 05/08/2016.
	*/
class TileEntitySolarium extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_SOLARIUM, 16) with ITickable {

	val SLOT_LARVA = 0
	val TICKS_PER_SECOND = 20
	var progress = 0
	var targetTime = Int.MaxValue
	var ticks = 0
	var product: Option[ItemStack] = None

	val NBT_PROGRESS_TAG = "Progress"
	val NBT_PRODUCT_TAG = "Product"

	def updateProgress() = {
		ticks += 1
		if (ticks == TICKS_PER_SECOND) {
			progress += 1
			ticks = 0
		}
	}

	def reset(): Unit = {
		ticks = 0
		progress = 0
		targetTime = Int.MaxValue
		product = None
	}

	override def update(): Unit = {
		val larva = getStackInSlot(SLOT_LARVA)
		if (larva != null) {
			val species = larva.getItem.asInstanceOf[ItemAnt].species
			targetTime = species.traits.getTrait(AntTraitRegistry.incubationTime)
			if (product.isEmpty) product = Some(new ItemStack(ItemRegistry.getAnt(species).get, 1, Random.nextInt(AntTypes.values.size - 1)))
			updateProgress()
			if (progress >= targetTime) {
				decrStackSize(SLOT_LARVA, 1)
				addStack(product.get)
				reset()
			}
		} else reset()
	}

	override def getInventoryStackLimit: Int = 64

	override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index match {
		case SLOT_LARVA => stack.getItem.isInstanceOf[ItemAnt] && stack.getMetadata == AntTypes.LARVA.id
		case _ => stack.getItem.isInstanceOf[ItemAnt]
	}

	override def openInventory(player: EntityPlayer): Unit = {}

	override def getFieldCount: Int = 0

	override def getField(id: Int): Int = id match {
		case 0 => progress
		case 1 => targetTime
		case _ => 0
	}

	override def setField(id: Int, value: Int): Unit = id match {
		case 0 => progress = value
		case 1 => targetTime = value
	}

	override def numFields: Int = 2

	override def readFromNBT(compound: NBTTagCompound): Unit = {
		super.readFromNBT(compound)
		progress = compound.getInteger(NBT_PROGRESS_TAG)
		product = if(compound.hasKey(NBT_PRODUCT_TAG, 10)) Some(ItemStack.loadItemStackFromNBT(compound.getCompoundTag(NBT_PRODUCT_TAG))) else None
	}

	override def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
		super.writeToNBT(compound)
		compound.setInteger(NBT_PROGRESS_TAG, progress)
		if(product.isDefined) {
			val stackTag = new NBTTagCompound
			product.get.writeToNBT(stackTag)
			compound.setTag(NBT_PRODUCT_TAG, stackTag)
		}
		compound
	}
}
