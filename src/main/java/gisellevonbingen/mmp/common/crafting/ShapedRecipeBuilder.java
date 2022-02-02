package gisellevonbingen.mmp.common.crafting;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;

public class ShapedRecipeBuilder extends SingleOutputRecipeBuilder
{
	private final List<String> patterns;
	private final Map<Character, Ingredient> keys;

	public ShapedRecipeBuilder(ResourceLocation id)
	{
		super(id);

		this.patterns = new ArrayList<>();
		this.keys = new HashMap<>();
	}

	public List<String> getPatterns()
	{
		return this.patterns;
	}

	public void addPattern(String pattern)
	{
		this.patterns.add(pattern);
	}

	public void addPattern(Collection<String> patterns)
	{
		this.patterns.addAll(patterns);
	}

	public void addPattern(String... patterns)
	{
		this.addPattern(Lists.newArrayList(patterns));
	}

	public Map<Character, Ingredient> getKey()
	{
		return this.keys;
	}

	public ShapedRecipeBuilder addKey(Character key, Ingredient ingredient)
	{
		this.keys.put(key, ingredient);
		return this;
	}

	public Result getResult()
	{
		return new Result(this);
	}

	public static class Result extends SingleOutputRecipeResult
	{
		private final List<String> patterns;
		private final Map<Character, Ingredient> keys;

		public Result(ShapedRecipeBuilder builder)
		{
			super(builder);

			this.patterns = new ArrayList<>(builder.patterns);
			this.keys = new HashMap<>(builder.keys);
		}

		public void serializeRecipeData(JsonObject json)
		{
			super.serializeRecipeData(json);

			JsonArray patternJson = new JsonArray();

			for (String pattern : this.patterns)
			{
				patternJson.add(pattern);
			}

			json.add("pattern", patternJson);

			JsonObject keyJson = new JsonObject();

			for (Map.Entry<Character, Ingredient> entry : this.keys.entrySet())
			{
				keyJson.add(String.valueOf(entry.getKey()), entry.getValue().toJson());
			}

			json.add("key", keyJson);
		}

		public List<String> getPatterns()
		{
			return Lists.newArrayList(this.patterns);
		}

		public Map<Character, Ingredient> getKeys()
		{
			return Maps.newHashMap(this.keys);
		}

		@Override
		public IRecipeSerializer<?> getType()
		{
			return IRecipeSerializer.SHAPED_RECIPE;
		}

	}

}
