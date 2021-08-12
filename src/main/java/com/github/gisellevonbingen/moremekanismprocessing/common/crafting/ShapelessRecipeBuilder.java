package com.github.gisellevonbingen.moremekanismprocessing.common.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapelessRecipeBuilder extends RecipeBuilder
{
	private Item output;
	private int count;
	private final List<Ingredient> ingredients;

	public ShapelessRecipeBuilder(ResourceLocation id)
	{
		super(id);
		this.ingredients = new ArrayList<>();
	}

	public Result getResult()
	{
		return new Result(this);
	}

	public void add(Ingredient ingredient)
	{
		this.ingredients.add(ingredient);
	}

	public void add(Ingredient ingredient, int count)
	{
		for (int i = 0; i < count; ++i)
		{
			this.ingredients.add(ingredient);
		}

	}

	public Item getOutput()
	{
		return this.output;
	}

	public void setOutput(Item output)
	{
		this.output = output;
	}

	public int getCount()
	{
		return this.count;
	}

	public void setCount(int count)
	{
		this.count = count;
	}

	public void setOutput(Item output, int count)
	{
		this.setOutput(output);
		this.setCount(count);
	}

	public List<Ingredient> getIngredients()
	{
		return this.ingredients;
	}

	public IRecipeSerializer<?> getType()
	{
		return (IRecipeSerializer<?>) IRecipeSerializer.SHAPELESS_RECIPE;
	}

	public static class Result extends RecipeResult
	{
		private final Item output;
		private final int count;
		private final ArrayList<Ingredient> ingredients;

		public Result(ShapelessRecipeBuilder builder)
		{
			super(builder);

			this.output = builder.output;
			this.count = builder.count;
			this.ingredients = new ArrayList<>(builder.ingredients);
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

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

		@Override
		public IRecipeSerializer<?> getType()
		{
			return IRecipeSerializer.SHAPELESS_RECIPE;
		}

	}

}
