package com.github.samtebbs33.common

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

/**
  * Created by samuelt on 31/08/2016.
  */
trait MyrmecologyInventory extends IInventory {

  def inventory: Array[Option[ItemStack]]
  def inBounds(index: Int) = index < getSizeInventory && index >= 0

  override def decrStackSize(index: Int, count: Int): ItemStack = {
    val stack = getStackInSlot(index)
    if (stack != null) {
      if (stack.stackSize <= count) setInventorySlotContents(index, null)
      else {
        val result = stack.splitStack(count)
        setInventorySlotContents(index, stack)
        return result
      }
    }
    stack
  }

  def getStackSizeInSlot(slot: Int) = getStackInSlot(slot) match {
    case null ⇒ 0
    case stack ⇒ stack.stackSize
  }

  def addStack(stack: ItemStack): Unit = {
    val stackSizeLimit = Math.min(getInventoryStackLimit, stack.getItem.getItemStackLimit(stack))
    for (slot <- 0 to getSizeInventory) {
      if (isItemValidForSlot(slot, stack)) {
        if (stack.stackSize <= 0) return
        val slotStack = getStackInSlot(slot)
        if (slotStack == null) {
          val temp = stack.copy()
          stack.stackSize -= stackSizeLimit
          setInventorySlotContents(slot, temp)
        } else if (slotStack.isItemEqual(stack)) {
          val remaining = Math.min(stackSizeLimit - slotStack.stackSize, stack.stackSize)
          stack.stackSize -= remaining
          slotStack.stackSize += remaining
        }
      }
    }
  }

  def canHoldStack(stack: ItemStack): Boolean = {
    var stackSize = stack.stackSize
    val stackSizeLimit = Math.min(getInventoryStackLimit, stack.getItem.getItemStackLimit(stack))
    for (slot <- 0 until getSizeInventory) {
      if (isItemValidForSlot(slot, stack)) {
        if (stackSize <= 0) return true
        val slotStack = getStackInSlot(slot)
        if (slotStack == null) stackSize -= stackSizeLimit
        else if (slotStack.isItemEqual(stack)) stackSize -= stackSizeLimit - slotStack.stackSize
      }
    }
    stackSize <= 0
  }

  override def closeInventory(player: EntityPlayer): Unit = ???

  override def getSizeInventory: Int = inventory.length

  override def setInventorySlotContents(index: Int, stack: ItemStack): Unit = {
    if (inBounds(index)) inventory.update(index, Option(stack))
    markDirty()
  }

  override def getStackInSlot(index: Int): ItemStack =
    if (inBounds(index)) inventory(index).orNull
    else null

  def getStackSize(slot: Int) = getStackInSlot(slot) match {
    case null => 0
    case stack => stack.stackSize
  }

  override def removeStackFromSlot(index: Int): ItemStack = {
    val temp = getStackInSlot(index)
    setInventorySlotContents(index, null)
    temp
  }

  override def clear(): Unit = forEachSlot(removeStackFromSlot)

  def slotIsEmpty(i: Int) = getStackInSlot(i) == null

  def slotIsNotEmpty(i: Int) = !slotIsEmpty(i)

  def occupiedSlots(max: Int = getSizeInventory) = Range(0, max).filter(slotIsNotEmpty)

  def forEachSlot[T](f: (Int) => T, max: Int = getSizeInventory) = Range(0, max).foreach(f)

  def forEachOccupiedSlot[T](f: (Int) => T, max: Int = getSizeInventory) = occupiedSlots(max).foreach(f)

}
