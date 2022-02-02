package com.github.gisellevonbingen.moremekanismprocessing.common.slurry;

import java.util.function.UnaryOperator;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.registration.impl.SlurryDeferredRegister;
import mekanism.common.registration.impl.SlurryRegistryObject;
import net.minecraftforge.registries.RegistryObject;

public class SlurryDeferredRegister2 extends SlurryDeferredRegister
{
	public SlurryDeferredRegister2(String modid)
	{
		super(modid);
	}

	public SlurryRegistryObject<Slurry, Slurry> register(MaterialType materialType, UnaryOperator<SlurryBuilder> builderModifier)
	{
		RegistryObject<Slurry> dirty = this.register(MoreMekanismProcessingSlurryBuilder.dirty(), materialType, builderModifier);
		RegistryObject<Slurry> clean = this.register(MoreMekanismProcessingSlurryBuilder.clean(), materialType, builderModifier);
		return new SlurryRegistryObject<>(dirty, clean);
	}

	public RegistryObject<Slurry> register(MoreMekanismProcessingSlurryBuilder builder, MaterialType materialType, UnaryOperator<SlurryBuilder> builderModifier)
	{
		builder.materialType(materialType);
		builder.ore(MaterialState.ORE.getStateItemTag(materialType));
		return this.register(builder, builderModifier);
	}

	public RegistryObject<Slurry> register(MoreMekanismProcessingSlurryBuilder builder, UnaryOperator<SlurryBuilder> builderModifier)
	{
		return this.internal.register(builder.getSlurryName(), () -> new MoreMekanismProcessingSlurry((MoreMekanismProcessingSlurryBuilder) builderModifier.apply(builder)));
	}

}
