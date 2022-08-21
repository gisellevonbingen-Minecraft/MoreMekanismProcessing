package gisellevonbingen.mmp.common.integration;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.data.event.GatherDataEvent;

public abstract class IntegrationMod
{
	public IntegrationMod()
	{

	}

	public abstract void initialize();

	public abstract String getModId();

	public ResourceLocation getResourceLocation(String path)
	{
		return new ResourceLocation(this.getModId(), path);
	}

	public void addDataGenerator(GatherDataEvent event)
	{

	}

	public void addBlockTags(IntegrationBlockTagsGenerator generator)
	{

	}

	public void addItemTags(IntegrationItemTagsGenerator generator)
	{

	}

}
