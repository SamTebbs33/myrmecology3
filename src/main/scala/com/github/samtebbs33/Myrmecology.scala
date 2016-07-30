package com.github.samtebbs33

import com.github.samtebbs33.init.Registry.{AntSpeciesRegistry, BlockRegistry, ItemRegistry}
import net.minecraftforge.fml.common.{FMLCommonHandler, Mod}
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.relauncher.Side

/**
  * Created by samtebbs on 30/07/2016.
  */
@Mod(modid = Myrmecology.MOD_ID, modLanguage = "scala", version = Myrmecology.MOD_VERSION, name = Myrmecology.MOD_NAME)
object Myrmecology {

  final val MOD_ID = "mymecology"
  final val MOD_VERSION = "0.0.0"
  final val MOD_NAME = "Myrmecology"

  @EventHandler
  def preInit(e : FMLPreInitializationEvent) {
    println("myrmecology preInit")
    BlockRegistry.registerBlocks()
    AntSpeciesRegistry.registerSpecies()
    ItemRegistry.registerItems()
  }

  @EventHandler
  def init(e : FMLInitializationEvent) = println("myrmecology init")

  @EventHandler
  def init(e : FMLPostInitializationEvent) = println("myrmecology postInit")

  def onClient() = FMLCommonHandler.instance.getSide() == Side.CLIENT

  def onServer() = !onClient()

}
