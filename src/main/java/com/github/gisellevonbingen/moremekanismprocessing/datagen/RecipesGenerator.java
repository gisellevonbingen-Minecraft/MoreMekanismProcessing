package com.github.gisellevonbingen.moremekanismprocessing.datagen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.CookingRecipeBuilder;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.ShapedRecipeBuilder;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.ShapelessRecipeBuilder;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions.OverrideRespectCondition;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions.ProcessingLevelCondition;
import com.github.gisellevonbingen.moremekanismprocessing.common.crafting.conditions.TagNotEmptyCondition;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialResultShape;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;
import com.github.gisellevonbingen.moremekanismprocessing.common.slurry.MoreMekanismProcessingSlurries;
import com.github.gisellevonbingen.moremekanismprocessing.common.slurry.MoreMekanismProcessingSlurry;
import com.github.gisellevonbingen.moremekanismprocessing.common.slurry.MoreMekanismProcessingSlurryBuilder;
import com.github.gisellevonbingen.moremekanismprocessing.function.ThreeFunction;

import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ChemicalDissolutionRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidSlurryToSlurryRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackGasToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.inputs.FluidStackIngredient;
import mekanism.api.recipes.inputs.ItemStackIngredient;
import mekanism.api.recipes.inputs.chemical.GasStackIngredient;
import mekanism.api.recipes.inputs.chemical.SlurryStackIngredient;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.IFinishedRecipe;
import net.minecraft.data.RecipeProvider;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.fluids.FluidStack;

public class RecipesGenerator extends RecipeProvider
{
	public RecipesGenerator(DataGenerator generator)
	{
		super(generator);
	}

	@Override
	protected void buildShapelessRecipes(Consumer<IFinishedRecipe> consumer)
	{
		for (MaterialType materialType : MaterialType.values())
		{
			new OreRecipesGenerator(materialType, consumer).build();
		}

	}

	public ResourceLocation getRecipeName(String output, String name)
	{
		return MoreMekanismProcessing.rl((output + "/" + name).toLowerCase());
	}

	public static class OreRecipesGenerator
	{
		private MaterialType materialType;
		private Consumer<IFinishedRecipe> consumer;
		private List<ICondition> conditions;

		public OreRecipesGenerator(MaterialType materialType, Consumer<IFinishedRecipe> consumer)
		{
			this.materialType = materialType;
			this.consumer = consumer;
			this.conditions = new ArrayList<>();
		}

		public ICondition createConditionHasOre()
		{
			return new TagNotEmptyCondition(MaterialState.ORE.getStateTagName(this.materialType));
		}

		public void applyProcssingLevelCondition(int processingLevel, Runnable runnable)
		{
			this.applyCondition(new ProcessingLevelCondition(this.materialType, processingLevel), runnable);
		}

		public void applyCondition(ICondition condition, Runnable runnable)
		{
			try
			{
				this.conditions.add(condition);
				runnable.run();
			}
			finally
			{
				this.conditions.remove(condition);
			}

		}

		public void applyCondition(Consumer<ICondition> consumer)
		{
			for (ICondition condition : this.conditions)
			{
				consumer.accept(condition);
			}

		}

		public void build()
		{
			if (this.materialType.isRespectMekanism() == true)
			{
				this.conditions.add(new OverrideRespectCondition(this.materialType));
			}

			this.applyProcssingLevelCondition(5, () -> this.buildProcessingLevel5());
			this.applyProcssingLevelCondition(4, () -> this.buildProcessingLevel4());
			this.applyProcssingLevelCondition(3, () -> this.buildProcessingLevel3());
			this.applyProcssingLevelCondition(2, () -> this.buildProcessingLevel2());

			this.buildOthers();
		}

		public void buildProcessingLevel5()
		{
			GasStackIngredient hydrogenChloride = GasStackIngredient.from(new GasStack(MekanismGases.HYDROGEN_CHLORIDE.get(), 1));
			GasStackIngredient sulfuricAcid = GasStackIngredient.from(new GasStack(MekanismGases.SULFURIC_ACID.get(), 1));
			FluidStackIngredient water = FluidStackIngredient.from(new FluidStack(Fluids.WATER, 5));

			if (this.canProcess(MaterialState.ORE, MaterialState.CRYSTAL) == true)
			{
				SlurryRegistryObject<Slurry, Slurry> slurryRegistry = MoreMekanismProcessingSlurries.getSlurryRegistry(this.materialType);
				Slurry dirtySlurry = slurryRegistry.getDirtySlurry();
				Slurry cleanSlurry = slurryRegistry.getCleanSlurry();

				this.buildChemicalDissolution(MaterialState.ORE, dirtySlurry, 1000, sulfuricAcid);
				this.buildChemicalWashing(water, dirtySlurry, cleanSlurry);
				this.buildChemicalCrystallizing(SlurryStackIngredient.from(new SlurryStack(cleanSlurry, 200)), MaterialState.CRYSTAL, 1);
			}

			if (this.canProcess(MaterialState.CRYSTAL, MaterialState.SHARD) == true)
			{
				this.buildItemStackGasToItemStack(MaterialState.CRYSTAL, MaterialState.SHARD, 1, hydrogenChloride, ItemStackGasToItemStackRecipeBuilder::injecting);
			}

		}

