package com.github.samtebbs33.init

import java.util.{Set, HashSet}

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.ant.AntSpecies
import com.github.samtebbs33.block.{BlockAntHill, MyrmecologyBlock}
import com.github.samtebbs33.item.MyrmecologyItem
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by samtebbs on 30/07/2016.
  */
object Registry {

  val renderItem = if(Myrmecology.onClient()) Some(Minecraft.getMinecraft().getRenderItem()) else None

  object AntSpeciesRegistry {
    final val species : Set[AntSpecies] = new HashSet[AntSpecies]
    final val testSpecies = new AntSpecies("test_species")

    def registerSpecies(): Unit = {
      registerSpecies(testSpecies)
    }

    def registerSpecies(s : AntSpecies): Unit = species.add(s)

  }

  object BlockRegistry {
    final val testHill = new BlockAntHill("test_hill", AntSpeciesRegistry.testSpecies)

    def registerBlocks(): Unit = {
      registerAntHills()
    }

    private def registerAntHills(): Unit = {
      registerBlock(testHill)
    }

    private def registerBlock(block : MyrmecologyBlock): Unit = {
      block.setRegistryName(Myrmecology.MOD_ID, block.getUnlocalisedName())
      GameRegistry.register(block)
      //val itemBlock = new ItemBlock(block)
      //itemBlock.setRegistryName(Myrmecology.MOD_ID, block.getUnlocalisedName() + "_item")
      //GameRegistry.register(itemBlock)
      if(Myrmecology.onClient()) renderItem.get.getItemModelMesher().register(Item.getItemFromBlock(block), 0, new ModelResourceLocation(Myrmecology.MOD_ID + ":" + block.getUnlocalisedName(), "inventory"))
    }

  }

  object ItemRegistry {
    def registerItems(): Unit = {
      // TODO
    }

    private def registerItem(item : MyrmecologyItem): Unit = {
      GameRegistry.register(item)
      if(Myrmecology.onClient()) renderItem.get.getItemModelMesher().register(item, 0, new ModelResourceLocation(Myrmecology.MOD_ID + ":" + item.getUnlocalisedName(), "inventory"));
    }

  }

}
