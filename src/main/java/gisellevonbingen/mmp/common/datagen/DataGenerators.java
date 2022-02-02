package gisellevonbingen.mmp.common.datagen;

import gisellevonbingen.mmp.common.data.EmptyExistingFileHelper;
import gisellevonbingen.mmp.common.integration.IntegrationBlockTagsGenerator;
import gisellevonbingen.mmp.common.integration.IntegrationItemTagsGenerator;
import gisellevonbingen.mmp.common.integration.MoreMekanismProcessingIntagrations;
import net.minecraft.data.DataGenerator;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.forge.event.lifecycle.GatherDataEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerators
{
	@SubscribeEvent
	public static void gatherData(GatherDataEvent event)
	{
		DataGenerator generator = event.getGenerator();
		ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

		if (event.includeServer())
		{
			BlockTagsGenerator blockTagsGenerator = new BlockTagsGenerator(generator, existingFileHelper);
			generator.addProvider(blockTagsGenerator);
			generator.addProvider(new ItemTagsGenerator(generator, blockTagsGenerator, existingFileHelper));
			generator.addProvider(new SlurryTagGenerator(generator, existingFileHelper));
			generator.addProvider(new RecipesGenerator(generator));
			generator.addProvider(new LanguagesGenerator(generator));

			IntegrationBlockTagsGenerator integrationBlockTagsGenerator = new IntegrationBlockTagsGenerator(generator, existingFileHelper);
			generator.addProvider(integrationBlockTagsGenerator);
			generator.addProvider(new IntegrationItemTagsGenerator(generator, integrationBlockTagsGenerator, existingFileHelper));
		}

		if (event.includeClient())
		{
			generator.addProvider(new ItemModelGenerator(generator, new EmptyExistingFileHelper()));
		}

		MoreMekanismProcessingIntagrations.getMods().forEach(m -> m.addDataGenerator(event));
	}

}
