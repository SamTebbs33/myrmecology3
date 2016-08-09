package com.github.samtebbs33.registry

import com.github.samtebbs33.common.ant.{AntTrait, AntTraits}

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntTraitRegistry {
  final val isWinged = new AntTrait("isWinged")
  final val isNocturnal = new AntTrait("isNocturnal")
  val incubationTime = new AntTrait("incubationTime")

  final val basicTraits = new AntTraits(isNocturnal, isWinged, incubationTime)

}
