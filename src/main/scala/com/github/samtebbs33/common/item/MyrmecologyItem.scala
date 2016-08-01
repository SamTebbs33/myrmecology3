package com.github.samtebbs33.common.item

import com.github.samtebbs33.Named
import com.github.samtebbs33.common.Registry
import com.github.samtebbs33.common.Registry.ItemRegistry
import net.minecraft.item.Item

/**
	* Created by samtebbs on 30/07/2016.
	*/
abstract class MyrmecologyItem(name: String) extends Item with Named {

	setUnlocalizedName(longName)
	setCreativeTab(Registry.creativeTab)
	ItemRegistry.items.add(this)

	override def shortName: String = name
}
