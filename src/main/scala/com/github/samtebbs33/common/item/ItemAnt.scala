package com.github.samtebbs33.common.item

import java.util

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.behaviour.Behaviour
import com.github.samtebbs33.common.ant.{AntTypes, Species}
import com.github.samtebbs33.registry.ItemRegistry
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}

/**
  * Created by samtebbs on 31/07/2016.
  */
class ItemAnt(val species: Species) extends MyrmecologyItem("ant_" + species.name) {

  setHasSubtypes(true)
  ItemRegistry.ants.add(this)

  override def resourceName(metadata: Int): String = Myrmecology.MOD_ID + ":ant_" + getAntTypeString(metadata)

  override def getUnlocalizedName(stack: ItemStack): String = "item." + super.unlocalisedName(stack.getMetadata) + "_" + getAntTypeString(stack.getMetadata)

  override def getSubItems(itemIn: Item, tab: CreativeTabs, subItems: util.List[ItemStack]): Unit =
    for (elem <- AntTypes.values) subItems.add(new ItemStack(itemIn, 1, elem.id))

  override def showDurabilityBar(stack: ItemStack): Boolean = false

  override def getMetadata(damage: Int): Int = damage

  def getAntTypeString(metadata: Int): String = AntTypes.apply(metadata).toString.toLowerCase

  override def usesColourHandler: Boolean = true

  override def getColourHandler: IItemColor = new IItemColor {
    override def getColorFromItemstack(stack: ItemStack, tintIndex: Int): Int = {
      tintIndex match {
        case 0 => species.primaryColour
        case _ => species.secondaryColour
      }
    }
  }
}

object ItemAnt {
  def getBehaviour(itemStack: ItemStack) = itemStack.getItem match {
    case _: ItemAnt ⇒ Behaviour.getBehaviour(itemStack.getTagCompound.getString("Behaviour"))
  }
}
