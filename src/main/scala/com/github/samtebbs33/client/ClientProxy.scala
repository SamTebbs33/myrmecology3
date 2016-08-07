package com.github.samtebbs33.client

import java.util
import java.util.function.Consumer

import com.github.samtebbs33.common.item.MyrmecologyItem
import com.github.samtebbs33.registry.ItemRegistry
import com.github.samtebbs33.{Myrmecology, Proxy}
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.{ModelBakery, ModelResourceLocation}
import net.minecraft.client.renderer.color.{IItemColor, ItemColors}
import net.minecraft.item.{Item, ItemStack}
import net.minecraftforge.fml.common.event.{FMLInitializationEvent, FMLPreInitializationEvent}
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

import scala.collection.JavaConversions._

/**
	* Created by samtebbs on 31/07/2016.
	*/
class ClientProxy extends Proxy {

	override def init(e: FMLInitializationEvent): Unit = {
		super.init(e)
		val itemColours = Minecraft.getMinecraft.getItemColors
		ItemRegistry.items.foreach(item => if(item.usesColourHandler) itemColours.registerItemColorHandler(item.getColourHandler, item))
	}

	override def registerModel(item: Item): Unit = {
		if(item.getHasSubtypes) {
			val subItems = new util.LinkedList[ItemStack]
			item.getSubItems(item, null, subItems)
			subItems.foreach(registerModel)
		} else registerModel(new ItemStack(item, 0, 0))
	}

	private def registerModel(stack: ItemStack): Unit = {
		val location: ModelResourceLocation = stack.getItem match {
			case item : MyrmecologyItem => new ModelResourceLocation(item.resourceName(stack.getMetadata), "inventory")
			case item => new ModelResourceLocation(item.getRegistryName, "inventory")
		}
		if(stack.getItem.getHasSubtypes) ModelBakery.registerItemVariants(stack.getItem, location)
		Minecraft.getMinecraft.getRenderItem.getItemModelMesher
			.register(stack.getItem, stack.getMetadata, location)
	}

}
