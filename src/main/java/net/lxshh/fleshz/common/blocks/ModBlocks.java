package net.lxshh.fleshz.common.blocks;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS = DeferredRegister.createBlocks(FleshZ.MOD_ID);

    public static final DeferredBlock<Block> OAK_WOOD_RACK = register("oak_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> SPRUCE_WOOD_RACK = register("spruce_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> BIRCH_WOOD_RACK = register("birch_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BIRCH_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> JUNGLE_WOOD_RACK = register("jungle_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.JUNGLE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> ACACIA_WOOD_RACK = register("acacia_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ACACIA_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> CHERRY_WOOD_RACK = register("cherry_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.CHERRY_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> DARK_OAK_WOOD_RACK = register("dark_oak_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.DARK_OAK_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> MANGROVE_WOOD_RACK = register("mangrove_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.MANGROVE_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> BAMBOO_WOOD_RACK = register("bamboo_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> CRIMSON_WOOD_RACK = register("crimson_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion().strength(0.25F, 0.5F)));
    public static final DeferredBlock<Block> WARPED_WOOD_RACK = register("warped_wood_rack", () -> new WoodRackBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_PLANKS).noOcclusion().strength(0.25F, 0.5F)));

    private static <T extends Block> DeferredBlock<T> register(String name, Supplier<T> blockFactory) {
        DeferredBlock<T> block = BLOCKS.register(name, blockFactory);
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        return block;
    }
}
