package com.github.samtebbs33.common.ant

import java.util

import com.github.samtebbs33.common.ant.behaviour.Behaviour$
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.world.biome.Biome

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * Created by samtebbs on 30/07/2016.
  */
class Species(val name: String, val primaryColour: Int, val secondaryColour: Int, binomialName: String, val spawnChanceMap: Map[Biome, Float] = Map()) {

  val traitMap = mutable.Map[Trait, Int]()

  def getTrait(antTrait: Trait) = traitMap.getOrElseUpdate(antTrait, antTrait.default)

  def getTraitBool(antTrait: Trait) = getTrait(antTrait) match {
    case 0 => false
    case _ => true
  }

  def setTrait(antTrait: Trait, int: Int) = traitMap.put(antTrait, int)

  def setTrait(antTrait: Trait, boolean: Boolean) = traitMap.put(antTrait, if (boolean) 1 else 0)

}
