package com.github.samtebbs33.common.ant

import scala.collection.mutable

/**
	* Created by samtebbs on 31/07/2016.
	*/
class AntTraits(traits: AntTrait*) {

	val traitMap = new mutable.HashMap[AntTrait, Int]()

	def setTrait(antTrait: AntTrait, value: Int) = traitMap.put(antTrait, value)

	def getTrait(antTrait: AntTrait) = traitMap.getOrElse(antTrait, 0)

	def geTraitBool(antTrait: AntTrait) = getTrait(antTrait) > 0

}
