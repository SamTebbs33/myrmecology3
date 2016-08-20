package com.github.samtebbs33.registry

import java.util.function.Consumer

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.Util._
import com.github.samtebbs33.common.CraftingRecipe
import com.github.samtebbs33.common.block.MyrmecologyBlock
import com.github.samtebbs33.common.item.MyrmecologyItem
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.item.Item
import net.minecraftforge.fml.common.registry.GameRegistry
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 30/07/2016.
  */
object Registry {

  final var creativeTab: CreativeTabs = _

  val PLAINS = "plains"
  val FOREST = "forest"
  val DESERT = "desert"
  val SWAMP = "swamp"

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

  def registerCraftingRecipe(craftingRecipe: CraftingRecipe): Unit = {
    println(craftingRecipe.recipeParams)
    GameRegistry.addRecipe(craftingRecipe.output, craftingRecipe.recipeParams: _*)
  }

  def registerCraftingRecipes() = BlockRegistry.blocks.foreach(_.craftingRecipe.ifDefined(registerCraftingRecipe))

}
