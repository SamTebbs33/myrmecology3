package com.github.samtebbs33.registry

import java.util.{HashSet, Set}

import com.github.samtebbs33.common.ant.AntSpecies
import net.minecraft.init.Biomes

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntSpeciesRegistry {
  final val species: Set[AntSpecies] = new HashSet[AntSpecies]

  final val speciesPlains = new AntSpecies(Registry.PLAINS, 0x162308, 0x406618, "Antus Fieldia", Map(Biomes.PLAINS → 1f)) {
    setTrait(AntTraitRegistry.incubationTime, 10)
    setTrait(AntTraitRegistry.breedingTime, 10)
  }
}
