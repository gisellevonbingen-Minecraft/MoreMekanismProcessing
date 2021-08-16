package com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions;

import net.minecraftforge.common.crafting.CraftingHelper;

public class MoreMekanismProcessingConditions
{
	public static void register()
	{
		CraftingHelper.register(new TagNotEmptyCondition.Serializer());
		CraftingHelper.register(new ProcessingLevelCondition.Serializer());
		CraftingHelper.register(new OverrideRespectCondition.Serializer());
	}

}
