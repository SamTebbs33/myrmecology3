package com.github.samtebbs33.common.ant

import com.github.samtebbs33.common.tileentity.TileEntityFormicarium

/**
  * Created by samtebbs on 09/08/2016.
  */
abstract class AntAI {
  def shouldExecute(formicarium: TileEntityFormicarium): Boolean

  def execute(implicit formicarium: TileEntityFormicarium)
}
