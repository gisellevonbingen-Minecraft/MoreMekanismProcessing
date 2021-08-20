package com.github.gisellevonbingen.moremekanismprocessing.integration;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public abstract class IntegrationMod
{
	public IntegrationMod()
	{

	}

	public abstract void initialize();

	public abstract String getModId();

	public ResourceLocation getResourceLocation(String path)
	{
		return new ResourceLocation(this.getModId(), path);
	}

	public void addDataGenerator(GatherDataEvent event)
	{

	}

	public void addBlockTags(IntegrationBlockTagsGenerator generator)
	{

	}

	public void addItemTags(IntegrationItemTagsGenerator generator)
	{

	}

}
