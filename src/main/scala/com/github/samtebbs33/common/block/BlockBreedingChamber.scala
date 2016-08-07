package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.tileentity.TileEntityBreedingChamber
import net.minecraft.block.material.Material

/**
	* Created by samtebbs on 07/08/2016.
	*/
class BlockBreedingChamber(name: String) extends MyrmecologyBlockContainer(Material.GLASS, name, GuiHandler.ID_BREEDING_CHAMBER, classOf[TileEntityBreedingChamber]) {

}
