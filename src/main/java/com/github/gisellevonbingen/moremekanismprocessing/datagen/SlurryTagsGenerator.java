package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.common.slurry.MoreMekanismProcessingSlurries;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.tag.ChemicalTagsProvider.SlurryTagsProvider;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.tags.MekanismTags;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlurryTagsGenerator extends SlurryTagsProvider
{
	protected SlurryTagsGenerator(DataGenerator gen, ExistingFileHelper existingFileHelper)
	{
		super(gen, MoreMekanismProcessing.MODID, existingFileHelper);
	}

	@Override
	public String getName()
	{
		return "Slurry Tags";
	}

	@Override
	protected void addTags()
	{
		Builder<Slurry> dirty = this.tag(MekanismTags.Slurries.DIRTY);
		Builder<Slurry> clean = this.tag(MekanismTags.Slurries.CLEAN);

		for (MaterialType materialType : MaterialType.values())
		{
			SlurryRegistryObject<Slurry, Slurry> registry = MoreMekanismProcessingSlurries.getSlurryRegistry(materialType);

			if (registry != null)
			{
				dirty.add(registry.getDirtySlurry());
				clean.add(registry.getCleanSlurry());
			}

		}

	}

}
