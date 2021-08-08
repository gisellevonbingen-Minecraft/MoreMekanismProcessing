package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsTagsGenerator extends ItemTagsProvider
{
	public ItemsTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, IceAndFireMod.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		Map<INamedTag<Block>, INamedTag<Item>> map = new HashMap<>();
		map.put(Tags.Blocks.ORES, Tags.Items.ORES);
		map.put(IceAndFireTags.Blocks.ORES_SAPPHIRE, IceAndFireTags.Items.ORES_SAPPHIRE);
		map.put(IceAndFireTags.Blocks.ORES_AMETHYST, IceAndFireTags.Items.ORES_AMETHYST);

		for (Entry<INamedTag<Block>, INamedTag<Item>> entry : map.entrySet())
		{
			this.copy(entry.getKey(), entry.getValue());
		}

	}

}
