package com.github.samtebbs33.block

import com.github.samtebbs33.{Myrmecology, Reference}
import com.github.samtebbs33.util.Named
import net.minecraft.block._
import net.minecraft.block.material.Material

/**
  * Created by samtebbs on 30/07/2016.
  */
abstract class MyrmecologyBlock(material: Material, name : String) extends Block(material) with Named {

  setUnlocalizedName(Myrmecology.MOD_ID + "_" + name)
  setCreativeTab(Reference.creativeTab)

  override def getUnlocalisedName(): String = getUnlocalizedName()

}
