package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

import net.minecraft.advancements.Advancement.Builder;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.ShapelessRecipeBuilder;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class RecipeHelper
{
	public static final int DefaultCookTime = 200;

	public static ResourceLocation appendName(ResourceLocation parent, String name)
	{
		return new ResourceLocation(parent.getNamespace(), parent.getPath() + name);
	}

	public static ShapelessRecipeBuilder.Result shapeless(ResourceLocation recipeName, Item itemOutput, int outputCount, List<Ingredient> ingredients, String group)
	{
		return new ShapelessRecipeBuilder.Result(recipeName, itemOutput, outputCount, group, ingredients, Builder.advancement(), recipeName);
	}

	public static void acceptAll(Consumer<IFinishedRecipe> consumer, IFinishedRecipe recipe)
	{
		consumer.accept(recipe);
	}

	public static void acceptAll(Consumer<IFinishedRecipe> consumer, Collection<? extends IFinishedRecipe> recipes)
	{
		for (IFinishedRecipe recipe : recipes)
		{
			consumer.accept(recipe);
		}

	}

	private RecipeHelper()
	{

	}

}
