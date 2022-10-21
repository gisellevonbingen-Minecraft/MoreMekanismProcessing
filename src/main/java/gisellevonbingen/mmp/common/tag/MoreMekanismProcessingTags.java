package gisellevonbingen.mmp.common.tag;

import java.util.HashMap;
import java.util.Map;

import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;

public class MoreMekanismProcessingTags
{
	public static class Blocks
	{

	}

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
					if (materialState.hasOwnItem() == true)
					{
						INamedTag<Item> tag = ItemTags.bind(materialState.getStateTagName(materialType).toString());
						map2.put(materialState, tag);
					}

				}

			}

		}

	}

}
