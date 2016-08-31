package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.MyrmecologyInventory
import com.github.samtebbs33.common.item.ItemAnt
import net.minecraft.entity.player.{EntityPlayer, InventoryPlayer}
import net.minecraft.inventory.{IInventory, Slot}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.NBTTagCompound
import net.minecraft.util.text.ITextComponent

/**
  * Created by samuelt on 31/08/2016.
  */
class ContainerMyrmopaedia(playerInv: IInventory, itemStack: ItemStack) extends MyrmecologyContainer(playerInv, new MyrmopaediaInventory(itemStack)) {
  override def slots: util.List[Slot] = new util.ArrayList[Slot](){{
    add(new Slot(inv, 0, 18, 147))
  }}
}

class MyrmopaediaInventory(itemStack: ItemStack) extends MyrmecologyInventory {

  // Load the item from the itemstack's NBT
  var invItem: Option[ItemStack] =  itemStack.getTagCompound match {
    case null => None
    case compound => compound.getCompoundTag("Inventory") match {
      case null => None
      case x => Some(ItemStack.loadItemStackFromNBT(x))
    }
  }

  override def inventory: Array[Option[ItemStack]] = Array(invItem)

  override def getInventoryStackLimit: Int = 1

  override def markDirty(): Unit = {
    // Save the item to the itemstack's NBT
    val tag = if(itemStack.hasTagCompound) itemStack.getTagCompound else new NBTTagCompound()
    invItem match {
      case None => tag.removeTag("Inventory")
      case Some(x) => x.writeToNBT(tag)
    }
  }

  override def isItemValidForSlot(index: Int, stack: ItemStack): Boolean = stack.getItem.isInstanceOf[ItemAnt]

  override def openInventory(player: EntityPlayer): Unit = {}

  override def getFieldCount: Int = 0

  override def getField(id: Int): Int = 0

  override def isUseableByPlayer(player: EntityPlayer): Boolean = true

  override def setField(id: Int, value: Int): Unit = {}

  override def getDisplayName: ITextComponent = null

  override def getName: String = ""

  override def hasCustomName: Boolean = false
}
