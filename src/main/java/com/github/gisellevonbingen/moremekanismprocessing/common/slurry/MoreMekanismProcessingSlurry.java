package com.github.gisellevonbingen.moremekanismprocessing.common.slurry;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryBuilder;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;

public class MoreMekanismProcessingSlurry extends Slurry
{
	private MaterialType materialType;
	private String state;

	public MoreMekanismProcessingSlurry(SlurryBuilder builder, MaterialType materialType, String state)
	{
		super(builder);

		this.materialType = materialType;
		this.state = state;
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	@Override
	public int getTint()
	{
		return this.materialType.getColor();
	}

	@Override
	public ITextComponent getTextComponent()
	{
		String descriptionId = this.getTranslationKey();

		if (LanguageMap.getInstance().has(descriptionId) == true)
		{
			return new TranslationTextComponent(descriptionId);
		}
		else
		{
			String statedDescriptionId = MaterialState.makeDescriptionId(this.state + "_slurry");
			String materialTypedescriptionId = this.materialType.getDescriptionId();
			TranslationTextComponent materialType = new TranslationTextComponent(materialTypedescriptionId);
			return new TranslationTextComponent(statedDescriptionId, materialType);
		}

	}

}
