package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class AntBehaviour(name: String) {

  AntBehaviour.behaviourMap.put(name, this)

  def shouldExecute(formicarium: TileEntityFormicarium): Boolean
  def execute(implicit formicarium: TileEntityFormicarium)

}

object AntBehaviour {

  val behaviourMap = scala.collection.mutable.Map[String, AntBehaviour]()

  val treeResourceGathering = new AntBehaviourTree("resource gathering")
  val treeWood = treeResourceGathering.addChild(new AntBehaviourTree("wood"))
  val treeFood = treeResourceGathering.addChild(new AntBehaviourTree("food"))
  val treeCrops = treeFood.addChild(new AntBehaviourTree("crops"))
  val treeAnimals = treeFood.addChild(new AntBehaviourTree("animals"))

  def getBehaviour(name: String) = behaviourMap.get(name)

}
