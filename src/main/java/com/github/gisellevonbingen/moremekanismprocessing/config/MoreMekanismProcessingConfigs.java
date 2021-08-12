package com.github.gisellevonbingen.moremekanismprocessing.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class MoreMekanismProcessingConfigs
{
	public static final CommonConfig Common;
	public static final ForgeConfigSpec CommonSpec;

	static
	{
		Pair<CommonConfig, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		Common = pair.getLeft();
		CommonSpec = pair.getRight();
	}

}
