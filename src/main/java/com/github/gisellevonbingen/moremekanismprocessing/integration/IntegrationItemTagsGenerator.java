package com.github.gisellevonbingen.moremekanismprocessing.integration;

import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	public IntegrationItemTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, MoreMekanismProcessing.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{

	}

	protected void copyOres(Map<INamedTag<Block>, INamedTag<Item>> map)
	{
		this.copy(Tags.Blocks.ORES, Tags.Items.ORES);

		for (Entry<INamedTag<Block>, INamedTag<Item>> entry : map.entrySet())
		{
			this.copy(entry.getKey(), entry.getValue());
		}

	}

}
