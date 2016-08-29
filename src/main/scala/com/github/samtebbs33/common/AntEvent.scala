package com.github.samtebbs33.common

import com.github.samtebbs33.common.tileentity.{TileEntityBreedingChamber, TileEntitySolarium}
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.Event

/**
  * Created by samtebbs on 28/08/2016.
  */

object AntEvent {
  class AntEvent(cancelable: Boolean = true) extends Event {
    override def isCancelable: Boolean = cancelable
  }
  class AntBreedEvent(val drone: ItemStack, val queen: ItemStack, val larva: ItemStack, val breedingChamber: TileEntityBreedingChamber) extends AntEvent
  class AntMatureEvent(val result: ItemStack, val solarium: TileEntitySolarium) extends AntEvent
  class AntHillExtractionEvent(val pos: BlockPos, val player: EntityPlayer, val extractor: ItemStack) extends AntEvent(false)

  def dispatch(event: AntEvent, canceled: () ⇒ Unit = () ⇒ (), notCanceled: () ⇒ Unit = () ⇒ ()) = {
    MinecraftForge.EVENT_BUS.post(event)
    event.isCanceled match {
      case true ⇒ canceled()
      case _ ⇒ notCanceled()
    }
  }

}