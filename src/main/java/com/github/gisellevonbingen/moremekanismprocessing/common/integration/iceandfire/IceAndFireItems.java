package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.Item.Properties;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IceAndFireItems
{
	public static final DeferredRegister<Item> REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, IntagrationIceAndFire.MODID);

	public static void register()
	{
		REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
		REGISTER.register("sapphire_ore", () -> new BlockItem(IceAndFireBlocks.SapphireOre.get(), new Properties()));
		REGISTER.register("amethest_ore", () -> new BlockItem(IceAndFireBlocks.AmethystOre.get(), new Properties()));
	}

}
