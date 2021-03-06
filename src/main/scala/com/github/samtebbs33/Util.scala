package com.github.samtebbs33

import java.util.Objects

import net.minecraft.block.state.IBlockState
import net.minecraft.item.ItemStack
import net.minecraft.util.math.{AxisAlignedBB, BlockPos, Vec3i}
import net.minecraft.world.World

import scala.collection.mutable
import scala.util.Random

/**
  * Created by samtebbs on 09/08/2016.
  */
object Util {

  implicit class OptionUtil[T](opt: Option[T]) {
    def ifDefined[A](f: (T) ⇒ A): Unit = opt match {
      case Some(foo) ⇒ f(foo)
      case None ⇒
    }

    def ifNotDefined[A](f: () ⇒ A): Unit = opt match {
      case None ⇒ f()
      case _ ⇒
    }

    def `?:`(v: T) = opt.getOrElse(v)

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
    def getBlocksInRadius(pos: BlockPos, radius: Vec3i): mutable.Set[(BlockPos, IBlockState)] = {
      val set = mutable.Set[(BlockPos, IBlockState)]()
      getBlockPositions(pos, radius).filter(!world.isAirBlock(_)).map(pos ⇒ (pos, world.getBlockState(pos))).foreach(set.add)
      set
    }

    def getBlockPositions(pos: BlockPos, radius: Vec3i) = {
      val posX = pos.getX
      val posY = pos.getY
      val posZ = pos.getZ
      val radiusX = radius.getX
      val radiusY = radius.getY
      val radiusZ = radius.getZ
      val set = mutable.Set[BlockPos]()
      Range(posX - radiusX, posX + radiusX).foreach(x ⇒ Range(posY - radiusY, posY + radiusY).foreach(y ⇒ Range(posZ - radiusZ, posZ + radiusZ).map(z ⇒ new BlockPos(x, y, z)).foreach(set.add)))
      set
    }
  }

  implicit class Vec3iUtil(vec: Vec3i) {
    def toAABB(radius: Vec3i) = new AxisAlignedBB(vec.getX - radius.getX,
      vec.getY - radius.getY,
      vec.getZ - radius.getZ,
      vec.getX + radius.getX,
      vec.getY + radius.getY,
      vec.getZ + radius.getZ
    )
  }

  implicit class BlockPosUtil(blockPos: BlockPos) {
    def toVec3i = new Vec3i(blockPos.getX, blockPos.getY, blockPos.getZ)
  }

  implicit class BooleanUtil(bool: Boolean) {
    def `?`[T](left: T, right: T) = if(bool) left else right
    def `?:`(right: Boolean) = bool ? (bool, right)
  }

  implicit class MathsUtil(a: Double) {
    def `**`(b: Double) = Math.pow(a, b)
  }

  implicit class AnyUtil(a: Any) {
    def ===(b: Any) = Objects.equals(a, b)
  }
}
