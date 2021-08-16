package com.github.gisellevonbingen.moremekanismprocessing.config;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
	public final ForgeConfigSpec.BooleanValue showOreNotExistRecipes;
	public final Map<MaterialType, ForgeConfigSpec.ConfigValue<Integer>> processingLevels;
	public final Map<MaterialType, Map<String, ForgeConfigSpec.BooleanValue>> detail;

	public CommonConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("general");

		this.showOreNotExistRecipes = builder.comment("true : show all recipes", "false : hide ore not exist recipes (can hide recipes when some mod not installed)").define("showOreNotExistRecipes", false);

		builder.pop();

		builder.comment("processingLevel : set ores processing max level", "    5 : can into up x5 (able all procssing)", "    4 : can into up x4 (disable ore dissolution, slurry crystalizing)", "    3 : can into up x3 (disable injecting)", "    2 : can into up x2 (disable purifying and clumps crushing)", "    0~1 : disable all processing in this mod");
		builder.push("ores");

		this.processingLevels = new HashMap<>();
		this.detail = new HashMap<>();

		for (MaterialType materialType : MaterialType.values())
		{
			builder.push(materialType.getBaseName());

			this.processingLevels.put(materialType, builder.define("processingLevel", this.getProcessingLevel(materialType)));

			builder.pop();
		}

		builder.pop();
	}

	public int getProcessingLevel(MaterialType materialType)
	{
//		if (materialType == MaterialType.Diamond || materialType == MaterialType.Emerald)
//		{
//			return 0;
//		}
//		else
		{
			return 5;
		}

	}

}
