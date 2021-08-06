package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public enum MaterialType
{
	// Tinkers Construct
	Cobalt(MaterialResultShape.INGOT, "cobalt", "Cobalt", 0x1E66BF),

	// Thermal Foundation
	Silver(MaterialResultShape.INGOT, "silver", "Silver", 0xD8E4ED),
	Nickel(MaterialResultShape.INGOT, "nickel", "Nickel", 0xE5E09E),
	Niter(MaterialResultShape.GEM, "niter", "Niter", 0xC0C0C0),
	Sulfur(MaterialResultShape.GEM, "sulfur", "Sulfur", 0xEFFCCB),

	// Just Test
	// Unknownium(MaterialResultShape.INGOT, "unknownium", "Unknownium", 0xFFFFFF),;
	;

	private final MaterialResultShape resultShape;
	private final String baseName;
	private final String displayName;
	private final int color;

	private MaterialType(MaterialResultShape resultShape, String baseName, String displayName, int color)
	{
		this.resultShape = resultShape;
		this.baseName = baseName;
		this.displayName = displayName;
		this.color = 0xFF000000 | color;
	}

	public String getDescriptionId()
	{
		return makeDescriptionId(this.baseName);
	}

	public static String makeDescriptionId(String baseName)
	{
		return Util.makeDescriptionId("materialType", new ResourceLocation(MoreMekanismProcessing.MODID, baseName));
	}

	public MaterialResultShape getResultShape()
	{
		return this.resultShape;
	}

	public String getBaseName()
	{
		return this.baseName;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public int getColor()
	{
		return this.color;
	}

}
