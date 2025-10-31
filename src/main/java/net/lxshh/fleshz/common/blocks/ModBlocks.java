package net.lxshh.fleshz.common.blocks;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCK = DeferredRegister.create(ForgeRegistries.BLOCKS, FleshZ.MOD_ID);

    public static final RegistryObject<Block> WOOD_RACK = BLOCK.register("wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.of()));

}
