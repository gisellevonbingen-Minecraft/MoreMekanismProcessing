package gisellevonbingen.mmp.client;

import java.util.Map;
import java.util.Map.Entry;

import gisellevonbingen.mmp.client.renderer.color.CommonItemColor;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.world.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.RegistryObject;

public class ClientHandler
{
	public ClientHandler()
	{
		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(this);
	}

	@SubscribeEvent
	public void registerItemColors(ColorHandlerEvent.Item event)
	{
		for (Entry<MaterialType, Map<MaterialState, RegistryObject<Item>>> entry : MoreMekanismProcessingItems.PROCESSING_ITEMS.entrySet())
		{
			CommonItemColor itemColor = new CommonItemColor(entry.getKey());

			for (RegistryObject<Item> item : entry.getValue().values())
			{
				event.getItemColors().register(itemColor, item.get());
			}

		}

	}

}
