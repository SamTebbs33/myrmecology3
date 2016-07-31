package com.github.samtebbs33.item

import com.github.samtebbs33.Myrmecology
import com.github.samtebbs33.util.Named
import net.minecraft.item.Item

/**
  * Created by samtebbs on 30/07/2016.
  */
class MyrmecologyItem(name : String) extends Item with Named {

  setUnlocalizedName(name)

  override def getUnlocalisedName(): String = name
}
