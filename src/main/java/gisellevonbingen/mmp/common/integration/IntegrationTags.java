package gisellevonbingen.mmp.common.integration;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

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
		public static final INamedTag<Block> ORES_YELLORITE = BlockTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Block> ORES_URANIUM = BlockTags.createOptional(NAME_ORES_URANIUM);

		public static void initialize()
		{

		}

	}

	public static class Items
	{
		public static final INamedTag<Item> ORES_YELLORITE = ItemTags.createOptional(NAME_ORES_YELLORITE);
		public static final INamedTag<Item> ORES_URANIUM = ItemTags.createOptional(NAME_ORES_URANIUM);

		public static void initialize()
		{

		}

	}

}
