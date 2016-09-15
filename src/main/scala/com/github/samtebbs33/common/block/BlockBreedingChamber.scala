package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.tileentity.TileEntityBreedingChamber
import com.github.samtebbs33.common.{CraftingRecipe, GuiHandler}
import net.minecraft.block.material.Material
import net.minecraft.init.Blocks
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 07/08/2016.
  */
class BlockBreedingChamber(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.breedingChambedID, classOf[TileEntityBreedingChamber]) {
  override def craftingRecipe: Option[CraftingRecipe] = Some(new CraftingRecipe(
    new ItemStack(this), Array("www", "wsw", "www"), Map(
      'w' → new ItemStack(Blocks.WOODEN_SLAB),
      's' → new ItemStack(Blocks.SAND)
    )
  ))
}
