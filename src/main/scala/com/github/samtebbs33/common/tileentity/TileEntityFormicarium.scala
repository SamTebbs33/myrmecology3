package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 09/08/2016.
  */
class TileEntityFormicarium extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_FORMICARIUM, 18) {

  val SLOT_ANT_END = 9

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index match {
    case x if x < SLOT_ANT_END ⇒ stack.getItem.isInstanceOf[ItemAnt] && stack.getMetadata == AntTypes.WORKER.id
    case _ ⇒ true
  }

  override def openInventory(player: EntityPlayer): Unit = {}

  override def getFieldCount: Int = 0

  override def getField(id: Int): Int = 0

  override def setField(id: Int, value: Int): Unit = 0
}
