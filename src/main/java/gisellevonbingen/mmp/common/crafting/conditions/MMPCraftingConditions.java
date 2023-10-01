package gisellevonbingen.mmp.common.crafting.conditions;

import net.minecraftforge.common.crafting.CraftingHelper;

public class MMPCraftingConditions
{
	public static void register()
	{
		CraftingHelper.register(new ProcessingLevelCondition.Serializer());
	}

}
