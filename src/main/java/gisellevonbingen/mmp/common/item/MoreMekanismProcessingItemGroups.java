package gisellevonbingen.mmp.common.item;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.StringTextComponent;

public class MoreMekanismProcessingItemGroups
{
	public static final ItemGroup tabMoreMekanismProcessing = new ItemGroup(MoreMekanismProcessing.MODID)
	{
		NonNullList<ItemStack> itemStacks = NonNullList.create();
		int iconIndex = 0;
		long lastMillis = 0L;
		ItemStack fallbackIcon = null;
		ItemStack icon = null;

		@Override
		public ItemStack makeIcon()
		{
			int size = this.itemStacks.size();

			if (size > 0)
			{
				this.iconIndex = this.iconIndex % size;
				return this.itemStacks.get(this.iconIndex);
			}
			else if (this.fallbackIcon == null)
			{
				this.fallbackIcon = new ItemStack(MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER);
			}

			return this.fallbackIcon;
		}

		@Override
		public ItemStack getIconItem()
		{
			long millis = System.currentTimeMillis();

			if (this.lastMillis == 0)
			{
				this.iconIndex = 0;
				this.lastMillis = millis;
				this.icon = this.makeIcon();
			}
			else if ((millis - this.lastMillis) > 2000)
			{
				this.iconIndex++;
				this.lastMillis = millis;
				this.icon = this.makeIcon();
			}

			return this.icon;
		};

		@Override
		public void fillItemList(NonNullList<ItemStack> list)
		{
			this.itemStacks.clear();
			super.fillItemList(this.itemStacks);

			list.addAll(this.itemStacks);
		};

		@Override
		public ITextComponent getDisplayName()
		{
			return new StringTextComponent(MoreMekanismProcessing.MODANME);
		};

	};

}
