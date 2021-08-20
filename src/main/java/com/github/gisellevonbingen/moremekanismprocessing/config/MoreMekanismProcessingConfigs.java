package com.github.gisellevonbingen.moremekanismprocessing.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import com.electronwill.nightconfig.core.file.CommentedFileConfig;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModContainer;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;
import net.minecraftforge.fml.loading.FMLPaths;

@Mod.EventBusSubscriber
public class MoreMekanismProcessingConfigs
{
	public static final Map<ModConfig.Type, ForgeConfigSpec> SPECS;

	public static final CommonConfig Common;
	public static final ForgeConfigSpec CommonSpec;

	public static final ClientConfig Client;
	public static final ForgeConfigSpec ClientSpec;

	static
	{
		SPECS = new HashMap<>();

		Pair<CommonConfig, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		Common = common.getLeft();
		CommonSpec = common.getRight();
		SPECS.put(ModConfig.Type.COMMON, CommonSpec);

		Pair<ClientConfig, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		Client = client.getLeft();
		ClientSpec = client.getRight();
		SPECS.put(ModConfig.Type.CLIENT, ClientSpec);
	}

	public static void read(ModContainer modContaier)
	{
		read(modContaier, ModConfig.Type.COMMON);

		Dist dist = FMLEnvironment.dist;

		if (dist.isClient())
		{
			read(modContaier, ModConfig.Type.CLIENT);
		}

		if (dist.isDedicatedServer())
		{
			read(modContaier, ModConfig.Type.SERVER);
		}

	}

	public static void read(ModContainer modContaier, ModConfig.Type type)
	{
		ForgeConfigSpec spec = SPECS.get(type);

		if (spec != null)
		{
			ModConfig config = new ModConfig(type, spec, modContaier);
			CommentedFileConfig configData = config.getHandler().reader(FMLPaths.CONFIGDIR.get()).apply(config);
			config.getSpec().setConfig(configData);
			configData.save();
		}

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
