package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider
{
	public ItemTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, MoreMekanismProcessing.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				Builder<Item> add = this.tag(materialState.getCategoryTag());
				Builder<Item> add2 = this.tag(materialState.getStateTag(materialType));

				if (materialState != MaterialState.ORE)
				{
					Item item = materialState.getItem(materialType);
					add.add(item);
					add2.add(item);
				}

			}

		}

	}

}
