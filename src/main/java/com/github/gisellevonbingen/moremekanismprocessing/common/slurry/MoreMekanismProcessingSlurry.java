package com.github.gisellevonbingen.moremekanismprocessing.common.slurry;

import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import mekanism.api.chemical.slurry.Slurry;
import net.minecraft.network.chat.Component;

public class MoreMekanismProcessingSlurry extends Slurry
{
	public static final String SLURRY = "slurry";

	private final MaterialType materialType;
	private final String slurryType;

	public MoreMekanismProcessingSlurry(MoreMekanismProcessingSlurryBuilder builder)
	{
		super(builder);

		this.materialType = builder.materialType();
		this.slurryType = builder.slurryType();
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public String getSlurryType()
	{
		return this.slurryType;
	}

	@Override
	public int getTint()
	{
		return this.getMaterialType().getColor();
	}

	@Override
	public Component getTextComponent()
	{
		String descriptionId = this.getTranslationKey();
		String statedDescriptionId = this.getStatedDescriptionId();
		return MaterialState.createTextComponent(descriptionId, statedDescriptionId, this.getMaterialType());
	}

	public String getSlurryName()
	{
		return getSlurryName(this.getSlurryType(), this.getMaterialType());
	}

	public String getStatedDescriptionId()
	{
		return makeDescriptionId(this.getSlurryType());
	}

	public static String makeDescriptionId(String slurryType)
	{
		return MaterialState.makeDescriptionId(getSlurryTypeKey(slurryType));
	}

	public static String getSlurryTypeKey(String slurryType)
	{
		return slurryType + "_" + SLURRY;
	}

	public static String getSlurryName(String slurryType, MaterialType materialType)
	{
		return getSlurryName(slurryType, materialType.getBaseName());
	}

	public static String getSlurryName(String slurryType, String materialType)
	{
		return slurryType + "_" + materialType;
	}

}
