package com.github.samtebbs33.init

import java.util.{ArrayList, List}

import com.github.samtebbs33.ant.AntSpecies
import net.minecraft.block.Block
import net.minecraftforge.fml.common.registry.GameRegistry

/**
  * Created by samtebbs on 30/07/2016.
  */
object Registry {

  final val species : List[AntSpecies] = ArrayList[AntSpecies]

  def registerSpecies(): Unit = {
    // TODO
  }

  def registerBlocks(): Unit = {
    registerAntHills()
  }

  private def registerAntHills(): Unit = {
    // TODO
  }

  private def registerBlock(block : Block) = GameRegistry.register(block)

}
