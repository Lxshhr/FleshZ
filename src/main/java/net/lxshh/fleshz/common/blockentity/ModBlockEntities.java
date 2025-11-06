package net.lxshh.fleshz.common.blockentity;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, FleshZ.MOD_ID);

    public static final RegistryObject<BlockEntityType<WoodRackEntity>> WOOD_RACK_ENTITY = BLOCK_ENTITY.register("wood_rack_entity", () ->
            BlockEntityType.Builder.of(WoodRackEntity::new,
                    ModBlocks.OAK_WOOD_RACK.get(),
                    ModBlocks.SPRUCE_WOOD_RACK.get(),
                    ModBlocks.BIRCH_WOOD_RACK.get(),
                    ModBlocks.ACACIA_WOOD_RACK.get(),
                    ModBlocks.JUNGLE_WOOD_RACK.get(),
                    ModBlocks.CHERRY_WOOD_RACK.get(),
                    ModBlocks.DARK_OAK_WOOD_RACK.get(),
                    ModBlocks.MANGROVE_WOOD_RACK.get(),
                    ModBlocks.BAMBOO_WOOD_RACK.get()
            ).build(null));
}
