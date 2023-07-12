package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.material.MaterialType;
import gisellevonbingen.mmp.common.slurry.MoreMekanismProcessingSlurries;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.datagen.tag.ChemicalTagsProvider.SlurryTagsProvider;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.tags.MekanismTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class SlurryTagGenerator extends SlurryTagsProvider
{
	protected SlurryTagGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, MoreMekanismProcessing.MODID, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		TagAppender<Slurry> dirty = this.tag(MekanismTags.Slurries.DIRTY);
		TagAppender<Slurry> clean = this.tag(MekanismTags.Slurries.CLEAN);

		for (MaterialType materialType : MaterialType.values())
		{
			SlurryRegistryObject<Slurry, Slurry> registry = MoreMekanismProcessingSlurries.getSlurryRegistry(materialType);

			if (registry != null)
			{
				dirty.addOptional(registry.getDirtySlurry().getRegistryName());
				clean.addOptional(registry.getCleanSlurry().getRegistryName());
			}

		}

	}

}
