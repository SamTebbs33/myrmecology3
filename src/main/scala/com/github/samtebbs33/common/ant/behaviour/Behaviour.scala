package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.ant.behaviour.resource._
import com.github.samtebbs33.common.ant.behaviour.resource.crops.{BehaviourGrowCrops, BehaviourHarvest, BehaviourPlantCrops}
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class Behaviour(val name: String) {

  Behaviour.behaviourMap.put(name, this)

  def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit

}

object Behaviour {

  val behaviourMap = scala.collection.mutable.Map[String, Behaviour]()

  val treeMisc = new BehaviourTree("misc")
  val treeResourceGathering = new BehaviourTree("resource gathering")
  val treeDamage = new BehaviourTree("damage")
  val treeWood = treeResourceGathering.addChild(new BehaviourTree("wood"))
  val treeFood = treeResourceGathering.addChild(new BehaviourTree("food"))
  val treeRock = treeResourceGathering.addChild(new BehaviourTree("rock"))
  val treeCrops = treeFood.addChild(new BehaviourTree("crops"))
  val treeAnimals = treeFood.addChild(new BehaviourTree("animals"))

  val behaviourHarvestCrops = treeCrops.addBehaviour(new BehaviourHarvest("harvest crops"))
  val behaviourPlantCrops = treeCrops.addBehaviour(new BehaviourPlantCrops("plant crops"))
  val behaviourGrowCrops = treeCrops.addBehaviour(new BehaviourGrowCrops("grow crops"))

  val behaviourChopTree = treeWood.addBehaviour(new BehaviourChopTree("chop tree"))
  val behaviourCutLeaves = treeWood.addBehaviour(new BehaviourCutLeaves("cut leaves"))

  val behaviourMine = treeRock.addBehaviour(new BehaviourMine("mine ore"))

  val behaviourScavenge = treeMisc.addBehaviour(new BehaviourScavenge("scavenge"))

  val behaviourDamageMobs = treeDamage.addBehaviour(new BehaviourDamageMobs("poison"))

  def get(name: String) = behaviourMap.get(name)

}
