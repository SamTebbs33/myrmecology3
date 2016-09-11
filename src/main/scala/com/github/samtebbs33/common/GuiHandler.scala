package com.github.samtebbs33.common

import com.github.samtebbs33.client.gui.{GuiBreedingChamber, GuiFormicarium, GuiMyrmopaedia, GuiSolarium}
import com.github.samtebbs33.common.container.{ContainerBreedingChamber, ContainerFormicarium, ContainerMyrmopaedia, ContainerSolarium}
import com.github.samtebbs33.common.tileentity.{MyrmecologyTileEntityContainer, TileEntityBreedingChamber, TileEntityFormicarium, TileEntitySolarium}
import net.minecraft.client.gui.GuiScreen
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.inventory.Container
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraftforge.fml.common.network.IGuiHandler

/**
  * Created by samtebbs on 06/08/2016.
  */
object GuiHandler extends IGuiHandler {

  val ID_SOLARIUM = 0
  val ID_BREEDING_CHAMBER = 1
  val ID_FORMICARIUM = 2
  val ID_MYRMOPAEDIA = 3

  def getTileEntity[T <: MyrmecologyTileEntityContainer](world: World, x: Int, y: Int, z: Int, clazz: Class[T]): T = clazz.cast(world.getTileEntity(new BlockPos(x, y, z)))

  override def getClientGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): GuiScreen = ID match {
    case ID_SOLARIUM => new GuiSolarium(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntitySolarium]))
    case ID_BREEDING_CHAMBER => new GuiBreedingChamber(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntityBreedingChamber]))
    case ID_FORMICARIUM ⇒ new GuiFormicarium(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntityFormicarium]))
    case ID_MYRMOPAEDIA => new GuiMyrmopaedia(player.inventory, player.getHeldItemMainhand)
  }

  override def getServerGuiElement(ID: Int, player: EntityPlayer, world: World, x: Int, y: Int, z: Int): Container = ID match {
    case ID_SOLARIUM => new ContainerSolarium(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntitySolarium]))
    case ID_BREEDING_CHAMBER => new ContainerBreedingChamber(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntityBreedingChamber]))
    case ID_FORMICARIUM ⇒ new ContainerFormicarium(player.inventory, getTileEntity(world, x, y, z, classOf[TileEntityFormicarium]))
    case ID_MYRMOPAEDIA => new ContainerMyrmopaedia(player.inventory, player.getHeldItemMainhand)
  }
}
