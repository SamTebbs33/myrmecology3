package com.github.samtebbs33

import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import registry.{BlockRegistry, ItemRegistry, Registry}

/**
	* Created by samtebbs on 31/07/2016.
	*/
class Proxy {

	def registerModel(item: Item) {}

	def init(e: FMLInitializationEvent): Unit = {
		BlockRegistry.registerBlocks
		ItemRegistry.registerItems
		Registry.registerCreativeTab
	}

	def preInit(e: FMLPreInitializationEvent): Unit = {}

	def postInit(e: FMLPostInitializationEvent): Unit = {}

}
