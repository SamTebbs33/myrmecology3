package com.github.samtebbs33.common

import java.util
import java.util.function.Consumer
import java.util.{HashSet, Set}

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.{AntSpecies, AntTrait, AntTraits}
import com.github.samtebbs33.common.block.{BlockAntHill, MyrmecologyBlock}
import com.github.samtebbs33.common.item.MyrmecologyItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.{Biomes, Items}
import net.minecraft.item.{Item, ItemBlock}
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

/**
	* Created by samtebbs on 30/07/2016.
	*/
object Registry {

	final val creativeTab = makeCreativeTab(Items.ARROW)

	object AntTraitsRegistry {
		final val traitIsWinged = new AntTrait("isWinged")
		final val traitIsNocturnal = new AntTrait("isNocturnal")

		final val basicTraits = new AntTraits(traitIsNocturnal, traitIsWinged)

	}

	object AntSpeciesRegistry {
		final val species: Set[AntSpecies] = new HashSet[AntSpecies]

		final val speciesPlains = new AntSpecies("species_plains", "Antus Fieldia", AntTraitsRegistry.basicTraits, Biomes.PLAINS) {}
	}

	object BlockRegistry {
		final val blocks = new HashSet[MyrmecologyBlock]()

		final val antHillPlains = new BlockAntHill("plains", AntSpeciesRegistry.speciesPlains)

		def registerBlocks() {
			blocks.forEach(new Consumer[MyrmecologyBlock] {
				override def accept(t: MyrmecologyBlock): Unit = registerBlock(t)
			})
		}

		private def registerBlock(block: MyrmecologyBlock) {
			block.setRegistryName(block.getExternalName())
			GameRegistry.register(block)
			ItemRegistry.registerBlock(block)
		}

	}

	object ItemRegistry {
		final val items = new HashSet[MyrmecologyItem]()

		final val antExtractor = new MyrmecologyItem("ant_extractor") {}

		def registerItems() {
			items.forEach(new Consumer[MyrmecologyItem] {
				override def accept(t: MyrmecologyItem): Unit = registerItem(t)
			})
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
