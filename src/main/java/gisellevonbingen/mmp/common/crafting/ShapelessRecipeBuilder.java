package gisellevonbingen.mmp.common.crafting;

import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapelessRecipeBuilder extends SingleOutputRecipeBuilder
{
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

	public List<Ingredient> getIngredients()
	{
		return this.ingredients;
	}

	public IRecipeSerializer<?> getType()
	{
		return IRecipeSerializer.SHAPELESS_RECIPE;
	}

	public static class Result extends SingleOutputRecipeResult
	{
		private final ArrayList<Ingredient> ingredients;

		public Result(ShapelessRecipeBuilder builder)
		{
			super(builder);

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
