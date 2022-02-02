package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;

import net.minecraft.resources.ResourceLocation;

public class IceAndFireMod extends IntegrationMod
{
	public static final String MODID = "iceandfire";
	public static final ResourceLocation AMYTHEST_ORE = rl("amythest_ore");
	public static final ResourceLocation SAPPHIRE_ORE = rl("sapphire_ore");

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

		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Sapphire), SAPPHIRE_ORE);
		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Amethyst), AMYTHEST_ORE);
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
