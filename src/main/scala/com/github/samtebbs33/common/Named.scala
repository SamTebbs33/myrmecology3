package com.github.samtebbs33.common

import com.github.samtebbs33.Myrmecology

/**
  * Created by samtebbs on 31/07/2016.
  */
trait Named {

  def shortName: String

  def unlocalisedName(metadata: Int = 0) = Myrmecology.MOD_ID + "_" + shortName

  def registryName = Myrmecology.MOD_ID + ":" + shortName

  def resourceName(metadata: Int) = registryName

}
