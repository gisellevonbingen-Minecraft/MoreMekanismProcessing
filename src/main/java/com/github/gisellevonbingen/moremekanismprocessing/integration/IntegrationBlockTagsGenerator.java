package com.github.gisellevonbingen.moremekanismprocessing.integration;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationBlockTagsGenerator extends BlockTagsProvider
{
	public IntegrationBlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, MoreMekanismProcessing.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{

	}

	protected void tagOres(INamedTag<Block> tag, ResourceLocation blockName)
	{
		this.tag(Blocks.ORES).addOptional(blockName);
		this.tag(tag).addOptional(blockName);
	}

}
