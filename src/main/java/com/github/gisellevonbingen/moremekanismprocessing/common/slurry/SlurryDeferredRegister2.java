package com.github.gisellevonbingen.moremekanismprocessing.common.slurry;

import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraftforge.fml.RegistryObject;

public class SlurryDeferredRegister2 extends SlurryDeferredRegister
{
	public SlurryDeferredRegister2(String modid)
	{
		super(modid);
	}

	public SlurryRegistryObject<Slurry, Slurry> register(MaterialType materialType, UnaryOperator<SlurryBuilder> builderModifier)
	{
		RegistryObject<Slurry> dirty = this.register("dirty", materialType, SlurryBuilder.dirty(), builderModifier);
		RegistryObject<Slurry> clean = this.register("clean", materialType, SlurryBuilder.clean(), builderModifier);
		return new SlurryRegistryObject<>(dirty, clean);
	}

	public RegistryObject<Slurry> register(String type, MaterialType materialType, SlurryBuilder builder, UnaryOperator<SlurryBuilder> builderModifier)
	{
		return this.internal.register(type + "_" + materialType.getBaseName(), () -> new MoreMekanismProcessingSlurry(builderModifier.apply(new SlurryBuildOperator(materialType).apply(builder)), materialType, type));
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
			INamedTag<Item> tag = MaterialState.ORE.getStateItemTag(this.materialType);
			return builder.ore(tag);
		}

		public MaterialType getOreType()
		{
			return this.materialType;
		}

	}

}
