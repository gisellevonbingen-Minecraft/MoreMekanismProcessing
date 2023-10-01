package gisellevonbingen.mmp.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gisellevonbingen.mmp.common.config.MMPConfigs;
import gisellevonbingen.mmp.common.crafting.conditions.MMPCraftingConditions;
import gisellevonbingen.mmp.common.datagen.DataGenerators;
import gisellevonbingen.mmp.common.integration.MMPIntagrations;
import gisellevonbingen.mmp.common.item.MMPCreativeModeTabs;
import gisellevonbingen.mmp.common.item.MMPItems;
import gisellevonbingen.mmp.common.slurry.MMPSlurries;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(MoreMekanismProcessing.MODID)
public class MoreMekanismProcessing
{
	public static final String MODID = "moremekanismprocessing";
	public static final String MODANME = "More Mekanism Processing";
	public static final Logger LOGGER = LogManager.getLogger();

	public MoreMekanismProcessing()
	{
		MMPConfigs.register(ModLoadingContext.get());

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(new DataGenerators());
		modEventBus.register(MMPConfigs.class);

		MMPItems.ITEMS.register(modEventBus);
		MMPSlurries.SLURRIES.register(modEventBus);
		MMPCreativeModeTabs.TABS.register(modEventBus);
		MMPCraftingConditions.register();
		MMPIntagrations.initialize();
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
