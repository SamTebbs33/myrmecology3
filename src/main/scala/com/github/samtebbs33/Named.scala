package com.github.samtebbs33

/**
	* Created by samtebbs on 31/07/2016.
	*/
trait Named {

	def getShortName() : String
	def getLongName() = Myrmecology.MOD_ID + "_" + getShortName()
	def getExternalName() = Myrmecology.MOD_ID + ":" + getShortName()

}
