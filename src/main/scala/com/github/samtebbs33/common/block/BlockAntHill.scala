package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.ant.AntSpecies
import net.minecraft.block.material.Material

/**
	* Created by samtebbs on 30/07/2016.
	*/
class BlockAntHill(name: String, species: AntSpecies*) extends MyrmecologyBlock(Material.CACTUS, "ant_hill_" + name) {

}
