package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer())
		{
			generator.addProvider(new ItemTagsGenerator(generator, new BlockTagsGenerator(generator, existingFileHelper), existingFileHelper));
			generator.addProvider(new SlurryTagsGenerator(generator, existingFileHelper));
			generator.addProvider(new RecipesGenerator(generator));
			generator.addProvider(new LanguagesGenerator(generator));
		}

		if (event.includeClient())
		{
			generator.addProvider(new ItemsModelGenerator(generator, existingFileHelper));
		}

	}

}
