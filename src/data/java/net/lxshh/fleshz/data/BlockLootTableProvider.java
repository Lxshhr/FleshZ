package net.lxshh.fleshz.data;

import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.registries.DeferredHolder;

import java.util.Set;
import java.util.stream.Collectors;

public class BlockLootTableProvider extends BlockLootSubProvider {
    public BlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    protected void generate() {
        this.dropSelf(ModBlocks.OAK_WOOD_RACK.get());
        this.dropSelf(ModBlocks.SPRUCE_WOOD_RACK.get());
        this.dropSelf(ModBlocks.BIRCH_WOOD_RACK.get());
        this.dropSelf(ModBlocks.JUNGLE_WOOD_RACK.get());
        this.dropSelf(ModBlocks.DARK_OAK_WOOD_RACK.get());
        this.dropSelf(ModBlocks.ACACIA_WOOD_RACK.get());
        this.dropSelf(ModBlocks.MANGROVE_WOOD_RACK.get());
        this.dropSelf(ModBlocks.CHERRY_WOOD_RACK.get());
        this.dropSelf(ModBlocks.BAMBOO_WOOD_RACK.get());
        this.dropSelf(ModBlocks.CRIMSON_WOOD_RACK.get());
        this.dropSelf(ModBlocks.WARPED_WOOD_RACK.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream()
                .map(DeferredHolder::get)
                .collect(Collectors.toSet());
    }
}
