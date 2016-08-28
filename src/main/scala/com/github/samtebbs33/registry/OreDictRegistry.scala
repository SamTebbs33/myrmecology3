package com.github.samtebbs33.registry

import net.minecraft.block.Block
import net.minecraft.item.ItemBlock
import net.minecraftforge.oredict.OreDictionary

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * Created by samtebbs on 24/08/2016.
  */
object OreDictRegistry {

  val blockMap = mutable.Map[String, mutable.MutableList[Block]]()

  def registerBlock(oreDictName: String): Unit = {
    val itemBlocks = OreDictionary.getOres(oreDictName).map(_.getItem).filter(_.isInstanceOf[ItemBlock]).map(_.asInstanceOf[ItemBlock].block)
    itemBlocks.foreach(block ⇒ blockMap.getOrElseUpdate(oreDictName, new mutable.MutableList[Block]()).add(block))
  }

}
