package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.util.ResourceLocation;

public class ExtremeReactors2Mod extends IntegrationMod
{
	public static final String MODID = "bigreactors";
	public static final ResourceLocation YELLORITE_ORE = rl("yellorite_ore");

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

		generator.tagOres(IntegrationTags.Blocks.ORES_YELLORITE, YELLORITE_ORE);
		generator.tagOres(IntegrationTags.Blocks.ORES_URANIUM, YELLORITE_ORE);
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
