package gisellevonbingen.mmp.common.integration;

import java.util.concurrent.CompletableFuture;

import gisellevonbingen.mmp.common.datagen.AbstractBlockTagsGenerator;
import gisellevonbingen.mmp.common.datagen.AbstractItemTagsGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationItemTagsGenerator extends AbstractItemTagsGenerator
{
	public IntegrationItemTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, AbstractBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, blockTagsGenerator, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addItemTags(this));
		this.copyTags();
	}

	protected void copyTags()
	{
		this.blockTagGenerator.streamTags().forEach(blockTag ->
		{
			TagKey<Item> itemTag = ItemTags.create(blockTag.location());
			this.copy(blockTag, itemTag);
		});

	}

	public void tagOptional(TagKey<Item> tag, ResourceLocation blockName)
	{
		this.tag(tag).addOptional(blockName);
	}

}
