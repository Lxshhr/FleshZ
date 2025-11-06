package net.lxshh.fleshz.common;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> RACKS = BlockTags.create(ResourceLocation.tryBuild(FleshZ.MOD_ID, "racks"));
    }
}
