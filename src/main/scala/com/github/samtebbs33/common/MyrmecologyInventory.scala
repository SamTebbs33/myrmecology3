package com.github.samtebbs33.common

import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

/**
  * Created by samuelt on 31/08/2016.
  */
trait MyrmecologyInventory extends IInventory {
  def inventoryArray: Array[Option[ItemStack]]
}
