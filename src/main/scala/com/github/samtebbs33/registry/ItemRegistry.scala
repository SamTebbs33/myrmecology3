package com.github.samtebbs33.registry

import java.util.HashSet
import java.util.function.Predicate

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.Species
import com.github.samtebbs33.common.block.MyrmecologyBlock
import com.github.samtebbs33.common.item.{ItemAnt, ItemAntExtractor, ItemMyrmopaedia, MyrmecologyItem}
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 02/08/2016.
  */
object ItemRegistry {

  final val NAME_MYRMOPAEDIA = "myrmopaedia"

  def getAnt(species: Species): Option[ItemAnt] = {
    val i = ItemRegistry.ants.stream().filter(new Predicate[ItemAnt] {
      override def test(t: ItemAnt): Boolean = t.species == species
    }).findFirst()
    if (i.isPresent) Some(i.get()) else None
  }

  final val items = new HashSet[MyrmecologyItem]()
  final val ants = new HashSet[ItemAnt]()

  final val antExtractor = new ItemAntExtractor("ant_extractor")
  final val myrmopaedia = new ItemMyrmopaedia(NAME_MYRMOPAEDIA)

  final val antPlains = new ItemAnt(AntSpeciesRegistry.speciesPlains)
  final val antForest = new ItemAnt(AntSpeciesRegistry.speciesForest)
  val antDesert = new ItemAnt(AntSpeciesRegistry.speciesDesert)
  val antSwamp = new ItemAnt(AntSpeciesRegistry.speciesSwamp)
  val antJungle = new ItemAnt(AntSpeciesRegistry.speciesJungle)
  val antStone = new ItemAnt(AntSpeciesRegistry.speciesStone)

  def registerItems(): Unit = {
    items.foreach(registerItem)
  }

  def registerBlock(block: MyrmecologyBlock): Unit = {
    val itemBlock = new ItemBlock(block)
    itemBlock.setRegistryName(block.getRegistryName)
    GameRegistry.register(itemBlock)
    Myrmecology.proxy.registerModel(itemBlock)
  }

  private def registerItem(item: MyrmecologyItem): Unit = {
    item.setRegistryName(item.registryName)
    GameRegistry.register(item)
    Myrmecology.proxy.registerModel(item)
  }

}
