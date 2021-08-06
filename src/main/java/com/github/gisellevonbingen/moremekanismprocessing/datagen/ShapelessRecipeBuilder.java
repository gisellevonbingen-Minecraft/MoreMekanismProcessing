package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapelessRecipeBuilder
{
	private final ResourceLocation id;
	private String group;
	private Item output;
	private int count;
	private final List<Ingredient> ingredients;

	public ShapelessRecipeBuilder(ResourceLocation id)
	{
		this.id = id;
		this.ingredients = new ArrayList<>();

		this.setGroup("");
	}

	public Result getResult()
	{
		return new Result(this);
	}

	public ShapelessRecipeBuilder add(Ingredient ingredient)
	{
		this.ingredients.add(ingredient);
		return this;
	}

	public ShapelessRecipeBuilder add(Ingredient ingredient, int count)
	{
		for (int i = 0; i < count; ++i)
		{
			this.ingredients.add(ingredient);
		}

		return this;
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public String getGroup()
	{
		return this.group;
	}

	public ShapelessRecipeBuilder setGroup(String group)
	{
		this.group = group;
		return this;
	}

	public Item getOutput()
	{
		return this.output;
	}

	public ShapelessRecipeBuilder setOutput(Item output)
	{
		this.output = output;
		return this;
	}

	public int getCount()
	{
		return this.count;
	}

	public ShapelessRecipeBuilder setCount(int count)
	{
		this.count = count;
		return this;
	}

	public ShapelessRecipeBuilder setOutput(Item output, int count)
	{
		this.setOutput(output);
		this.setCount(count);
		return this;
	}

	public List<Ingredient> getIngredients()
	{
		return this.ingredients;
	}

	public static class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final String group;
		private final Item output;
		private final int count;
		private final ArrayList<Ingredient> ingredients;

		public Result(ShapelessRecipeBuilder builder)
		{
			this.id = builder.id;
			this.group = builder.group;
			this.output = builder.output;
			this.count = builder.count;
			this.ingredients = new ArrayList<>(builder.ingredients);
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			if (!Strings.isNullOrEmpty(this.group))
			{
				json.addProperty("group", this.group);
			}

			JsonArray ingredientsJson = new JsonArray();

			for (Ingredient ingredient : this.ingredients)
			{
				ingredientsJson.add(ingredient.toJson());
			}

			json.add("ingredients", ingredientsJson);

			JsonObject resultJson = new JsonObject();
			resultJson.addProperty("item", this.output.getRegistryName().toString());

			if (this.count > 1)
			{
				resultJson.addProperty("count", this.count);
			}

			json.add("result", resultJson);
		}

		@Override
		public ResourceLocation getId()
		{
			return this.id;
		}

		public String getGroup()
		{
			return this.group;
		}

		public Item getOutput()
		{
			return this.output;
		}

		public int getCount()
		{
			return this.count;
		}

		public ArrayList<Ingredient> getIngredients()
		{
			return Lists.newArrayList(this.ingredients);
		}

		public IRecipeSerializer<?> getType()
		{
			return IRecipeSerializer.SHAPELESS_RECIPE;
		}

		@Nullable
		public JsonObject serializeAdvancement()
		{
			return null;
		}

		@Nullable
		public ResourceLocation getAdvancementId()
		{
			return null;
		}

	}

}
