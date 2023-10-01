package gisellevonbingen.mmp.common.tag;

import java.util.HashMap;
import java.util.Map;

import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class MMPTags
{
	public static class Blocks
	{

	}

	public static class Items
	{
		public static final Map<MaterialType, Map<MaterialState, TagKey<Item>>> PROCESSING_ITEM_TAGS = new HashMap<>();

		static
		{
			initialize();
		}

		public static TagKey<Item> getProcessingItemTag(MaterialType materialType, MaterialState materialState)
		{
			Map<MaterialState, TagKey<Item>> map = PROCESSING_ITEM_TAGS.get(materialType);
			return map != null ? map.get(materialState) : null;
		}

		public static void initialize()
		{
			for (MaterialType materialType : MaterialType.values())
			{
				Map<MaterialState, TagKey<Item>> map2 = new HashMap<>();
				PROCESSING_ITEM_TAGS.put(materialType, map2);

				for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
				{
					if (materialState.hasOwnItem() == true)
					{
						TagKey<Item> tag = ItemTags.create(materialState.getStateTagName(materialType));
						map2.put(materialState, tag);
					}

				}

			}

		}

	}

}
