package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationBlockTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.data.DataGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtremeReactors2BlockTagsGenerator extends IntegrationBlockTagsGenerator
{
	public ExtremeReactors2BlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		this.tagOres(IntegrationTags.Blocks.ORES_YELLORITE, new ResourceLocation(ExtremeReactors2Mod.MODID, "yellorite_ore"));
		this.tagOres(IntegrationTags.Blocks.ORES_URANIUM, new ResourceLocation(ExtremeReactors2Mod.MODID, "yellorite_ore"));
	}

}
