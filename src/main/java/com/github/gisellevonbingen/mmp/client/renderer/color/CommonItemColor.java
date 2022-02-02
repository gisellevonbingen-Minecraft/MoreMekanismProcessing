package com.github.gisellevonbingen.mmp.client.renderer.color;

import com.github.gisellevonbingen.mmp.common.material.MaterialType;

import net.minecraft.client.color.item.ItemColor;
import net.minecraft.world.item.ItemStack;

public class CommonItemColor implements ItemColor
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
