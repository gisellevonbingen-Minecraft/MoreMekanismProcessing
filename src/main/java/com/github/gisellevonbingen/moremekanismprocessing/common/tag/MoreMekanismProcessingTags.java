package com.github.gisellevonbingen.moremekanismprocessing.common.tag;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class MoreMekanismProcessingTags
{
	public static final Map<MaterialType, Map<MaterialState, ITag.INamedTag<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

	static
	{
		initialize();
	}

	public static ITag.INamedTag<Item> getProcessingItemTag(MaterialType materialType, MaterialState materialState)
	{
		Map<MaterialState, ITag.INamedTag<Item>> map = PROCESSING_ITEM_TAGS.get(materialType);
		return map != null ? map.get(materialState) : null;
	}

	public static void initialize()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			Map<MaterialState, ITag.INamedTag<Item>> map2 = new HashMap<>();
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

	private static ITag.INamedTag<Item> tag(MaterialType materialType, MaterialState materialState)
	{
		return ItemTags.bind(materialState.getStateTagName(materialType).toString());
	}

}
