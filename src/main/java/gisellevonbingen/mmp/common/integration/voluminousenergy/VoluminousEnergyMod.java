package gisellevonbingen.mmp.common.integration.voluminousenergy;

import gisellevonbingen.mmp.common.integration.IntegrationBlockTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationMod;
import gisellevonbingen.mmp.common.integration.IntegrationTags;
import net.minecraft.resources.ResourceLocation;

public class VoluminousEnergyMod extends IntegrationMod
{
	public static final String MODID = "voluminousenergy";
	public static final ResourceLocation BAUXITE_ORE = rl("bauxiteore");
	public static final ResourceLocation BAUXITE_DEEPSLATE_ORE = rl("deepslate_bauxite_ore");
	public static final ResourceLocation BAUXITE_RAW = rl("raw_bauxite");
	public static final ResourceLocation RUTILE_ORE = rl("rutileore");
	public static final ResourceLocation RUTILE_DEEPSLATE_ORE = rl("deepslate_rutile_ore");
	public static final ResourceLocation RUTILE_RAW = rl("raw_rutile");

	public VoluminousEnergyMod()
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

		generator.tagOres(IntegrationTags.Blocks.ORES_ALUMINUM, BAUXITE_ORE);
		generator.tagOres(IntegrationTags.Blocks.ORES_ALUMINUM, BAUXITE_DEEPSLATE_ORE);
		generator.tagOres(IntegrationTags.Blocks.ORES_TITANIUM, RUTILE_ORE);
		generator.tagOres(IntegrationTags.Blocks.ORES_TITANIUM, RUTILE_DEEPSLATE_ORE);
	}
	
	
	@Override
	public void addItemTags(IntegrationItemTagsGenerator generator)
	{
		super.addItemTags(generator);

		generator.tagOres(IntegrationTags.Items.RAW_MATERIALS_ALUMINUM, BAUXITE_RAW);
		generator.tagOres(IntegrationTags.Items.RAW_MATERIALS_TITANIUM, RUTILE_RAW);
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
