package com.github.samtebbs33.common.item

import com.github.samtebbs33.common.block.BlockAntHill
import net.minecraft.block.state.IBlockState

/**
  * Created by samtebbs on 05/08/2016.
  */
class ItemAntExtractor(s: String) extends MyrmecologyItem(s) {

  override def canHarvestBlock(blockIn: IBlockState): Boolean = blockIn.getBlock.isInstanceOf[BlockAntHill]

}
