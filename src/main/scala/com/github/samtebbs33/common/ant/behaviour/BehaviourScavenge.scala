package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.entity.item.EntityItem
import net.minecraft.item.ItemStack
import net.minecraft.util.math.{AxisAlignedBB, Vec3i}

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

/**
  * Created by samuelt on 26/08/2016.
  */
class BehaviourScavenge(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val pos = formicarium.getPos
    val items = formicarium.getWorld.getEntitiesWithinAABB(classOf[EntityItem], new AxisAlignedBB(pos.getX - radius.getX,
      pos.getY - radius.getY,
      pos.getZ - radius.getZ,
      pos.getX + radius.getX,
      pos.getY + radius.getY,
      pos.getZ + radius.getZ
    ))
    var i = 0
    items.map(entity => (entity, entity.getEntityItem)).filter(pair => formicarium.canHoldStack(pair._2)).takeWhile(_ => i < numAnts).foreach(pair => {
      formicarium.addStack(pair._2)
      pair._1.setDead()
      i += 1
    })
  }

}
