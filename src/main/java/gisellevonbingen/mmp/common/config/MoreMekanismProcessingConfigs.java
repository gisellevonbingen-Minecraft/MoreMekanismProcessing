package gisellevonbingen.mmp.common.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.loading.FMLEnvironment;

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

	public static void register(ModLoadingContext modLoadingContext)
	{
		register(modLoadingContext, ModConfig.Type.COMMON);

		Dist dist = FMLEnvironment.dist;

		if (dist.isClient())
		{
			register(modLoadingContext, ModConfig.Type.CLIENT);
		}

		if (dist.isDedicatedServer())
		{
			register(modLoadingContext, ModConfig.Type.SERVER);
		}

	}

	public static void register(ModLoadingContext modLoadingContext, ModConfig.Type type)
	{
		ForgeConfigSpec spec = SPECS.get(type);

		if (spec != null)
		{
			modLoadingContext.registerConfig(type, spec);
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
