package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.Named
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.block.Block
import net.minecraft.block.material.Material

/**
  * Created by samtebbs on 30/07/2016.
  */
abstract class MyrmecologyBlock(material: Material, name: String) extends Block(material) with Named {

  setUnlocalizedName(unlocalisedName(0))
  BlockRegistry.blocks.add(this)

  override def shortName: String = name
}
