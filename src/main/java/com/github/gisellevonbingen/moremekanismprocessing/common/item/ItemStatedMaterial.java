package com.github.gisellevonbingen.moremekanismprocessing.common.item;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;

public class ItemStatedMaterial extends Item
{
	private final MaterialType materialType;
	private final MaterialState materialState;

	public ItemStatedMaterial(MaterialType materialType, MaterialState materialState)
	{
		super(new Properties().tab(MoreMekanismProcessingItemGroups.tabMoreMekanismProcessing));

		this.materialType = materialType;
		this.materialState = materialState;
	}

	@Override
	public ITextComponent getName(ItemStack itemStack)
	{
		String descriptionId = this.getDescriptionId(itemStack);
		String statedDescriptionId = this.materialState.getStatedDescriptionId();
		return MaterialState.createTextComponent(descriptionId, statedDescriptionId, this.getMaterialType());
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public MaterialState getOreState()
	{
		return this.materialState;
	}

}
