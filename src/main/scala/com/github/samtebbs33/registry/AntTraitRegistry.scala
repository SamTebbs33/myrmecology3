package com.github.samtebbs33.registry

import java.util

import com.github.samtebbs33.common.ant.Trait

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntTraitRegistry {

  val traits = new util.LinkedList[Trait]()

  val isWinged = new Trait("isWinged")
  val isNocturnal = new Trait("isNocturnal")
  val incubationTime = new Trait("incubationTime", 10)
  val breedingTime = new Trait("breedingTime", 10)

}
