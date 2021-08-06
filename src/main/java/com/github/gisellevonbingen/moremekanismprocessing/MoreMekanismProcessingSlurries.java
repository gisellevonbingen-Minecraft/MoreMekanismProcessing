package com.github.gisellevonbingen.moremekanismprocessing;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismSlurries;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class MoreMekanismProcessingSlurries
{
	public static final Map<MaterialType, SlurryRegistryObject<Slurry, Slurry>> SLURRIES = new HashMap<>();

	public static SlurryRegistryObject<Slurry, Slurry> getSlurryRegistry(MaterialType materialType)
	{
		return SLURRIES.get(materialType);
	}

	public static void register()
	{
		register(MekanismSlurries.SLURRIES);
	}

	public static void register(SlurryDeferredRegister register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

		for (MaterialType materialType : MaterialType.values())
		{
			if (materialType.getResultShape().canProcess(MaterialState.CRYSTAL) == true)
			{
				SlurryRegistryObject<Slurry, Slurry> registryObject = register.register(materialType.getBaseName(), new SlurryBuildOperator(materialType));
				SLURRIES.put(materialType, registryObject);
			}

		}

	}

	public static final class SlurryBuildOperator implements UnaryOperator<SlurryBuilder>
	{
		private final MaterialType materialType;

		private SlurryBuildOperator(MaterialType materialType)
		{
			this.materialType = materialType;
		}

		@Override
		public SlurryBuilder apply(SlurryBuilder builder)
		{
			INamedTag<Item> tag = MaterialState.ORE.getStateTag(this.materialType);
			return builder.color(this.materialType.getColor()).ore(tag);
		}

		public MaterialType getOreType()
		{
			return this.materialType;
		}

	}

}
