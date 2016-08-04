package com.github.samtebbs33.common.item

import com.github.samtebbs33.common.Named
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.item.{Item, ItemStack}
import registry.{ItemRegistry, Registry}

/**
	* Created by samtebbs on 30/07/2016.
	*/
abstract class MyrmecologyItem(name: String) extends Item with Named {

	setUnlocalizedName(unlocalisedName(0))
	setCreativeTab(Registry.creativeTab)
	ItemRegistry.items.add(this)

	override def shortName: String = name

	def usesColourHandler : Boolean = false

	def getColourHandler : IItemColor = null

	override def getUnlocalizedName(stack: ItemStack): String = getUnlocalizedName

}
