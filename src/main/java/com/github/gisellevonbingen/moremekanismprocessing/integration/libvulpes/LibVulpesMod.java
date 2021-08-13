package com.github.gisellevonbingen.moremekanismprocessing.integration.libvulpes;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationMod;

import net.minecraft.util.ResourceLocation;

public class LibVulpesMod extends IntegrationMod
{
	public static final String MODID = "libvulpes";

	public LibVulpesMod()
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

		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Titanium), new ResourceLocation(MODID, "orerutile"));
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

}
