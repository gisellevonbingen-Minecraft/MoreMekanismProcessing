package gisellevonbingen.mmp.common.material;

import org.apache.commons.lang3.NotImplementedException;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.tag.MoreMekanismProcessingTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ITag;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ITagCollection;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Util;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.LanguageMap;
import net.minecraft.util.text.TranslationTextComponent;
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

	public INamedTag<Item> getStateItemTag(MaterialType materialType)
	{
		if (this != MaterialState.ORE)
		{
			return MoreMekanismProcessingTags.Items.getProcessingItemTag(materialType, this);
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

	public INamedTag<Block> getStateBlockTag(MaterialType materialType)
	{
		ITagCollection<Block> allTags = BlockTags.getAllTags();
		ResourceLocation tagName = this.getStateTagName(materialType);
		INamedTag<Block> tag = (INamedTag<Block>) allTags.getTag(tagName);

		if (tag != null)
		{
			return tag;
		}
		else
		{
			return BlockTags.bind(tagName.toString());
		}

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
		if (this != MaterialState.ORE)
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
		return Util.makeDescriptionId("statedMaterial", MoreMekanismProcessing.rl(baseName));
	}

	public static ITextComponent createTextComponent(String translationKey, String statedDescriptionId, MaterialType materialType)
	{
		if (LanguageMap.getInstance().has(translationKey) == true)
		{
			return new TranslationTextComponent(translationKey);
		}
		else
		{
			return new TranslationTextComponent(statedDescriptionId, new TranslationTextComponent(materialType.getDescriptionId()));
		}

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
