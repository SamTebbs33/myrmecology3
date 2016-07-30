package com.github.samtebbs33.init

import java.util.{ArrayList, List}

import com.github.samtebbs33.ant.AntSpecies
import com.github.samtebbs33.block.BlockAntHill
import net.minecraft.block.Block
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by samtebbs on 30/07/2016.
  */
object Registry {

  object AntSpeciesRegistry {
    final val species : List[AntSpecies] = new ArrayList[AntSpecies]
    final val testSpecies = new AntSpecies("test_species")

    def registerSpecies(): Unit {
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

    private def registerBlock(block : Block): Unit = GameRegistry.register(block)

  }

  object ItemRegistry {
    def registerItems(): Unit = {
      // TODO
    }
  }

}
