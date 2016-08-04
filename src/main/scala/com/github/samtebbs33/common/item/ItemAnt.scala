package com.github.samtebbs33.common.item

import java.util

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.ant.{AntSpecies, AntTypes}
import net.minecraft.client.renderer.RenderItem
import net.minecraft.client.renderer.color.IItemColor
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.fml.client.registry.RenderingRegistry

/**
	* Created by samtebbs on 31/07/2016.
	*/
class ItemAnt(species: AntSpecies) extends MyrmecologyItem("ant_" + species.name) {

	setHasSubtypes(true)

	override def resourceName(metadata: Int): String =  Myrmecology.MOD_ID + ":ant_" + getAntTypeString(metadata)

	override def getUnlocalizedName(stack: ItemStack): String = super.unlocalisedName(stack.getMetadata) + "_" + getAntTypeString(stack.getMetadata)

	override def getSubItems(itemIn: Item, tab: CreativeTabs, subItems: util.List[ItemStack]): Unit =
		for (elem <- AntTypes.values) subItems.add(new ItemStack(itemIn, 0, elem.id))

	override def showDurabilityBar(stack: ItemStack): Boolean = false

	override def getMetadata(damage: Int): Int = damage

	def getAntTypeString(metadata: Int): String = AntTypes.apply(metadata).toString.toLowerCase

	override def usesColourHandler: Boolean = true

	override def getColourHandler: IItemColor = new IItemColor {
		override def getColorFromItemstack(stack: ItemStack, tintIndex: Int): Int = {
			tintIndex match {
				case 0 => return species.primaryColour
				case _ => return species.secondaryColour
			}
		}
	}
}
