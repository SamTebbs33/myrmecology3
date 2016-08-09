package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ProgressTracker
import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.ITickable
import com.github.samtebbs33.Util._
import net.minecraft.nbt.NBTTagCompound

/**
  * Created by samtebbs on 09/08/2016.
  */
class TileEntityFormicarium extends MyrmecologyTileEntityContainer(BlockRegistry.NAME_FORMICARIUM, 18) with ITickable {

  val SLOT_ANT_END = 9

  val tracker = new ProgressTracker(1)

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index match {
    case x if x < SLOT_ANT_END ⇒ stack.getItem.isInstanceOf[ItemAnt] && stack.getMetadata == AntTypes.WORKER.id
    case _ ⇒ true
  }

  override def openInventory(player: EntityPlayer): Unit = {}

  override def getFieldCount: Int = 0

  override def getField(id: Int): Int = 0

  override def setField(id: Int, value: Int): Unit = {}

  override def update(): Unit = {
    tracker.update
    if(tracker.done) {
      tracker.reset
      forEachOccupiedSlot(slot ⇒ getStackInSlot(slot).getItemAs[ItemAnt].species.updateAI(this), SLOT_ANT_END)
    }
  }


  override def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
    super.writeToNBT(compound)
    tracker.writeToNBT(compound)
  }

  override def readFromNBT(compound: NBTTagCompound): Unit = {
    super.readFromNBT(compound)
    tracker.readFromNBT(compound)
  }
}
