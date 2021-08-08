package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider
{
	public BlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, IntagrationIceAndFire.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{
		this.tag(IceAndFireTags.Blocks.ORES_SAPPHIRE).add(IceAndFireBlocks.SapphireOre.get());
		this.tag(IceAndFireTags.Blocks.ORES_AMETHYST).add(IceAndFireBlocks.AmethystOre.get());
	}

}
