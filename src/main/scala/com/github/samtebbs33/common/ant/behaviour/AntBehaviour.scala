package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.{BlockCrops, IGrowable}
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3i

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class AntBehaviour(name: String) {

  AntBehaviour.behaviourMap.put(name, this)

  def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack])

}

object AntBehaviour {

  val behaviourMap = scala.collection.mutable.Map[String, AntBehaviour]()

  val treeResourceGathering = new AntBehaviourTree("resource gathering")
  val treeWood = treeResourceGathering.addChild(new AntBehaviourTree("wood"))
  val treeFood = treeResourceGathering.addChild(new AntBehaviourTree("food"))
  val treeCrops = treeFood.addChild(new AntBehaviourTree("crops"))
  val treeAnimals = treeFood.addChild(new AntBehaviourTree("animals"))

  val behaviourHarvestCrops = new AntBehaviour("harvest crops") {

    val radius = new Vec3i(5, 5, 5)

    override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
      val crops = formicarium.getWorld.getTypedBlocksInRadius[IGrowable](formicarium.getPos, radius)
    }

  }

  def getBehaviour(name: String) = behaviourMap.get(name)

}
