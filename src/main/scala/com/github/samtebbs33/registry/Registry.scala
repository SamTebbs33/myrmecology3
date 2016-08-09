package com.github.samtebbs33.registry

import java.util.function.Consumer

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.common.block.MyrmecologyBlock
import com.github.samtebbs33.common.item.MyrmecologyItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

/**
  * Created by samtebbs on 30/07/2016.
  */
object Registry {

  final var creativeTab: CreativeTabs = _

  val PLAINS = "plains"

  private def makeCreativeTab(iconItem: Item) = new CreativeTabs(Myrmecology.MOD_ID) {
    @SideOnly(Side.CLIENT)
    override def getTabIconItem = iconItem
  }

  def registerCreativeTab(): Unit = {
    creativeTab = makeCreativeTab(ItemRegistry.antPlains)
    ItemRegistry.items.forEach(new Consumer[MyrmecologyItem] {
      override def accept(t: MyrmecologyItem): Unit = t.setCreativeTab(Registry.creativeTab)
    })
    BlockRegistry.blocks.forEach(new Consumer[MyrmecologyBlock] {
      override def accept(t: MyrmecologyBlock): Unit = t.setCreativeTab(Registry.creativeTab)
    })
  }

}
