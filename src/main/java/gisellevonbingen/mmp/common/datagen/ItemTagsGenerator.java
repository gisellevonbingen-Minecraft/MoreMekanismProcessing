package gisellevonbingen.mmp.common.datagen;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.data.BlockTagsProvider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.ItemTagsProvider;
import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ItemTagsGenerator extends ItemTagsProvider
{
	public ItemTagsGenerator(DataGenerator p_i244817_1_, BlockTagsProvider p_i244817_2_, ExistingFileHelper p_i244817_4_)
	{
		super(p_i244817_1_, p_i244817_2_, MoreMekanismProcessing.MODID, p_i244817_4_);
	}

	@Override
	protected void addTags()
	{
		this.buildStatedMaterials();
	}

	protected void buildStatedMaterials()
	{
		for (MaterialType materialType : MaterialType.values())
		{
			for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
			{
				Builder<Item> builderCategory = this.tag(materialState.getCategoryTag());
				Builder<Item> builderState = this.tag(materialState.getStateItemTag(materialType));
				
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
