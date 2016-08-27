package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.{BlockCrops, BlockMelon}
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemSeeds, ItemStack}
import net.minecraft.util.math.Vec3i
import net.minecraftforge.oredict.OreDictionary

/**
  * Created by samuelt on 24/08/2016.
  */
class BehaviourPlantCrops(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    var i = 0
    val plantableItems = formicarium.occupiedSlots().map(slot => (slot, formicarium.getStackInSlot(slot).getItem))
      .filter(pair => pair._2.isInstanceOf[IPlantable])
      .map(pair => (pair._1, pair._2.asInstanceOf[IPlantable]))
      .foreach(pair => {
        val slot = pair._1
        val plantable = pair._2
        world.getBlocksInRadius(formicarium.getPos, radius).takeWhile(_ => i < numAnts && formicarium.getStackSize(slot) > 0)
          .foreach(pair => {
            val pos = pair._1.up()
            val plant = plantable.getPlant(world, pos)
            val block = plant.getBlock
            if(block.canPlaceBlockAt(world, pos)) {
              world.setBlockState(pos, plant)
              formicarium.decrStackSize(slot, 1)
              i += 1
            }
          })
      })
  }

}
