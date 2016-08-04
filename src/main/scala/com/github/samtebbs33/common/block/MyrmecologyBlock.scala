package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.Named
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs
import registry.{BlockRegistry, Registry}

/**
	* Created by samtebbs on 30/07/2016.
	*/
abstract class MyrmecologyBlock(material: Material, name: String) extends Block(material) with Named {

	setUnlocalizedName(unlocalisedName(0))
	setCreativeTab(Registry.creativeTab)
	BlockRegistry.blocks.add(this)

	override def shortName: String = name
}
