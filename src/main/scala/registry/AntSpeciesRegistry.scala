package registry

import java.util.{HashSet, Set}

import com.github.samtebbs33.common.ant.AntSpecies
import net.minecraft.init.Biomes
import net.minecraft.world.BossInfo.Color

/**
	* Created by samtebbs on 02/08/2016.
	*/
object AntSpeciesRegistry {
	final val species: Set[AntSpecies] = new HashSet[AntSpecies]

	final val speciesPlains = new AntSpecies(Registry.PLAINS, 0x162308, 0x406618, "Antus Fieldia", AntTraitRegistry.basicTraits, Biomes.PLAINS)
}
