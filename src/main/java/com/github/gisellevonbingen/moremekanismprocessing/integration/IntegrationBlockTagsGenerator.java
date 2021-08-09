package com.github.gisellevonbingen.moremekanismprocessing.integration;

import java.util.ArrayList;
import java.util.List;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.google.common.collect.Lists;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationBlockTagsGenerator extends BlockTagsProvider
{
	private final List<INamedTag<Block>> oreTags;

	public IntegrationBlockTagsGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.oreTags = new ArrayList<>();
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addBlockTags(this));
	}

	public List<INamedTag<Block>> getOreTags()
	{
		return Lists.newArrayList(this.oreTags);
	}

	@Override
	public Builder<Block> tag(INamedTag<Block> tag)
	{
		return super.tag(tag);
	}

	public void tagOres(INamedTag<Block> tag, ResourceLocation blockName)
	{
		this.tag(Blocks.ORES).addOptional(blockName);
		this.tag(tag).addOptional(blockName);

		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

	}

}
