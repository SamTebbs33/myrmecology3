package com.github.samtebbs33.registry

import java.util

import com.github.samtebbs33.common.ant.Trait

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntTraitRegistry {

  val traits = new util.LinkedList[Trait[_]]()

  val isWinged = new Trait("isWinged", false)
  val isNocturnal = new Trait("isNocturnal", false)
  val incubationTime = new Trait("incubationTime", 10)
  val breedingTime = new Trait("breedingTime", 10)

}
