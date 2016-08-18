package com.github.samtebbs33.common

import java.util

import net.minecraft.item.ItemStack

/**
  * Created by samtebbs on 13/08/2016.
  */
class CraftingRecipe(val output: ItemStack, val recipe: Array[String], val ingredients: Map[Char, ItemStack]) {

  def recipeParams = {
    val result = new util.LinkedList[Any]
    recipe.foreach(result.add)
    ingredients.foreach(pair ⇒ {
      result.add(pair._1)
      result.add(pair._2)
    })
    result.toArray
  }

}
