package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationItemTagsGenerator;
import com.github.gisellevonbingen.moremekanismprocessing.integration.IntegrationTags;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IceAndFireItemTagsGenerator extends IntegrationItemTagsGenerator
{
	public IceAndFireItemTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		Map<INamedTag<Block>, INamedTag<Item>> map = new HashMap<>();
		map.put(IntegrationTags.Blocks.ORES_SAPPHIRE, IntegrationTags.Items.ORES_SAPPHIRE);
		map.put(IntegrationTags.Blocks.ORES_AMETHYST, IntegrationTags.Items.ORES_AMETHYST);
		this.copyOres(map);
	}

}
