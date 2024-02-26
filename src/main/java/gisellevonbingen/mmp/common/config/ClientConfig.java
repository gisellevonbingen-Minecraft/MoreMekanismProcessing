package gisellevonbingen.mmp.common.config;

import java.util.HashMap;
import java.util.Map;

import gisellevonbingen.mmp.common.material.MaterialType;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.ModConfigSpec.ConfigValue;

public class ClientConfig
{
	public static final int RADIX = 16;

	public final Map<MaterialType, ModConfigSpec.ConfigValue<String>> colors;
	public final Map<MaterialType, Integer> parsedColors;

	public ClientConfig(ModConfigSpec.Builder builder)
	{
		builder.comment("color : ores rgb color");
		builder.push("ores");

		this.colors = new HashMap<>();
		this.parsedColors = new HashMap<>();

		for (MaterialType materialType : MaterialType.values())
		{
			builder.push(materialType.getBaseName());

			builder.comment("if empty, use default", "default : " + Integer.toString(materialType.getDefaultColor() & 0x00FFFFFF, RADIX));
			this.colors.put(materialType, builder.define("color", ""));

			builder.pop();
		}

		builder.pop();
	}

	public void parseConfig()
	{
		this.parsedColors.clear();

		for (MaterialType materialType : MaterialType.values())
		{
			ConfigValue<String> value = this.colors.get(materialType);
			int color = this.parseColor(value, materialType.getColor());
			this.parsedColors.put(materialType, color);
		}

	}

	public int parseColor(ConfigValue<String> configValue, int fallback)
	{
		try
		{
			if (configValue != null)
			{
				return Integer.parseInt(configValue.get(), RADIX);
			}

		}
		catch (Exception e)
		{

		}

		return fallback;
	}

}
