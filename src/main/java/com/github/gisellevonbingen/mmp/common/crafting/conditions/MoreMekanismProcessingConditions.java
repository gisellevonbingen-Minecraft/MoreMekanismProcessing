package com.github.gisellevonbingen.mmp.common.crafting.conditions;

import net.minecraftforge.common.crafting.CraftingHelper;

public class MoreMekanismProcessingConditions
{
	public static void register()
	{
		CraftingHelper.register(new ProcessingLevelCondition.Serializer());
	}

}
