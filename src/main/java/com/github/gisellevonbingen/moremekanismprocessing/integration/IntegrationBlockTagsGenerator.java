package com.github.gisellevonbingen.moremekanismprocessing.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private final Map<INamedTag<Block>, List<ResourceLocation>> tags;
	private final List<INamedTag<Block>> oreTags;

	public IntegrationBlockTagsGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.tags = new HashMap<>();
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

	public void tagOres(INamedTag<Block> tag, ResourceLocation blockName)
	{
		this.targOres0(Blocks.ORES, blockName);
		this.targOres0(tag, blockName);
	}

	private void targOres0(INamedTag<Block> tag, ResourceLocation blockName)
	{
		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

		this.tag(tag, blockName);
	}

	public void tag(INamedTag<Block> tag, ResourceLocation blockName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(blockName) == false)
		{
			this.tag(tag).addOptional(blockName);
			list.add(blockName);
		}

	}

}
