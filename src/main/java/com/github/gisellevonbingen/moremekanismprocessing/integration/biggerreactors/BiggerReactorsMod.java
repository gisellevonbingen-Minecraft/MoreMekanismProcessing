package com.github.gisellevonbingen.moremekanismprocessing.integration.biggerreactors;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.util.ResourceLocation;

public class BiggerReactorsMod extends IntegrationMod
{
	public static final String MODID = "biggerreactors";

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
