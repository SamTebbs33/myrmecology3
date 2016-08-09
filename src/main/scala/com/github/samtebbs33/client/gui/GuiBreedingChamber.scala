package com.github.samtebbs33.client.gui

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.container.ContainerBreedingChamber
import com.github.samtebbs33.common.tileentity.TileEntityBreedingChamber
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.inventory.IInventory
import net.minecraft.util.ResourceLocation

/**
  * Created by samtebbs on 08/08/2016.
  */
class GuiBreedingChamber(playerInv: IInventory, te: TileEntityBreedingChamber) extends GuiContainer(new ContainerBreedingChamber(playerInv, te)) {

  val textureLocation = new ResourceLocation(Myrmecology.MOD_ID, "textures/gui/" + BlockRegistry.NAME_BREEDING_CHAMBER + ".png")

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F)
    mc.getTextureManager.bindTexture(textureLocation)
    val k = (width - xSize) / 2
    val l = (height - ySize) / 2
    drawTexturedModalRect(k, l, 0, 0, xSize, ySize)

    val progress = te.progress
    val targetTime = te.targetTime

    if (progress > 0) {
      val scaledProgress = progress * 13 / targetTime
      drawTexturedModalRect(k + 29, l + 36, 176, 0, progress + 1, 16);
    }
  }
}
