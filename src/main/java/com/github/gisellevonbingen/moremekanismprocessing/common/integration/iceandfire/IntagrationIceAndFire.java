package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.common.integration.IntegrationMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class IntagrationIceAndFire extends IntegrationMod
{
	public static final String MODID = "iceandfire";

	@Override
	public void addDataGenerator(GatherDataEvent event)
	{
		super.addDataGenerator(event);

		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		DataGenerator generator = event.getGenerator();

		BlockTagsGenerator blockTagsGenerator = new BlockTagsGenerator(generator, existingFileHelper);
		generator.addProvider(blockTagsGenerator);
		generator.addProvider(new ItemsTagsGenerator(generator, blockTagsGenerator, existingFileHelper));
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
