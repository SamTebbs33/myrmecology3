package com.github.samtebbs33.common.ant.behaviour

import java.util
import java.util.stream.Collectors

import scala.collection.mutable
import scala.util.Random
import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 20/08/2016.
  */
class BehaviourTree(val name: String) {

  val children = new util.ArrayList[BehaviourTree]()
  val behaviour = new util.ArrayList[Behaviour]()

  def addChild(antBehaviourTree: BehaviourTree) = {
    children.add(antBehaviourTree)
    antBehaviourTree
  }

  def addBehaviour(antAI: Behaviour) = {
    behaviour.add(antAI)
    antAI
  }

  def allBehaviour: util.List[Behaviour] = {
    val list = behaviour.stream().collect(Collectors.toList[Behaviour])
    children.foreach(_.allBehaviour.foreach(list.add))
    list
  }

  def randomBehaviour: Behaviour = {
    val all = allBehaviour
    all.get(Random.nextInt(all.size))
  }

}
