package com.github.samtebbs33.registry

import com.github.samtebbs33.common.block._
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 02/08/2016.
  */
object BlockRegistry {
  final val blocks = new java.util.LinkedList[MyrmecologyBlock]()
  val antHills = new java.util.ArrayList[BlockAntHill]()

  final val solariumName = "solarium"
  final val formicariumName = "formicarium"
  final val breddingChamberName = "breeding_chamber"

  final val solarium = new BlockSolarium(solariumName)
  final val breedingChamber = new BlockBreedingChamber(breddingChamberName)
  final val formicarium = new BlockFormicarium(formicariumName)

  final val antHillPlains = new BlockAntHill(Registry.plainsName, ItemRegistry.antPlains)
  final val antHillForest = new BlockAntHill(Registry.forestName, ItemRegistry.antForest)
  val antHillDesert = new BlockAntHill(Registry.desertName, ItemRegistry.antDesert)
  val antHillSwamp = new BlockAntHill(Registry.swampName, ItemRegistry.antSwamp)
  val antHillJungle = new BlockAntHill(Registry.jungleName, ItemRegistry.antJungle)
  val antHillStone = new BlockAntHill(Registry.stoneName, ItemRegistry.antStone)

  def registerTileEntities(): Unit = {
    blocks.foreach[Unit] {
      case block: MyrmecologyBlockEntity[_] => GameRegistry.registerTileEntity(block.tileEntityClass, block.shortName)
      case _ => // Crashes without this line
    }
  }

  def registerBlocks(): Unit = {
    blocks.foreach(registerBlock)
  }

  private def registerBlock(block: MyrmecologyBlock): Unit = {
    block.setRegistryName(block.registryName)
    GameRegistry.register(block)
    ItemRegistry.registerBlock(block)
  }

}
