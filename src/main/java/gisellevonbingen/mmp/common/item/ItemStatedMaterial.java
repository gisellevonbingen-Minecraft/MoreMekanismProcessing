package gisellevonbingen.mmp.common.item;

import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;

public class ItemStatedMaterial extends Item
{
	private final MaterialType materialType;
	private final MaterialState materialState;

	public ItemStatedMaterial(MaterialType materialType, MaterialState materialState)
	{
		super(new Properties().tab(MoreMekanismProcessingItemGroups.tabMoreMekanismProcessing));

		this.materialType = materialType;
		this.materialState = materialState;
	}

	@Override
	public void fillItemCategory(ItemGroup group, NonNullList<ItemStack> list)
	{
		if (MoreMekanismProcessingItems.testProcessingLevel(this.materialType, this.materialState) == true)
		{
			super.fillItemCategory(group, list);
		}

	}

	@Override
	public ITextComponent getName(ItemStack itemStack)
	{
		String descriptionId = this.getDescriptionId(itemStack);
		String statedDescriptionId = this.materialState.getStatedDescriptionId();
		return MaterialState.createTextComponent(descriptionId, statedDescriptionId, this.getMaterialType());
	}

	public MaterialType getMaterialType()
	{
		return this.materialType;
	}

	public MaterialState getOreState()
	{
		return this.materialState;
	}

}
