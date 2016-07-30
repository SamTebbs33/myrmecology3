package com.github.samtebbs33

import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.util.WeightedRandom.Item

/**
  * Created by samtebbs on 30/07/2016.
  */
object Reference {

  final val creativeTab = new CreativeTabs(CreativeTabs.CREATIVE_TAB_ARRAY.length - 1, Myrmecology.MOD_ID) {
    override def getTabIconItem = Items.ARROW
  }

}
