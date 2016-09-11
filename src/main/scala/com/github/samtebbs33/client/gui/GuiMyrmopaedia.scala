package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.{ContainerMyrmopaedia, MyrmecologyContainer}
import com.github.samtebbs33.common.item.ItemAnt
import com.github.samtebbs33.registry.ItemRegistry
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

import scala.collection.mutable

/**
  * Created by samuelt on 31/08/2016.
  */
class GuiMyrmopaedia(playerInv: IInventory, itemStack: ItemStack) extends MyrmecologyGuiContainer(ItemRegistry.NAME_MYRMOPAEDIA, new ContainerMyrmopaedia(playerInv, itemStack)) {

  var lines = new mutable.MutableList[String]()
  var screen = 0
  var ant = getAntFromInventory

  var infoScreen = () ⇒ {
    val species = ItemAnt.getSpecies(ant)
    lines += s"Binomial name: ${species.binomialName}"
    lines += s"Behaviour: ${ItemAnt.getBehaviour(ant)}"
  }
  val screens = Array(infoScreen)

  xSize += xSize / 4
  ySize += ySize / 2 + 7

  def getAntFromInventory = container.inv.getStackInSlot(0)

  override def drawGuiContainerForegroundLayer(mouseX: Int, mouseY: Int): Unit = {
    super.drawGuiContainerForegroundLayer(mouseX, mouseY)
    var lastY = 0
    lines.zipWithIndex.foreach(pair ⇒ {
      fontRendererObj.drawString(pair._1, 12, 8 * (pair._2 + 3) + lastY, 0xFFFFFF)
      lastY += 2
    })
  }

  def refreshPage(): Unit = {
    println("refreshPage")
    lines.clear()
    screens(screen).apply()
  }

  override def updateScreen(): Unit = {
    super.updateScreen()
    val newAnt = getAntFromInventory
    println(newAnt)
    if(newAnt != null && !newAnt.equals(ant)) {
      ant = newAnt
      refreshPage()
    }
  }
}
