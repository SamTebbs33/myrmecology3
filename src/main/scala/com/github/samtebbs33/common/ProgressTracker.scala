package com.github.samtebbs33.common

import net.minecraft.nbt.NBTTagCompound

/**
  * Created by samtebbs on 09/08/2016.
  */
class ProgressTracker(var targetTime: Int = Int.MaxValue) {

  var progress = 0
  var ticks = 0
  final val ticksPerSecond = 20

  def reset: Unit = {
    progress = 0
    ticks = 0
  }

  def update = {
    ticks += 1
    if (ticks >= ticksPerSecond) {
      progress += 1
      ticks = 0
    }
  }

  def done = progress >= targetTime

  def writeToNBT(compound: NBTTagCompound): NBTTagCompound = {
    compound.setInteger("progress", progress)
    compound
  }

  def readFromNBT(compound: NBTTagCompound) = progress = compound.getInteger("progress")

}
