package gisellevonbingen.mmp.common.crafting;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

import com.google.common.base.Strings;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import net.minecraft.data.IFinishedRecipe;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.crafting.CraftingHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;

public abstract class RecipeBuilder
{
	private final ResourceLocation id;
	private String group;
	private final List<ICondition> conditions;

	public RecipeBuilder(ResourceLocation id)
	{
		this.id = id;
		this.conditions = new ArrayList<>();

		this.setGroup("");
	}

	public ResourceLocation getId()
	{
		return this.id;
	}

	public String getGroup()
	{
		return this.group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}

	public List<ICondition> getConditions()
	{
		return this.conditions;
	}

	public void addCondition(ICondition condition)
	{
		this.conditions.add(condition);
	}

	public abstract static class RecipeResult implements IFinishedRecipe
	{
		private final ResourceLocation id;
		private final String group;
		private final List<ICondition> conditions;

		public RecipeResult(RecipeBuilder builder)
		{
			this(builder, builder.id);
		}

		public RecipeResult(RecipeBuilder builder, ResourceLocation id)
		{
			this.id = id;
			this.group = builder.group;
			this.conditions = new ArrayList<>(builder.conditions);
		}

		@Override
		public void serializeRecipeData(JsonObject json)
		{
			if (!Strings.isNullOrEmpty(this.group))
			{
				json.addProperty("group", this.group);
			}

			if (this.conditions.isEmpty() == false)
			{
				JsonArray conditionsArray = new JsonArray();

				for (ICondition condition : this.conditions)
				{
					conditionsArray.add((JsonElement) CraftingHelper.serialize(condition));
				}

				json.add("conditions", (JsonElement) conditionsArray);
			}

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

		public List<ICondition> getConditions()
		{
			return new ArrayList<>(this.conditions);
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
