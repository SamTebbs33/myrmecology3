package registry

import com.github.samtebbs33.common.ant.{AntTrait, AntTraits}

/**
	* Created by samtebbs on 02/08/2016.
	*/
object AntTraitRegistry {
	final val traitIsWinged = new AntTrait("isWinged")
	final val traitIsNocturnal = new AntTrait("isNocturnal")

	final val basicTraits = new AntTraits(traitIsNocturnal, traitIsWinged)

}
