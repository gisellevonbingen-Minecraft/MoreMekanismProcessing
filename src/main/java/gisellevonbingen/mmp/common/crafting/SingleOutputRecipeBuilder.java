package gisellevonbingen.mmp.common.crafting;

import com.google.gson.JsonObject;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

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

		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

			JsonObject resultJson = new JsonObject();

			ItemStack result = this.result;
			resultJson.addProperty("item", result.getItem().getRegistryName().toString());

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
