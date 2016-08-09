package com.github.samtebbs33.registry

import java.util.HashSet

import com.github.samtebbs33.common.block._
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 02/08/2016.
  */
object BlockRegistry {
  final val blocks = new HashSet[MyrmecologyBlock]()

  final val NAME_SOLARIUM = "solarium"
  final val NAME_BREEDING_CHAMBER = "breeding_chamber"

  final val solarium = new BlockSolarium(NAME_SOLARIUM)
  final val breedingChamber = new BlockBreedingChamber(NAME_BREEDING_CHAMBER)
  final val antHillPlains = new BlockAntHill(Registry.PLAINS, ItemRegistry.antPlains)

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
