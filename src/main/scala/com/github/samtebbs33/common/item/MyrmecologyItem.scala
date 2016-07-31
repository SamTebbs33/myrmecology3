package com.github.samtebbs33.common.item

import com.github.samtebbs33.{Myrmecology, Named}
import net.minecraft.item.Item

/**
	* Created by samtebbs on 30/07/2016.
	*/
abstract class MyrmecologyItem(name: String) extends Item with Named {

	setUnlocalizedName(getLongName())

	override def getShortName(): String = name
}
