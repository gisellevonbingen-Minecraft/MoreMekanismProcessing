package gisellevonbingen.mmp.common.item;

import gisellevonbingen.mmp.common.material.MaterialState;
import gisellevonbingen.mmp.common.material.MaterialType;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

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
	public void fillItemCategory(CreativeModeTab group, NonNullList<ItemStack> list)
	{
		if (MoreMekanismProcessingItems.testProcessingLevel(this.materialType, this.materialState) == true)
		{
			super.fillItemCategory(group, list);
		}

	}

	@Override
	public Component getName(ItemStack itemStack)
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
