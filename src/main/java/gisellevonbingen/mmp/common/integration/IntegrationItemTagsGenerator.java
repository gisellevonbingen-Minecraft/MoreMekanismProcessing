package gisellevonbingen.mmp.common.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.Tag;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	protected final IntegrationBlockTagsGenerator blockTagsGenerator;
	private final Map<Tag.Named<Item>, List<ResourceLocation>> tags;

	public IntegrationItemTagsGenerator(DataGenerator generator, IntegrationBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(generator, blockTagsGenerator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.blockTagsGenerator = blockTagsGenerator;
		this.tags = new HashMap<>();
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addItemTags(this));
		this.copyOres();
	}

	@Override
	public void copy(Tag.Named<Block> blockTag, Tag.Named<Item> itemTag)
	{
		super.copy(blockTag, itemTag);
	}

	protected void copyOres()
	{
		for (Tag.Named<Block> blockTag : this.blockTagsGenerator.getOreTags())
		{
			Tag.Named<Item> itemTag = ItemTags.bind(blockTag.getName().toString());
			this.copy(blockTag, (Tag.Named<Item>) itemTag);
		}

	}

	public void tag(Tag.Named<Item> tag, ResourceLocation itemName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(itemName) == false)
		{
			this.tag(tag).addOptional(itemName);
			list.add(itemName);
		}

	}

}
