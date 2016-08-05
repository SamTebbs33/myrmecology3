package com.github.samtebbs33.common.block

import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
	* Created by samtebbs on 05/08/2016.
	*/
abstract class MyrmecologyBlockEntity(mat : Material, name : String) extends MyrmecologyBlock(mat, name) with ITileEntityProvider{

	isBlockContainer = true

	override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
		super.breakBlock(worldIn, pos, state)
		worldIn.removeTileEntity(pos)
	}

}
