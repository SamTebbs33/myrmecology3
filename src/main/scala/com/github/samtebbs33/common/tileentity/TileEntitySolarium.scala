package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.ITickable
import net.minecraftforge.event.entity.minecart.MinecartUpdateEvent

/**
	* Created by samtebbs on 05/08/2016.
	*/
class TileEntitySolarium(name : String) extends MyrmecologyContainer(name, 1) with ITickable {
	override def update(): Unit = {

	}

	override def getInventoryStackLimit: Int = 1

	override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = stack.getItem.isInstanceOf[ItemAnt] && stack.getMetadata == AntTypes.LARVA.id

	override def openInventory(player: EntityPlayer): Unit = ???

	override def getFieldCount: Int = 0

	override def getField(id: Int): Int = 0

	override def setField(id: Int, value: Int): Unit = {}

}
