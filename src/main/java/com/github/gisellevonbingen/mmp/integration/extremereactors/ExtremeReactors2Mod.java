package com.github.gisellevonbingen.mmp.integration.extremereactors;

import com.github.gisellevonbingen.mmp.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.mmp.integration.IntegrationMod;
import com.github.gisellevonbingen.mmp.integration.IntegrationTags;

import net.minecraft.resources.ResourceLocation;

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
