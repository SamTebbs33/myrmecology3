package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.inventory.{IInventory, Slot}

/**
  * Created by samtebbs on 09/08/2016.
  */
class ContainerFormicarium(playerInv: IInventory, te: TileEntityFormicarium) extends MyrmecologyContainer(playerInv, te) {
  override def slots: util.List[Slot] = ???
}
