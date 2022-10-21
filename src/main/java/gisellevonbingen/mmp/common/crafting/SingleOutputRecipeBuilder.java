package gisellevonbingen.mmp.common.crafting;

import com.google.gson.JsonObject;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.ForgeRegistries;

public abstract class SingleOutputRecipeBuilder extends RecipeBuilder
{
	private ItemStack output;

	public SingleOutputRecipeBuilder(ResourceLocation id)
	{
		super(id);
	}

	public ItemStack getOutput()
	{
		return this.output;
	}

	public void setOutput(ItemStack output)
	{
		this.output = output;
	}

	public void setOutput(Item item)
	{
		this.setOutput(item, 1);
	}

	public void setOutput(Item item, int count)
	{
		this.setOutput(new ItemStack(item, count));
	}

	public static abstract class SingleOutputRecipeResult extends RecipeResult
	{
		private final ItemStack result;

		public SingleOutputRecipeResult(SingleOutputRecipeBuilder builder)
		{
			super(builder);
			this.result = builder.getOutput().copy();
		}

		public SingleOutputRecipeResult(SingleOutputRecipeBuilder builder, ResourceLocation id)
		{
			super(builder, id);
			this.result = builder.getOutput().copy();
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

			JsonObject resultJson = new JsonObject();

			ItemStack result = this.result;
			resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result.getItem()).toString());

			if (result.getCount() > 1)
			{
				resultJson.addProperty("count", result.getCount());
			}

			if (result.getTag() != null)
			{
				resultJson.addProperty("nbt", result.getTag().toString());
			}

			json.add("result", resultJson);
		}

		public ItemStack getResult()
		{
			return result.copy();
		}

	}

}
