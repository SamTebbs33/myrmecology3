package com.github.samtebbs33.common.ant.behaviour

import java.util

/**
  * Created by samtebbs on 20/08/2016.
  */
class AntBehaviourTree(name: String) {

  val children = new util.ArrayList[AntBehaviourTree]()
  val behaviour = new util.ArrayList[AntBehaviour]()

  def addChild(antBehaviourTree: AntBehaviourTree) = {
    children.add(antBehaviourTree)
    antBehaviourTree
  }

  def addBehaviour(antAI: AntBehaviour) = {
    behaviour.add(antAI)
    antAI
  }

}
