package gisellevonbingen.mmp.common.integration.bluepower;

import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationMod;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.resources.ResourceLocation;

public class BluePowerMod extends IntegrationMod
{
	public static final String MODID = "bluepower";

	public static final ResourceLocation SILVER_RAW_ITEM = rl("silver_raw");
	public static final ResourceLocation ZINC_RAW_ITEM = rl("zinc_raw");
	public static final ResourceLocation TUNGSTEN_RAW_ITEM = rl("tungsten_raw");

	public BluePowerMod()
	{

	}

	@Override
	public void initialize()
	{

	}

	@Override
	public void addItemTags(IntegrationItemTagsGenerator generator)
	{
		super.addItemTags(generator);

		generator.tagOptional(MaterialState.RAW_ITEM.getStateItemTag(MaterialType.SILVER), SILVER_RAW_ITEM);
		generator.tagOptional(MaterialState.RAW_ITEM.getStateItemTag(MaterialType.ZINC), ZINC_RAW_ITEM);
		generator.tagOptional(MaterialState.RAW_ITEM.getStateItemTag(MaterialType.TUNGSTEN), TUNGSTEN_RAW_ITEM);
	}

	@Override
	public String getModId()
	{
		return MODID;
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
