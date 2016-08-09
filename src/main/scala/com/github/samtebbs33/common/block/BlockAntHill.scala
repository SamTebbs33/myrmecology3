package com.github.samtebbs33.common.block

import java.util

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.{ItemAnt, ItemAntExtractor}
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPos
import net.minecraft.world.IBlockAccess

import scala.util.Random

/**
  * Created by samtebbs on 30/07/2016.
  */
class BlockAntHill(name: String, species: ItemAnt*) extends MyrmecologyBlock(Material.CACTUS, "ant_hill_" + name) {
  override def canHarvestBlock(world: IBlockAccess, pos: BlockPos, player: EntityPlayer): Boolean = player.getHeldItemMainhand.getItem.isInstanceOf[ItemAntExtractor]

  def maxDroppedAnts = 4

  def minDroppedAnts = 1

  override def getDrops(world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int): util.List[ItemStack] = {
    val result = new util.LinkedList[ItemStack]()
    val maxSpawned = maxDroppedAnts
    val minSpawned = minDroppedAnts
    val numToSpawn = Random.nextInt(maxSpawned - minSpawned) + minSpawned
    for (i <- 0 to numToSpawn) {
      val item = species(Random.nextInt(species.length))
      result.add(new ItemStack(item, 1, AntTypes.LARVA.id))
    }
    result
  }
}
