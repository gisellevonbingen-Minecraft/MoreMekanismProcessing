package gisellevonbingen.mmp.common.slurry;

import java.util.Objects;

import gisellevonbingen.mmp.common.material.MaterialType;
import mekanism.api.chemical.slurry.SlurryBuilder;
import mekanism.common.Mekanism;
import net.minecraft.resources.ResourceLocation;

public class MoreMekanismProcessingSlurryBuilder extends SlurryBuilder
{
	public static final String DIRTY = "dirty";
	public static final String CLEAN = "clean";

	private final String slurryType;

	private MaterialType materialType;

	protected MoreMekanismProcessingSlurryBuilder(String slurryType, ResourceLocation texture)
	{
		super(texture);

		this.slurryType = slurryType;
	}

	public String slurryType()
	{
		return this.slurryType;
	}

	public MaterialType materialType()
	{
		return this.materialType;
	}

	public void materialType(MaterialType materialType)
	{
		this.materialType = materialType;
	}

	public String getSlurryName()
	{
		return MoreMekanismProcessingSlurry.getSlurryName(this.slurryType(), this.materialType());
	}

	public static MoreMekanismProcessingSlurryBuilder dirty()
	{
		return new MoreMekanismProcessingSlurryBuilder(DIRTY, Mekanism.rl(MoreMekanismProcessingSlurry.SLURRY + "/" + DIRTY));
	}

	public static MoreMekanismProcessingSlurryBuilder clean()
	{
		return new MoreMekanismProcessingSlurryBuilder(CLEAN, Mekanism.rl(MoreMekanismProcessingSlurry.SLURRY + "/" + CLEAN));
	}

	public static MoreMekanismProcessingSlurryBuilder builder(String state, ResourceLocation texture)
	{
		return new MoreMekanismProcessingSlurryBuilder(state, Objects.<ResourceLocation> requireNonNull(texture));
	}

}
