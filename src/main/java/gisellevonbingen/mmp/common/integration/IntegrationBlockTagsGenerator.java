package gisellevonbingen.mmp.common.integration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationBlockTagsGenerator extends BlockTagsProvider
{
	private final Map<TagKey<Block>, List<ResourceLocation>> tags;
	private final List<TagKey<Block>> oreTags;

	public IntegrationBlockTagsGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, MoreMekanismProcessing.MODID, existingFileHelper);

		this.tags = new HashMap<>();
		this.oreTags = new ArrayList<>();
	}

	@Override
	protected void addTags()
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addBlockTags(this));
	}

	public List<TagKey<Block>> getTags()
	{
		return Lists.newArrayList(this.tags.keySet());
	}

	public void tagOres(TagKey<Block> tag, ResourceLocation blockName)
	{
		this.targOres0(Blocks.ORES, blockName);
		this.targOres0(tag, blockName);
	}

	private void targOres0(TagKey<Block> tag, ResourceLocation blockName)
	{
		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

		this.tag(tag, blockName);
	}

	public void tag(TagKey<Block> tag, ResourceLocation blockName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(blockName) == false)
		{
			this.tag(tag).addOptional(blockName);
			list.add(blockName);
		}

	}

}
