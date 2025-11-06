package net.lxshh.fleshz.common.blocks;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, FleshZ.MOD_ID);

    public static final RegistryObject<Block> OAK_WOOD_RACK = register("oak_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.OAK_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> SPRUCE_WOOD_RACK = register("spruce_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.SPRUCE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> BIRCH_WOOD_RACK = register("birch_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.BIRCH_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> JUNGLE_WOOD_RACK = register("jungle_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.JUNGLE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> ACACIA_WOOD_RACK = register("acacia_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.ACACIA_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> CHERRY_WOOD_RACK = register("cherry_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.CHERRY_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> DARK_OAK_WOOD_RACK = register("dark_oak_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.DARK_OAK_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> MANGROVE_WOOD_RACK = register("mangrove_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.MANGROVE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final RegistryObject<Block> BAMBOO_WOOD_RACK = register("bamboo_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.copy(Blocks.BAMBOO_PLANKS).noOcclusion().strength(0.25F, 0.5F)));

    private static <T extends Block> RegistryObject<T> register(String name, Supplier<T> blockFactory) {
        RegistryObject<T> block = BLOCKS.register(name, blockFactory);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }

}
