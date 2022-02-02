package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import java.util.HashMap;
import java.util.Map;

import mekanism.common.Mekanism;
import mekanism.common.registration.WrappedRegistryObject;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.IForgeRegistryEntry;

public class MaterialTypeBuilder
{
	private final String baseName;
	private final Map<MaterialState, ResourceLocation> presetItems;
	private MaterialResultShape resultShape;
	private String displayName;
	private int color;
	private boolean respect;

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

	public MaterialTypeBuilder presetItem(MaterialState state, IForgeRegistryEntry<Item> holder)
	{
		return this.presetItem(state, holder.getRegistryName());
	}

	public MaterialTypeBuilder presetItem(MaterialState state, WrappedRegistryObject<Item> holder)
	{
		return this.presetItem(state, Mekanism.MODID, holder);
	}

	public MaterialTypeBuilder presetItem(MaterialState state, String modid, WrappedRegistryObject<Item> holder)
	{
		return this.presetItem(state, modid, holder.getInternalRegistryName());
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
	
	public boolean respect()
	{
		return this.respect;
	}
	
	public MaterialTypeBuilder respect(boolean respect)
	{
		this.respect = respect;
		return this;
	}

}
