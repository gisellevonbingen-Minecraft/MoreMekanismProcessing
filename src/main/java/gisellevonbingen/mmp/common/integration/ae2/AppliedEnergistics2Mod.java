package gisellevonbingen.mmp.common.integration.ae2;

import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationMod;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.item.Item;
import net.minecraft.tags.ITag.INamedTag;
import net.minecraft.tags.ItemTags;
import net.minecraft.util.ResourceLocation;

public class AppliedEnergistics2Mod extends IntegrationMod
{
	public static final String MODID = "appliedenergistics2";
	public static final INamedTag<Item> TAGS_DUSTS_CERTUS_QUARTZ = ItemTags.createOptional(rl("dusts/certus_quartz"));

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
