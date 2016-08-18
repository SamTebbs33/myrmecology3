package com.github.samtebbs33.common.item

import com.github.samtebbs33.common.CraftingRecipe
import com.github.samtebbs33.common.block.BlockAntHill
import net.minecraft.block.state.IBlockState
import net.minecraft.init.Items
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 05/08/2016.
  */
class ItemAntExtractor(s: String) extends MyrmecologyItem(s) {

  override def canHarvestBlock(blockIn: IBlockState): Boolean = blockIn.getBlock.isInstanceOf[BlockAntHill]

  override def craftingRecipe: Option[CraftingRecipe] = Some(new CraftingRecipe(
    new ItemStack(this), Array("", "dsd", ""), Map('d' → new ItemStack(Items.DYE, 1, 2), 's' → new ItemStack(Items.IRON_SHOVEL))
  ))

}
