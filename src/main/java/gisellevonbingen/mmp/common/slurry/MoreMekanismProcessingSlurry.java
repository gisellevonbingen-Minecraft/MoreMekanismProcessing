package gisellevonbingen.mmp.common.slurry;

import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import mekanism.api.chemical.slurry.Slurry;
import net.minecraft.util.text.ITextComponent;

public class MoreMekanismProcessingSlurry extends Slurry
{
	public static final String SLURRY = "slurry";

	private final MaterialType materialType;
	private final String slurryType;

	public MoreMekanismProcessingSlurry(MoreMekanismProcessingSlurryBuilder builder)
	{
		super(builder);

		this.materialType = builder.materialType();
		this.slurryType = builder.slurryType();
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public String getSlurryType()
	{
		return this.slurryType;
	}

	@Override
	public int getTint()
	{
		return this.getMaterialType().getColor();
	}

	@Override
	public ITextComponent getTextComponent()
	{
		String descriptionId = this.getTranslationKey();
		String statedDescriptionId = this.getStatedDescriptionId();
		return MaterialState.createTextComponent(descriptionId, statedDescriptionId, this.getMaterialType());
	}

	public String getSlurryName()
	{
		return getSlurryName(this.getSlurryType(), this.getMaterialType());
	}

	public String getStatedDescriptionId()
	{
		return makeDescriptionId(this.getSlurryType());
	}

	@Override
	public boolean isHidden()
	{
		MaterialState crystal = MaterialState.CRYSTAL;

		if (MoreMekanismProcessingItems.testProcessingLevel(this.materialType, crystal) == false)
		{
			return true;
		}
		else
		{
			return super.isHidden();
		}

	}

	public static String makeDescriptionId(String slurryType)
	{
		return MaterialState.makeDescriptionId(getSlurryTypeKey(slurryType));
	}

	public static String getSlurryTypeKey(String slurryType)
	{
		return slurryType + "_" + SLURRY;
	}

	public static String getSlurryName(String slurryType, MaterialType materialType)
	{
		return getSlurryName(slurryType, materialType.getBaseName());
	}

	public static String getSlurryName(String slurryType, String materialType)
	{
		return slurryType + "_" + materialType;
	}

}
