package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.ProgressTracker
import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.{AntTraitRegistry, BlockRegistry, ItemRegistry}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.ITickable
import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.event.AntEvent
import com.github.samtebbs33.common.event.AntEvent.AntMatureEvent

import scala.util.Random

/**
  * Created by samtebbs on 05/08/2016.
  */
class TileEntitySolarium extends MyrmecologyTileEntityContainer(BlockRegistry.solariumName, 16) with ITickable {

  val larvaSlot = 0
  val ticksPerSecond = 20
  val tracker = new ProgressTracker
  var product: Option[ItemStack] = None

  val progressNbtTag = "Progress"
  val productNbtTag = "Product"

  def reset = {
    product = None
    tracker.reset
  }

  override def update(): Unit = {
    val larva = getStackInSlot(larvaSlot)
    if (larva != null) {
      val species = larva.getItem.asInstanceOf[ItemAnt].species
      tracker.targetTime = species.getTraitAs[Int](AntTraitRegistry.incubationTime)
      if (product.isEmpty) product = Some(new ItemStack(ItemRegistry.getAnt(species).get, 1, Random.nextInt(AntTypes.values.size - 1)))
      tracker.update
      if (tracker.done) {
        AntEvent.dispatch(new AntMatureEvent(product.get, this), notCanceled = () ⇒ {
          decrStackSize(larvaSlot, 1)
          addStack(product.get)
        })
        reset
      }
    } else reset
  }

  override def getInventoryStackLimit: Int = 64

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = index match {
    case `larvaSlot` => stack.getItem.isInstanceOf[ItemAnt] && stack.getMetadata == AntTypes.LARVA.id
    case _ => stack.getItem.isInstanceOf[ItemAnt]
  }

  override def openInventory(player: EntityPlayer): Unit = {}

  override def getField(id: Int): Int = id match {
    case 0 => tracker.progress
    case 1 => tracker.targetTime
    case _ => 0
  }

  override def setField(id: Int, value: Int): Unit = id match {
    case 0 => tracker.progress = value
    case 1 => tracker.targetTime = value
  }

  override def getFieldCount: Int = 2

  override def readFromNBT(compound: NBTTagCompound): Unit = {
    super.readFromNBT(compound)
    tracker.readFromNBT(compound)
    product = if (compound.hasKey(productNbtTag, 10)) Some(ItemStack.loadItemStackFromNBT(compound.getCompoundTag(productNbtTag))) else None
  }

  override def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
    super.writeToNBT(compound)
    tracker.writeToNBT(compound)
    product.ifDefined(p ⇒ {
      val stackTag = new NBTTagCompound
      p.writeToNBT(stackTag)
      compound.setTag(productNbtTag, stackTag)
    })
    compound
  }

  override def closeInventory(player: EntityPlayer): Unit = {}
}
