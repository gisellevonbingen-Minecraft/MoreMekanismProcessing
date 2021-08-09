package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.util.UnsafeHelper;

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
		boolean enable = this.existingFileHelper.isEnabled();
		Field enableField = null;

		try
		{
			try
			{
				enableField = ExistingFileHelper.class.getDeclaredField("enable");
				enableField.setAccessible(true);
			}
			catch (NoSuchFieldException | SecurityException e)
			{
				e.printStackTrace();
			}

			if (enableField != null)
			{
				UnsafeHelper.putBoolean(this.existingFileHelper, enableField, false);
			}

			this.onRegisterModels();
		}
		finally
		{
			if (enableField != null)
			{
				UnsafeHelper.putBoolean(this.existingFileHelper, enableField, enable);
			}

		}

	}

	protected void onRegisterModels()
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
		if (materialState == MaterialState.GEM || materialState == MaterialState.INGOT || materialState == MaterialState.DUST)
		{
			return new ResourceLocation(MoreMekanismProcessing.MODID, "item/" + materialState.getBaseName());
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
