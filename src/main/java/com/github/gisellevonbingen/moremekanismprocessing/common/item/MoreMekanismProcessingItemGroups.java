package com.github.gisellevonbingen.moremekanismprocessing.common.item;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MoreMekanismProcessingItemGroups
{
	public static final ItemGroup tabMoreMekanismProcessing = new ItemGroup(MoreMekanismProcessing.MODID)
	{
		@Override
		public ItemStack makeIcon()
		{
			return MaterialState.CRYSTAL.getItemStack(MaterialType.Cobalt);
		}

		@Override
		public ITextComponent getDisplayName()
		{
			return new StringTextComponent(MoreMekanismProcessing.MODANME);
		};

	};

}
