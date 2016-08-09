package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import net.minecraft.inventory.{IInventory, Slot}

/**
  * Created by samtebbs on 06/08/2016.
  */
class ContainerSolarium(playerInv: IInventory, te: TileEntitySolarium) extends MyrmecologyContainer(playerInv, te) {

  var progress = 0

  override def slots: util.List[Slot] = {
    val list = new util.LinkedList[Slot]
    // Larva slot
    list.add(new Slot(te, 0, 8, 17))
    // Mature ant slots
    val rows = 3
    val cols = 5
    var id = 0
    Range(0, rows).foreach(y => Range(0, cols).foreach(x => {
      id += 1
      list.add(new Slot(te, id, 62 + x * 18, 17 + y * 18))
    }))
    list
  }

}
