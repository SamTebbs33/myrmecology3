package com.github.samtebbs33.common.ant

import net.minecraft.entity.ai.EntityAIBase
import net.minecraftforge.event.AttachCapabilitiesEvent.TileEntity

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class AntAI {
  def shouldExecute(formicarium: TileEntity): Boolean
  def execute(implicit formicarium: TileEntity)
}
