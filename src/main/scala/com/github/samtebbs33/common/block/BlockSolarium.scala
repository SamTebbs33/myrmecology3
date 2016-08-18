package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import com.github.samtebbs33.common.{CraftingRecipe, GuiHandler}
import net.minecraft.block.material.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 05/08/2016.
  */
class BlockSolarium(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.ID_SOLARIUM, classOf[TileEntitySolarium]) {
  override def craftingRecipe: Option[CraftingRecipe] = Some(new CraftingRecipe(
    new ItemStack(this), Array("sss", "g g", "sls"), Map(
      's' → new ItemStack(Blocks.WOODEN_SLAB),
      'g' → new ItemStack(Blocks.GLASS),
      'l' → new ItemStack(Blocks.REDSTONE_LAMP)
    )
  ))
}
