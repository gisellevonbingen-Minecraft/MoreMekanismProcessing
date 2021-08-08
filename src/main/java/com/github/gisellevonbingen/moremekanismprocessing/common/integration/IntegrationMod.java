package com.github.gisellevonbingen.moremekanismprocessing.common.integration;

import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public abstract class IntegrationMod
{
	public IntegrationMod()
	{

	}

	public void addDataGenerator(GatherDataEvent event)
	{

	}

	public abstract String getModId();

}