		public void buildProcessingLevel4()
		{
			GasStackIngredient hydrogenChloride = GasStackIngredient.from(new GasStack(MekanismGases.HYDROGEN_CHLORIDE.get(), 1));
			GasStackIngredient oxygen = GasStackIngredient.from(new GasStack(MekanismGases.OXYGEN.get(), 1));

			if (this.canProcess(MaterialState.ORE, MaterialState.SHARD) == true)
			{
				this.buildItemStackGasToItemStack(MaterialState.ORE, MaterialState.SHARD, 4, hydrogenChloride, ItemStackGasToItemStackRecipeBuilder::injecting);
			}

			if (this.canProcess(MaterialState.SHARD, MaterialState.CLUMP) == true)
			{
				this.buildItemStackGasToItemStack(MaterialState.SHARD, MaterialState.CLUMP, 1, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			}

		}

		public void buildProcessingLevel3()
		{
			GasStackIngredient oxygen = GasStackIngredient.from(new GasStack(MekanismGases.OXYGEN.get(), 1));

			if (this.canProcess(MaterialState.ORE, MaterialState.CLUMP) == true)
			{
				this.buildItemStackGasToItemStack(MaterialState.ORE, MaterialState.CLUMP, 3, oxygen, ItemStackGasToItemStackRecipeBuilder::purifying);
			}

			if (this.canProcess(MaterialState.CLUMP, MaterialState.DIRTY_DUST) == true)
			{
				this.buildItemToItemStack(MaterialState.CLUMP, MaterialState.DIRTY_DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
			}

			if (this.canProcess(MaterialState.DIRTY_DUST, MaterialState.DUST) == true)
			{
				this.buildItemToItemStack(MaterialState.DIRTY_DUST, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::enriching);
			}

		}

		public void buildProcessingLevel2()
		{
			if (this.materialType.isRespectMekanism() == true)
			{
				return;
			}

			if (this.materialType.getResultShape() == MaterialResultShape.GEM)
			{
				if (this.canProcess(MaterialState.ORE, MaterialState.GEM) == true)
				{
					this.buildItemToItemStack(MaterialState.ORE, MaterialState.GEM, 2, ItemStackToItemStackRecipeBuilder::enriching);
				}

			}
			else
			{
				if (this.canProcess(MaterialState.ORE, MaterialState.DUST) == true)
				{
					this.buildItemToItemStack(MaterialState.ORE, MaterialState.DUST, 2, ItemStackToItemStackRecipeBuilder::enriching);
				}

			}

			if (this.canProcess(MaterialState.DUST, MaterialState.INGOT) == true)
			{
				this.buildCook(MaterialState.DUST, MaterialState.INGOT);
				this.buildItemToItemStack(MaterialState.INGOT, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
			}

			if (this.canProcess(MaterialState.DUST, MaterialState.GEM) == true)
			{
				this.buildItemToItemStack(MaterialState.DUST, MaterialState.GEM, 1, ItemStackToItemStackRecipeBuilder::enriching);
				this.buildItemToItemStack(MaterialState.GEM, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
			}

		}

		public void buildOthers()
		{
			if (this.canProcess(MaterialState.INGOT, MaterialState.NUGGET) == true)
			{
				this.buildNuggetFromIngot();
				this.buildIngotFromNugget();
			}

		}

		public void buildCrushingCook(MaterialState stateOutput)
		{
			this.buildCook(MaterialState.DUST, stateOutput);
			this.buildItemToItemStack(stateOutput, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
		}

		public boolean canProcess(MaterialState... states)
		{
			return this.materialType.getResultShape().canProcess(Arrays.asList(states));
		}

		public String from(String name)
		{
			return "from_" + name;
		}

		public String from(String name, String method)
		{
			return this.from(name) + "_" + method;
		}

		public String from(MaterialState materialState)
		{
			return this.from(materialState.getBaseName());
		}

		public String from(MaterialState materialState, String method)
		{
			return this.from(materialState.getBaseName(), method);
		}

		public void buildChemicalCrystallizing(SlurryStackIngredient slurryInput, MaterialState stateOutput, int outputCount)
		{
			ItemStack output = stateOutput.getItemStack(this.materialType, outputCount);
			ChemicalCrystallizerRecipeBuilder builder = ChemicalCrystallizerRecipeBuilder.crystallizing(slurryInput, output);
			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(stateOutput, this.from("slurry")));
		}

		public void buildChemicalWashing(FluidStackIngredient fluidInput, Slurry slurryInput, Slurry slurryOutput)
		{
			SlurryStackIngredient slurryStackInput = SlurryStackIngredient.from(new SlurryStack(slurryInput, 1));
			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, 1);
			FluidSlurryToSlurryRecipeBuilder builder = FluidSlurryToSlurryRecipeBuilder.washing(fluidInput, slurryStackInput, slurryStackOutput);
			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(MoreMekanismProcessingSlurry.SLURRY, MoreMekanismProcessingSlurryBuilder.CLEAN));
		}

		public void buildChemicalDissolution(MaterialState stateInput, Slurry slurryOutput, int outputAmount, GasStackIngredient gasInput)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput);
			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, outputAmount);
			ChemicalDissolutionRecipeBuilder builder = ChemicalDissolutionRecipeBuilder.dissolution(itemInput, gasInput, slurryStackOutput);

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(MoreMekanismProcessingSlurry.SLURRY, MoreMekanismProcessingSlurryBuilder.DIRTY));
		}

		public void buildItemStackGasToItemStack(MaterialState stateInput, MaterialState stateOutput, int outputCount, GasStackIngredient gasInput, ThreeFunction<ItemStackIngredient, GasStackIngredient, ItemStack, ItemStackGasToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput);
			ItemStack output = stateOutput.getItemStack(this.materialType, outputCount);
			ItemStackGasToItemStackRecipeBuilder builder = function.apply(itemInput, gasInput, output);

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(stateOutput, this.from(stateInput)));
		}

		public void buildItemToItemStack(MaterialState stateInput, MaterialState stateOutput, int outputCount, BiFunction<ItemStackIngredient, ItemStack, ItemStackToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput);
			ItemStack output = stateOutput.getItemStack(this.materialType, outputCount);
			ItemStackToItemStackRecipeBuilder builder = function.apply(itemInput, output);

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(stateOutput, this.from(stateInput)));
		}

		public void buildCook(MaterialState stateInput, MaterialState stateOutput)
		{
			Ingredient itemInput = this.getTaggedIngredient(stateInput);
			Item output = stateOutput.getItem(this.materialType);
			ResourceLocation recipeName = this.getRecipeName(stateOutput, this.from(stateInput));
			CookingRecipeBuilder builder = new CookingRecipeBuilder(recipeName);
			builder.setOutput(output);
			builder.setIngredient(itemInput);
			builder.setExperience(0.3F);

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			this.consumer.accept(builder.getSmelting());
			this.consumer.accept(builder.getBlasting());
		}

		public void buildIngotFromNugget()
		{
			MaterialState stateInput = MaterialState.NUGGET;
			MaterialState stateOutput = MaterialState.INGOT;
			Item itemOutput = stateOutput.getItem(this.materialType);

			ResourceLocation recipeName = this.getRecipeName(stateOutput, this.from(stateInput));
			ShapedRecipeBuilder builder = new ShapedRecipeBuilder(recipeName);
			builder.setGroup(this.getGroup(stateOutput));
			builder.setOutput(itemOutput);
			builder.addPattern("###", "#*#", "###");
			builder.addKey('#', this.getTaggedIngredient(stateInput));
			builder.addKey('*', this.getExcatIngredient(stateInput));

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			this.consumer.accept(builder.getResult());
		}

		public void buildNuggetFromIngot()
		{
			MaterialState stateInput = MaterialState.INGOT;
			MaterialState stateOutput = MaterialState.NUGGET;
			Item itemOutput = stateOutput.getItem(this.materialType);

			ResourceLocation recipeName = this.getRecipeName(stateOutput, this.from(stateInput));
			ShapelessRecipeBuilder builder = new ShapelessRecipeBuilder(recipeName);
			builder.setGroup(this.getGroup(stateOutput));
			builder.setOutput(itemOutput, 9);
			builder.add(this.getExcatIngredient(stateInput));

			if (stateInput == MaterialState.ORE)
			{
				builder.addCondition(this.createConditionHasOre());
			}

			this.applyCondition(builder::addCondition);
			this.consumer.accept(builder.getResult());
		}

		public ResourceLocation getRecipeName(MaterialState stateOutput, String name)
		{
			return this.getRecipeName(stateOutput.getBaseName(), name);
		}

		public ResourceLocation getRecipeName(String stateOutput, String name)
		{
			return MoreMekanismProcessing.rl(("processing/" + this.materialType.getBaseName() + "/" + stateOutput + "/" + name).toLowerCase());
		}

		public Ingredient getExcatIngredient(MaterialState materialState)
		{
			return Ingredient.of(materialState.getItem(this.materialType));
		}

		public Ingredient getTaggedIngredient(MaterialState materialState)
		{
			return Ingredient.of(this.getTag(materialState));
		}

		public ItemStackIngredient getTaggedItemStackIngredient(MaterialState materialState)
		{
			return this.getTaggedItemStackIngredient(materialState, 1);
		}

		public ItemStackIngredient getTaggedItemStackIngredient(MaterialState materialState, int amount)
		{
			return ItemStackIngredient.from(this.getTag(materialState), amount);
		}

		public INamedTag<Item> getTag(MaterialState materialState)
		{
			return ItemTags.bind(materialState.getStateTagName(this.materialType).toString());
		}

		public String getGroup(MaterialState stateOutput)
		{
			return stateOutput.getItem(this.materialType).getRegistryName().toString();
		}

		public MaterialType getMaterialType()
		{
			return this.materialType;
		}

		public Consumer<IFinishedRecipe> getConsumer()
		{
			return this.consumer;
		}

	}

}
