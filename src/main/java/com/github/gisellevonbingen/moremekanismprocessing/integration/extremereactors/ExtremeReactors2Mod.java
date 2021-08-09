package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class ExtremeReactors2Mod extends IntegrationMod
{
	public static final String MODID = "bigreactors";

	public ExtremeReactors2Mod()
	{

	}

	@Override
	public void initialize()
	{

	}

	@Override
	public void addDataGenerator(GatherDataEvent event)
	{
		super.addDataGenerator(event);

		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		DataGenerator generator = event.getGenerator();

		if (event.includeServer() == true)
		{
			ExtremeReactors2BlockTagsGenerator blockTagsGenerator = new ExtremeReactors2BlockTagsGenerator(generator, existingFileHelper);
			generator.addProvider(blockTagsGenerator);
			generator.addProvider(new ExtremeReactors2ItemTagsGenerator(generator, blockTagsGenerator, existingFileHelper));
		}

	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
