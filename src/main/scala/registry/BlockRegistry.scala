package registry

import java.util.HashSet
import java.util.function.Consumer

import com.github.samtebbs33.common.block.{BlockAntHill, BlockSolarium, MyrmecologyBlock}
import com.github.samtebbs33.common.tileentity.TileEntitySolarium
import net.minecraftforge.fml.common.registry.GameRegistry

import scala.collection.JavaConversions._

/**
	* Created by samtebbs on 02/08/2016.
	*/
object BlockRegistry {
	final val blocks = new HashSet[MyrmecologyBlock]()

	final val solarium = new BlockSolarium("solarium")
	final val antHillPlains = new BlockAntHill(Registry.PLAINS, ItemRegistry.antPlains)

	def registerTileEntities(): Unit = {
		GameRegistry.registerTileEntity(classOf[TileEntitySolarium], solarium.shortName)
	}

	def registerBlocks(): Unit = {
		blocks.foreach(registerBlock)
	}

	private def registerBlock(block: MyrmecologyBlock): Unit = {
		block.setRegistryName(block.registryName)
		GameRegistry.register(block)
		ItemRegistry.registerBlock(block)
	}

}
