package com.github.gisellevonbingen.moremekanismprocessing.config;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraftforge.common.ForgeConfigSpec;

public class ClientConfig
{
	public final Map<MaterialType, ForgeConfigSpec.ConfigValue<Integer>> colors;

	public ClientConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("ores");

		this.colors = new HashMap<>();

		for (MaterialType materialType : MaterialType.values())
		{
			builder.push(materialType.getBaseName());

			this.colors.put(materialType, builder.define("color", materialType.getDefaultColor()));

			builder.pop();
		}

		builder.pop();
	}

}
