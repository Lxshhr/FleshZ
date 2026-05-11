package net.lxshh.fleshz;

import com.mojang.logging.LogUtils;
import net.lxshh.fleshz.client.ModClient;
import net.lxshh.fleshz.common.blockentity.ModBlockEntities;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.items.ModItems;
import net.lxshh.fleshz.common.loot_modifiers.ModLootModifiers;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.compat.everycomp.WoodGoodIntegration;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.BuildCreativeModeTabContentsEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.slf4j.Logger;

@Mod(FleshZ.MOD_ID)
public class FleshZ
{
    public static final String MOD_ID = "fleshz";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FleshZ() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY.register(modEventBus);
        ModRecipes.SERIALIZERS.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
        ModLootModifiers.LOOT_MODIFIERS.register(modEventBus);

        modEventBus.addListener(this::buildContents);

        everyCompatModule();

        if (FMLEnvironment.dist == Dist.CLIENT) {
            ModClient.init();
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
            event.accept(ModBlocks.CRIMSON_WOOD_RACK.get());
            event.accept(ModBlocks.WARPED_WOOD_RACK.get());
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
