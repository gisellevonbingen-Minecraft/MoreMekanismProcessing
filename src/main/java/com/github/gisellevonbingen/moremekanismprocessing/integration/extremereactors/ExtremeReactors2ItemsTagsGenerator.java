package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.moremekanismprocessing.common.tag.MoreMekanismProcessingTags;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtremeReactors2ItemsTagsGenerator extends ItemTagsProvider
{
	public ExtremeReactors2ItemsTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, ExtremeReactors2Mod.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		Map<INamedTag<Block>, INamedTag<Item>> map = new HashMap<>();
		map.put(Tags.Blocks.ORES, Tags.Items.ORES);
		map.put(MoreMekanismProcessingTags.Blocks.ORES_YELLORITE, MoreMekanismProcessingTags.Items.ORES_YELLORITE);
		map.put(MoreMekanismProcessingTags.Blocks.ORES_URANIUM, MoreMekanismProcessingTags.Items.ORES_URANIUM);

		for (Entry<INamedTag<Block>, INamedTag<Item>> entry : map.entrySet())
		{
			this.copy(entry.getKey(), entry.getValue());
		}

	}

}
