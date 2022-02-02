package gisellevonbingen.mmp.common.integration.libvulpes;

import gisellevonbingen.mmp.common.integration.IntegrationBlockTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationMod;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.util.ResourceLocation;

public class LibVulpesMod extends IntegrationMod
{
	public static final String MODID = "libvulpes";
	public static final ResourceLocation RUTILE_ORE = rl("orerutile");

	public LibVulpesMod()
	{

	}

	@Override
	public void initialize()
	{

	}

	@Override
	public void addBlockTags(IntegrationBlockTagsGenerator generator)
	{
		super.addBlockTags(generator);

		generator.tagOres(MaterialState.ORE.getStateBlockTag(MaterialType.Titanium), RUTILE_ORE);
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
