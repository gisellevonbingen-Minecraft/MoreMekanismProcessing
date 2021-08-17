package com.github.gisellevonbingen.moremekanismprocessing.config;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;

@Mod.EventBusSubscriber
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

	@SubscribeEvent
	public static void onLoad(ModConfig.Loading event)
	{
		parseConfig(event);
	}

	@SubscribeEvent
	public static void onReload(ModConfig.Reloading event)
	{
		parseConfig(event);
	}

	public static void parseConfig(ModConfig.ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == ClientSpec)
		{
			Client.parseConfig();
		}

	}

}
