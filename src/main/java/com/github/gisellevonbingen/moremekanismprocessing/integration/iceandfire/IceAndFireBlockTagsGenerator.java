package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IceAndFireBlockTagsGenerator extends IntegrationBlockTagsGenerator
{
	public IceAndFireBlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		this.tagOres(IntegrationTags.Blocks.ORES_SAPPHIRE, new ResourceLocation(IceAndFireMod.MODID, "sapphire_ore"));
		this.tagOres(IntegrationTags.Blocks.ORES_AMETHYST, new ResourceLocation(IceAndFireMod.MODID, "amythest_ore"));
	}

}
