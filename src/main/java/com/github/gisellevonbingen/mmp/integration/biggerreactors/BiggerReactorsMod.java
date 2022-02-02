package com.github.gisellevonbingen.mmp.integration.biggerreactors;

import com.github.gisellevonbingen.mmp.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.mmp.integration.IntegrationMod;
import com.github.gisellevonbingen.mmp.integration.IntegrationTags;

import net.minecraft.resources.ResourceLocation;

public class BiggerReactorsMod extends IntegrationMod
{
	public static final String MODID = "biggerreactors";
	public static final ResourceLocation YELLORITE_ORE = rl("yellorite_ore");

	public BiggerReactorsMod()
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
