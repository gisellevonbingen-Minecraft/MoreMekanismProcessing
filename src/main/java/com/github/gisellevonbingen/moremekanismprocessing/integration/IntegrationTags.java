package com.github.gisellevonbingen.moremekanismprocessing.integration;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class IntegrationTags
{
	public static final ResourceLocation NAME_ORES_SAPPHIRE = new ResourceLocation("forge", "ores/" + MaterialType.Sapphire.getBaseName());
	public static final ResourceLocation NAME_ORES_AMETHYST = new ResourceLocation("forge", "ores/" + MaterialType.Amethyst.getBaseName());

	public static final ResourceLocation NAME_ORES_YELLORITE = new ResourceLocation("forge", "ores/yellorite");
	public static final ResourceLocation NAME_ORES_URANIUM = new ResourceLocation("forge", "ores/uranium");

	public static class Blocks
	{
		public static final INamedTag<Block> ORES_SAPPHIRE = BlockTags.createOptional(NAME_ORES_SAPPHIRE);
		public static final INamedTag<Block> ORES_AMETHYST = BlockTags.createOptional(NAME_ORES_AMETHYST);
		
		public static final INamedTag<Block> ORES_YELLORITE = BlockTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Block> ORES_URANIUM = BlockTags.createOptional(NAME_ORES_URANIUM);
	}

	public static class Items
	{
		public static final INamedTag<Item> ORES_YELLORITE = ItemTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Item> ORES_URANIUM = ItemTags.createOptional(NAME_ORES_URANIUM);

		public static final INamedTag<Item> ORES_SAPPHIRE = ItemTags.createOptional(NAME_ORES_SAPPHIRE);
		public static final INamedTag<Item> ORES_AMETHYST = ItemTags.createOptional(NAME_ORES_AMETHYST);
	}

}
