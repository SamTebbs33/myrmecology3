package com.github.samtebbs33.common.event

import com.github.samtebbs33.common.event.AntEvent.AntMatureEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

/**
  * Created by samtebbs on 29/08/2016.
  */
class AntEventHandler {

  @SubscribeEvent
  def onAntMature(matureEvent: AntMatureEvent): Unit = {

  }

}
