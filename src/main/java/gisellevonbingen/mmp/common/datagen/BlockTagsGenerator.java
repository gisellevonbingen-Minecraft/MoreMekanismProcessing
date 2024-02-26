package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.integration.MMPIntagrations;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.Tags.Blocks;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends BlockTagsProvider
{
	public BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, MoreMekanismProcessing.MODID, existingFileHelper);
	}

	public Stream<TagKey<Block>> streamTags()
	{
		return this.builders.keySet().stream().map(BlockTags::create);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		MMPIntagrations.getMods().forEach(m -> m.addBlockTags(this));
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
