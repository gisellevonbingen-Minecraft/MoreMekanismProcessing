package com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.config.MoreMekanismProcessingConfigs;
import com.google.gson.JsonObject;

import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class OverrideRespectCondition implements ICondition
{
	private static final ResourceLocation NAME = MoreMekanismProcessing.rl("override_respect");
	private final MaterialType materialType;

	public OverrideRespectCondition(MaterialType materialType)
	{
		this.materialType = materialType;
	}

	@Override
	public ResourceLocation getID()
	{
		return NAME;
	}

	@Override
	public boolean test()
	{
		if (this.materialType == null || this.materialType.isRespectMekanism() == false)
		{
			return true;
		}
		else
		{
			ConfigValue<Boolean> config = MoreMekanismProcessingConfigs.Common.overrideRecpects.get(this.materialType);
			return config != null && config.get() == true;
		}

	}

	@Override
	public String toString()
	{
		return "override_respect(\"" + this.materialType.getBaseName() + ")";
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public static class Serializer implements IConditionSerializer<OverrideRespectCondition>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public void write(JsonObject json, OverrideRespectCondition value)
		{
			json.addProperty("materialType", value.materialType.getBaseName());
		}

		@Override
		public OverrideRespectCondition read(JsonObject json)
		{
			String asString = JSONUtils.getAsString(json, "materialType");
			MaterialType materialType = Arrays.stream(MaterialType.values()).filter(t -> StringUtils.equals(t.getBaseName(), asString)).findFirst().get();
			return new OverrideRespectCondition(materialType);
		}

		@Override
		public ResourceLocation getID()
		{
			return OverrideRespectCondition.NAME;
		}

	}

}
