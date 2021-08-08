package com.github.gisellevonbingen.moremekanismprocessing.common.integration.iceandfire;

import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class IceAndFireBlocks
{
	public static final DeferredRegister<Block> REGISTER = DeferredRegister.create(ForgeRegistries.BLOCKS, IntagrationIceAndFire.MODID);
	public static RegistryObject<Block> SapphireOre;
	public static RegistryObject<Block> AmethystOre;

	public static void register()
	{
		REGISTER.register(FMLJavaModLoadingContext.get().getModEventBus());
		SapphireOre = REGISTER.register("sapphire_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE)));
		AmethystOre = REGISTER.register("amethest_ore", () -> new Block(AbstractBlock.Properties.of(Material.STONE)));
	}

}
