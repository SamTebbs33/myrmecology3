package com.github.samtebbs33.common.ant.behaviour

import java.util

/**
  * Created by samtebbs on 20/08/2016.
  */
class BehaviourTree(name: String) {

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

}
