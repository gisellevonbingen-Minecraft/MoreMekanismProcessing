package com.github.gisellevonbingen.moremekanismprocessing.common.slurry;

import java.util.HashMap;
import java.util.Map;
import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.eventbus.api.IEventBus;

public class MoreMekanismProcessingSlurries
{
	public static final SlurryDeferredRegister SLURRIES = new SlurryDeferredRegister(MoreMekanismProcessing.MODID);
	public static final Map<MaterialType, SlurryRegistryObject<Slurry, Slurry>> MAP = new HashMap<>();

	public static SlurryRegistryObject<Slurry, Slurry> getSlurryRegistry(MaterialType materialType)
	{
		return MAP.get(materialType);
	}

	public static void register(IEventBus eventBus)
	{
		register(eventBus, SLURRIES);
	}

	public static void register(IEventBus eventBus, SlurryDeferredRegister register)
	{
		register.register(eventBus);

		for (MaterialType materialType : MaterialType.values())
		{
			if (materialType.getResultShape().canProcess(MaterialState.CRYSTAL) == true)
			{
				SlurryRegistryObject<Slurry, Slurry> registryObject = register.register(materialType.getBaseName(), new SlurryBuildOperator(materialType));
				MAP.put(materialType, registryObject);
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
