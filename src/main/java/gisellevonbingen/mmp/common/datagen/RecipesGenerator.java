package gisellevonbingen.mmp.common.datagen;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Consumer;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.crafting.CookingRecipeBuilder;
import gisellevonbingen.mmp.common.crafting.ShapedRecipeBuilder;
import gisellevonbingen.mmp.common.crafting.ShapelessRecipeBuilder;
import gisellevonbingen.mmp.common.crafting.conditions.ProcessingLevelCondition;
import gisellevonbingen.mmp.common.material.MaterialResultShape;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import gisellevonbingen.mmp.common.slurry.MoreMekanismProcessingSlurries;
import gisellevonbingen.mmp.common.slurry.MoreMekanismProcessingSlurry;
import gisellevonbingen.mmp.common.slurry.MoreMekanismProcessingSlurryBuilder;
import gisellevonbingen.mmp.common.util.ThreeFunction;
import mekanism.api.chemical.gas.Gas;
import mekanism.api.chemical.gas.GasStack;
import mekanism.api.chemical.slurry.Slurry;
import mekanism.api.chemical.slurry.SlurryStack;
import mekanism.api.datagen.recipe.builder.ChemicalCrystallizerRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ChemicalDissolutionRecipeBuilder;
import mekanism.api.datagen.recipe.builder.FluidSlurryToSlurryRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackChemicalToItemStackRecipeBuilder;
import mekanism.api.datagen.recipe.builder.ItemStackToItemStackRecipeBuilder;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient.GasStackIngredient;
import mekanism.api.recipes.ingredients.ChemicalStackIngredient.SlurryStackIngredient;
import mekanism.api.recipes.ingredients.FluidStackIngredient;
import mekanism.api.recipes.ingredients.ItemStackIngredient;
import mekanism.api.recipes.ingredients.creator.IngredientCreatorAccess;
import mekanism.common.registration.impl.SlurryRegistryObject;
import mekanism.common.registries.MekanismGases;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.NotCondition;
import net.minecraftforge.common.crafting.conditions.TagEmptyCondition;
import net.minecraftforge.registries.ForgeRegistries;

public class RecipesGenerator extends RecipeProvider
{
	public RecipesGenerator(PackOutput output)
	{
		super(output);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer)
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
		private Consumer<FinishedRecipe> consumer;
		private List<ICondition> conditions;

		public OreRecipesGenerator(MaterialType materialType, Consumer<FinishedRecipe> consumer)
		{
			this.materialType = materialType;
			this.consumer = consumer;
			this.conditions = new ArrayList<>();
		}

		public ICondition createConditionHasState(MaterialState state)
		{
			return new NotCondition(new TagEmptyCondition(state.getStateTagName(this.materialType)));
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

		public void applyConditionWithState(Consumer<ICondition> consumer, MaterialState state)
		{
			if (state.hasOwnItem() == false)
			{
				consumer.accept(this.createConditionHasState(state));
			}

			this.applyCondition(consumer);
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
			this.applyProcssingLevelCondition(5, () -> this.buildProcessingLevel5());
			this.applyProcssingLevelCondition(4, () -> this.buildProcessingLevel4());
			this.applyProcssingLevelCondition(3, () -> this.buildProcessingLevel3());
			this.applyProcssingLevelCondition(2, () -> this.buildProcessingLevel2());

			this.buildOthers();
		}

		public void buildProcessingLevel5()
		{
			if (this.canProcess(MaterialState.CRYSTAL) == true)
			{
				SlurryRegistryObject<Slurry, Slurry> slurryRegistry = MoreMekanismProcessingSlurries.getSlurryRegistry(this.materialType);
				Slurry dirtySlurry = slurryRegistry.getDirtySlurry();
				Slurry cleanSlurry = slurryRegistry.getCleanSlurry();
				FluidStackIngredient water = IngredientCreatorAccess.fluid().from(Fluids.WATER, 5);

				if (this.canProcess(MaterialState.ORE) == true)
				{
					GasStackIngredient sulfuricAcid1 = IngredientCreatorAccess.gas().from(MekanismGases.SULFURIC_ACID.get(), 1);
					this.buildChemicalDissolution(MaterialState.ORE, 1, dirtySlurry, 1000, sulfuricAcid1);
				}

				if (this.canProcess(MaterialState.RAW_ITEM) == true)
				{
					GasStackIngredient sulfuricAcid1 = IngredientCreatorAccess.gas().from(MekanismGases.SULFURIC_ACID.get(), 1);
					this.buildChemicalDissolution(MaterialState.RAW_ITEM, 3, dirtySlurry, 2000, sulfuricAcid1);

					GasStackIngredient sulfuricAcid2 = IngredientCreatorAccess.gas().from(MekanismGases.SULFURIC_ACID.get(), 2);
					this.buildChemicalDissolution(MaterialState.RAW_STORAGE_BLOCKS, 1, dirtySlurry, 6000, sulfuricAcid2);
				}

				this.buildChemicalWashing(water, dirtySlurry, cleanSlurry);
				this.buildChemicalCrystallizing(IngredientCreatorAccess.slurry().from(cleanSlurry, 200), MaterialState.CRYSTAL, 1);

				if (this.canProcess(MaterialState.SHARD) == true)
				{
					GasStackIngredient hydrogenChloride = IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.CRYSTAL, 1, MaterialState.SHARD, 1, hydrogenChloride, ItemStackChemicalToItemStackRecipeBuilder::injecting);
				}

			}

		}

