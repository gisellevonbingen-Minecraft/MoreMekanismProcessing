package gisellevonbingen.mmp.common.item;

import java.util.List;
import java.util.function.Supplier;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import mekanism.common.registries.MekanismBlocks;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.RegistryObject;

public class MoreMekanismProcessingItemGroups
{
	public static final CreativeModeTab tabMoreMekanismProcessing = CreativeModeTab.builder().title(Component.literal(MoreMekanismProcessing.MODANME)).icon(new Supplier<ItemStack>()
	{
		int iconIndex = 0;
		long lastMillis = 0L;
		ItemStack icon = null;
		ItemStack fallbackIcon = null;

		public ItemStack makeIcon()
		{
			List<RegistryObject<Item>> entries = MoreMekanismProcessingItems.REGISTER.getEntries().stream().toList();
			int size = entries.size();

			if (size > 0)
			{
				this.iconIndex = this.iconIndex % size;
				return new ItemStack(entries.get(this.iconIndex).get());
			}
			else if (this.fallbackIcon == null)
			{
				this.fallbackIcon = new ItemStack(MekanismBlocks.CHEMICAL_DISSOLUTION_CHAMBER);
			}

			return this.fallbackIcon;
		}

		@Override
		public ItemStack get()
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
	}).displayItems((parameters, output) ->
	{
		MoreMekanismProcessingItems.REGISTER.getEntries().stream().map(RegistryObject<Item>::get).filter(item ->
		{
			return item instanceof ItemStatedMaterial i ? MoreMekanismProcessingItems.testProcessingLevel(i.getMaterialType(), i.getOreState()) : true;
		});
	}).build();

}
