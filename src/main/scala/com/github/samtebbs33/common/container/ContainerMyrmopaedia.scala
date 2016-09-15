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
class ContainerMyrmopaedia(playerInv: IInventory, itemStack: ItemStack) extends MyrmecologyContainer(playerInv, new MyrmopaediaInventory(itemStack), 173, 18) {

  override def slots: util.List[Slot] = new util.ArrayList[Slot](){{
    add(new Slot(inv, 0, 18, 147))
  }}
}

class MyrmopaediaInventory(itemStack: ItemStack) extends MyrmecologyInventory {

  val inventoryNbtTag = "Inventory"

  // Load the item from the itemstack's NBT
  if(!itemStack.hasTagCompound) itemStack.setTagCompound(new NBTTagCompound)

  val inventoryArray = new Array[Option[ItemStack]](1)
  inventoryArray(0) = readNBT(itemStack.getTagCompound)

  def readNBT(tag: NBTTagCompound): Option[ItemStack] = tag.hasKey(inventoryNbtTag) match {
    case true ⇒ Some(ItemStack.loadItemStackFromNBT(tag.getCompoundTag(inventoryNbtTag)))
    case false ⇒ None
  }

  override def inventory: Array[Option[ItemStack]] = inventoryArray

  override def getInventoryStackLimit: Int = 1

  override def markDirty(): Unit = {
    // Save the item to the itemstack's NBT
    val tag = if(itemStack.hasTagCompound) itemStack.getTagCompound else new NBTTagCompound()
    inventory(0) match {
      case None => tag.removeTag(inventoryNbtTag)
      case Some(x) => val compound = new NBTTagCompound
        tag.setTag(inventoryNbtTag, compound)
        x.writeToNBT(compound)
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
