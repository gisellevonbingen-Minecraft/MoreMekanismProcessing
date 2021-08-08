package com.github.gisellevonbingen.moremekanismprocessing.integration;

import java.util.ArrayList;
import java.util.List;

import com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire.IceAndFireMod;
import com.google.common.collect.Lists;

public class MoreMekanismProcessingIntagrations
{
	private static final List<IntegrationMod> MODS = new ArrayList<>();

	public static void initialize()
	{
		MODS.addAll(createIntegrationMods());
		MODS.forEach(m -> m.initialize());
	}

	public static List<IntegrationMod> getMods()
	{
		return Lists.newArrayList(MODS);
	}

	private static List<IntegrationMod> createIntegrationMods()
	{
		List<IntegrationMod> mods = new ArrayList<>();
		mods.add(new IceAndFireMod());

		return mods;
	}

}
