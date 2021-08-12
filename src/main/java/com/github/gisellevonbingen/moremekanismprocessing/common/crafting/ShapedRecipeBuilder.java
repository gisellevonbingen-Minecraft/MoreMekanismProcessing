package com.github.gisellevonbingen.moremekanismprocessing.common.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapedRecipeBuilder extends RecipeBuilder
{
	private Item output;
	private int count;
	private final List<String> patterns;
	private final Map<Character, Ingredient> keys;

	public ShapedRecipeBuilder(ResourceLocation id)
	{
		super(id);

		this.patterns = new ArrayList<>();
		this.keys = new HashMap<>();

		this.setCount(1);
	}

	public ShapedRecipeBuilder(ResourceLocation id, Item output, int count)
	{
		this(id);

		this.setOutput(output);
		this.setCount(count);
	}

	public Item getOutput()
	{
		return this.output;
	}

	public void setOutput(Item output)
	{
		this.output = output;
	}

	public void setOutput(Item output, int count)
	{
		this.setOutput(output);
		this.setCount(count);
	}

	public int getCount()
	{
		return this.count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public List<String> getPatterns()
	{
		return this.patterns;
	}

	public void addPattern(String pattern)
	{
		this.patterns.add(pattern);
	}

	public void addPattern(Collection<String> patterns)
	{
		this.patterns.addAll(patterns);
	}

	public void addPattern(String... patterns)
	{
		this.addPattern(Lists.newArrayList(patterns));
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

	public Result getResult()
	{
		return new Result(this);
	}

	public static class Result extends RecipeResult
	{
		private final Item result;
		private final int count;
		private final List<String> patterns;
		private final Map<Character, Ingredient> keys;

		public Result(ShapedRecipeBuilder builder)
		{
			super(builder);

			this.result = builder.output;
			this.count = builder.count;
			this.patterns = new ArrayList<>(builder.patterns);
			this.keys = new HashMap<>(builder.keys);
		}

		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

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

		@Override
		public IRecipeSerializer<?> getType()
		{
			return IRecipeSerializer.SHAPED_RECIPE;
		}

	}

}
