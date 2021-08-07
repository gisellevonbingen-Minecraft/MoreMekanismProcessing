package com.github.gisellevonbingen.moremekanismprocessing.common.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.github.gisellevonbingen.moremekanismprocessing.MoreMekanismProcessing;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialState;
import com.github.gisellevonbingen.moremekanismprocessing.common.material.MaterialType;

import net.minecraft.item.Item;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class MoreMekanismProcessingItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, MoreMekanismProcessing.MODID);
	public static final Map<MaterialType, Map<MaterialState, RegistryObject<Item>>> PROCESSING_ITEMS = new HashMap<>();

	public static ItemStatedMaterial getProcessingItem(MaterialType materialType, MaterialState materialState)
	{
		Map<MaterialState, RegistryObject<Item>> map = PROCESSING_ITEMS.get(materialType);

		if (map == null)
		{
			return null;
		}

		RegistryObject<Item> registryObject = map.get(materialState);
		return registryObject != null ? (ItemStatedMaterial) registryObject.get() : null;
	}

	public static List<ItemStatedMaterial> getProcessingItems(MaterialState materialState)
	{
		List<ItemStatedMaterial> list = new ArrayList<>();

		for (Entry<MaterialType, Map<MaterialState, RegistryObject<Item>>> entry : PROCESSING_ITEMS.entrySet())
		{
			RegistryObject<Item> registryObject = entry.getValue().get(materialState);

			if (registryObject != null)
			{
				ItemStatedMaterial item = (ItemStatedMaterial) registryObject.get();
				list.add(item);
			}

		}

		return list;
	}

	public static void register()
	{
		register(REGISTER);
	}

	public static void register(DeferredRegister<Item> register)
	{
		register.register(FMLJavaModLoadingContext.get().getModEventBus());

		for (MaterialType materialType : MaterialType.values())
		{
			registerOreType(register, materialType);
		}

	}

	public static void registerOreType(DeferredRegister<Item> registry, MaterialType materialType)
	{
		Map<MaterialState, RegistryObject<Item>> map2 = new HashMap<>();
		PROCESSING_ITEMS.put(materialType, map2);

		for (MaterialState materialState : materialType.getResultShape().getProcessableStates())
		{
			if (materialState != MaterialState.ORE)
			{
				RegistryObject<Item> registryObject = registry.register(materialState.getItemName(materialType), () -> new ItemStatedMaterial(materialType, materialState));
				map2.put(materialState, registryObject);
			}

		}

	}

}
