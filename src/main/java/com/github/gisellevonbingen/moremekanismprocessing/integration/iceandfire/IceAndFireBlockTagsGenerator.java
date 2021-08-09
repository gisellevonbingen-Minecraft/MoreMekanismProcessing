package com.github.gisellevonbingen.moremekanismprocessing.integration.iceandfire;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import net.minecraft.block.Block;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IceAndFireBlockTagsGenerator extends BlockTagsProvider
{
	public IceAndFireBlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, IceAndFireMod.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		Map<INamedTag<Block>, String> map = new HashMap<>();
		map.put(IceAndFireTags.Blocks.ORES_SAPPHIRE, "sapphire_ore");
		map.put(IceAndFireTags.Blocks.ORES_AMETHYST, "amythest_ore");

		for (Entry<INamedTag<Block>, String> entry : map.entrySet())
		{
			ResourceLocation name = new ResourceLocation(this.modId, entry.getValue());
			this.tag(Blocks.ORES).addOptional(name);
			this.tag(entry.getKey()).addOptional(name);
		}

	}

}
