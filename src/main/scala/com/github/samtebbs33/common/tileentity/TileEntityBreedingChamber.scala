package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ITickable

/**
	* Created by samtebbs on 07/08/2016.
	*/
class TileEntityBreedingChamber extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_BREEDING_CHAMBER, 17) with ITickable {

	val SLOT_DRONE = 0
	val SLOT_QUEEN = 1

	var progress = 0
	var ticks = 0
	var targetTime = 0

	override def getInventoryStackLimit: Int = 64

	override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = stack.getItem.isInstanceOf[ItemAnt] && (index match {
		case SLOT_DRONE => stack.getMetadata == AntTypes.DRONE.id
		case SLOT_QUEEN => stack.getMetadata == AntTypes.QUEEN.id
		case _ => true
	})

	override def openInventory(player: EntityPlayer): Unit = ()

	override def getFieldCount: Int = 2

	override def getField(id: Int): Int = id match {
		case 0 => progress
		case _ => targetTime
	}

	override def setField(id: Int, value: Int): Unit = id match {
		case 0 => progress = value
		case 1 => targetTime = value
	}

	override def update(): Unit = {
		// TODO
	}
}
