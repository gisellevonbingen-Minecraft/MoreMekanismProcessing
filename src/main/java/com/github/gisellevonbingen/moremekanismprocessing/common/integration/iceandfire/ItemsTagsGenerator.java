package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemsTagsGenerator extends ItemTagsProvider
{
	public ItemsTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, IntagrationIceAndFire.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		this.copy(IceAndFireTags.Blocks.ORES_SAPPHIRE, IceAndFireTags.Items.ORES_SAPPHIRE);
		this.copy(IceAndFireTags.Blocks.ORES_AMETHYST, IceAndFireTags.Items.ORES_AMETHYST);
	}

}
