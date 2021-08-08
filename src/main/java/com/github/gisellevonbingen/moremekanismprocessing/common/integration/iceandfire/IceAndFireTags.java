package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class IceAndFireTags
{
	public static final ResourceLocation NAME_ORES_SAPPHIRE = new ResourceLocation("forge", "ores/" + MaterialType.Sapphire.getBaseName());
	public static final ResourceLocation NAME_ORES_AMETHYST = new ResourceLocation("forge", "ores/" + MaterialType.Amethyst.getBaseName());

	public static class Items
	{
		public static final INamedTag<Item> ORES_SAPPHIRE = tag(NAME_ORES_SAPPHIRE);
		public static final INamedTag<Item> ORES_AMETHYST = tag(NAME_ORES_AMETHYST);

		public static INamedTag<Item> tag(ResourceLocation name)
		{
			return ItemTags.bind(name.toString());
		}

	}

	public static class Blocks
	{
		public static final INamedTag<Block> ORES_SAPPHIRE = tag(NAME_ORES_SAPPHIRE);
		public static final INamedTag<Block> ORES_AMETHYST = tag(NAME_ORES_AMETHYST);

		public static INamedTag<Block> tag(ResourceLocation name)
		{
			return BlockTags.bind(name.toString());
		}

	}

}
