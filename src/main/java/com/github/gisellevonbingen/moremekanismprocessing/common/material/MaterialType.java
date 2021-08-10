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

	// Blue Power
	Ruby(MaterialResultShape.GEM, "ruby", "Ruby", 0xCC3333),
	Sapphire(MaterialResultShape.GEM, "sapphire", "Sapphire", 0x3333CC),
	Amethyst(MaterialResultShape.GEM, "amethyst", "Amethyst", 0xCC33CC),
	GreenSapphire(MaterialResultShape.GEM, "green_sapphire", "Green Sapphire", 0x33CC33),
	Zinc(MaterialResultShape.INGOT, "zinc", "Zinc", 0xCCCC8E),
	Tungsten(MaterialResultShape.INGOT, "tungsten", "Tungsten", 0x333333),

	// Project Red
	// Ruby
	// Sapphire
	Peridot(MaterialResultShape.GEM, "peridot", "Peridot", 0x66FF66),
	// Copper
	// Tin
	// Silver
	Electrotine(MaterialResultShape.DUST, "electrotine", "Electrotine", 0x0CB4F8),

	// Ice and Fire: Dragons
	// Copper
	// Silver
	// Sapphire
	// Amethyst

	// Silent Gear
	Bort(MaterialResultShape.GEM, "bort", "Bort", 0x7495AF),
	CrimsonIron(MaterialResultShape.INGOT, "crimson_iron", "Crimson Iron", 0xF44770),
	AzureSiler(MaterialResultShape.INGOT, "azure_silver", "Azure Silver", 0xCE9CF3),

	// Immersive Engineering
	Aluminum(MaterialResultShape.INGOT, "aluminum", "Aluminum", 0xD8D8D8),
	
	// EOL
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
		if (this == CrimsonIron)
		{
			return 0xF44770;
		}

		return this.color;
	}

}
