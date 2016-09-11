package com.github.samtebbs33.common.ant

import com.github.samtebbs33.common.ant.behaviour.BehaviourTree
import net.minecraft.world.biome.Biome

import scala.collection.mutable

/**
  * Created by samtebbs on 30/07/2016.
  */
class Species(val name: String, val primaryColour: Int, val secondaryColour: Int, val binomialName: String, val spawnChanceMap: Map[Biome, Float], val behaviourTrees: BehaviourTree*) {

  val traitMap = mutable.Map[Trait[_], Any]()

  def getTrait(antTrait: Trait[_]) = traitMap.getOrElseUpdate(antTrait, antTrait.default)
  def getTraitAs[T](antTrait : Trait[_]) = getTrait(antTrait).asInstanceOf[T]

  def getTraitBool(antTrait: Trait[_]) = getTrait(antTrait) match {
    case 0 => false
    case _ => true
  }

  def setTrait(antTrait: Trait[_], int: Int) = traitMap.put(antTrait, int)

  def setTrait(antTrait: Trait[_], boolean: Boolean) = traitMap.put(antTrait, if (boolean) 1 else 0)

}
