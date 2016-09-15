package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.ContainerSolarium
import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.inventory.IInventory

/**
  * Created by samtebbs on 06/08/2016.
  */
class GuiSolarium(playerInv: IInventory, te: TileEntitySolarium) extends MyrmecologyGuiContainer(BlockRegistry.solariumName, new ContainerSolarium(playerInv, te)) {

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)

    val progress = te.tracker.progress
    val targetTime = te.tracker.targetTime

    if (progress > 0) {
      val scaledProgress = progress * 24 / targetTime
      drawTexturedModalRect(k + 31, l + 16, 176, 0, scaledProgress + 1, 16)
    }
  }
}
