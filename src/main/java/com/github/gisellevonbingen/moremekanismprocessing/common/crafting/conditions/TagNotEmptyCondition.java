package com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
import net.minecraft.util.JSONUtils;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class TagNotEmptyCondition implements ICondition
{
	private static final ResourceLocation NAME = new ResourceLocation(MoreMekanismProcessing.MODID, "tag_not_empty");
	private final ResourceLocation tagName;

	public TagNotEmptyCondition(ResourceLocation tagName)
	{
		this.tagName = tagName;
	}

	@Override
	public ResourceLocation getID()
	{
		return NAME;
	}

	@Override
	public boolean test()
	{
		ITag<Item> tag = TagCollectionManager.getInstance().getItems().getTag(this.tagName);
		return tag != null && tag.getValues().isEmpty() == false;
	}

	@Override
	public String toString()
	{
		return "tag_not_empty(\"" + this.tagName + "\")";
	}

	public ResourceLocation getTagName()
	{
		return this.tagName;
	}

	public static class Serializer implements IConditionSerializer<TagNotEmptyCondition>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public void write(JsonObject json, TagNotEmptyCondition value)
		{
			json.addProperty("tag", value.tagName.toString());
		}

		@Override
		public TagNotEmptyCondition read(JsonObject json)
		{
			return new TagNotEmptyCondition(new ResourceLocation(JSONUtils.getAsString(json, "tag")));
		}

		@Override
		public ResourceLocation getID()
		{
			return TagNotEmptyCondition.NAME;
		}

	}

}
