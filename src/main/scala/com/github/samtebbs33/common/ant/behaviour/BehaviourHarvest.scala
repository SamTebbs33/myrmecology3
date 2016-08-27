package com.github.samtebbs33.common.ant.behaviour

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block._
import net.minecraft.block.properties.IProperty
import net.minecraft.block.state.IBlockState
import net.minecraft.client.renderer.block.model.ModelBakery
import net.minecraft.item.ItemStack
import net.minecraft.util.math.Vec3i

/**
  * Created by samuelt on 24/08/2016.
  */
class BehaviourHarvest(name: String) extends Behaviour(name) {
  type HarvestChecker = (IBlockState) => Boolean

  val radius = new Vec3i(5, 5, 5)

  def metadataHarvester(state: IBlockState) = state.getBlock.asInstanceOf[BlockCrops].isMaxAge(state)
  def blockHarvester(state: IBlockState) = true

  val harvesters = Map[Class[_ <: Block], HarvestChecker](
    classOf[BlockCrops] -> metadataHarvester,
    classOf[BlockMelon] -> blockHarvester,
    classOf[BlockPumpkin] -> blockHarvester
  )

  def canHarvestCrop(block: IBlockState): Boolean = {
    harvesters.find(pair => pair._1.isInstance(block.getBlock)) match {
      case Some(pair) => pair._2.apply(block)
      case _ => false
    }
  }

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    val crops = world.getBlocksInRadius(formicarium.getPos, radius)
    var i = 0
    println(crops.find(pair ⇒ !pair._2.getBlock.isInstanceOf[IGrowable]))
    crops.filter(pair => canHarvestCrop(pair._2)).foreach(pair => {
      if(i < numAnts) {
        world.destroyBlock(pair._1, true)
        i += 1
      }
    })
  }
}
