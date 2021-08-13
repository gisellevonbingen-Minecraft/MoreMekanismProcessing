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
	Aluminum(new MaterialTypeBuilder("aluminum").resultShape(MaterialResultShape.INGOT).displayName("Aluminum").color(0xA0C4D8)),
	Amethyst(new MaterialTypeBuilder("amethyst").resultShape(MaterialResultShape.GEM).displayName("Amethyst").color(0xCC33CC)),
	AzureSiler(new MaterialTypeBuilder("azure_silver").resultShape(MaterialResultShape.INGOT).displayName("Azure Silver").color(0xCE9CF3)),
	Bismuth(new MaterialTypeBuilder("bismuth").resultShape(MaterialResultShape.INGOT).displayName("Bismuth").color(0xE8D8E8)),
	Bort(new MaterialTypeBuilder("bort").resultShape(MaterialResultShape.GEM).displayName("Bort").color(0x7495AF)),
	Cobalt(new MaterialTypeBuilder("cobalt").resultShape(MaterialResultShape.INGOT).displayName("Cobalt").color(0x1E66BF)),
	CrimsonIron(new MaterialTypeBuilder("crimson_iron").resultShape(MaterialResultShape.INGOT).displayName("Crimson Iron").color(0xF44770)),
	Dilithium(new MaterialTypeBuilder("dilithium").resultShape(MaterialResultShape.GEM).displayName("Dilithium").color(0xD8C4C4)),
	Electrotine(new MaterialTypeBuilder("electrotine").resultShape(MaterialResultShape.DUST).displayName("Electrotine").color(0x0CB4F8)),
	GreenSapphire(new MaterialTypeBuilder("green_sapphire").resultShape(MaterialResultShape.GEM).displayName("Green Sapphire").color(0x33CC33)),
	Iridium(new MaterialTypeBuilder("iridium").resultShape(MaterialResultShape.INGOT).displayName("Iridium").color(0xD8D8C4)),
	Lithium(new MaterialTypeBuilder("lithium").resultShape(MaterialResultShape.INGOT).displayName("Lithium").color(0x808080)),
	Nickel(new MaterialTypeBuilder("nickel").resultShape(MaterialResultShape.INGOT).displayName("Nickel").color(0xE5E09E)),
	Niter(new MaterialTypeBuilder("niter").resultShape(MaterialResultShape.DUST).displayName("Niter").color(0xB8AFAF)),
	Peridot(new MaterialTypeBuilder("peridot").resultShape(MaterialResultShape.GEM).displayName("Peridot").color(0x66FF66)),
	Platinum(new MaterialTypeBuilder("platinum").resultShape(MaterialResultShape.INGOT).displayName("Platinum").color(0xA0F0FF)),
	Ruby(new MaterialTypeBuilder("ruby").resultShape(MaterialResultShape.GEM).displayName("Ruby").color(0xCC3333)),
	Sapphire(new MaterialTypeBuilder("sapphire").resultShape(MaterialResultShape.GEM).displayName("Sapphire").color(0x3333CC)),
	Silver(new MaterialTypeBuilder("silver").resultShape(MaterialResultShape.INGOT).displayName("Silver").color(0xD8E4ED)),
	Sulfur(new MaterialTypeBuilder("sulfur").resultShape(MaterialResultShape.DUST).displayName("Sulfur").color(0xD8CC51).presetItem(MaterialState.DUST, Mekanism.MODID, MekanismItems.SULFUR_DUST.getInternalRegistryName())),
	Titanium(new MaterialTypeBuilder("titanium").resultShape(MaterialResultShape.INGOT).displayName("Titanium").color(0x405060)),
	Tungsten(new MaterialTypeBuilder("tungsten").resultShape(MaterialResultShape.INGOT).displayName("Tungsten").color(0x333333)),
	Zinc(new MaterialTypeBuilder("zinc").resultShape(MaterialResultShape.INGOT).displayName("Zinc").color(0xCCCC8E)),

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
		return this.color;
	}

}
