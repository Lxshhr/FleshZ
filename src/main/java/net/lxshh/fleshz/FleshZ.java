package net.lxshh.fleshz;

import net.lxshh.fleshz.client.ModClient;
import net.lxshh.fleshz.common.blockentity.ModBlockEntities;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.items.ModItems;
import net.lxshh.fleshz.common.loot_modifiers.ModLootModifiers;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.compat.everycomp.WoodGoodIntegration;
import net.minecraft.world.item.CreativeModeTabs;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import org.slf4j.Logger;
import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.ModContainer;

@Mod(FleshZ.MOD_ID)
public class FleshZ
{
    public static final String MOD_ID = "fleshz";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FleshZ(IEventBus modEventBus, ModContainer modContainer, Dist dist) {
        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY.register(modEventBus);
        ModRecipes.SERIALIZERS.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);

        modEventBus.addListener(this::buildContents);

         everyCompatModule();

         if (dist == Dist.CLIENT) {
            ModClient.init(modEventBus);
         }
    }

    public void buildContents(BuildCreativeModeTabContentsEvent event) {
        if (event.getTabKey().equals(CreativeModeTabs.INGREDIENTS)) {
            event.accept(ModItems.HIDE.get());
            event.accept(ModItems.PREPARED_HIDE.get());
            event.accept(ModItems.ROTTEN_LEATHER.get());
        }

        if (event.getTabKey().equals(CreativeModeTabs.BUILDING_BLOCKS)) {
            event.accept(ModBlocks.OAK_WOOD_RACK.get());
            event.accept(ModBlocks.SPRUCE_WOOD_RACK.get());
            event.accept(ModBlocks.BIRCH_WOOD_RACK.get());
            event.accept(ModBlocks.ACACIA_WOOD_RACK.get());
            event.accept(ModBlocks.JUNGLE_WOOD_RACK.get());
            event.accept(ModBlocks.CHERRY_WOOD_RACK.get());
            event.accept(ModBlocks.DARK_OAK_WOOD_RACK.get());
            event.accept(ModBlocks.MANGROVE_WOOD_RACK.get());
            event.accept(ModBlocks.BAMBOO_WOOD_RACK.get());
        }
    }

    private static void everyCompatModule() {
        try {
            if (ModList.get().isLoaded("everycomp")) {
                WoodGoodIntegration.init();
            } else {
                LOGGER.info("EveryCompat module is not loaded");
            }
        } catch (Exception e) {
            LOGGER.error("Failed to start EveryComp module", e);
        }
    }
}
