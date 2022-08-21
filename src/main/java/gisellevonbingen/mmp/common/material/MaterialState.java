package gisellevonbingen.mmp.common.material;

import org.apache.commons.lang3.NotImplementedException;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.tag.MoreMekanismProcessingTags;
import mekanism.common.tags.MekanismTags;
import net.minecraft.Util;
import net.minecraft.locale.Language;
import net.minecraft.network.chat.Component;
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
	ORE(new MaterialStateBuilder("ore", Tags.Items.ORES).hasOwnItem(false)),
	RAW_STORAGE_BLOCKS(new MaterialStateBuilder("raw_storage_blocks", Tags.Items.STORAGE_BLOCKS).tagPrefix("raw_").hasOwnItem(false)),
	RAW_ITEM(new MaterialStateBuilder("raw_ore", Tags.Items.RAW_MATERIALS).hasOwnItem(false)),
	DUST(new MaterialStateBuilder("dust", Tags.Items.DUSTS)),
	DIRTY_DUST(new MaterialStateBuilder("dirty_dust", MekanismTags.Items.DIRTY_DUSTS)),
	CLUMP(new MaterialStateBuilder("clump", MekanismTags.Items.CLUMPS)),
	SHARD(new MaterialStateBuilder("shard", MekanismTags.Items.SHARDS)),
	CRYSTAL(new MaterialStateBuilder("crystal", MekanismTags.Items.CRYSTALS)),
	INGOT(new MaterialStateBuilder("ingot", Tags.Items.INGOTS)),
	GEM(new MaterialStateBuilder("gem", Tags.Items.GEMS)),
	NUGGET(new MaterialStateBuilder("nugget", Tags.Items.NUGGETS)),;

	public static class MaterialStateBuilder
	{
		private final String baseName;
		private final TagKey<Item> categoryTag;

		private boolean hasOwnItem;
		private String tagPrefix;

		public MaterialStateBuilder(String baseName, TagKey<Item> categoryTag)
		{
			this.baseName = baseName;
			this.categoryTag = categoryTag;

			this.hasOwnItem = true;
			this.tagPrefix = "";
		}

		public MaterialStateBuilder hasOwnItem(boolean hasOwnItem)
		{
			this.hasOwnItem = hasOwnItem;
			return this;
		}

		public MaterialStateBuilder tagPrefix(String tagPrefix)
		{
			this.tagPrefix = tagPrefix;
			return this;
		}

	}

	private String baseName;
	private TagKey<Item> categoryTag;

	private boolean hasOwnItem;
	private String tagPrefix;

	MaterialState(MaterialStateBuilder builder)
	{
		this.baseName = builder.baseName;
		this.categoryTag = builder.categoryTag;

		this.hasOwnItem = builder.hasOwnItem;
		this.tagPrefix = builder.tagPrefix;
	}

	public ResourceLocation getStateTagName(MaterialType materialType)
	{
		ResourceLocation categoryTagName = this.getCategoryTag().location();
		String tagPrefix = this.getTagPrefix();

		StringBuilder pathBuilder = new StringBuilder();
		pathBuilder.append(categoryTagName.getPath());
		pathBuilder.append("/");
		pathBuilder.append(tagPrefix);
		pathBuilder.append(materialType.getBaseName());
		return new ResourceLocation(categoryTagName.getNamespace(), pathBuilder.toString());
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
			return Component.translatable(translationKey);
		}
		else
		{
			return Component.translatable(statedDescriptionId, Component.translatable(materialType.getDescriptionId()));
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

	public String getTagPrefix()
	{
		return this.tagPrefix;
	}

}
