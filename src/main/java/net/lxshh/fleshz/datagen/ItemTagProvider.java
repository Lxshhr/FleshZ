package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.ModTags;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

public class ItemTagProvider extends ItemTagsProvider {
    public ItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags) {
        super(output, lookupProvider, blockTags);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Items.RACKS)
                .add(ModBlocks.ACACIA_WOOD_RACK.asItem())
                .add(ModBlocks.OAK_WOOD_RACK.asItem())
                .add(ModBlocks.DARK_OAK_WOOD_RACK.asItem())
                .add(ModBlocks.SPRUCE_WOOD_RACK.asItem())
                .add(ModBlocks.BIRCH_WOOD_RACK.asItem())
                .add(ModBlocks.MANGROVE_WOOD_RACK.asItem())
                .add(ModBlocks.BAMBOO_WOOD_RACK.asItem())
                .add(ModBlocks.CHERRY_WOOD_RACK.asItem())
                .add(ModBlocks.JUNGLE_WOOD_RACK.asItem());
    }

    @Override
    public String getName() {
        return FleshZ.MOD_ID + " Item Tags";
    }
}
