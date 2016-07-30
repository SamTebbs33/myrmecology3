package com.github.samtebbs33.block

import com.github.samtebbs33.ant.AntSpecies
import net.minecraft.block.material.Material

/**
  * Created by samtebbs on 30/07/2016.
  */
class BlockAntHill(name : String, species : List[AntSpecies]) extends MyrmecologyBlock(Material.CACTUS, name) {

  def addSpecies(s : AntSpecies) = s :: species

}
