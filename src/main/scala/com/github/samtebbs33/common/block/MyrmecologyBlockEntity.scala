package com.github.samtebbs33.common.block

import com.github.samtebbs33.common.tileentity.MyrmecologyContainer
import net.minecraft.block.ITileEntityProvider
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.tileentity.TileEntity
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
	* Created by samtebbs on 05/08/2016.
	*/
abstract class MyrmecologyBlockEntity(mat : Material, name : String) extends MyrmecologyBlock(mat, name) with ITileEntityProvider{

	isBlockContainer = true

	override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
		worldIn.getTileEntity(pos) match {
			case te : MyrmecologyContainer => InventoryHelper.dropInventoryItems(worldIn, pos, te)
		}
		worldIn.removeTileEntity(pos)
		super.breakBlock(worldIn, pos, state)
	}

	override def onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack): Unit = {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
		if(stack.hasDisplayName) worldIn.getTileEntity(pos) match {
			case te : MyrmecologyContainer => te.customName = stack.getDisplayName
		}
	}
}
