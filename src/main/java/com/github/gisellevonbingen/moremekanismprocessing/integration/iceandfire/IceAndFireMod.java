package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;

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

		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Sapphire), new ResourceLocation(MODID, "sapphire_ore"));
		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Amethyst), new ResourceLocation(MODID, "amythest_ore"));
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
