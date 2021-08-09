package com.github.gisellevonbingen.moremekanismprocessing.client.renderer.color;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.client.renderer.color.IItemColor;
import net.minecraft.item.ItemStack;

public class CommonItemColor implements IItemColor
{
	private MaterialType type;

	public CommonItemColor(MaterialType type)
	{
		this.type = type;
	}

	@Override
	public int getColor(ItemStack itemColor, int p1)
	{
		if (p1 == 0)
		{
			return 0xFF000000 | this.type.getColor();
		}
		else
		{
			return 0xFFFFFFFF;
		}

	}

	public MaterialType getType()
	{
		return this.type;
	}

}
