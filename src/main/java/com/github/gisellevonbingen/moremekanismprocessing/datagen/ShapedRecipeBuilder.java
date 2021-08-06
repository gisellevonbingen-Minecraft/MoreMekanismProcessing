package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapedRecipeBuilder
{
	private final ResourceLocation id;
	private String group;
	private Item output;
	private int count;
	private final List<String> patterns;
	private final Map<Character, Ingredient> keys;

	public ShapedRecipeBuilder(ResourceLocation id)
	{
		this.id = id;
		this.patterns = new ArrayList<>();
		this.keys = new HashMap<>();

		this.setGroup("");
		this.setCount(1);
	}

	public ShapedRecipeBuilder(ResourceLocation id, Item output, int count)
	{
		this(id);

		this.setOutput(output);
		this.setCount(count);
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public String getGroup()
	{
		return this.group;
	}

	public ShapedRecipeBuilder setGroup(String group)
	{
		this.group = group;
		return this;
	}

	public Item getOutput()
	{
		return this.output;
	}

	public ShapedRecipeBuilder setOutput(Item output)
	{
		this.output = output;
		return this;
	}

	public ShapedRecipeBuilder setOutput(Item output, int count)
	{
		this.setOutput(output);
		this.setCount(count);
		return this;
	}

	public int getCount()
	{
		return this.count;
	}

	public ShapedRecipeBuilder setCount(int count)
	{
		this.count = count;
		return this;
	}

	public List<String> getPatterns()
	{
		return this.patterns;
	}

	public ShapedRecipeBuilder addPattern(String pattern)
	{
		this.patterns.add(pattern);
		return this;
	}

	public ShapedRecipeBuilder addPattern(Collection<String> patterns)
	{
		this.patterns.addAll(patterns);
		return this;
	}

	public ShapedRecipeBuilder addPattern(String... patterns)
	{
		return this.addPattern(Lists.newArrayList(patterns));
	}

	public Map<Character, Ingredient> getKey()
	{
		return this.keys;
	}

	public ShapedRecipeBuilder addKey(Character key, Ingredient ingredient)
	{
		this.keys.put(key, ingredient);
		return this;
	}

	public IRecipeSerializer<?> getType()
	{
		return (IRecipeSerializer<?>) IRecipeSerializer.SHAPED_RECIPE;
	}

	public Result getResult()
	{
		return new Result(this);
	}

	public static class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final String group;
		private final Item result;
		private final int count;
		private final List<String> patterns;
		private final Map<Character, Ingredient> keys;
		private final IRecipeSerializer<?> type;

		public Result(ShapedRecipeBuilder builder)
		{
			this.id = builder.id;
			this.group = builder.group;
			this.result = builder.output;
			this.count = builder.count;
			this.patterns = new ArrayList<>(builder.patterns);
			this.keys = new HashMap<>(builder.keys);
			this.type = builder.getType();
		}

		public void serializeRecipeData(JsonObject json)
		{
			if (!Strings.isNullOrEmpty(this.group))
			{
				json.addProperty("group", this.group);
			}

			JsonArray patternJson = new JsonArray();

			for (String pattern : this.patterns)
			{
				patternJson.add(pattern);
			}

			json.add("pattern", patternJson);

			JsonObject keyJson = new JsonObject();

			for (Map.Entry<Character, Ingredient> entry : this.keys.entrySet())
			{
				keyJson.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			json.add("key", keyJson);

			JsonObject resultJson = new JsonObject();

			resultJson.addProperty("item", this.result.getRegistryName().toString());

			if (this.count > 1)
			{
				resultJson.addProperty("count", this.count);
			}

			json.add("result", resultJson);
		}

		public ResourceLocation getId()
		{
			return this.id;
		}

		public String getGroup()
		{
			return this.group;
		}

		public Item getResult()
		{
			return this.result;
		}

		public int getCount()
		{
			return this.count;
		}

		public List<String> getPatterns()
		{
			return Lists.newArrayList(this.patterns);
		}

		public Map<Character, Ingredient> getKeys()
		{
			return Maps.newHashMap(this.keys);
		}

		public IRecipeSerializer<?> getType()
		{
			return this.type;
		}

		@Override
		public ResourceLocation getAdvancementId()
		{
			return null;
		}

		@Override
		public JsonObject serializeAdvancement()
		{
			return null;
		}

	}

}
