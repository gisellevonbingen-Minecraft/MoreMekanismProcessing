package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider
{
	public BlockTagsGenerator(DataGenerator p_i244820_1_, ExistingFileHelper p_i244820_3_)
	{
		super(p_i244820_1_, MoreMekanismProcessing.MODID, p_i244820_3_);
	}

	@Override
	protected void addTags()
	{

	}

}
