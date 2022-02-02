package com.github.gisellevonbingen.mmp.datagen;

import com.github.gisellevonbingen.mmp.MoreMekanismProcessing;
import com.github.gisellevonbingen.mmp.common.material.MaterialState;
import com.github.gisellevonbingen.mmp.common.material.MaterialType;

import net.minecraft.data.DataGenerator;
import net.minecraft.data.tags.BlockTagsProvider;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
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
		this.buildStatedMaterials();
	}

	protected void buildStatedMaterials()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				TagAppender<Item> builderCategory = this.tag(materialState.getCategoryTag());
				TagAppender<Item> builderState = this.tag(materialState.getStateItemTag(materialType));

				if (materialState != MaterialState.ORE)
				{
					ResourceLocation itemName = materialState.getItemName(materialType);
					builderCategory.addOptional(itemName);
					builderState.addOptional(itemName);
				}

			}

		}

	}

}
