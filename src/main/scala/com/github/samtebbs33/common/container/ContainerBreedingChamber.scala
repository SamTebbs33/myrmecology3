package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.tileentity.MyrmecologyTileEntityContainer
import net.minecraft.inventory.{IInventory, Slot}

/**
  * Created by samtebbs on 08/08/2016.
  */
class ContainerBreedingChamber(playerInv: IInventory, te: MyrmecologyTileEntityContainer) extends MyrmecologyContainer(playerInv, te) {
  override def slots: util.List[Slot] = {
    val list = new util.LinkedList[Slot]
    // Drone slot
    list.add(new Slot(te, 0, 27, 17))
    // Queen slot
    list.add(new Slot(te, 1, 27, 53))
    // Mature ant slots
    val rows = 3
    val cols = 5
    var id = 1
    Range(0, rows).foreach(y => Range(0, cols).foreach(x => {
      id += 1
      list.add(new Slot(te, id, 62 + x * 18, 17 + y * 18))
    }))
    list
  }
}
