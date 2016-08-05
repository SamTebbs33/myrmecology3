package registry

import com.github.samtebbs33.Myrmecology
import net.minecraft.creativetab.CreativeTabs
import net.minecraft.init.Items
import net.minecraft.item.Item
import net.minecraftforge.fml.relauncher.{Side, SideOnly}

/**
	* Created by samtebbs on 30/07/2016.
	*/
object Registry {

	final val creativeTab = makeCreativeTab(Items.ARROW)

	val PLAINS = "plains"

	private def makeCreativeTab(iconItem: Item) = new CreativeTabs(Myrmecology.MOD_ID) {
		@SideOnly(Side.CLIENT)
		override def getTabIconItem = iconItem
	}

}
