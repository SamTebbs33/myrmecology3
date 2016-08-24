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
  val harvesters = scala.collection.mutable.Map[Class[_ <: Block], HarvestChecker]()

  def metadataHarvester(state: IBlockState) = state.getBlock.asInstanceOf[BlockCrops].isMaxAge(state)
  def blockHarvester(state: IBlockState) = true

  addHarvester(blockHarvester, classOf[BlockPumpkin])
  addHarvester(blockHarvester, classOf[BlockMelon])
  addHarvester(metadataHarvester, classOf[BlockCrops])

  def addHarvester(harvester: HarvestChecker, cls: Class[_ <: Block]) = harvesters.put(cls, harvester)

  def harvestCrop(block: IBlockState): Boolean = {
    harvesters.find(pair => pair._1.isInstance(block.getBlock.getClass)) match {
      case Some(pair) => pair._2.apply(block)
      case _ => false
    }
  }

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    val crops = world.getTypedBlocksInRadius[IGrowable](formicarium.getPos, radius)
    var i = 0
    crops.takeWhile(_ => i < numAnts).foreach(pair => if(harvestCrop(pair._2)) {
      world.destroyBlock(pair._1, true)
      i += 1
    })
  }
}
