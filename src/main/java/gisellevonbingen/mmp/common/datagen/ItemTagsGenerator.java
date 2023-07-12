package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;

import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends AbstractItemTagsGenerator
{
	public ItemTagsGenerator(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, BlockTagsGenerator blockTagsGenerator, ExistingFileHelper existingFileHelper)
	{
		super(output, lookupProvider, blockTagsGenerator, existingFileHelper);
	}

	@Override
	protected void addTags(HolderLookup.Provider lookupProvider)
	{
		this.buildStatedMaterials();
	}

	protected void buildStatedMaterials()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				TagAppender<Item> builderCategory = this.tag(materialState.getCategoryTag());
				TagAppender<Item> builderState = this.tag(materialState.getStateItemTag(materialType));

				if (materialState.hasOwnItem() == true)
				{
					ResourceLocation itemName = materialState.getItemName(materialType);
					builderCategory.addOptional(itemName);
					builderState.addOptional(itemName);
				}

			}

		}

	}

}
