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
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	protected final IntegrationBlockTagsGenerator blockTagsGenerator;
	private final Map<TagKey<Item>, List<ResourceLocation>> tags;
	private final List<TagKey<Item>> oreTags;

	public IntegrationItemTagsGenerator(DataGenerator generator, IntegrationBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(generator, blockTagsGenerator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.blockTagsGenerator = blockTagsGenerator;
		this.tags = new HashMap<>();
		this.oreTags = new ArrayList<>();
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addItemTags(this));
		this.copyTags();
	}

	@Override
	public void copy(TagKey<Block> blockTag, TagKey<Item> itemTag)
	{
		super.copy(blockTag, itemTag);
	}

	protected void copyTags()
	{
		for (TagKey<Block> blockTag : this.blockTagsGenerator.getTags())
		{
			TagKey<Item> itemTag = ItemTags.create(blockTag.location());
			this.copy(blockTag, itemTag);
		}

	}

	public void tagOres(TagKey<Item> tag, ResourceLocation blockName)
	{
		this.targOres0(Items.ORES, blockName);
		this.targOres0(tag, blockName);
	}

	private void targOres0(TagKey<Item> tag, ResourceLocation blockName)
	{
		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

		this.tag(tag, blockName);
	}

	public void tag(TagKey<Item> tag, ResourceLocation blockName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(blockName) == false)
		{
			this.tag(tag).addOptional(blockName);
			list.add(blockName);
		}

	}

}
