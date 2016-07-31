package com.github.samtebbs33.common.block

import com.github.samtebbs33.Named
import com.github.samtebbs33.common.Registry.BlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.Material
import net.minecraft.creativetab.CreativeTabs

/**
	* Created by samtebbs on 30/07/2016.
	*/
abstract class MyrmecologyBlock(material: Material, name: String) extends Block(material) with Named {

	setUnlocalizedName(getLongName())
	setCreativeTab(CreativeTabs.BUILDING_BLOCKS)
	BlockRegistry.blocks.add(this)

	override def getShortName(): String = name
}
