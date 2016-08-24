package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.BlockCrops
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemSeeds, ItemStack}
import net.minecraft.util.math.Vec3i

/**
  * Created by samuelt on 24/08/2016.
  */
class BehaviourPlantCrops(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
   val world = formicarium.getWorld
    val crops = world.getTypedBlocksInRadius[BlockCrops](formicarium.getPos, radius)
    val plantableItems = crops.map(pair => pair._2.getBlock.asInstanceOf[BlockCrops].getItem(world, pair._1, pair._2))
  }

}
