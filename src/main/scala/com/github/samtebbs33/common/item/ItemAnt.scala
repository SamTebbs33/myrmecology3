package com.github.samtebbs33.common.item

import java.util

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.ant.{AntTypes, Species}
import com.github.samtebbs33.registry.ItemRegistry
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import com.github.samtebbs33.Util._
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.nbt.NBTTagString
import net.minecraft.world.World

/**
  * Created by samtebbs on 31/07/2016.
  */
class ItemAnt(val species: Species) extends MyrmecologyItem("ant_" + species.name) {

  setHasSubtypes(true)
  ItemRegistry.ants.add(this)

  override def resourceName(metadata: Int): String = Myrmecology.modID + ":ant_" + getAntTypeString(metadata)

  override def getUnlocalizedName(stack: ItemStack): String = "item." + super.unlocalisedName(stack.getMetadata) + "_" + getAntTypeString(stack.getMetadata)

  override def getSubItems(itemIn: Item, tab: CreativeTabs, subItems: util.List[ItemStack]): Unit =
    for (elem <- AntTypes.values) {
      val stack = new ItemStack(itemIn, 1, elem.id)
      ItemAnt.assignBehaviour(stack)
      subItems.add(stack)
    }

  override def getItemStackLimit(stack: ItemStack): Int = stack.getMetadata match {
    case x if x == AntTypes.WORKER.id ⇒ 1
    case _ ⇒ 64
  }

  override def showDurabilityBar(stack: ItemStack): Boolean = false

  override def getMetadata(damage: Int): Int = damage

  def getAntTypeString(metadata: Int): String = AntTypes.apply(metadata).toString.toLowerCase

  override def usesColourHandler: Boolean = true

  override def addInformation(stack: ItemStack, playerIn: EntityPlayer, tooltip: util.List[String], advanced: Boolean): Unit = {
    super.addInformation(stack, playerIn, tooltip, advanced)
    val behaviour = ItemAnt.getBehaviour(stack)
    tooltip.add(s"Behaviour: ${
      behaviour match {
        case Some(beh) ⇒ beh.name
        case None ⇒ "None"
      }}")
  }

  override def onCreated(stack: ItemStack, worldIn: World, playerIn: EntityPlayer): Unit = {
    super.onCreated(stack, worldIn, playerIn)
    ItemAnt.assignBehaviour(stack)
  }

  override def getColourHandler = Some(new IItemColor {
    override def getColorFromItemstack(stack: ItemStack, tintIndex: Int): Int = {
      tintIndex match {
        case 0 => species.primaryColour
        case _ => species.secondaryColour
      }
    }
  })
}

object ItemAnt {

  val behaviourNbtTag = "Behaviour"

  def getSpecies(stack: ItemStack) = stack.getItem.asInstanceOf[ItemAnt].species

  def getBehaviour(itemStack: ItemStack): Option[Behaviour] = itemStack.getItem match {
    case _: ItemAnt if itemStack.getMetadata == AntTypes.WORKER.id ⇒ Behaviour.get(itemStack.getTagCompound.getString(behaviourNbtTag))
    case _ ⇒ None
  }
  def assignBehaviour(antStack: ItemStack): Unit = {
    antStack.getItem match {
      case _: ItemAnt if antStack.getMetadata == AntTypes.WORKER.id ⇒
        val species = antStack.getItem.asInstanceOf[ItemAnt].species
        val behaviour = species.behaviourTrees.rand.randomBehaviour.name
        antStack.setTagInfo(behaviourNbtTag, new NBTTagString(behaviour))
      case _ ⇒
    }
  }
}
