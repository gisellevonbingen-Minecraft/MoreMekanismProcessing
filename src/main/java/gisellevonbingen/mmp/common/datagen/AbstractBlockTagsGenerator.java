package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import org.jetbrains.annotations.Nullable;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AbstractBlockTagsGenerator extends BlockTagsProvider
{
	public AbstractBlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
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

	}

}
