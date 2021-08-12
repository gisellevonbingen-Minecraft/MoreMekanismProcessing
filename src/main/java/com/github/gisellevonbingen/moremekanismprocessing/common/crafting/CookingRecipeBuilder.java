package com.github.gisellevonbingen.moremekanismprocessing.common.crafting;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class CookingRecipeBuilder extends RecipeBuilder
{
	private Item output;
	private Ingredient ingredient;
	private float experience;
	private int smeltingTime;
	private int blastingTime;

	public CookingRecipeBuilder(ResourceLocation id)
	{
		super(id);

		this.setCookingTime(200);
	}

	public CookingRecipeBuilder(ResourceLocation id, Item output, int count)
	{
		this(id);

		this.setOutput(output);
	}

	public Item getOutput()
	{
		return this.output;
	}

	public CookingRecipeBuilder setOutput(Item output)
	{
		this.output = output;
		return this;
	}

	public Ingredient getIngredient()
	{
		return this.ingredient;
	}

	public CookingRecipeBuilder setIngredient(Ingredient ingredient)
	{
		this.ingredient = ingredient;
		return this;
	}

	public float getExperience()
	{
		return this.experience;
	}

	public CookingRecipeBuilder setExperience(float experience)
	{
		this.experience = experience;
		return this;
	}

	public int getSmeltingTime()
	{
		return this.smeltingTime;
	}

	public CookingRecipeBuilder setSmeltingTime(int smeltingTime)
	{
		this.smeltingTime = smeltingTime;
		return this;
	}

	public int getBlastingTime()
	{
		return this.blastingTime;
	}

	public CookingRecipeBuilder setBlastingTime(int blastingTime)
	{
		this.blastingTime = blastingTime;
		return this;
	}

	public CookingRecipeBuilder setCookingTime(int cookingTime)
	{
		this.setSmeltingTime(cookingTime);
		this.setBlastingTime(cookingTime / 2);
		return this;
	}

	public Result getSmelting()
	{
		return new Result(this, "smelting", this.smeltingTime, IRecipeSerializer.SMELTING_RECIPE);
	}

	public Result getBlasting()
	{
		return new Result(this, "blasting", this.blastingTime, IRecipeSerializer.BLASTING_RECIPE);
	}

	public static class Result extends RecipeResult
	{
		private final Item output;
		private final Ingredient ingredient;
		private final float experience;
		private final int cookingTime;

		private IRecipeSerializer<?> type;

		public Result(CookingRecipeBuilder builder, String name, int cookingTime, IRecipeSerializer<?> type)
		{
			super(builder, new ResourceLocation(builder.getId().getNamespace(), builder.getId().getPath() + "_" + name));

			this.output = builder.output;
			this.ingredient = builder.ingredient;
			this.experience = builder.experience;
			this.cookingTime = cookingTime;
			this.type = type;
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

			json.add("ingredient", this.ingredient.toJson());
			json.addProperty("result", this.output.getRegistryName().toString());
			json.addProperty("experience", this.experience);
			json.addProperty("cookingtime", this.cookingTime);
		}

		public Item getOutput()
		{
			return this.output;
		}

		public Ingredient getIngredient()
		{
			return this.ingredient;
		}

		public float getExperience()
		{
			return this.experience;
		}

		public int getCookingTime()
		{
			return this.cookingTime;
		}

		@Override
		public IRecipeSerializer<?> getType()
		{
			return this.type;
		}

	}

}
