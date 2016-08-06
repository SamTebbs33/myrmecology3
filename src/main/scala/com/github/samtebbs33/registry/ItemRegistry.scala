package com.github.samtebbs33.registry

import java.util.HashSet

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.block.MyrmecologyBlock
import com.github.samtebbs33.common.item.{ItemAnt, ItemAntExtractor, MyrmecologyItem}
import net.minecraft.item.ItemBlock
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.collection.JavaConversions._

/**
	* Created by samtebbs on 02/08/2016.
	*/
object ItemRegistry {
	final val items = new HashSet[MyrmecologyItem]()

	final val antExtractor = new ItemAntExtractor("ant_extractor")
	final val antPlains = new ItemAnt(AntSpeciesRegistry.speciesPlains)

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
