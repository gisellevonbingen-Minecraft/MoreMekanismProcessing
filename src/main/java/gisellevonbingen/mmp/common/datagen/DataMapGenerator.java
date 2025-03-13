package gisellevonbingen.mmp.common.datagen;

import java.util.Map.Entry;
import java.util.concurrent.CompletableFuture;

import gisellevonbingen.mmp.common.chemical.MMPChemicals;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import mekanism.api.chemical.Chemical;
import mekanism.api.datamaps.IMekanismDataMapTypes;
import mekanism.api.datamaps.chemical.ChemicalSolidTag;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.common.data.DataMapProvider;

public class DataMapGenerator extends DataMapProvider
{
	public DataMapGenerator(PackOutput packOutput, CompletableFuture<Provider> lookupProvider)
	{
		super(packOutput, lookupProvider);
	}

	@Override
	protected void gather(Provider provider)
	{
		super.gather(provider);

		Builder<ChemicalSolidTag, Chemical> chemicalSolidTagBuilder = this.builder(IMekanismDataMapTypes.INSTANCE.chemicalSolidTag());

		for (Entry<MaterialType, SlurryRegistryObject<Chemical, Chemical>> entry : MMPChemicals.MAP.entrySet())
		{
			TagKey<Item> tag = MaterialState.ORE.getStateTag(entry.getKey());
			chemicalSolidTagBuilder.add(entry.getValue().getCleanSlurry(), new ChemicalSolidTag(tag), false);
		}

	}

}
