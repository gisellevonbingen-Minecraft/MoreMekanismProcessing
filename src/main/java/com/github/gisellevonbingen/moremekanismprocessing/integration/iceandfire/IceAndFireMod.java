package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.util.ResourceLocation;

public class IceAndFireMod extends IntegrationMod
{
	public static final String MODID = "iceandfire";

	public IceAndFireMod()
	{

	}

	@Override
	public void initialize()
	{

	}

	@Override
	public void addBlockTags(IntegrationBlockTagsGenerator generator)
	{
		super.addBlockTags(generator);

		generator.tagOres(IntegrationTags.Blocks.ORES_SAPPHIRE, new ResourceLocation(MODID, "sapphire_ore"));
		generator.tagOres(IntegrationTags.Blocks.ORES_AMETHYST, new ResourceLocation(MODID, "amythest_ore"));
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
