package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.util.ArrayList;
import java.util.List;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.common.Mekanism;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelBuilder;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemModelGenerator extends ItemModelProvider
{
	public ItemModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
	{
		super(generator, MoreMekanismProcessing.MODID, existingFileHelper);
	}

	@Override
	protected void registerModels()
	{
		List<MaterialState> hasOverlay = new ArrayList<>();
		hasOverlay.add(MaterialState.DIRTY_DUST);
		hasOverlay.add(MaterialState.CLUMP);
		hasOverlay.add(MaterialState.SHARD);
		hasOverlay.add(MaterialState.CRYSTAL);

		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				if (materialState != MaterialState.ORE)
				{
					Item item = materialState.getItem(materialType);
					ItemModelBuilder builder = this.singleTexture(item.getRegistryName().getPath(), this.mcLoc("item/generated"), "layer0", this.getTexture(materialState));

					if (hasOverlay.contains(materialState) == true)
					{
						builder.texture("layer1", this.getMekanismTexture(materialState.getBaseName() + "_overlay"));
					}

				}

			}

		}

	}

	protected ResourceLocation getMekanismTexture(String name)
	{
		return new ResourceLocation(Mekanism.MODID, "item/" + name);
	}

	protected ResourceLocation getTexture(MaterialState materialState)
	{
		if (materialState == MaterialState.GEM || materialState == MaterialState.INGOT)
		{
			return new ResourceLocation(MoreMekanismProcessing.MODID, "item/" + materialState.getBaseName());
		}
		else if (materialState == MaterialState.DUST)
		{
			return this.getTexture(MaterialState.DIRTY_DUST);
		}
		else
		{
			return this.getMekanismTexture(materialState.getBaseName());
		}

	}

	protected ResourceLocation child(ResourceLocation parent)
	{
		return new ResourceLocation(parent.getNamespace(), "item/" + parent.getPath());
	}

}
