package com.github.samtebbs33.common.item

import java.util

import com.github.samtebbs33.common.ant.{AntSpecies, AntTypes}
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}

/**
	* Created by samtebbs on 31/07/2016.
	*/
abstract class ItemAnt(species : AntSpecies) extends MyrmecologyItem("ant_" + species.name) {

	setHasSubtypes(true)

	override def getSubItems(itemIn: Item, tab: CreativeTabs, subItems: util.List[ItemStack]): Unit = for (elem <- AntTypes.values) {subItems.add(new ItemStack(itemIn, 0, elem.id))}

	override def showDurabilityBar(stack: ItemStack): Boolean = false
}
