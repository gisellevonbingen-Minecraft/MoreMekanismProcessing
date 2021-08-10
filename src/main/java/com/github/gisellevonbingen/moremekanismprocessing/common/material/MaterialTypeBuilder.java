package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.util.ResourceLocation;

public class MaterialTypeBuilder
{
	private final String baseName;
	private final Map<MaterialState, ResourceLocation> presetItems;
	private MaterialResultShape resultShape;
	private String displayName;
	private int color;

	public MaterialTypeBuilder(String baseName)
	{
		this.baseName = baseName;
		this.presetItems = new HashMap<>();
	}

	public Map<MaterialState, ResourceLocation> presetItems()
	{
		return this.presetItems;
	}

	public ResourceLocation presetItem(MaterialState state)
	{
		return this.presetItems.get(state);
	}

	public MaterialTypeBuilder presetItem(MaterialState state, ResourceLocation name)
	{
		this.presetItems.put(state, name);
		return this;
	}

	public MaterialTypeBuilder presetItem(MaterialState state, String modid, String itemName)
	{
		return this.presetItem(state, new ResourceLocation(modid, itemName));
	}

	public String baseName()
	{
		return this.baseName;
	}

	public MaterialResultShape resultShape()
	{
		return this.resultShape;
	}

	public MaterialTypeBuilder resultShape(MaterialResultShape resultShape)
	{
		this.resultShape = resultShape;
		return this;
	}

	public String displayName()
	{
		return this.displayName;
	}

	public MaterialTypeBuilder displayName(String displayName)
	{
		this.displayName = displayName;
		return this;
	}

	public int color()
	{
		return this.color;
	}

	public MaterialTypeBuilder color(int color)
	{
		this.color = color;
		return this;
	}

}
