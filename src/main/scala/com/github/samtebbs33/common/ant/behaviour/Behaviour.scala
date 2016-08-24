package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.state.IBlockState
import net.minecraft.block._
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3i

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class Behaviour(name: String) {

  Behaviour.behaviourMap.put(name, this)

  def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack])

}

object Behaviour {

  val behaviourMap = scala.collection.mutable.Map[String, Behaviour]()

  val treeResourceGathering = new BehaviourTree("resource gathering")
  val treeWood = treeResourceGathering.addChild(new BehaviourTree("wood"))
  val treeFood = treeResourceGathering.addChild(new BehaviourTree("food"))
  val treeCrops = treeFood.addChild(new BehaviourTree("crops"))
  val treeAnimals = treeFood.addChild(new BehaviourTree("animals"))

  val behaviourHarvestCrops = new BehaviourHarvest("harvest crops")

  def getBehaviour(name: String) = behaviourMap.get(name)

}
