package com.github.samtebbs33.common.item

import com.github.samtebbs33.common.{Craftable, Named}
import com.github.samtebbs33.registry.ItemRegistry
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.{Item, ItemStack}

/**
  * Created by samtebbs on 30/07/2016.
  */
abstract class MyrmecologyItem(name: String) extends Item with Named with Craftable{

  setUnlocalizedName(unlocalisedName(0))
  ItemRegistry.items.add(this)

  override def shortName: String = name

  def usesColourHandler: Boolean = false

  def getColourHandler: IItemColor = null

  override def getUnlocalizedName(stack: ItemStack): String = getUnlocalizedName

}
