package com.github.samtebbs33.client.gui

import akka.io.Tcp.Register
import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.container.ContainerSolarium
import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntityContainer, TileEntitySolarium}
import com.github.samtebbs33.registry.{BlockRegistry, Registry}
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.inventory.IInventory
import net.minecraft.util.ResourceLocation

/**
	* Created by samtebbs on 06/08/2016.
	*/
class GuiSolarium(playerInv : IInventory, te  : TileEntitySolarium) extends GuiContainer(new ContainerSolarium(playerInv, te)) {

	val textureLocation = new ResourceLocation(Myrmecology.MOD_ID, "textures/gui/" + BlockRegistry.NAME_SOLARIUM + ".png")

	override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
		GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F)
		mc.getTextureManager.bindTexture(textureLocation)
		val k = (width - xSize) / 2
		val l = (height - ySize) / 2
		drawTexturedModalRect(k, l, 0, 0, xSize, ySize)

		val progress = te.progress
		val targetTime = te.targetTime

		if (progress > 0) {
			val scaledProgress = progress * 24 / targetTime // TODO
			drawTexturedModalRect(k + 31, l + 16, 176, 0, scaledProgress + 1, 16)
		}
	}
}
