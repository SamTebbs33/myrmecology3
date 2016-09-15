package com.github.samtebbs33.common.ant.behaviour.resource

import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.block.BlockOre
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3i

/**
  * Created by samtebbs on 27/08/2016.
  */
class BehaviourMine(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    var i = 0
    val oreBlocks = world.getBlocksInRadius(formicarium.getPos, radius)
      .filter(_._2.getBlock.isInstanceOf[BlockOre])
      .filter(pair ⇒ pair._2.getBlock.getHarvestLevel(pair._2) < 2)
    oreBlocks.map(pair ⇒ (pair._1, pair._2.getBlock.asInstanceOf[BlockOre]))
      .foreach(pair ⇒ {
        if (i < numAnts) {
          world.destroyBlock(pair._1, true)
          i += 1
        }
      })
  }
}
