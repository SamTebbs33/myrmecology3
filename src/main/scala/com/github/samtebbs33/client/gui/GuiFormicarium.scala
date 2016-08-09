package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.ContainerFormicarium
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.IInventory

/**
  * Created by samtebbs on 09/08/2016.
  */
class GuiFormicarium(playerInv: IInventory, te: TileEntityFormicarium) extends GuiContainer(new ContainerFormicarium(playerInv, te)) {
  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = ???
}
