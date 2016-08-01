package com.github.samtebbs33

/**
	* Created by samtebbs on 31/07/2016.
	*/
trait Named {

	def shortName: String

	def longName = Myrmecology.MOD_ID + "_" + shortName

	def externalName = Myrmecology.MOD_ID + ":" + shortName

}
