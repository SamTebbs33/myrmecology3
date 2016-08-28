package com.github.samtebbs33.common

import com.github.samtebbs33.common.tileentity.TileEntityBreedingChamber
import net.minecraft.item.ItemStack
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

/**
  * Created by samtebbs on 28/08/2016.
  */

object AntEvent {
  class AntBreedEvent(val drone: ItemStack, val queen: ItemStack, val larva: ItemStack, val breedingChamber: TileEntityBreedingChamber) extends AntEvent

  def dispatch(event: AntEvent) = MinecraftForge.EVENT_BUS.post(event)

}

class AntEvent extends Event {

}
