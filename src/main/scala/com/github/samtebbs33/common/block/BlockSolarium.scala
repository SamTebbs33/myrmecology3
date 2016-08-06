package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
	* Created by samtebbs on 05/08/2016.
	*/
class BlockSolarium(name : String) extends MyrmecologyBlockEntity(Material.GLASS, name) {
	override def createNewTileEntity(worldIn: World, meta: Int): TileEntity = new TileEntitySolarium(name)
}
