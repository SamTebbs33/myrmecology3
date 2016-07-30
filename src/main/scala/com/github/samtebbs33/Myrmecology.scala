package com.github.samtebbs33

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}

/**
  * Created by samtebbs on 30/07/2016.
  */
@Mod(modid = Myrmecology.MOD_ID, modLanguage = "scala", version = Myrmecology.MOD_VERSION, name = Myrmecology.MOD_NAME)
object Myrmecology {

  final val MOD_ID = "mymecology"
  final val MOD_VERSION = "0.0.0"
  final val MOD_NAME = "Myrmecology"

  @EventHandler
  def preInit(e : FMLPreInitializationEvent) = println("myrmecology preInit")

  @EventHandler
  def init(e : FMLInitializationEvent) = println("myrmecology init")

  @EventHandler
  def init(e : FMLPostInitializationEvent) = println("myrmecology postInit")

}
