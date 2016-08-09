package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 09/08/2016.
  */
class TileEntityFormicarium extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_FORMICARIUM, 20) {
  override def getInventoryStackLimit: Int = ???

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = ???

  override def openInventory(player: EntityPlayer): Unit = ???

  override def getFieldCount: Int = ???

  override def getField(id: Int): Int = ???

  override def setField(id: Int, value: Int): Unit = ???
}
