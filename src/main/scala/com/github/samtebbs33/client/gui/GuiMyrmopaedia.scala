package com.github.samtebbs33.client.gui

import com.github.samtebbs33.common.container.{ContainerMyrmopaedia, MyrmecologyContainer}
import com.github.samtebbs33.registry.ItemRegistry
import net.minecraft.inventory.IInventory
import net.minecraft.item.ItemStack

/**
  * Created by samuelt on 31/08/2016.
  */
class GuiMyrmopaedia(playerInv: IInventory, itemStack: ItemStack) extends MyrmecologyGuiContainer(ItemRegistry.NAME_MYRMOPAEDIA, new ContainerMyrmopaedia(playerInv, itemStack)) {

  xSize += xSize / 4
  ySize += ySize / 2 + 7

}
