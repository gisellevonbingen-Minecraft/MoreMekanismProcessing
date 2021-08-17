package com.github.gisellevonbingen.moremekanismprocessing.config;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;

public class CommonConfig
{
	public final ForgeConfigSpec.BooleanValue showOreNotExistRecipes;
	public final Map<MaterialType, ForgeConfigSpec.ConfigValue<Integer>> processingLevels;
	public final Map<MaterialType, ForgeConfigSpec.BooleanValue> overrideRecpects;

	public CommonConfig(ForgeConfigSpec.Builder builder)
	{
		builder.push("general");

		this.showOreNotExistRecipes = builder.comment("true : show all recipes", "false : hide ore not exist recipes (can hide recipes when some mod not installed)").define("showOreNotExistRecipes", false);

		builder.pop();

		builder.comment("processingLevel : set ores processing max level", "    5 : can into up x5 (able all procssing)", "    4 : can into up x4 (disable ore dissolution, slurry crystalizing)", "    3 : can into up x3 (disable injecting)", "    2 : can into up x2 (disable purifying and clumps crushing)", "    1 : reserved (currently same with 0)", "    0 : disable all processing in this mod");
		builder.push("ores");

		this.processingLevels = new HashMap<>();
		this.overrideRecpects = new HashMap<>();

		for (MaterialType materialType : MaterialType.values())
		{
			builder.push(materialType.getBaseName());

			if (materialType.isRespectMekanism() == true)
			{
				ResourceLocation dustTag = MaterialState.DUST.getStateTagName(materialType);
				builder.comment("exist for modpacks, set true to enable this material recipes", "    warning : when enabled, ore block can infinitely regenerate using Mekanism Combiner Default Recipe", "    propose remove/override Mekanism Combiner Default Recipe", "    e.g.) \"ingredient\":{\"tag\":\"" + dustTag + "\"}},\"amount\":8");
				this.overrideRecpects.put(materialType, builder.define("overrideRespect", false));
			}

			this.processingLevels.put(materialType, builder.define("processingLevel", 5));

			builder.pop();
		}

		builder.pop();
	}

}
