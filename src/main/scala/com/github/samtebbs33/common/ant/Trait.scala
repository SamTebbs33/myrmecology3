package com.github.samtebbs33.common.ant

import com.github.samtebbs33.registry.AntTraitRegistry

/**
  * Created by samtebbs on 31/07/2016.
  */
class Trait[T](name: String, val default: T) {
  AntTraitRegistry.traits.add(this)
}
