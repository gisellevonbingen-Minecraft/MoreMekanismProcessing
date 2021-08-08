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
	public static class Items
	{
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
						INamedTag<Item> tag = tag(materialType, materialState);
						map2.put(materialState, tag);
					}

				}

			}

		}

		public static INamedTag<Item> tag(MaterialType materialType, MaterialState materialState)
		{
			return tag(materialState.getStateTagName(materialType));
		}

		public static INamedTag<Item> tag(ResourceLocation name)
		{
			return ItemTags.bind(name.toString());
		}

	}

	public static class Blocks
	{
		public static INamedTag<Block> tag(ResourceLocation name)
		{
			return BlockTags.bind(name.toString());
		}

	}

}
