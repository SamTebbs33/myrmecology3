package com.github.samtebbs33.common.worldgen

import java.util.Random

import com.github.samtebbs33.registry.BlockRegistry
import net.minecraft.util.math.BlockPos
import net.minecraft.world.World
import net.minecraft.world.chunk.{IChunkGenerator, IChunkProvider}
import net.minecraftforge.fml.common.IWorldGenerator

/**
  * Created by samtebbs on 10/08/2016.
  */
class WorldGen extends IWorldGenerator {

  var spawnChance = 0.25f

  override def generate(random: Random, chunkX: Int, chunkZ: Int, world: World, chunkGenerator: IChunkGenerator, chunkProvider: IChunkProvider): Unit = {
    if (random.nextFloat() < spawnChance) {
      val numAntHills = BlockRegistry.antHills.size
      val sequence = for (i <- 1 to numAntHills) yield random.nextInt(numAntHills)
      sequence.foreach(index ⇒ {
        val block = BlockRegistry.antHills.get(index)
        val biome = world.getBiomeForCoordsBody(new BlockPos(chunkX << 4, 0, chunkZ << 4))
        val chance = block.generationChance(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, biome)
        if (random.nextFloat() < chance) block.generate(random, chunkX, chunkZ, world, chunkGenerator, chunkProvider, biome)
      })
    }
  }

}
