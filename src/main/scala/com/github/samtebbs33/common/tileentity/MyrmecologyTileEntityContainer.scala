package com.github.samtebbs33.common.tileentity

import com.github.samtebbs33.common.MyrmecologyInventory
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}

/**
  * Created by samtebbs on 06/08/2016.
  */
abstract class MyrmecologyTileEntityContainer(val name: String, invSize: Int) extends MyrmecologyTileEntity with MyrmecologyInventory {

  val inventoryArray = new Array[Option[ItemStack]](invSize)
  inventoryArray.indices.foreach(i => inventoryArray(i) = None)
  val inventoryNbtTag = "Items"
  val slotNbtTag = "Slot"
  val customNameNbtTag = "CustomName"

  var customName = ""

  override def inventory: Array[Option[ItemStack]] = inventoryArray
  override def getName: String = if (hasCustomName) customName else "container." + name

  override def hasCustomName: Boolean = !customName.isEmpty

  override def isUseableByPlayer(player: EntityPlayer): Boolean = player.getDistanceSq(this.pos) <= 64

  override def readFromNBT(compound: NBTTagCompound): Unit = {
    super.readFromNBT(compound)
    val itemList = compound.getTagList(inventoryNbtTag, 10)
    Range(0, itemList.tagCount()).foreach(i => {
      val tag = itemList.getCompoundTagAt(i)
      setInventorySlotContents(tag.getByte(slotNbtTag) & 255, ItemStack.loadItemStackFromNBT(tag))
    })
    if (compound.hasKey(customNameNbtTag)) customName = compound.getString(customNameNbtTag)
  }

  override def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
    super.writeToNBT(compound)
    val tagList = new NBTTagList
    forEachOccupiedSlot(i => {
      val stackTag = new NBTTagCompound
      stackTag.setByte(slotNbtTag, i.toByte)
      getStackInSlot(i).writeToNBT(stackTag)
      tagList.appendTag(stackTag)
    })
    compound.setTag(inventoryNbtTag, tagList)
    if (hasCustomName) compound.setString(customNameNbtTag, customName)
    compound
  }
}
