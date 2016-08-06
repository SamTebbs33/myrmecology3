package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntity, TileEntitySolarium}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{Container, IInventory, Slot}
import net.minecraft.item.ItemStack

/**
	* Created by samtebbs on 06/08/2016.
	*/
class ContainerSolarium(playerInv : IInventory, te : TileEntitySolarium) extends MyrmecologyContainer(playerInv, te) {
	override def slots: List[Slot] = {
		val list = new util.LinkedList[Slot]
		// Larva slot
		list.add(new Slot(te, 0, 8, 17))
		// Mature ant slots
		val rows = 3
		val cols = 5
		Range(0, rows).foreach(y => Range(0, cols).foreach(x => list.add(new Slot(te, rows * y + x + 1 , 62 + x * 18, 17 + y * 18))))
		list
	}

	override def transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack = {
		val slot = inventorySlots.get(index)
		if(slot == null || !slot.getHasStack) return
		val stack = slot.getStack
		if(index < te.getSizeInventory) {
			// From tile to player inv, merge to any player inv slot
			if(!mergeItemStack(stack, te.getSizeInventory, inventorySlots.size(), false)) return null
		} else if(te.isItemValidForSlot(0, stack)){
			// From player inv to tile, merge to larva slot
			if(!mergeItemStack(stack, 0, 1, false)) return null
		}
		stack
	}
}
