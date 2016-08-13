package com.github.samtebbs33.registry

import java.util.{HashSet, Set}

import com.github.samtebbs33.common.ant.{AntAI, AntSpecies}
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.init.Biomes
import net.minecraft.world.biome.Biome

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntSpeciesRegistry {
  final val species: Set[AntSpecies] = new HashSet[AntSpecies]

  final val speciesPlains = new AntSpecies(Registry.PLAINS, 0x162308, 0x406618, "Antus Fieldia", AntTraitRegistry.basicTraits, Map(Biomes.PLAINS → 1f)) {
    traits.setTrait(AntTraitRegistry.incubationTime, 10)
    traits.setTrait(AntTraitRegistry.breedingTime, 10)
  }
}
