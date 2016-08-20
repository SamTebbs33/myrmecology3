package com.github.samtebbs33.common.ant

import java.util

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.world.biome.Biome

import scala.collection.JavaConversions._
import scala.collection.mutable

/**
  * Created by samtebbs on 30/07/2016.
  */
class AntSpecies(val name: String, val primaryColour: Int, val secondaryColour: Int, binomialName: String, val spawnMap: Map[Biome, Float] = Map()) {

  val aiList = new util.ArrayList[AntAI]()
  val traitMap = mutable.Map[AntTrait, Int]()

  def getTrait(antTrait: AntTrait) = traitMap.getOrElseUpdate(antTrait, antTrait.default)

  def getTraitBool(antTrait: AntTrait) = getTrait(antTrait) match {
    case 0 => false
    case _ => true
  }

  def setTrait(antTrait: AntTrait, int: Int) = traitMap.put(antTrait, int)

  def setTrait(antTrait: AntTrait, boolean: Boolean) = traitMap.put(antTrait, if (boolean) 1 else 0)

  def updateAI(formicarium: TileEntityFormicarium): Unit = {
    for (ai: AntAI ← aiList) if (ai.shouldExecute(formicarium)) {
      ai.execute(formicarium)
      return
    }
  }

  def addAi(ai: AntAI) = aiList.add(ai)

}
