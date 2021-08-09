package com.github.gisellevonbingen.moremekanismprocessing.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	protected final IntegrationBlockTagsGenerator blockTagsGenerator;
	private final Map<INamedTag<Item>, List<ResourceLocation>> tags;

	public IntegrationItemTagsGenerator(DataGenerator generator, IntegrationBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(generator, blockTagsGenerator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.blockTagsGenerator = blockTagsGenerator;
		this.tags = new HashMap<>();
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addItemTags(this));
		this.copyOres();
	}

	@Override
	public void copy(INamedTag<Block> blockTag, INamedTag<Item> itemTag)
	{
		super.copy(blockTag, itemTag);
	}

	protected void copyOres()
	{
		for (INamedTag<Block> blockTag : this.blockTagsGenerator.getOreTags())
		{
			INamedTag<Item> itemTag = ItemTags.bind(blockTag.getName().toString());
			this.copy(blockTag, (INamedTag<Item>) itemTag);
		}

	}

	public void tag(INamedTag<Item> tag, ResourceLocation itemName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(itemName) == false)
		{
			this.tag(tag).addOptional(itemName);
			list.add(itemName);
		}

	}

}
