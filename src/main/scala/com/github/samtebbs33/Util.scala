package com.github.samtebbs33

import java.util.stream.Collectors

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.{BlockPos, Vec3i}
import net.minecraft.world.World

import scala.util.Random
import scala.collection.JavaConversions._
import scala.collection.JavaConverters._
import scala.collection.immutable.HashSet

/**
  * Created by samtebbs on 09/08/2016.
  */
object Util {

  implicit class OptionUtil[T](opt: Option[T]) {
    def ifDefined[A](f: (T) ⇒ A) = if (opt.isDefined) f.apply(opt.get)

    def ifNotDefined[A](f: () ⇒ A) = if (opt.isEmpty) f.apply()
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

  implicit class WorldUtil(world: World) {
    def getBlocksInRadius(pos: BlockPos, radius: Vec3i): Set[(BlockPos, IBlockState)] = {
      val posX = pos.getX
      val posY = pos.getY
      val posZ = pos.getZ
      val radiusX = radius.getX
      val radiusY = radius.getY
      val radiusZ = radius.getZ
      val set = Set[(BlockPos, IBlockState)]()
      Range(posX - radiusX, posX + radiusX).foreach(x ⇒
        Range(posY - radiusY, posY + radiusY).foreach(y ⇒
          Range(posZ - radiusZ, posZ + radiusZ).toStream.map(z ⇒ new BlockPos(x, y, z)).filter(!world.isAirBlock(_)).map(pos => (pos, world.getBlockState(pos))).foreach(set.add)
        )
      )
      set
    }
    def getTypedBlocksInRadius[T](pos: BlockPos, radius: Vec3i) = getBlocksInRadius(pos, radius).filter(pair=> pair._2.getBlock.isInstanceOf[T])
  }

}
