package com.github.gisellevonbingen.moremekanismprocessing.client;

import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.moremekanismprocessing.client.renderer.color.CommonItemColor;
import com.github.gisellevonbingen.moremekanismprocessing.common.item.MoreMekanismProcessingItems;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraftforge.client.event.ColorHandlerEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

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
