package gisellevonbingen.mmp.common.datagen;

import gisellevonbingen.mmp.common.data.EmptyExistingFileHelper;
import gisellevonbingen.mmp.common.integration.IntegrationBlockTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.MoreMekanismProcessingIntagrations;
import net.minecraft.data.DataGenerator;
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
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		BlockTagsGenerator blockTagsGenerator = new BlockTagsGenerator(generator, existingFileHelper);
		generator.addProvider(event.includeServer(), blockTagsGenerator);
		generator.addProvider(event.includeServer(), new ItemTagsGenerator(generator, blockTagsGenerator, existingFileHelper));
		generator.addProvider(event.includeServer(), new SlurryTagGenerator(generator, existingFileHelper));
		generator.addProvider(event.includeServer(), new RecipesGenerator(generator));
		generator.addProvider(event.includeServer(), new LanguagesGenerator(generator));

		IntegrationBlockTagsGenerator integrationBlockTagsGenerator = new IntegrationBlockTagsGenerator(generator, existingFileHelper);
		generator.addProvider(true, integrationBlockTagsGenerator);
		generator.addProvider(true, new IntegrationItemTagsGenerator(generator, integrationBlockTagsGenerator, existingFileHelper));

		generator.addProvider(event.includeClient(), new ItemModelGenerator(generator, new EmptyExistingFileHelper()));

		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addDataGenerator(event));
	}

}
