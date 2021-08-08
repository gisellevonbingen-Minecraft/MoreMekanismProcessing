package com.github.gisellevonbingen.moremekanismprocessing.integration;

import net.minecraftforge.fml.event.lifecycle.GatherDataEvent;

public abstract class IntegrationMod
{
	public IntegrationMod()
	{

	}

	public abstract void initialize();

	public abstract String getModId();

	public void addDataGenerator(GatherDataEvent event)
	{

	}

}
