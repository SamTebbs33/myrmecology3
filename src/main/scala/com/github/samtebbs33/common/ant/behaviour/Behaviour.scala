package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.item.{ItemHoe, ItemStack}

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class Behaviour(name: String) {

  Behaviour.behaviourMap.put(name, this)

  def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack])

}

object Behaviour {

  val behaviourMap = scala.collection.mutable.Map[String, Behaviour]()

  val treeMisc = new BehaviourTree("misc")
  val treeResourceGathering = new BehaviourTree("resource gathering")
  val treeWood = treeResourceGathering.addChild(new BehaviourTree("wood"))
  val treeFood = treeResourceGathering.addChild(new BehaviourTree("food"))
  val treeCrops = treeFood.addChild(new BehaviourTree("crops"))
  val treeAnimals = treeFood.addChild(new BehaviourTree("animals"))

  val behaviourHarvestCrops = treeCrops.addBehaviour(new BehaviourHarvest("harvest crops"))
  val behaviourPlantCrops = treeCrops.addBehaviour(new BehaviourPlantCrops("plant crops"))
  val behaviourGrowCrops = treeCrops.addBehaviour(new BehaviourGrowCrops("grow crops"))

  val behaviourScavenge = treeMisc.addBehaviour(new BehaviourScavenge("scavenge"))

  def getBehaviour(name: String) = behaviourMap.get(name)

}
