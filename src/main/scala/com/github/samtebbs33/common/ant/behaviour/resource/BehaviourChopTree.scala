package com.github.samtebbs33.common.ant.behaviour.resource

import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.block.BlockLog
import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.{BlockPos, Vec3i}
import net.minecraft.world.World

/**
  * Created by samtebbs on 28/08/2016.
  */
class BehaviourChopTree(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  def getBlocks(world: World, pos: BlockPos, state: IBlockState): scala.collection.mutable.Set[BlockPos] = {
    val logSet = scala.collection.mutable.Set[BlockPos]()
    val block = state.getBlock
    def addNeighbours(from: BlockPos): Unit = {
      val blockPositions = world.getBlocksInRadius(from, new Vec3i(1, 1, 1))
        .filter(pair ⇒ block.getClass.isInstance(pair._2.getBlock))
      blockPositions.map(_._1)
        .diff(logSet)
        .foreach(p ⇒ {
          logSet.add(p)
          addNeighbours(p)
        })
    }
    logSet.add(pos)
    addNeighbours(pos)
    logSet
  }

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    val log = world.getBlocksInRadius(formicarium.getPos, radius).find(_._2.getBlock.isInstanceOf[BlockLog])
    log.ifDefined(pair ⇒ {
      getBlocks(world, pair._1, pair._2).foreach(pos ⇒ world.destroyBlock(pos, true))
    })
  }
}
