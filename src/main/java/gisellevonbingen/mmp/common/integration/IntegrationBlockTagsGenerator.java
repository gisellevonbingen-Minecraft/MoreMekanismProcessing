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
import net.minecraft.tags.Tag;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationBlockTagsGenerator extends BlockTagsProvider
{
	private final Map<Tag.Named<Block>, List<ResourceLocation>> tags;
	private final List<Tag.Named<Block>> oreTags;

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

	public List<Tag.Named<Block>> getOreTags()
	{
		return Lists.newArrayList(this.oreTags);
	}

	public void tagOres(Tag.Named<Block> tag, ResourceLocation blockName)
	{
		this.targOres0(Blocks.ORES, blockName);
		this.targOres0(tag, blockName);
	}

	private void targOres0(Tag.Named<Block> tag, ResourceLocation blockName)
	{
		if (this.oreTags.contains(tag) == false)
		{
			this.oreTags.add(tag);
		}

		this.tag(tag, blockName);
	}

	public void tag(Tag.Named<Block> tag, ResourceLocation blockName)
	{
		List<ResourceLocation> list = this.tags.computeIfAbsent(tag, t -> new ArrayList<>());

		if (list.contains(blockName) == false)
		{
			this.tag(tag).addOptional(blockName);
			list.add(blockName);
		}

	}

}
