package gisellevonbingen.mmp.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gisellevonbingen.mmp.common.config.MMPConfigs;
import gisellevonbingen.mmp.common.crafting.conditions.MMPCraftingConditions;
import gisellevonbingen.mmp.common.integration.MMPIntagrations;
import gisellevonbingen.mmp.common.item.MMPCreativeModeTabs;
import gisellevonbingen.mmp.common.item.MMPItems;
import gisellevonbingen.mmp.common.slurry.MMPSlurries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;

@Mod(MoreMekanismProcessing.MODID)
public class MoreMekanismProcessing
{
	public static final String MODID = "moremekanismprocessing";
	public static final String MODANME = "More Mekanism Processing";
	public static final Logger LOGGER = LogManager.getLogger();

	public MoreMekanismProcessing()
	{
		ModLoadingContext modLoadingContext = ModLoadingContext.get();
		MMPConfigs.register(modLoadingContext);
		IEventBus modEventBus = modLoadingContext.getActiveContainer().getEventBus();

		MMPItems.ITEMS.register(modEventBus);
		MMPSlurries.SLURRIES.register(modEventBus);
		MMPCreativeModeTabs.TABS.register(modEventBus);
		MMPCraftingConditions.CONDITIONS.register(modEventBus);
		MMPIntagrations.initialize();
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
