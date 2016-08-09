package com.github.samtebbs33.client.gui

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.container.MyrmecologyContainer
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.client.gui.inventory.GuiContainer
import net.minecraft.client.renderer.GlStateManager
import net.minecraft.util.ResourceLocation

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class MyrmecologyGuiContainer(texturePath: String, container: MyrmecologyContainer) extends GuiContainer(container) {

  val textureLocation = new ResourceLocation(Myrmecology.MOD_ID, "textures/gui/" + texturePath + ".png")

  var k : Int = 0
  var l : Int = 0

  override def drawGuiContainerBackgroundLayer(partialTicks: Float, mouseX: Int, mouseY: Int): Unit = {
    GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F)
    mc.getTextureManager.bindTexture(textureLocation)
    k = (width - xSize) / 2
    l = (height - ySize) / 2
    drawTexturedModalRect(k, l, 0, 0, xSize, ySize)
  }
}
