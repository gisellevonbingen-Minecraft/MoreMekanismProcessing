package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;

import org.jetbrains.annotations.Nullable;

import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;

public class BlockTagsGenerator extends AbstractBlockTagsGenerator
{
	public BlockTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, existingFileHelper);
	}

	@Override
	public void addTags(HolderLookup.Provider lookupProvider)
	{

	}

}
