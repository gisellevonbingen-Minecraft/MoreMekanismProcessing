package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;
import com.github.gisellevonbingen.moremekanismprocessing.util.LauncherUtil;

import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public class IceAndFireMod extends IntegrationMod
{
	public static final String MODID = "iceandfire";

	public IceAndFireMod()
	{

	}

	@Override
	public void initialize()
	{
		if (LauncherUtil.isRunData() == true)
		{
			IceAndFireBlocks.register();
			IceAndFireItems.register();
		}

	}

	@Override
	public void addDataGenerator(GatherDataEvent event)
	{
		super.addDataGenerator(event);

		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();
		DataGenerator generator = event.getGenerator();

		if (event.includeServer() == true)
		{
			BlockTagsGenerator blockTagsGenerator = new BlockTagsGenerator(generator, existingFileHelper);
			generator.addProvider(blockTagsGenerator);
			generator.addProvider(new ItemsTagsGenerator(generator, blockTagsGenerator, existingFileHelper));
		}

	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
