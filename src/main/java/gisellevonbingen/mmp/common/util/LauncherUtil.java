package gisellevonbingen.mmp.common.util;

public class LauncherUtil
{
	public static boolean isRunTarget(String requireTarget)
	{
		String target = System.getenv("target");
		return target != null && target.equalsIgnoreCase(requireTarget);
	}

	public static boolean isRunDevData()
	{
		return isRunTarget("fmluserdevdata");
	}

	public static boolean isRunDevClient()
	{
		return isRunTarget("fmluserdevclient");
	}

	private LauncherUtil()
	{

	}

}
