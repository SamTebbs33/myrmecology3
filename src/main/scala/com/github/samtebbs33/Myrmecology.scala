package com.github.samtebbs33

import com.github.samtebbs33.common.event.AntEvent.AntBreedEvent
import com.github.samtebbs33.common.event.AntEventHandler
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.event.ForgeEventFactory
import net.minecraftforge.fml.common.Mod.EventHandler
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPostInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import net.minecraftforge.fml.common.{Mod, SidedProxy}

/**
  * Created by samtebbs on 30/07/2016.
  */
@Mod(modid = Myrmecology.modID, modLanguage = "scala", version = Myrmecology.modVersion, name = Myrmecology.modName)
object Myrmecology {

  final val modID = "myrmecology"
  final val modVersion = "0.0.0"
  final val modName = "Myrmecology"

  @SidedProxy(clientSide = "com.github.samtebbs33.client.ClientProxy", serverSide = "com.github.samtebbs33.server.ServerProxy", modId = modID)
  var proxy: Proxy = _

  @EventHandler
  def preInit(e: FMLPreInitializationEvent): Unit = {
    proxy.preInit(e)
  }

  @EventHandler
  def init(e: FMLInitializationEvent): Unit = {
    proxy.init(e)
  }

  @EventHandler
  def postInit(e: FMLPostInitializationEvent): Unit = {
    proxy.postInit(e)
    MinecraftForge.EVENT_BUS.register(new AntEventHandler)
  }

}
