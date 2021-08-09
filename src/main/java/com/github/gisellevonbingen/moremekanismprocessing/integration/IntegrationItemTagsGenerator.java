package com.github.gisellevonbingen.moremekanismprocessing.integration;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	protected final IntegrationBlockTagsGenerator blockTagsGenerator;

	public IntegrationItemTagsGenerator(DataGenerator generator, IntegrationBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(generator, blockTagsGenerator, MoreMekanismProcessing.MODID, existingFileHelper);
		this.blockTagsGenerator = blockTagsGenerator;
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addItemTags(this));
		this.copyOres();
	}

	@Override
	public Builder<Item> tag(INamedTag<Item> tag)
	{
		return super.tag(tag);
	}

	@Override
	public void copy(INamedTag<Block> blockTag, INamedTag<Item> itemTag)
	{
		super.copy(blockTag, itemTag);
	}

	protected void copyOres()
	{
		this.copy(Tags.Blocks.ORES, Tags.Items.ORES);

		for (INamedTag<Block> blockTag : this.blockTagsGenerator.getOreTags())
		{
			INamedTag<Item> itemTag = ItemTags.bind(blockTag.getName().toString());
			this.copy(blockTag, (INamedTag<Item>) itemTag);
		}

	}

}
