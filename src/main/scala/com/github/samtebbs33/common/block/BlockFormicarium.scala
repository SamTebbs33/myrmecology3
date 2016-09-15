package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.common.{CraftingRecipe, GuiHandler}
import net.minecraft.block.material.Material
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 09/08/2016.
  */
class BlockFormicarium(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.formicariumID, classOf[TileEntityFormicarium]) {
  override def craftingRecipe: Option[CraftingRecipe] = Some(new CraftingRecipe(
    new ItemStack(this), Array("wdw", "gsg", "wdw"), Map(
      'w' → new ItemStack(Blocks.WOODEN_SLAB),
      'd' → new ItemStack(Items.DYE, 1, 1),
      'g' → new ItemStack(Blocks.GLASS),
      's' → new ItemStack(Blocks.SAND))
  ))
}
