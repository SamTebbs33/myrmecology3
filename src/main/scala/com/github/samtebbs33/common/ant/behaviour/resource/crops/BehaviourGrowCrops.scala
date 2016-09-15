package com.github.samtebbs33.common.ant.behaviour.resource.crops

import java.util.Random

import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.block.state.IBlockState
import net.minecraft.block.{BlockGrass, IGrowable}
import net.minecraft.init.Items
import net.minecraft.item.ItemStack
import net.minecraft.util.math.{BlockPos, Vec3i}

/**
  * Created by samuelt on 25/08/2016.
  */
class BehaviourGrowCrops(name: String) extends Behaviour(name) {
  val radius = new Vec3i(5, 5, 5)
  val bonemeal = new ItemStack(Items.DYE, 1, 15)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    val crops = world.getBlocksInRadius(formicarium.getPos, radius).filter(pair ⇒ pair._2.getBlock.isInstanceOf[IGrowable])
    var i = 0
    val bonemealSlots = formicarium.occupiedSlots().map(slot => (slot, formicarium.getStackInSlot(slot))).filter(pair => pair._2.isItemEqual(bonemeal)).map(_._1)
    val rand = new Random()
    // Filter crops by those that can grow
    val cropFilter = (tuple: (BlockPos, IBlockState, IGrowable)) => tuple._3.canGrow(world, tuple._1, tuple._2, world.isRemote) && !tuple._2.getBlock.isInstanceOf[BlockGrass]
    // Loops over each crop, filtered by those that can be grown
    val cropLoop = (slot: Int) => crops
      .map(pair => (pair._1, pair._2, pair._2.getBlock.asInstanceOf[IGrowable]))
      .filter(cropFilter).foreach(tuple => {
      if (i < numAnts && formicarium.getStackSize(slot) > 0) {
        // Grow the crop
        tuple._3.grow(world, rand, tuple._1, tuple._2)
        // Remove one bonemeal
        formicarium.decrStackSize(slot, 1)
        i += 1
      }
    })
    bonemealSlots.foreach(cropLoop)
  }

}

