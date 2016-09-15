package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.ContainerFormicarium
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.inventory.IInventory

/**
  * Created by samtebbs on 09/08/2016.
  */
class GuiFormicarium(playerInv: IInventory, te: TileEntityFormicarium) extends MyrmecologyGuiContainer(BlockRegistry.formicariumName, new ContainerFormicarium(playerInv, te))
