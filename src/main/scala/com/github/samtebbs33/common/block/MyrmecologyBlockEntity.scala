package com.github.samtebbs33.common.block

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntityContainer, MyrmecologyTileEntity}
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
	* Created by samtebbs on 05/08/2016.
	*/
abstract class MyrmecologyBlockEntity[T <: MyrmecologyTileEntity](mat : Material, name : String, cls : Class[T]) extends MyrmecologyBlock(mat, name) with ITileEntityProvider {

	override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
		worldIn.removeTileEntity(pos)
		super.breakBlock(worldIn, pos, state)
	}

	override def createNewTileEntity(world: World, meta: Int): TileEntity = {
		cls.newInstance()
	}
}
