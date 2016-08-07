package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.{AntTraitRegistry, BlockRegistry, ItemRegistry}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent

import scala.util.Random

/**
	* Created by samtebbs on 05/08/2016.
	*/
class TileEntitySolarium extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_SOLARIUM, 16) with ITickable {

	val SLOT_LARVA = 0
	val TICKS_PER_SECOND = 20
	var progress = 0
	var ticks = 0
	var product : Option[ItemStack] = None

	def updateProgress() = {
		//println("progress update")
		ticks += 1
		if(ticks == TICKS_PER_SECOND) {
			progress += 1
			ticks = 0
		}
	}

	def reset(): Unit = {
		ticks = 0
		progress = 0
		product = None
	}

	override def update(): Unit = {
		val larva = getStackInSlot(SLOT_LARVA)
		if(larva != null) {
			val species = larva.getItem.asInstanceOf[ItemAnt].species
			if(product.isEmpty) product = Some(new ItemStack(ItemRegistry.getAnt(species).get, 1, Random.nextInt(AntTypes.values.size)))
			updateProgress()
			if(progress >= species.traits.getTrait(AntTraitRegistry.incubationTime)) {
				println("Finishing incubation")
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

	override def openInventory(player: EntityPlayer): Unit = ???

	override def getFieldCount: Int = 0

	override def getField(id: Int): Int = 0

	override def setField(id: Int, value: Int): Unit = {}

}
