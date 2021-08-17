package com.github.gisellevonbingen.moremekanismprocessing.config;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.ForgeConfigSpec.ConfigValue;

public class ClientConfig
{
	public static final int RADIX = 16;

	public final Map<MaterialType, ForgeConfigSpec.ConfigValue<String>> colors;
	public final Map<MaterialType, Integer> parsedColors;

	public ClientConfig(ForgeConfigSpec.Builder builder)
	{
		builder.comment("color : ores rgb color");
		builder.push("ores");

		this.colors = new HashMap<>();
		this.parsedColors = new HashMap<>();

		for (MaterialType materialType : MaterialType.values())
		{
			builder.push(materialType.getBaseName());

			this.colors.put(materialType, builder.define("color", Integer.toString(materialType.getDefaultColor() & 0x00FFFFFF, RADIX)));

			builder.pop();
		}

		builder.pop();
	}

	public void parseConfig()
	{
		this.parsedColors.clear();

		for (MaterialType materialType : MaterialType.values())
		{
			ConfigValue<String> value = this.colors.get(materialType);
			int color = value != null ? Integer.valueOf(value.get(), RADIX) : materialType.getDefaultColor();
			this.parsedColors.put(materialType, color);
		}

	}

}
