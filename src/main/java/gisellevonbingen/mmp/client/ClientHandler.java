package gisellevonbingen.mmp.client;

import java.util.Map;
import java.util.Map.Entry;

import gisellevonbingen.mmp.client.renderer.color.CommonItemColor;
import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.world.item.Item;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterColorHandlersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = MoreMekanismProcessing.MODID, value = Dist.CLIENT, bus = Bus.MOD)
public class ClientHandler
{
	@SubscribeEvent
	public static void registerItemColors(RegisterColorHandlersEvent.Item event)
	{
		for (Entry<MaterialType, Map<MaterialState, RegistryObject<Item>>> entry : MoreMekanismProcessingItems.PROCESSING_ITEMS.entrySet())
		{
			CommonItemColor itemColor = new CommonItemColor(entry.getKey());

			for (RegistryObject<Item> item : entry.getValue().values())
			{
				event.register(itemColor, item.get());
			}

		}

	}

}
