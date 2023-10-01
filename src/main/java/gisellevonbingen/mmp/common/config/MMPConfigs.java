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
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;

@Mod.EventBusSubscriber
public class MMPConfigs
{
	public static final Map<ModConfig.Type, ForgeConfigSpec> SPECS;

	public static final CommonConfig COMMON;
	public static final ForgeConfigSpec COMMON_SPEC;

	public static final ClientConfig CLIENT;
	public static final ForgeConfigSpec CLIENT_SPEC;

	static
	{
		SPECS = new HashMap<>();

		Pair<CommonConfig, ForgeConfigSpec> common = new ForgeConfigSpec.Builder().configure(CommonConfig::new);
		COMMON = common.getLeft();
		COMMON_SPEC = common.getRight();
		SPECS.put(ModConfig.Type.COMMON, COMMON_SPEC);

		Pair<ClientConfig, ForgeConfigSpec> client = new ForgeConfigSpec.Builder().configure(ClientConfig::new);
		CLIENT = client.getLeft();
		CLIENT_SPEC = client.getRight();
		SPECS.put(ModConfig.Type.CLIENT, CLIENT_SPEC);
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
	public static void onLoad(ModConfigEvent.Loading event)
	{
		parseConfig(event);
	}

	@SubscribeEvent
	public static void onReload(ModConfigEvent.Reloading event)
	{
		parseConfig(event);
	}

	public static void parseConfig(ModConfigEvent event)
	{
		if (event.getConfig().getSpec() == CLIENT_SPEC)
		{
			CLIENT.parseConfig();
		}

	}

}
