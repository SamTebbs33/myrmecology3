package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.block.material.Material

/**
  * Created by samtebbs on 09/08/2016.
  */
class BlockFormicarium(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.ID_FORMICARIUM, classOf[TileEntityFormicarium]) {



}
