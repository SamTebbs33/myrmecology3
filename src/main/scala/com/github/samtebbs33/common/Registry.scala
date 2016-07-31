package com.github.samtebbs33.common

import java.util.{HashSet, Set}

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.AntSpecies
import com.github.samtebbs33.common.block.{BlockAntHill, MyrmecologyBlock}
import com.github.samtebbs33.common.item.MyrmecologyItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

/**
	* Created by samtebbs on 30/07/2016.
	*/
object Registry {

	final val creativeTab = makeCreativeTab(Items.ARROW)

	object AntSpeciesRegistry {
		final val species: Set[AntSpecies] = new HashSet[AntSpecies]
		final val testSpecies = new AntSpecies("test_species")

		def registerSpecies() {
			registerSpecies(testSpecies)
		}

		def registerSpecies(s: AntSpecies): Unit = species.add(s)

	}

	object BlockRegistry {
		final val antHillPlains = new BlockAntHill("plains", AntSpeciesRegistry.testSpecies)

		def registerBlocks() {
			registerAntHills()
		}

		private def registerAntHills() {
			registerBlock(antHillPlains)
		}

		private def registerBlock(block: MyrmecologyBlock) {
			block.setRegistryName(block.getExternalName())
			GameRegistry.register(block)
			ItemRegistry.registerBlock(block)
		}

	}

	object ItemRegistry {
		final val antExtractor = new MyrmecologyItem("ant_extractor") {}

		def registerItems() {
			registerItem(antExtractor)
		}

		def registerBlock(block: MyrmecologyBlock) {
			val itemBlock = new ItemBlock(block)
			itemBlock.setRegistryName(block.getRegistryName)
			GameRegistry.register(itemBlock)
			Myrmecology.proxy.registerModel(itemBlock)
		}

		private def registerItem(item: MyrmecologyItem) {
			item.setRegistryName(item.getExternalName())
			GameRegistry.register(item)
			Myrmecology.proxy.registerModel(item)
		}

	}

	private def makeCreativeTab(iconItem : Item) = new CreativeTabs(Myrmecology.MOD_ID) {
			@SideOnly(Side.CLIENT)
			override def getTabIconItem = iconItem
		}

}
