package net.lxshh.fleshz.data;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.ModTags;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class BlockTagProvider extends BlockTagsProvider {
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, FleshZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Blocks.RACKS)
                .add(ModBlocks.ACACIA_WOOD_RACK.get())
                .add(ModBlocks.OAK_WOOD_RACK.get())
                .add(ModBlocks.DARK_OAK_WOOD_RACK.get())
                .add(ModBlocks.SPRUCE_WOOD_RACK.get())
                .add(ModBlocks.BIRCH_WOOD_RACK.get())
                .add(ModBlocks.MANGROVE_WOOD_RACK.get())
                .add(ModBlocks.BAMBOO_WOOD_RACK.get())
                .add(ModBlocks.CHERRY_WOOD_RACK.get())
                .add(ModBlocks.JUNGLE_WOOD_RACK.get())
                .add(ModBlocks.CRIMSON_WOOD_RACK.get())
                .add(ModBlocks.WARPED_WOOD_RACK.get());

        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.ACACIA_WOOD_RACK.get())
                .add(ModBlocks.OAK_WOOD_RACK.get())
                .add(ModBlocks.DARK_OAK_WOOD_RACK.get())
                .add(ModBlocks.SPRUCE_WOOD_RACK.get())
                .add(ModBlocks.BIRCH_WOOD_RACK.get())
                .add(ModBlocks.MANGROVE_WOOD_RACK.get())
                .add(ModBlocks.BAMBOO_WOOD_RACK.get())
                .add(ModBlocks.CHERRY_WOOD_RACK.get())
                .add(ModBlocks.JUNGLE_WOOD_RACK.get())
                .add(ModBlocks.CRIMSON_WOOD_RACK.get())
                .add(ModBlocks.WARPED_WOOD_RACK.get());
    }
}
