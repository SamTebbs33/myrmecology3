package com.github.samtebbs33.common

/**
  * Created by samtebbs on 13/08/2016.
  */
trait Craftable {

  def craftingRecipe: Option[CraftingRecipe] = None

}
