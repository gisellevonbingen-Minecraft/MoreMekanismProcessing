package com.github.gisellevonbingen.moremekanismprocessing.integration.extremereactors;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.moremekanismprocessing.common.tag.MoreMekanismProcessingTags;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ExtremeReactors2BlockTagsGenerator extends BlockTagsProvider
{
	public ExtremeReactors2BlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, ExtremeReactors2Mod.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		Map<INamedTag<Block>, String> map = new HashMap<>();
		map.put(MoreMekanismProcessingTags.Blocks.ORES_YELLORITE, "yellorite_ore");
		map.put(MoreMekanismProcessingTags.Blocks.ORES_URANIUM, "yellorite_ore");

		for (Entry<INamedTag<Block>, String> entry : map.entrySet())
		{
			ResourceLocation name = new ResourceLocation(this.modId, entry.getValue());
			this.tag(Blocks.ORES).addOptional(name);
			this.tag(entry.getKey()).addOptional(name);
		}

	}

}
