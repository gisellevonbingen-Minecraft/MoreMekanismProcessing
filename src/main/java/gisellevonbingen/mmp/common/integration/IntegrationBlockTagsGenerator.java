package gisellevonbingen.mmp.common.integration;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import gisellevonbingen.mmp.common.datagen.AbstractBlockTagsGenerator;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags.Blocks;
import net.minecraftforge.common.data.ExistingFileHelper;

public class IntegrationBlockTagsGenerator extends AbstractBlockTagsGenerator
{
	public IntegrationBlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addBlockTags(this));
	}

	public void tagOresOptional(TagKey<Block> tag, ResourceLocation blockName)
	{
		this.tagOptional(Blocks.ORES, blockName);
		this.tagOptional(tag, blockName);
	}

	public void tagOptional(TagKey<Block> tag, ResourceLocation blockName)
	{
		this.tag(tag).addOptional(blockName);
	}

}
