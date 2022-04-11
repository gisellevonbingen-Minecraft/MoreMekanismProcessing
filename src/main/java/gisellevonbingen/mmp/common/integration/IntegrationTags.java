package gisellevonbingen.mmp.common.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class IntegrationTags
{
	public static final ResourceLocation NAME_ORES_YELLORITE = new ResourceLocation("forge", "ores/yellorite");
	public static final ResourceLocation NAME_ORES_URANIUM = new ResourceLocation("forge", "ores/uranium");

	public static void initialize()
	{
		Blocks.initialize();
		Items.initialize();
	}

	public static class Blocks
	{
		public static final TagKey<Block> ORES_YELLORITE = BlockTags.create(NAME_ORES_YELLORITE);
		public static final TagKey<Block> ORES_URANIUM = BlockTags.create(NAME_ORES_URANIUM);

		public static void initialize()
		{

		}

	}

	public static class Items
	{
		public static final TagKey<Item> ORES_YELLORITE = ItemTags.create(NAME_ORES_YELLORITE);
		public static final TagKey<Item> ORES_URANIUM = ItemTags.create(NAME_ORES_URANIUM);

		public static void initialize()
		{

		}

	}

}
