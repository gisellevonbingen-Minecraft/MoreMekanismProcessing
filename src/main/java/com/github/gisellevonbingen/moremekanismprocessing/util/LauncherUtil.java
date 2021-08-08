package com.github.gisellevonbingen.moremekanismprocessing.util;

public class LauncherUtil
{
	public static boolean isRunData()
	{
		String target = System.getenv("target");
		return target != null && target.equalsIgnoreCase("fmluserdevdata");
	}

	private LauncherUtil()
	{

	}

}
