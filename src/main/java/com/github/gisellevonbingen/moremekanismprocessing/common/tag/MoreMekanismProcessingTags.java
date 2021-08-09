package com.github.gisellevonbingen.moremekanismprocessing.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class MoreMekanismProcessingTags
{
	public static final ResourceLocation NAME_ORES_YELLORITE = new ResourceLocation("forge", "ores/yellorite");
	public static final ResourceLocation NAME_ORES_URANIUM = new ResourceLocation("forge", "ores/uranium");

	public static class Blocks
	{
		public static final INamedTag<Block> ORES_YELLORITE = BlockTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Block> ORES_URANIUM = BlockTags.createOptional(NAME_ORES_URANIUM);
	}

	public static class Items
	{
		public static final INamedTag<Item> ORES_YELLORITE = ItemTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Item> ORES_URANIUM = ItemTags.createOptional(NAME_ORES_URANIUM);
		
		public static final Map<MaterialType, Map<MaterialState, INamedTag<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

		static
		{
			initialize();
		}

		public static INamedTag<Item> getProcessingItemTag(MaterialType materialType, MaterialState materialState)
		{
			Map<MaterialState, INamedTag<Item>> map = PROCESSING_ITEM_TAGS.get(materialType);
			return map != null ? map.get(materialState) : null;
		}

		public static void initialize()
		{
			for (MaterialType materialType : MaterialType.values())
			{
				Map<MaterialState, INamedTag<Item>> map2 = new HashMap<>();
				PROCESSING_ITEM_TAGS.put(materialType, map2);

				for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
				{
					if (materialState != MaterialState.ORE)
					{
						INamedTag<Item> tag = ItemTags.bind(materialState.getStateTagName(materialType).toString());
						map2.put(materialState, tag);
					}

				}

			}

		}

	}

}
