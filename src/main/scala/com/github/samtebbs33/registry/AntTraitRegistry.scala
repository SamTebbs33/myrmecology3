package com.github.samtebbs33.registry

import com.github.samtebbs33.common.ant.{AntTrait, AntTraits}

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntTraitRegistry {
  val isWinged = new AntTrait("isWinged")
  val isNocturnal = new AntTrait("isNocturnal")
  val incubationTime = new AntTrait("incubationTime")
  val breedingTime = new AntTrait("breedingTime")

  final val basicTraits = new AntTraits(isNocturnal, isWinged, incubationTime, breedingTime)

}
