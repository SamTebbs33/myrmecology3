package com.github.samtebbs33.common.ant.behaviour.resource

import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.item.ItemStack
import com.github.samtebbs33.Util._
import net.minecraft.block.BlockLeaves
import net.minecraft.util.math.Vec3i

/**
  * Created by samtebbs on 29/08/2016.
  */
class BehaviourCutLeaves(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    var i = 0
    world.getBlocksInRadius(formicarium.getPos, radius).filter(_._2.getBlock.isInstanceOf[BlockLeaves])
      .foreach(pair ⇒ {
        if(i < numAnts) {
          world.destroyBlock(pair._1, true)
          i += 1
        }
      })
  }
}
