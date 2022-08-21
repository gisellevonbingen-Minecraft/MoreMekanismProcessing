package gisellevonbingen.mmp.common;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import gisellevonbingen.mmp.common.block.MoreMekanismProcessingBlocks;
import gisellevonbingen.mmp.common.config.MoreMekanismProcessingConfigs;
import gisellevonbingen.mmp.common.crafting.conditions.MoreMekanismProcessingConditions;
import gisellevonbingen.mmp.common.datagen.DataGenerators;
import gisellevonbingen.mmp.common.integration.MoreMekanismProcessingIntagrations;
import gisellevonbingen.mmp.common.item.MoreMekanismProcessingItems;
import gisellevonbingen.mmp.common.slurry.MoreMekanismProcessingSlurries;
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
		MoreMekanismProcessingConfigs.register(ModLoadingContext.get());

		IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
		modEventBus.register(new DataGenerators());
		modEventBus.register(MoreMekanismProcessingConfigs.class);

		MoreMekanismProcessingBlocks.register(modEventBus);
		MoreMekanismProcessingItems.register(modEventBus);
		MoreMekanismProcessingSlurries.register(modEventBus);
		MoreMekanismProcessingConditions.register();
		MoreMekanismProcessingIntagrations.initialize();
	}

	public static ResourceLocation rl(String path)
	{
		return new ResourceLocation(MODID, path);
	}

}
