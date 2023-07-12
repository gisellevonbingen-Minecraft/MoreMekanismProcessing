package gisellevonbingen.mmp.common.datagen;

import java.util.concurrent.CompletableFuture;

import gisellevonbingen.mmp.common.data.EmptyExistingFileHelper;
import gisellevonbingen.mmp.common.integration.IntegrationBlockTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.MoreMekanismProcessingIntagrations;
import net.minecraft.core.HolderLookup.Provider;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		PackOutput output = generator.getPackOutput();
		CompletableFuture<Provider> lookupProvider = event.getLookupProvider();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		BlockTagsGenerator blockTagsGenerator = new BlockTagsGenerator(output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTagsGenerator);
		generator.addProvider(event.includeServer(), new ItemTagsGenerator(output, lookupProvider, blockTagsGenerator, existingFileHelper));
		generator.addProvider(event.includeServer(), new SlurryTagGenerator(output, lookupProvider, existingFileHelper));
		generator.addProvider(event.includeServer(), new RecipesGenerator(output));
		generator.addProvider(event.includeServer(), new LanguagesGenerator(output));

		IntegrationBlockTagsGenerator integrationBlockTagsGenerator = new IntegrationBlockTagsGenerator(output, lookupProvider, existingFileHelper);
		generator.addProvider(event.includeServer(), integrationBlockTagsGenerator);
		generator.addProvider(event.includeServer(), new IntegrationItemTagsGenerator(output, lookupProvider, integrationBlockTagsGenerator, existingFileHelper));

		generator.addProvider(event.includeClient(), new ItemModelGenerator(output, new EmptyExistingFileHelper()));

		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addDataGenerator(event));
	}

}
