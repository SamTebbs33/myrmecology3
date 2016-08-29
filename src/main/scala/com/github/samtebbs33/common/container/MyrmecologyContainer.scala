package com.github.samtebbs33.common.container

import com.github.samtebbs33.common.tileentity.MyrmecologyTileEntityContainer
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{Container, IInventory, Slot}
import net.minecraft.item.ItemStack

import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 06/08/2016.
  */
abstract class MyrmecologyContainer(playerInv: IInventory, te: MyrmecologyTileEntityContainer) extends Container {

  // Add container's slots
  slots.foreach(addSlot)

  // Add player inventory
  Range(0, 3).foreach(y => Range(0, 9).foreach(x => addSlot(playerInv, x + y * 9 + 9, 8 + x * 18, 84 + y * 18)))
  Range(0, 9).foreach(x => addSlot(playerInv, x, 8 + x * 18, 142))

  val tileEntityFields = new Array[Int](te.getFieldCount)

  def slots: java.util.List[Slot]

  def addSlot(inv: IInventory, id: Int, x: Int, y: Int): Slot = addSlot(new Slot(inv, id, x, y))

  def addSlot(slot: Slot) = addSlotToContainer(slot)

  override def canInteractWith(playerIn: EntityPlayer): Boolean = te.isUseableByPlayer(playerIn)

  override def detectAndSendChanges(): Unit = {
    // Loop through each tile entity field, detect changes and update client
    tileEntityFields.zipWithIndex.foreach[Unit] { case (v, i) => {
      val teVal = te.getField(i)
      if (v != teVal) {
        tileEntityFields.update(i, teVal)
        this.listeners.foreach(listener => {
          listener.sendProgressBarUpdate(this, i, teVal)
        })
      }
    }
    }
  }

  override def updateProgressBar(id: Int, data: Int): Unit = {
    te.setField(id, data)
  }

  def firstValidSlot(stack: ItemStack): Int =
    te.inventory.toStream.zipWithIndex.map(_._2).find(slot ⇒ te.isItemValidForSlot(slot, stack)).getOrElse(-1)

  def lastValidSlot(stack: ItemStack, from: Int) =
    te.inventory.toStream.zipWithIndex.reverse.take(te.inventory.length - from).map(_._2).find(slot ⇒ te.isItemValidForSlot(slot, stack)).getOrElse(-1)

  override def transferStackInSlot(playerIn: EntityPlayer, index: Int): ItemStack = {
    val slot = inventorySlots.get(index)
    if (slot == null || !slot.getHasStack) return null
    val stack = slot.getStack
    val tileInv = index < te.getSizeInventory
    println(s"tileInv: $tileInv")
    val from = if(tileInv) te.getSizeInventory else firstValidSlot(stack)
    val to = if(tileInv) inventorySlots.size else lastValidSlot(stack, from + 1) + 1
    println(s"from $from, to $to")
    if (!mergeItemStack(stack, from, to, false)) return null
    else stack
  }

}
