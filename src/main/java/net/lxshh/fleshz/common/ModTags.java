package net.lxshh.fleshz.common;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

public class ModTags {
    public static class Blocks {
        public static final TagKey<Block> RACKS = BlockTags.create(ResourceLocation.fromNamespaceAndPath(FleshZ.MOD_ID, "racks"));
    }

    public static class Items {
        public static final TagKey<Item> RACKS = ItemTags.create(ResourceLocation.fromNamespaceAndPath(FleshZ.MOD_ID, "racks"));
    }

    public static class Entities {
        public static final TagKey<EntityType<?>> LEATHER_TO_HIDE = TagKey.create(Registries.ENTITY_TYPE, ResourceLocation.fromNamespaceAndPath(FleshZ.MOD_ID, "leather_to_hide"));
    }
}