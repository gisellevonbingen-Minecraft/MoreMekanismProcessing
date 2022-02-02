package com.github.gisellevonbingen.moremekanismprocessing.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;

public class MoreMekanismProcessingTags
{
	public static class Blocks
	{
		
	}

	public static class Items
	{
		public static final Map<MaterialType, Map<MaterialState, Tag.Named<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

		static
		{
			initialize();
		}

		public static Tag.Named<Item> getProcessingItemTag(MaterialType materialType, MaterialState materialState)
		{
			Map<MaterialState, Tag.Named<Item>> map = PROCESSING_ITEM_TAGS.get(materialType);
			return map != null ? map.get(materialState) : null;
		}

		public static void initialize()
		{
			for (MaterialType materialType : MaterialType.values())
			{
				Map<MaterialState, Tag.Named<Item>> map2 = new HashMap<>();
				PROCESSING_ITEM_TAGS.put(materialType, map2);

				for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
				{
					if (materialState != MaterialState.ORE)
					{
						Tag.Named<Item> tag = ItemTags.bind(materialState.getStateTagName(materialType).toString());
						map2.put(materialState, tag);
					}

				}

			}

		}

	}

}
