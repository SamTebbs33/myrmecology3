package com.github.samtebbs33.common.block

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.GuiHandler
import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntity, MyrmecologyTileEntityContainer}
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.InventoryHelper
import net.minecraft.item.ItemStack
import net.minecraft.util.{EnumFacing, EnumHand}
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World

/**
	* Created by samtebbs on 06/08/2016.
	*/
class MyrmecologyBlockContainer[T <: MyrmecologyTileEntity](material: Material, name : String, guiId : Int, cls : Class[T]) extends MyrmecologyBlockEntity(material, name, cls) {

	isBlockContainer = true

	override def onBlockPlacedBy(worldIn: World, pos: BlockPos, state: IBlockState, placer: EntityLivingBase, stack: ItemStack): Unit = {
		super.onBlockPlacedBy(worldIn, pos, state, placer, stack)
		if(stack.hasDisplayName) worldIn.getTileEntity(pos) match {
			case te : MyrmecologyTileEntityContainer => te.customName = stack.getDisplayName
		}
	}

	override def breakBlock(worldIn: World, pos: BlockPos, state: IBlockState): Unit = {
		worldIn.getTileEntity(pos) match {
			case te : MyrmecologyTileEntityContainer => InventoryHelper.dropInventoryItems(worldIn, pos, te)
		}
		super.breakBlock(worldIn, pos, state)
	}

	override def onBlockActivated(worldIn: World, pos: BlockPos, state: IBlockState, playerIn: EntityPlayer, hand: EnumHand, heldItem: ItemStack, side: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): Boolean = {
		super.onBlockActivated(worldIn, pos, state, playerIn, hand, heldItem, side, hitX, hitY, hitZ)
		if(!worldIn.isRemote) {
			playerIn.openGui(Myrmecology, guiId, worldIn, pos.getX, pos.getY, pos.getZ)
			true
		} else false
	}

}
