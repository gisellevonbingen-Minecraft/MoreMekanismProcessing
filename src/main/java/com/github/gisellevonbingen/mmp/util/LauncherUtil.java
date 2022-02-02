package com.github.gisellevonbingen.mmp.util;

import net.minecraftforge.fml.loading.FMLLoader;
import net.minecraftforge.fml.loading.targets.ForgeClientUserdevLaunchHandler;
import net.minecraftforge.fml.loading.targets.ForgeDataUserdevLaunchHandler;

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
