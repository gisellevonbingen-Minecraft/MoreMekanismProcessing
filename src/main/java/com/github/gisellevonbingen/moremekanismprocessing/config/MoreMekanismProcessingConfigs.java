package com.github.gisellevonbingen.moremekanismprocessing.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;

public class MoreMekanismProcessingConfigs
{
	public static final CommonConfig Common;
	public static final ForgeConfigSpec CommonSpec;

	public static final ClientConfig Client;
	public static final ForgeConfigSpec ClientSpec;

	static
	{
		Pair<CommonConfig, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		Common = common.getLeft();
		CommonSpec = common.getRight();

		Pair<ClientConfig, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		Client = client.getLeft();
		ClientSpec = client.getRight();
	}

}
