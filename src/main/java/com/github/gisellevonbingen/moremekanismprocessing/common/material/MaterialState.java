package com.github.gisellevonbingen.moremekanismprocessing.common.material;

import org.apache.commons.lang3.NotImplementedException;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessingItems;

import mekanism.common.tags.MekanismTags;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraftforge.common.Tags;

public enum MaterialState
{
	ORE("ore", Tags.Items.ORES),
	DUST("dust", Tags.Items.DUSTS),
	DIRTY_DUST("dirty_dust", MekanismTags.Items.DIRTY_DUSTS),
	CLUMP("clump", MekanismTags.Items.CLUMPS),
	SHARD("shard", MekanismTags.Items.SHARDS),
	CRYSTAL("crystal", MekanismTags.Items.CRYSTALS),
	INGOT("ingot", Tags.Items.INGOTS),
	GEM("gem", Tags.Items.GEMS),
	NUGGET("nugget", Tags.Items.NUGGETS),;

	private String baseName;
	private ITag.INamedTag<Item> categoryTag;

	MaterialState(String baseName, ITag.INamedTag<Item> categoryTag)
	{
		this.baseName = baseName;
		this.categoryTag = categoryTag;
	}

	public ResourceLocation getStateTagName(MaterialType materialType)
	{
		ResourceLocation categoryTagName = this.getCategoryTag().getName();
		return new ResourceLocation(categoryTagName.getNamespace(), categoryTagName.getPath() + "/" + materialType.getBaseName());
	}

	public INamedTag<Item> getStateTag(MaterialType materialType)
	{
		if (this != MaterialState.ORE)
		{
			return com.github.gisellevonbingen.moremekanismprocessing.common.tag.MoreMekanismProcessingTags.getProcessingItemTag(materialType, this);
		}
		else
		{
			ITagCollection<Item> allTags = ItemTags.getAllTags();
			ResourceLocation tagName = this.getStateTagName(materialType);
			INamedTag<Item> tag = (INamedTag<Item>) allTags.getTag(tagName);

			if (tag != null)
			{
				return tag;
			}
			else
			{
				return ItemTags.bind(tagName.toString());
			}

		}

	}

	public String getItemName(MaterialType materialType)
	{
		String stateName = this.getBaseName();
		String baseName = materialType.getBaseName();

		if (this == INGOT || this == NUGGET)
		{
			return baseName + "_" + stateName;
		}
		else
		{
			return stateName + "_" + baseName;
		}

	}

	public Item getItem(MaterialType materialType)
	{
		if (this != MaterialState.ORE)
		{
			return MoreMekanismProcessingItems.getProcessingItem(materialType, this);
		}
		else
		{
			throw new NotImplementedException("getItem(" + materialType.name() + ")");
		}

	}

	public ItemStack getItemStack(MaterialType materialType)
	{
		return new ItemStack(this.getItem(materialType));
	}

	public ItemStack getItemStack(MaterialType materialType, int count)
	{
		return new ItemStack(this.getItem(materialType), count);
	}

	public String getStatedDescriptionId()
	{
		return makeDescriptionId(this.baseName);
	}

	public static String makeDescriptionId(String baseName)
	{
		return Util.makeDescriptionId("statedMaterial", new ResourceLocation(MoreMekanismProcessing.MODID, baseName));
	}

	public String getBaseName()
	{
		return this.baseName;
	}

	public INamedTag<Item> getCategoryTag()
	{
		return this.categoryTag;
	}

}
