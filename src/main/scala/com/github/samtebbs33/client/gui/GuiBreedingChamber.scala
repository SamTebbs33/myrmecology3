package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.ContainerBreedingChamber
import com.github.samtebbs33.common.tileentity.TileEntityBreedingChamber
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.inventory.IInventory

/**
  * Created by samtebbs on 08/08/2016.
  */
class GuiBreedingChamber(playerInv: IInventory, te: TileEntityBreedingChamber) extends MyrmecologyGuiContainer(BlockRegistry.breddingChamberName, new ContainerBreedingChamber(playerInv, te)) {

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    super.drawGuiContainerBackgroundLayer(partialTicks, mouseX, mouseY)
    val progress = te.tracker.progress
    val targetTime = te.tracker.targetTime

    if (progress > 0) {
      val scaledProgress = progress * 13 / targetTime
      drawTexturedModalRect(k + 29, l + 36, 176, 0, scaledProgress + 1, 16);
    }
  }
}
