package gisellevonbingen.mmp.common.material;

import org.apache.commons.lang3.NotImplementedException;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.tag.MoreMekanismProcessingTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.Util;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;

public enum MaterialState
{
	ORE("ore", Tags.Items.ORES, false),
	RAW("raw_ore", Tags.Items.RAW_MATERIALS, false),
	DUST("dust", Tags.Items.DUSTS),
	DIRTY_DUST("dirty_dust", MekanismTags.Items.DIRTY_DUSTS),
	CLUMP("clump", MekanismTags.Items.CLUMPS),
	SHARD("shard", MekanismTags.Items.SHARDS),
	CRYSTAL("crystal", MekanismTags.Items.CRYSTALS),
	INGOT("ingot", Tags.Items.INGOTS),
	GEM("gem", Tags.Items.GEMS),
	NUGGET("nugget", Tags.Items.NUGGETS),;

	private String baseName;
	private TagKey<Item> categoryTag;
	private boolean hasOwnItem;

	MaterialState(String baseName, TagKey<Item> categoryTag)
	{
		this(baseName, categoryTag, true);
	}

	MaterialState(String baseName, TagKey<Item> categoryTag, boolean hasOwnItem)
	{
		this.baseName = baseName;
		this.categoryTag = categoryTag;
		this.hasOwnItem = hasOwnItem;
	}

	public ResourceLocation getStateTagName(MaterialType materialType)
	{
		ResourceLocation categoryTagName = this.getCategoryTag().location();
		return new ResourceLocation(categoryTagName.getNamespace(), categoryTagName.getPath() + "/" + materialType.getBaseName());
	}

	public TagKey<Item> getStateItemTag(MaterialType materialType)
	{
		if (this.hasOwnItem() == true)
		{
			return MoreMekanismProcessingTags.Items.getProcessingItemTag(materialType, this);
		}
		else
		{
			ResourceLocation tagName = this.getStateTagName(materialType);
			return ItemTags.create(tagName);
		}

	}

	public TagKey<Block> getStateBlockTag(MaterialType materialType)
	{
		ResourceLocation tagName = this.getStateTagName(materialType);
		return BlockTags.create(tagName);
	}

	public String getItemNamePath(MaterialType materialType)
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

	public ResourceLocation getItemName(MaterialType materialType)
	{
		if (this.hasOwnItem() == true)
		{
			return MoreMekanismProcessingItems.getProcessingItemName(materialType, this);
		}
		else
		{
			throw new NotImplementedException("getItemName(" + materialType.name() + ")");
		}

	}

	public Item getItem(MaterialType materialType)
	{
		if (this.hasOwnItem() == true)
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
		return Util.makeDescriptionId("statedMaterial", MoreMekanismProcessing.rl(baseName));
	}

	public static Component createTextComponent(String translationKey, String statedDescriptionId, MaterialType materialType)
	{
		if (Language.getInstance().has(translationKey) == true)
		{
			return new TranslatableComponent(translationKey);
		}
		else
		{
			return new TranslatableComponent(statedDescriptionId, new TranslatableComponent(materialType.getDescriptionId()));
		}

	}

	public String getBaseName()
	{
		return this.baseName;
	}

	public TagKey<Item> getCategoryTag()
	{
		return this.categoryTag;
	}

	public boolean hasOwnItem()
	{
		return this.hasOwnItem;
	}

}
