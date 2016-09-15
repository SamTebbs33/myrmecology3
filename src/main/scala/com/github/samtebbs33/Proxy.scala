package com.github.samtebbs33

import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.worldgen.WorldGen
import com.github.samtebbs33.registry.{BlockRegistry, ItemRegistry, OreDictRegistry, Registry}
import net.minecraft.item.Item
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.network.NetworkRegistry
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by samtebbs on 31/07/2016.
  */
class Proxy {

  def registerModel(item: Item): Unit = {}

  def init(e: FMLInitializationEvent): Unit = {
    BlockRegistry.registerBlocks
    ItemRegistry.registerItems
    Registry.registerCreativeTab
    NetworkRegistry.INSTANCE.registerGuiHandler(Myrmecology, GuiHandler)
    Registry.registerCraftingRecipes
  }

  def preInit(e: FMLPreInitializationEvent): Unit = {
    BlockRegistry.registerTileEntities()
    GameRegistry.registerWorldGenerator(new WorldGen, 10)
  }

  def postInit(e: FMLPostInitializationEvent): Unit = {
    OreDictRegistry.registerBlock("crop")
  }

}
