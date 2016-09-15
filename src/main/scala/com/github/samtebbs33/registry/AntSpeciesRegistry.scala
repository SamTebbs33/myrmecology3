package com.github.samtebbs33.registry

import java.util.{HashSet, Set}

import com.github.samtebbs33.common.ant.Species
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import net.minecraft.init.Biomes

/**
  * Created by samtebbs on 02/08/2016.
  */
object AntSpeciesRegistry {
  final val species: Set[Species] = new HashSet[Species]

  val speciesPlains = new Species(Registry.plainsName, 0x162308, 0x406618, "Antus Fieldia", Map(Biomes.PLAINS → 1f), Behaviour.treeCrops)
  val speciesForest = new Species(Registry.forestName, 0x020202, 0x333333, "Lasius Niger", Map(Biomes.FOREST → 1f), Behaviour.treeWood)
  val speciesDesert = new Species(Registry.desertName, 0x898000, 0xeada00, "Antus Desertus", Map(Biomes.DESERT → 1f), Behaviour.treeMisc)
  val speciesSwamp = new Species(Registry.swampName, 0x210020, 0x4B0049, "Antus Swampus", Map(Biomes.SWAMPLAND → 1f), Behaviour.treeDamage)
  val speciesJungle = new Species(Registry.jungleName, 0x3D0000, 0x790000, "Formica Rufa", Map(Biomes.JUNGLE → 1f), Behaviour.treeResourceGathering)
  val speciesStone = new Species(Registry.stoneName, 0x2B2B2B, 0x636363, "Formicidae Lapidus", Map(), Behaviour.treeRock)

}
