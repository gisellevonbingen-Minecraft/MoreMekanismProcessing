package com.github.gisellevonbingen.moremekanismprocessing;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;

public class MoreMekanismProcessingItemGroups
{
	public static final ItemGroup tabMoreMekanismProcessing = new ItemGroup(MoreMekanismProcessing.MODID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return MaterialState.CRYSTAL.getItemStack(MaterialType.Cobalt);
		}

	};

}
