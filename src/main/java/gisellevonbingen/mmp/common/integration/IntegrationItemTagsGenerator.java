package gisellevonbingen.mmp.common.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.block.Block;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Items;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends ItemTagsProvider
{
	protected final IntegrationBlockTagsGenerator blockTagsGenerator;
	private final Map<INamedTag<Item>, List<ResourceLocation>> tags;
	private final List<INamedTag<Item>> oreTags;

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
		this.copyOres();
	}

	@Override
	public void copy(INamedTag<Block> blockTag, INamedTag<Item> itemTag)
	{
		super.copy(blockTag, itemTag);
	}

	protected void copyOres()
	{
		for (INamedTag<Block> blockTag : this.blockTagsGenerator.getOreTags())
		{
			INamedTag<Item> itemTag = ItemTags.bind(blockTag.getName().toString());
			this.copy(blockTag, itemTag);
		}

	}

	public void tagOres(INamedTag<Item> tag, ResourceLocation blockName)
	{
		this.targOres0(Items.ORES, blockName);
		this.targOres0(tag, blockName);
	}

	private void targOres0(INamedTag<Item> tag, ResourceLocation blockName)
	{
		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

		this.tag(tag, blockName);
	}

	public void tag(INamedTag<Item> tag, ResourceLocation blockName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(blockName) == false)
		{
			this.tag(tag).addOptional(blockName);
			list.add(blockName);
		}

	}

}
