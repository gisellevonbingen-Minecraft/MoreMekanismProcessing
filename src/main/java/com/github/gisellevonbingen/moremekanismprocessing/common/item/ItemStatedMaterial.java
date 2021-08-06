package com.github.gisellevonbingen.moremekanismprocessing.common.item;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessingItemGroups;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;

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

		if (LanguageMap.getInstance().has(descriptionId) == true)
		{
			return new TranslationTextComponent(descriptionId);
		}
		else
		{
			String statedDescriptionId = this.materialState.getStatedDescriptionId();
			String materialTypedescriptionId = this.materialType.getDescriptionId();
			TranslationTextComponent materialType = new TranslationTextComponent(materialTypedescriptionId);
			return new TranslationTextComponent(statedDescriptionId, materialType);
		}

	}

	public MaterialType getOreType()
	{
		return this.materialType;
	}

	public MaterialState getOreState()
	{
		return this.materialState;
	}

}
