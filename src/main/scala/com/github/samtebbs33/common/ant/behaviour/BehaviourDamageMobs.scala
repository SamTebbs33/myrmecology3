package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.entity.monster.EntityMob
import net.minecraft.item.ItemStack
import com.github.samtebbs33.Util._
import net.minecraft.init.{MobEffects, PotionTypes}
import net.minecraft.potion.{Potion, PotionEffect, PotionUtils}
import net.minecraft.util.DamageSource
import net.minecraft.util.math.Vec3i

import scala.collection.JavaConverters._
import scala.collection.JavaConversions._

/**
  * Created by samtebbs on 28/08/2016.
  */
class BehaviourDamageMobs(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)
  val poisonPotionID = 19
  val damageSource = new DamageSource("myrmecology:ant")

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    var i = 0
    val mobs = world.getEntitiesWithinAABB(classOf[EntityMob], formicarium.getPos.toVec3i.toAABB(radius))
    mobs.foreach(mob ⇒ {
        if(i < numAnts) {
          mob.attackEntityFrom(damageSource, 2)
          i += 1
        }
    })
  }
}
