package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.event.AntEvent.{AntBreedEvent, AntMatureEvent}
import com.github.samtebbs33.common.ProgressTracker
import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.event.AntEvent
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.{AntTraitRegistry, BlockRegistry}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ITickable

/**
  * Created by samtebbs on 07/08/2016.
  */
class TileEntityBreedingChamber extends MyrmecologyTileEntityContainer(BlockRegistry.breddingChamberName, 17) with ITickable {

  val droneSlot = 0
  val queenSlot = 1

  val tracker = new ProgressTracker
  var product: Option[ItemStack] = None

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = stack.getItem.isInstanceOf[ItemAnt] && (index match {
    case `droneSlot` => stack.getMetadata == AntTypes.DRONE.id
    case `queenSlot` => stack.getMetadata == AntTypes.QUEEN.id
    case _ => true
  })

  override def openInventory(player: EntityPlayer): Unit = ()

  override def getFieldCount: Int = 2

  override def getField(id: Int): Int = id match {
    case 0 => tracker.progress
    case _ => tracker.targetTime
  }

  override def setField(id: Int, value: Int): Unit = id match {
    case 0 => tracker.progress = value
    case 1 => tracker.targetTime = value
  }

  override def update(): Unit = {
    val drone = getStackInSlot(droneSlot)
    val queen = getStackInSlot(queenSlot)
    if (drone != null && queen != null && drone.getItem == queen.getItem) {
      tracker.targetTime = queen.getItemAs[ItemAnt].species.getTraitAs[Int](AntTraitRegistry.breedingTime)
      product.ifNotDefined(() ⇒ product = Some(new ItemStack(drone.getItem, 2, AntTypes.LARVA.id)))
      if (canHoldStack(product.get)) {
        tracker.update
        if (tracker.done) {
          AntEvent.dispatch(new AntBreedEvent(drone, queen, product.get, this), notCanceled = () ⇒ {
            addStack(product.get)
            decrStackSize(queenSlot, 1)
            decrStackSize(droneSlot, 1)
          })
        }
      }
    } else tracker.reset
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
