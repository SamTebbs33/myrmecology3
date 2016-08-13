package com.github.samtebbs33.common.ant

import java.util

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium
import net.minecraft.world.biome.Biome
import net.minecraftforge.event.AttachCapabilitiesEvent.TileEntity

import scala.collection.JavaConversions._
import scala.collection.JavaConverters._

/**
  * Created by samtebbs on 30/07/2016.
  */
abstract class AntSpecies(val name: String, val primaryColour: Int, val secondaryColour: Int, binomialName: String, val traits: AntTraits, val spawnMap: Map[Biome, Float] = Map()) {

  val aiList = new util.ArrayList[AntAI]()

  def updateAI(formicarium: TileEntityFormicarium): Unit = {
    for(ai : AntAI ← aiList) if(ai.shouldExecute(formicarium)) {
      ai.execute(formicarium)
      return
    }
  }

  def addAi(ai: AntAI) = aiList.add(ai)

}
