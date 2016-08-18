package com.github.samtebbs33.registry

import java.util

import com.github.samtebbs33.common.ant.{AntTrait}

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntTraitRegistry {

  val traits = new util.LinkedList[AntTrait]()

  val isWinged = new AntTrait("isWinged")
  val isNocturnal = new AntTrait("isNocturnal")
  val incubationTime = new AntTrait("incubationTime")
  val breedingTime = new AntTrait("breedingTime")

}
