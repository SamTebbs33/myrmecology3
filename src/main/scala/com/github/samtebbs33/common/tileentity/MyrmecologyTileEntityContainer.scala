package com.github.samtebbs33.common.tileentity

import java.util.Objects

import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.{IInventory, Slot}
import net.minecraft.item.ItemStack
import net.minecraft.nbt.{NBTTagCompound, NBTTagList}

/**
	* Created by samtebbs on 06/08/2016.
	*/
abstract class MyrmecologyTileEntityContainer(val name : String, invSize : Int) extends MyrmecologyTileEntity with IInventory {

	val inventory = new Array[Option[ItemStack]](invSize)
	Range(0, inventory.length).foreach(i => inventory(i) = None)
	val NBT_INVENTORY_TAG = "Items"
	val NBT_SLOT_TAG = "Slot"
	val NBT_CUSTOM_NAME_TAG = "CustomName"

	var customName = ""

	def inBounds(index: Int) = index < getSizeInventory && index >= 0

	override def decrStackSize(index: Int, count: Int): ItemStack = {
		val stack = getStackInSlot(index)
		if(stack != null) {
			if(stack.stackSize <= count) setInventorySlotContents(index, null)
			else setInventorySlotContents(index, stack.splitStack(count))
		}
		stack
	}

	override def closeInventory(player: EntityPlayer): Unit = ???

	override def getSizeInventory: Int = invSize

	override def setInventorySlotContents(index: Int, stack: ItemStack): Unit = {
		if(inBounds(index)) inventory.update(index, Option(stack))
		markDirty()
	}

	override def getStackInSlot(index: Int): ItemStack =
		if(inBounds(index)) inventory(index).orNull
		else null

	override def removeStackFromSlot(index: Int): ItemStack = {
		val temp = getStackInSlot(index)
		setInventorySlotContents(index, null)
		temp
	}

	override def clear(): Unit = forEachSlot(removeStackFromSlot)

	override def getName: String = if(hasCustomName) customName else "container." + name

	override def hasCustomName: Boolean = !customName.isEmpty

	override def isUseableByPlayer(player: EntityPlayer): Boolean = player.getDistanceSq(this.pos) <= 64

	def slotIsEmpty(i : Int) = getStackInSlot(i) == null
	def slotIsNotEmpty(i : Int) = !slotIsEmpty(i)
	def forEachSlot[T](f : (Int) => T) = Range(0, getSizeInventory).foreach(f)
	def forEachOccupiedSlot[T](f : (Int) => T) = Range(0, getSizeInventory).filter(slotIsNotEmpty).foreach(f)

	override def readFromNBT(compound: NBTTagCompound): Unit = {
		super.readFromNBT(compound)
		val itemList = compound.getTagList(NBT_INVENTORY_TAG, 10)
		Range(0, itemList.tagCount()).foreach(i => {
			val tag = itemList.getCompoundTagAt(i)
			setInventorySlotContents(tag.getByte(NBT_SLOT_TAG) & 255, ItemStack.loadItemStackFromNBT(tag))
		})
		if(compound.hasKey(NBT_CUSTOM_NAME_TAG)) customName = compound.getString(NBT_CUSTOM_NAME_TAG)
	}

	override def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
		super.writeToNBT(compound)
		val tagList = new NBTTagList
		forEachOccupiedSlot(i => {
			val stackTag = new NBTTagCompound
			stackTag.setByte(NBT_SLOT_TAG, i.toByte)
			getStackInSlot(i).writeToNBT(stackTag)
			tagList.appendTag(stackTag)
		})
		compound.setTag(NBT_INVENTORY_TAG, tagList)
		if(hasCustomName) compound.setString(NBT_CUSTOM_NAME_TAG, customName)
		compound
	}
}
