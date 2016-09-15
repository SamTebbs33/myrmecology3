package com.github.samtebbs33.common.item
import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.GuiHandler
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.util.{ActionResult, EnumActionResult, EnumFacing, EnumHand}
import net.minecraft.world.World

/**
  * Created by samuelt on 31/08/2016.
  */
class ItemMyrmopaedia(name: String) extends MyrmecologyItem(name) {

  override def onItemRightClick(itemStackIn: ItemStack, worldIn: World, playerIn: EntityPlayer, hand: EnumHand): ActionResult[ItemStack] = {
    if(!worldIn.isRemote && !playerIn.isSneaking) {
      playerIn.openGui(Myrmecology, GuiHandler.myrmopaediaID, worldIn, playerIn.posX.toInt, playerIn.posY.toInt, playerIn.posZ.toInt)
      ActionResult.newResult(EnumActionResult.SUCCESS, itemStackIn)
    }
    ActionResult.newResult(EnumActionResult.PASS, itemStackIn)
  }

  override def onItemUse(stack: ItemStack, playerIn: EntityPlayer, worldIn: World, pos: BlockPos, hand: EnumHand, facing: EnumFacing, hitX: Float, hitY: Float, hitZ: Float): EnumActionResult =
    onItemRightClick(stack, worldIn, playerIn, hand).getType
}
