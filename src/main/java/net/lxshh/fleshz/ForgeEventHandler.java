package net.lxshh.fleshz;

import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.MissingMappingsEvent;

/**
 * Will be removed in 0.0.8
 * This was a temporary solution to replace the removed wood_rack with an Oak One
 */
@Deprecated
@Mod.EventBusSubscriber(modid = FleshZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ForgeEventHandler {

    @SubscribeEvent
    public static void onMissingMappings(MissingMappingsEvent event) {
        for (MissingMappingsEvent.Mapping<Block> mapping : event.getMappings(ForgeRegistries.Keys.BLOCKS, FleshZ.MOD_ID)) {
            if (mapping.getKey().getPath().equals("wood_rack")) {
                mapping.remap(ModBlocks.OAK_WOOD_RACK.get());
            }
        }

        for (MissingMappingsEvent.Mapping<Item> mapping : event.getMappings(ForgeRegistries.Keys.ITEMS, FleshZ.MOD_ID)) {
            if (mapping.getKey().getPath().equals("wood_rack")) {
                mapping.remap(ModBlocks.OAK_WOOD_RACK.get().asItem());
            }
        }
    }
}
