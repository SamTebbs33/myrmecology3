package com.github.samtebbs33.common.container

import java.util

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.inventory.{IInventory, Slot}

/**
  * Created by samtebbs on 09/08/2016.
  */
class ContainerFormicarium(playerInv: IInventory, te: TileEntityFormicarium) extends MyrmecologyContainer(playerInv, te) {
  override def slots: util.List[Slot] = {
    val list = new util.LinkedList[Slot]
    def addSlotGroup(baseX : Int, baseY : Int, baseID: Int): Unit = {
      var id = baseID
      Range(0, 3).foreach(y ⇒ Range(0, 3).foreach(x ⇒ {
        list.add(new Slot(te, id, baseX + 18 * x, baseY + 18 * y))
        id += 1
      }))
    }
    addSlotGroup(18, 17, 0)
    addSlotGroup(99, 17, 9)
    list
  }
}
