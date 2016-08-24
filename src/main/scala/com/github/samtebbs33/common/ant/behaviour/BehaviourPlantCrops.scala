package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.BlockCrops
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemSeeds, ItemStack}
import net.minecraft.util.math.{BlockPos, Vec3i}
import net.minecraftforge.common.IPlantable
import net.minecraftforge.oredict.OreDictionary

/**
  * Created by samuelt on 24/08/2016.
  */
class BehaviourPlantCrops(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    val plantableItems = formicarium.occupiedSlots().map(slot ⇒ (slot, formicarium.getStackInSlot(slot))).filter(_._2.getItem.isInstanceOf[IPlantable]).map(pair ⇒ (pair._1, pair._2.getItem.asInstanceOf[IPlantable]))
    var i = 0
    plantableItems.takeWhile(_ ⇒ i < numAnts).foreach(pair ⇒ {
      val slot = pair._1
      val plantable = pair._2
      world.getBlockPositions(formicarium.getPos, radius).takeWhile(_ ⇒ i < numAnts && formicarium.getStackSizeInSlot(slot) > 0).foreach(pos ⇒ {
        val state = plantable.getPlant(world, pos)
        state.getBlock match {
          case crop: BlockCrops ⇒ if (crop.canPlaceBlockAt(world, pos)){
            world.setBlockState(pos, state)
            formicarium.decrStackSize(slot, 1)
            i += 1
          }
        }
      })
    })
  }

}
