package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import com.google.common.base.Strings;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class CookingRecipeBuilder
{
	private final ResourceLocation id;
	private String group;
	private Item output;
	private Ingredient ingredient;
	private float experience;
	private int smeltingTime;
	private int blastingTime;

	public CookingRecipeBuilder(ResourceLocation id)
	{
		this.id = id;

		this.setGroup("");
		this.setCookingTime(200);
	}

	public CookingRecipeBuilder(ResourceLocation id, Item output, int count)
	{
		this(id);

		this.setOutput(output);
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public String getGroup()
	{
		return this.group;
	}

	public CookingRecipeBuilder setGroup(String group)
	{
		this.group = group;
		return this;
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

	public static class Result implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final String group;
		private final Item output;
		private final Ingredient ingredient;
		private final float experience;
		private final int cookingTime;

		private IRecipeSerializer<?> type;

		public Result(CookingRecipeBuilder builder, String name, int cookingTime, IRecipeSerializer<?> type)
		{
			this.id = new ResourceLocation(builder.id.getNamespace(), builder.id.getPath() + "_" + name);
			this.group = builder.group;
			this.output = builder.output;
			this.ingredient = builder.ingredient;
			this.experience = builder.experience;
			this.cookingTime = cookingTime;
			this.type = type;
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			if (!Strings.isNullOrEmpty(this.group))
			{
				json.addProperty("group", this.group);
			}

			json.add("ingredient", this.ingredient.toJson());
			json.addProperty("result", this.output.getRegistryName().toString());
			json.addProperty("experience", this.experience);
			json.addProperty("cookingtime", this.cookingTime);
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

	public static class Smelting implements IFinishedRecipe
	{
		@Override
		public ResourceLocation getAdvancementId()
		{
			return null;
		}

		@Override
		public ResourceLocation getId()
		{
			return null;
		}

		@Override
		public IRecipeSerializer<?> getType()
		{
			return null;
		}

		@Override
		public JsonObject serializeAdvancement()
		{
			return null;
		}

		@Override
		public void serializeRecipeData(JsonObject p0)
		{

		}

	}

	public static class Blasting implements IFinishedRecipe
	{
		@Override
		public ResourceLocation getAdvancementId()
		{
			return null;
		}

		@Override
		public ResourceLocation getId()
		{
			return null;
		}

		@Override
		public IRecipeSerializer<?> getType()
		{
			return null;
		}

		@Override
		public JsonObject serializeAdvancement()
		{
			return null;
		}

		@Override
		public void serializeRecipeData(JsonObject p0)
		{

		}

	}

}
