package com.github.samtebbs33.common.ant.behaviour
import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import com.github.samtebbs33.Util._
import net.minecraft.block.{BlockCrops, BlockMelon, BlockSapling, IGrowable}
import net.minecraft.init.{Blocks, Items}
import net.minecraft.item.{ItemBlock, ItemSeeds, ItemStack}
import net.minecraft.util.math.Vec3i
import net.minecraftforge.common.IPlantable
import net.minecraftforge.oredict.OreDictionary

/**
  * Created by samuelt on 24/08/2016.
  */
class BehaviourPlantCrops(name: String) extends Behaviour(name) {

  val radius = new Vec3i(5, 5, 5)

  override def execute(formicarium: TileEntityFormicarium, numAnts: Int, stacks: Seq[ItemStack]): Unit = {
    val world = formicarium.getWorld
    var i = 0
    val plantableItems = formicarium.occupiedSlots().map(slot => (slot, formicarium.getStackInSlot(slot).getItem))
    for(pair ← plantableItems) {
      for(pos ← world.getBlocksInRadius(formicarium.getPos, radius).map(_._1)) {
        val posUp = pos.up()
        val blockState = Option(pair._2 match {
          case itemBlock: ItemBlock if itemBlock.block.isInstanceOf[IGrowable] ⇒ itemBlock.block.getDefaultState
          case plantable: IPlantable ⇒ plantable.getPlant(world, posUp)
          case _ ⇒ null
        })
        blockState.ifDefined(state ⇒ {
          if(i < numAnts && formicarium.getStackSize(pair._1) > 0 && state.getBlock.canPlaceBlockAt(world, posUp)) {
            world.setBlockState(posUp, state)
            formicarium.decrStackSize(pair._1, 1)
            i += 1
          }
        })
      }
    }
  }

}
