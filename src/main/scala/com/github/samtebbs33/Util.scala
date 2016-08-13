package com.github.samtebbs33

import net.minecraft.item.ItemStack

import scala.util.Random

/**
  * Created by samtebbs on 09/08/2016.
  */
object Util {

  implicit class OptionUtil[T](opt: Option[T]) {
    def ifDefined[A](f: (T) ⇒ A) = if(opt.isDefined) f.apply(opt.get)
    def ifNotDefined[A](f: () ⇒ A) = if(opt.isEmpty) f.apply()
  }

  implicit class ItemStackUtil(stack: ItemStack) {
    def getItemAs[C] = stack.getItem.asInstanceOf[C]
  }

  implicit class ArrayUtil[T](array: Array[T]) {
    def rand = array(Random.nextInt(array.length))
  }

  implicit class SeqUtil[T](seq: Seq[T]) {
    def rand = seq(Random.nextInt(seq.size))
  }

}
