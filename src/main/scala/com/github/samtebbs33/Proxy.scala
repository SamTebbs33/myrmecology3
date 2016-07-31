package com.github.samtebbs33

import com.github.samtebbs33.common.Registry.{AntSpeciesRegistry, BlockRegistry, ItemRegistry}
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

/**
	* Created by samtebbs on 31/07/2016.
	*/
class Proxy {

	def registerModel(item: Item) {}

	def init(e: FMLInitializationEvent): Unit = {
		AntSpeciesRegistry.registerSpecies()
		BlockRegistry.registerBlocks()
		ItemRegistry.registerItems()
	}

	def preInit(e: FMLPreInitializationEvent) {

	}

	def postInit(e: FMLPostInitializationEvent) {}

}
