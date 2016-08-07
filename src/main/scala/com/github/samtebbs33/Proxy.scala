package com.github.samtebbs33

import com.github.samtebbs33.common.GuiHandler
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.network.NetworkRegistry
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
		NetworkRegistry.INSTANCE.registerGuiHandler(Myrmecology, GuiHandler)
	}

	def preInit(e: FMLPreInitializationEvent): Unit = {
		BlockRegistry.registerTileEntities()
	}

	def postInit(e: FMLPostInitializationEvent): Unit = {}

}
