package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import java.util.HashMap;
import java.util.Map;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;

import mekanism.common.Mekanism;
import mekanism.common.registries.MekanismItems;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;

public enum MaterialType
{
	// Tinkers Construct
	Cobalt(new MaterialTypeBuilder("cobalt").resultShape(MaterialResultShape.INGOT).displayName("Cobalt").color(0x1E66BF)),

	// Thermal Foundation
	Silver(new MaterialTypeBuilder("silver").resultShape(MaterialResultShape.INGOT).displayName("Silver").color(0xD8E4ED)),
	Nickel(new MaterialTypeBuilder("nickel").resultShape(MaterialResultShape.INGOT).displayName("Nickel").color(0xE5E09E)),
	Niter(new MaterialTypeBuilder("niter").resultShape(MaterialResultShape.DUST).displayName("Niter").color(0xB8AFAF)),
	Sulfur(new MaterialTypeBuilder("sulfur").resultShape(MaterialResultShape.DUST).displayName("Sulfur").color(0xE1FC93).presetItem(MaterialState.DUST, Mekanism.MODID, MekanismItems.SULFUR_DUST.getInternalRegistryName())),

	// Blue Power
	Ruby(new MaterialTypeBuilder("ruby").resultShape(MaterialResultShape.GEM).displayName("Ruby").color(0xCC3333)),
	Sapphire(new MaterialTypeBuilder("sapphire").resultShape(MaterialResultShape.GEM).displayName("Sapphire").color(0x3333CC)),
	Amethyst(new MaterialTypeBuilder("amethyst").resultShape(MaterialResultShape.GEM).displayName("Amethyst").color(0xCC33CC)),
	GreenSapphire(new MaterialTypeBuilder("green_sapphire").resultShape(MaterialResultShape.GEM).displayName("Green Sapphire").color(0x33CC33)),
	Zinc(new MaterialTypeBuilder("zinc").resultShape(MaterialResultShape.INGOT).displayName("Zinc").color(0xCCCC8E)),
	Tungsten(new MaterialTypeBuilder("tungsten").resultShape(MaterialResultShape.INGOT).displayName("Tungsten").color(0x333333)),

	// Project Red
	// Ruby
	// Sapphire
	Peridot(new MaterialTypeBuilder("peridot").resultShape(MaterialResultShape.GEM).displayName("Peridot").color(0x66FF66)),
	// Copper
	// Tin
	// Silver
	Electrotine(new MaterialTypeBuilder("electrotine").resultShape(MaterialResultShape.DUST).displayName("Electrotine").color(0x0CB4F8)),

	// Ice and Fire: Dragons
	// Copper
	// Silver
	// Sapphire
	// Amethyst

	// Silent Gear
	Bort(new MaterialTypeBuilder("bort").resultShape(MaterialResultShape.GEM).displayName("Bort").color(0x7495AF)),
	CrimsonIron(new MaterialTypeBuilder("crimson_iron").resultShape(MaterialResultShape.INGOT).displayName("Crimson Iron").color(0xF44770)),
	AzureSiler(new MaterialTypeBuilder("azure_silver").resultShape(MaterialResultShape.INGOT).displayName("Azure Silver").color(0xCE9CF3)),

	// Immersive Engineering
	Aluminum(new MaterialTypeBuilder("aluminum").resultShape(MaterialResultShape.INGOT).displayName("Aluminum").color(0xE5E5E5)),

	// EOL
	;

	private final String baseName;
	private final Map<MaterialState, ResourceLocation> presetItems;
	private final MaterialResultShape resultShape;
	private final String displayName;
	private final int color;

	private MaterialType(MaterialTypeBuilder builder)
	{
		this.resultShape = builder.resultShape();
		this.presetItems = new HashMap<>(builder.presetItems());
		this.baseName = builder.baseName();
		this.displayName = builder.displayName();
		this.color = 0xFF000000 | builder.color();
	}

	public String getDescriptionId()
	{
		return makeDescriptionId(this.baseName);
	}

	public static String makeDescriptionId(String baseName)
	{
		return Util.makeDescriptionId("materialType", new ResourceLocation(MoreMekanismProcessing.MODID, baseName));
	}

	public String getBaseName()
	{
		return this.baseName;
	}

	public Map<MaterialState, ResourceLocation> getPresetItems()
	{
		return new HashMap<>(this.presetItems);
	}

	public ResourceLocation getPresetItem(MaterialState materialState)
	{
		return this.presetItems.get(materialState);
	}

	public MaterialResultShape getResultShape()
	{
		return this.resultShape;
	}

	public String getDisplayName()
	{
		return this.displayName;
	}

	public int getColor()
	{
		if (this == Sulfur)
		{
			return 0xD8CC51;
		}

		return this.color;
	}

}
