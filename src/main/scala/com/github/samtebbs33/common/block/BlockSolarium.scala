package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import net.minecraft.block.material.Material

/**
  * Created by samtebbs on 05/08/2016.
  */
class BlockSolarium(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.ID_SOLARIUM, classOf[TileEntitySolarium]) {
}
