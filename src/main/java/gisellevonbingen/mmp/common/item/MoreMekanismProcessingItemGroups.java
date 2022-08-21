package gisellevonbingen.mmp.common.item;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.core.NonNullList;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class MoreMekanismProcessingItemGroups
{
	public static final CreativeModeTab tabMoreMekanismProcessing = new CreativeModeTab(MoreMekanismProcessing.MODID)
	{
		NonNullList<ItemStack> itemStacks = NonNullList.create();
		int iconIndex = 0;
		long lastMillis = 0L;
		ItemStack icon = null;
		ItemStack fallbackIcon = null;

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
		public Component getDisplayName()
		{
			return Component.literal(MoreMekanismProcessing.MODANME);
		};

	};

}
