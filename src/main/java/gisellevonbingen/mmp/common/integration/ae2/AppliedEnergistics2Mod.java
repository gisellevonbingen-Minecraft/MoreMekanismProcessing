package gisellevonbingen.mmp.common.integration.ae2;

import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationMod;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public class AppliedEnergistics2Mod extends IntegrationMod
{
	public static final String MODID = "appliedenergistics2";
	public static final TagKey<Item> TAGS_DUSTS_CERTUS_QUARTZ = ItemTags.create(rl("dusts/certus_quartz"));

	public AppliedEnergistics2Mod()
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

		generator.tag(TAGS_DUSTS_CERTUS_QUARTZ, MoreMekanismProcessingItems.getProcessingItemName(MaterialType.CERTUSQUARTZ, MaterialState.DUST));
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
