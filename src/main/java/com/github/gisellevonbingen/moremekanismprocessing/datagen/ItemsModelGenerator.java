package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.lang.reflect.Field;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.util.UnsafeHelper;

import mekanism.common.Mekanism;
import net.minecraft.data.DataGenerator;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import sun.misc.Unsafe;

public class ItemsModelGenerator extends ItemModelProvider
{
	public ItemsModelGenerator(DataGenerator generator, ExistingFileHelper existingFileHelper)
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

	private void onRegisterModels()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				if (materialState != MaterialState.ORE)
				{
					Item item = materialState.getItem(materialType);
					this.singleTexture(item.getRegistryName().getPath(), this.mcLoc("item/generated"), "layer0", this.getTexture(materialState));
				}

			}

		}

	}

	private ResourceLocation getTexture(MaterialState materialState)
	{
		if (materialState == MaterialState.INGOT)
		{
			return this.child(Items.IRON_INGOT.getRegistryName());
		}
		else if (materialState == MaterialState.GEM)
		{
			return new ResourceLocation(MoreMekanismProcessing.MODID, "item/" + materialState.getBaseName());
		}
		else
		{
			return new ResourceLocation(Mekanism.MODID, "item/" + materialState.getBaseName());
		}

	}

	private ResourceLocation child(ResourceLocation parent)
	{
		return new ResourceLocation(parent.getNamespace(), "item/" + parent.getPath());
	}

}
