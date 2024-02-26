package gisellevonbingen.mmp.common.util;

import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.fml.loading.targets.ForgeClientUserdevLaunchHandler;
import net.neoforged.fml.loading.targets.ForgeDataUserdevLaunchHandler;

public class LauncherUtil
{
	public static boolean isRunDevData()
	{
		return FMLLoader.getLaunchHandler() instanceof ForgeDataUserdevLaunchHandler;
	}

	public static boolean isRunDevClient()
	{
		return FMLLoader.getLaunchHandler() instanceof ForgeClientUserdevLaunchHandler;
	}

	private LauncherUtil()
	{

	}

}
