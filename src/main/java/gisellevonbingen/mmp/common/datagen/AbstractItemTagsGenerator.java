package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class AbstractItemTagsGenerator extends ItemTagsProvider
{
	protected final AbstractBlockTagsGenerator blockTagGenerator;

	public AbstractItemTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, AbstractBlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, blockTagsGenerator.contentsGetter(), MoreMekanismProcessing.MODID, existingFileHelper);
		this.blockTagGenerator = blockTagsGenerator;
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{

	}

}