		public void buildProcessingLevel4()
		{
			if (this.canProcess(MaterialState.SHARD) == true)
			{
				if (this.canProcess(MaterialState.ORE) == true)
				{
					GasStackIngredient hydrogenChloride = IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.ORE, 1, MaterialState.SHARD, 4, hydrogenChloride, ItemStackChemicalToItemStackRecipeBuilder::injecting);
				}

				if (this.canProcess(MaterialState.RAW_ITEM) == true)
				{
					GasStackIngredient hydrogenChloride1 = IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.RAW_ITEM, 3, MaterialState.SHARD, 8, hydrogenChloride1, ItemStackChemicalToItemStackRecipeBuilder::injecting);

					GasStackIngredient hydrogenChloride2 = IngredientCreatorAccess.gas().from(MekanismGases.HYDROGEN_CHLORIDE.get(), 2);
					this.buildItemStackGasToItemStack(MaterialState.RAW_STORAGE_BLOCKS, 1, MaterialState.SHARD, 24, hydrogenChloride2, ItemStackChemicalToItemStackRecipeBuilder::injecting);
				}

				if (this.canProcess(MaterialState.CLUMP) == true)
				{
					GasStackIngredient oxygen = IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.SHARD, 1, MaterialState.CLUMP, 1, oxygen, ItemStackChemicalToItemStackRecipeBuilder::purifying);
				}

			}

		}

		public void buildProcessingLevel3()
		{
			if (this.canProcess(MaterialState.CLUMP) == true)
			{
				if (this.canProcess(MaterialState.ORE) == true)
				{
					GasStackIngredient oxygen = IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.ORE, 1, MaterialState.CLUMP, 3, oxygen, ItemStackChemicalToItemStackRecipeBuilder::purifying);
				}

				if (this.canProcess(MaterialState.RAW_ITEM) == true)
				{
					GasStackIngredient oxygen1 = IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN.get(), 1);
					this.buildItemStackGasToItemStack(MaterialState.RAW_ITEM, 1, MaterialState.CLUMP, 2, oxygen1, ItemStackChemicalToItemStackRecipeBuilder::purifying);

					GasStackIngredient oxygen2 = IngredientCreatorAccess.gas().from(MekanismGases.OXYGEN.get(), 2);
					this.buildItemStackGasToItemStack(MaterialState.RAW_STORAGE_BLOCKS, 1, MaterialState.CLUMP, 18, oxygen2, ItemStackChemicalToItemStackRecipeBuilder::purifying);
				}

				if (this.canProcess(MaterialState.DIRTY_DUST) == true)
				{
					this.buildItemToItemStack(MaterialState.CLUMP, 1, MaterialState.DIRTY_DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);

					if (this.canProcess(MaterialState.DUST) == true)
					{
						this.buildItemToItemStack(MaterialState.DIRTY_DUST, 1, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::enriching);
					}

				}

			}

		}

		public void buildProcessingLevel2()
		{
			if (this.materialType.isRespectMekanism() == true)
			{
				return;
			}

			if (this.canProcess(MaterialState.ORE) == true)
			{
				if (this.materialType.getResultShape() == MaterialResultShape.GEM)
				{
					if (this.canProcess(MaterialState.GEM) == true)
					{
						this.buildItemToItemStack(MaterialState.ORE, 1, MaterialState.GEM, 2, ItemStackToItemStackRecipeBuilder::enriching);
					}

				}
				else
				{
					if (this.canProcess(MaterialState.DUST) == true)
					{
						this.buildItemToItemStack(MaterialState.ORE, 1, MaterialState.DUST, 2, ItemStackToItemStackRecipeBuilder::enriching);
					}

				}

			}

			if (this.canProcess(MaterialState.RAW_ITEM) == true)
			{
				this.buildItemToItemStack(MaterialState.RAW_ITEM, 3, MaterialState.DUST, 4, ItemStackToItemStackRecipeBuilder::enriching);
			}

			if (this.canProcess(MaterialState.RAW_STORAGE_BLOCKS) == true)
			{
				this.buildItemToItemStack(MaterialState.RAW_STORAGE_BLOCKS, 1, MaterialState.DUST, 12, ItemStackToItemStackRecipeBuilder::enriching);
			}

			if (this.canProcess(MaterialState.DUST) == true)
			{
				if (this.canProcess(MaterialState.INGOT) == true)
				{
					this.buildCook(MaterialState.DUST, MaterialState.INGOT);
					this.buildItemToItemStack(MaterialState.INGOT, 1, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
				}

				if (this.canProcess(MaterialState.GEM) == true)
				{
					this.buildItemToItemStack(MaterialState.DUST, 1, MaterialState.GEM, 1, ItemStackToItemStackRecipeBuilder::enriching);
					this.buildItemToItemStack(MaterialState.GEM, 1, MaterialState.DUST, 1, ItemStackToItemStackRecipeBuilder::crushing);
				}

			}

		}

		public void buildOthers()
		{
			if (this.materialType.isRespectMekanism() == true)
			{
				return;
			}

			if (this.canProcess(MaterialState.INGOT, MaterialState.NUGGET) == true)
			{
				this.buildNuggetFromIngot();
				this.buildIngotFromNugget();
			}

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
			builder.build(this.consumer, this.getRecipeName(stateOutput, this.from(MoreMekanismProcessingSlurry.SLURRY)));
		}

		public void buildChemicalWashing(FluidStackIngredient fluidInput, Slurry slurryInput, Slurry slurryOutput)
		{
			SlurryStackIngredient slurryStackInput = IngredientCreatorAccess.slurry().from(slurryInput, 1);
			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, 1);
			FluidSlurryToSlurryRecipeBuilder builder = FluidSlurryToSlurryRecipeBuilder.washing(fluidInput, slurryStackInput, slurryStackOutput);

			this.applyCondition(builder::addCondition);
			builder.build(this.consumer, this.getRecipeName(MoreMekanismProcessingSlurry.SLURRY, MoreMekanismProcessingSlurryBuilder.CLEAN));
		}

		public void buildChemicalDissolution(MaterialState stateInput, int inputCount, Slurry slurryOutput, int outputAmount, GasStackIngredient gasInput)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput, inputCount);
			SlurryStack slurryStackOutput = new SlurryStack(slurryOutput, outputAmount);
			ChemicalDissolutionRecipeBuilder builder = ChemicalDissolutionRecipeBuilder.dissolution(itemInput, gasInput, slurryStackOutput);

			this.applyConditionWithState(builder::addCondition, stateInput);
			builder.build(this.consumer, this.getRecipeName(MoreMekanismProcessingSlurry.SLURRY, MoreMekanismProcessingSlurryBuilder.DIRTY + "/" + stateInput.getBaseName()));
		}

		public void buildItemStackGasToItemStack(MaterialState stateInput, int inputCount, MaterialState stateOutput, int outputCount, GasStackIngredient gasInput, ThreeFunction<ItemStackIngredient, GasStackIngredient, ItemStack, ItemStackChemicalToItemStackRecipeBuilder<Gas, GasStack, GasStackIngredient>> function)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput, inputCount);
			ItemStack output = stateOutput.getItemStack(this.materialType, outputCount);
			ItemStackChemicalToItemStackRecipeBuilder<Gas, GasStack, GasStackIngredient> builder = function.apply(itemInput, gasInput, output);

			this.applyConditionWithState(builder::addCondition, stateInput);
			builder.build(this.consumer, this.getRecipeName(stateOutput, this.from(stateInput)));
		}

		public void buildItemToItemStack(MaterialState stateInput, int inputCount, MaterialState stateOutput, int outputCount, BiFunction<ItemStackIngredient, ItemStack, ItemStackToItemStackRecipeBuilder> function)
		{
			ItemStackIngredient itemInput = this.getTaggedItemStackIngredient(stateInput, inputCount);
			ItemStack output = stateOutput.getItemStack(this.materialType, outputCount);
			ItemStackToItemStackRecipeBuilder builder = function.apply(itemInput, output);

			this.applyConditionWithState(builder::addCondition, stateInput);
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
			this.applyConditionWithState(builder::addCondition, stateInput);

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

			this.applyConditionWithState(builder::addCondition, stateInput);
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

			this.applyConditionWithState(builder::addCondition, stateInput);
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
			TagKey<Item> tag = this.getTag(materialState);
			return this.getTaggedItemStackIngredient(tag);
		}

		public ItemStackIngredient getTaggedItemStackIngredient(MaterialState materialState, int amount)
		{
			TagKey<Item> tag = this.getTag(materialState);
			return this.getTaggedItemStackIngredient(tag, amount);
		}

		public ItemStackIngredient getTaggedItemStackIngredient(TagKey<Item> tag)
		{
			return this.getTaggedItemStackIngredient(tag, 1);
		}

		public ItemStackIngredient getTaggedItemStackIngredient(TagKey<Item> tag, int amount)
		{
			return IngredientCreatorAccess.item().from(tag, amount);
		}

		public TagKey<Item> getTag(MaterialState materialState)
		{
			return ItemTags.create(materialState.getStateTagName(this.materialType));
		}

		public String getGroup(MaterialState stateOutput)
		{
			return ForgeRegistries.ITEMS.getKey(stateOutput.getItem(this.materialType)).toString();
		}

		public MaterialType getMaterialType()
		{
			return this.materialType;
		}

		public Consumer<FinishedRecipe> getConsumer()
		{
			return this.consumer;
		}

	}

}
