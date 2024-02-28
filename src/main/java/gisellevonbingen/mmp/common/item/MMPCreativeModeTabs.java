package gisellevonbingen.mmp.common.item;

import gisellevonbingen.mmp.common.MoreMekanismProcessing;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.CreativeModeTab.DisplayItemsGenerator;
import net.minecraft.world.item.CreativeModeTab.ItemDisplayParameters;
import net.minecraft.world.item.CreativeModeTab.Output;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class MMPCreativeModeTabs
{
	public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MoreMekanismProcessing.MODID);
	public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MORE_MEKANISM_PROCESSING = TABS.register("tab", CreativeModeTab.builder()//
			.title(Component.literal(MoreMekanismProcessing.MODANME))//
			.withTabFactory(MMPCreativeModeTab::new)//
			.displayItems(new DisplayItemsGenerator()
			{
				@Override
				public void accept(ItemDisplayParameters parameters, Output output)
				{
					MMPItems.ITEMS.getEntries().stream().map(i -> i.get()).filter(item ->
					{
						return !(item instanceof ItemStatedMaterial i) || MMPItems.testProcessingLevel(i.getMaterialType(), i.getOreState());
					}).forEach(output::accept);
				}
			})::build);

}