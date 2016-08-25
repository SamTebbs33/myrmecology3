package com.github.samtebbs33.common.ant

import com.github.samtebbs33.registry.AntTraitRegistry

/**
  * Created by samtebbs on 31/07/2016.
  */
class Trait(name: String, val default: Int = 0) {
  AntTraitRegistry.traits.add(this)
}
