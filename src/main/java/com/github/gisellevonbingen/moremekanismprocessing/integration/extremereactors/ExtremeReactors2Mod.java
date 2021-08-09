package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.util.ResourceLocation;

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
	public void addBlockTags(IntegrationBlockTagsGenerator generator)
	{
		super.addBlockTags(generator);

		ResourceLocation blockName = new ResourceLocation(MODID, "yellorite_ore");
		generator.tagOres(IntegrationTags.Blocks.ORES_YELLORITE, blockName);
		generator.tagOres(IntegrationTags.Blocks.ORES_URANIUM, blockName);
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
