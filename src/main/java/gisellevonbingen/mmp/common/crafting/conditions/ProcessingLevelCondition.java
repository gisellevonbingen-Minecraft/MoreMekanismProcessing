package gisellevonbingen.mmp.common.crafting.conditions;

import java.util.Arrays;

import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonObject;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.config.MoreMekanismProcessingConfigs;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraftforge.common.crafting.conditions.ICondition;
import net.minecraftforge.common.crafting.conditions.IConditionSerializer;

public class ProcessingLevelCondition implements ICondition
{
	private static final ResourceLocation NAME = MoreMekanismProcessing.rl("processing_level");
	private final MaterialType materialType;
	private final int requireLevel;

	public ProcessingLevelCondition(MaterialType materialType, int requireLevel)
	{
		this.materialType = materialType;
		this.requireLevel = requireLevel;
	}

	@Override
	public ResourceLocation getID()
	{
		return NAME;
	}

	@Override
	public boolean test(IContext context)
	{
		if (this.materialType == null)
		{
			return true;
		}

		int level = (int) MoreMekanismProcessingConfigs.Common.processingLevels.get(this.materialType).get();
		return level >= this.requireLevel;
	}

	@Override
	public String toString()
	{
		return "processing_level(\"" + this.materialType.getBaseName() + "\" require " + this.requireLevel + ")";
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public int getRequireLevel()
	{
		return this.requireLevel;
	}

	public static class Serializer implements IConditionSerializer<ProcessingLevelCondition>
	{
		public static final Serializer INSTANCE = new Serializer();

		@Override
		public void write(JsonObject json, ProcessingLevelCondition value)
		{
			json.addProperty("materialType", value.materialType.getBaseName());
			json.addProperty("requireLevel", value.requireLevel);
		}

		@Override
		public ProcessingLevelCondition read(JsonObject json)
		{
			String asString = GsonHelper.getAsString(json, "materialType");
			MaterialType materialType = Arrays.stream(MaterialType.values()).filter(t -> StringUtils.equals(t.getBaseName(), asString)).findFirst().get();
			return new ProcessingLevelCondition(materialType, GsonHelper.getAsInt(json, "requireLevel"));
		}

		@Override
		public ResourceLocation getID()
		{
			return ProcessingLevelCondition.NAME;
		}

	}

}
