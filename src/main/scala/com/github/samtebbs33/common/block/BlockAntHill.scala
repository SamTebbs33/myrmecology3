package com.github.samtebbs33.common.block

import java.util

import com.github.samtebbs33.common.ant.AntTypes
import com.github.samtebbs33.common.item.{ItemAnt, ItemAntExtractor}
import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.block.material.Material
import net.minecraft.block.state.IBlockState
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.item.ItemStack
import net.minecraft.util.EnumFacing
import net.minecraft.util.math.BlockPos
import net.minecraft.world.biome.Biome
import net.minecraft.world.chunk.{IChunkGenerator, IChunkProvider}
import net.minecraft.world.{IBlockAccess, World}

import scala.util.Random

/**
  * Created by samtebbs on 30/07/2016.
  */
class BlockAntHill(name: String, species: ItemAnt) extends MyrmecologyBlock(Material.CACTUS, "ant_hill_" + name) {
  BlockRegistry.antHills.add(this)

  def maxDroppedAnts = 4

  def minDroppedAnts = 1

  def generate(random: java.util.Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider, biome: Biome): Unit = {
    val randX = chunkX * 16 + random.nextInt(16)
    val randZ = chunkZ * 16 + random.nextInt(16)
    val pos = world.getHeight(new BlockPos(randX, 0, randZ))
    val posDown = pos.down()
    if (!world.getBlockState(posDown).getBlock.isBlockSolid(world, posDown, EnumFacing.UP)) return
    if (world.getBlockState(pos).getBlock.isReplaceable(world, pos)) world.setBlockState(pos, this.getDefaultState)
  }

  override def canHarvestBlock(world: IBlockAccess, pos: BlockPos, player: EntityPlayer): Boolean = player.getHeldItemMainhand.getItem.isInstanceOf[ItemAntExtractor]

  def generationChance(rand: java.util.Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider, biome: Biome): Float = species.species.spawnMap.getOrElse(biome, 0)

  override def getDrops(world: IBlockAccess, pos: BlockPos, state: IBlockState, fortune: Int): util.List[ItemStack] = {
    val result = new util.LinkedList[ItemStack]()
    val maxSpawned = maxDroppedAnts
    val minSpawned = minDroppedAnts
    val numToSpawn = Random.nextInt(maxSpawned - minSpawned) + minSpawned
    for (i <- 0 to numToSpawn) result.add(new ItemStack(species, 1, AntTypes.LARVA.id))
    result
  }
}
