package com.github.samtebbs33.client

import com.github.samtebbs33.Proxy
import net.minecraft.client.Minecraft
import net.minecraft.client.renderer.block.model.ModelResourceLocation
import net.minecraft.item.Item

/**
	* Created by samtebbs on 31/07/2016.
	*/
class ClientProxy extends Proxy {
	override def registerModel(item: Item): Unit = {
		Minecraft.getMinecraft.getRenderItem.getItemModelMesher.register(item, 0, new ModelResourceLocation(item.getRegistryName, "inventory"))
	}
}
