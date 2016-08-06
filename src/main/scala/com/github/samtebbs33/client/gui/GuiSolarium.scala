package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.ContainerSolarium
import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntityContainer, TileEntitySolarium}
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.inventory.IInventory

/**
	* Created by samtebbs on 06/08/2016.
	*/
class GuiSolarium(playerInv : IInventory, te  : TileEntitySolarium) extends GuiContainer(new ContainerSolarium(playerInv, te)) {
	override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = ???
}
