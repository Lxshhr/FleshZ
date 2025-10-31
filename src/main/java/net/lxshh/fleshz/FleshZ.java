package net.lxshh.fleshz;

import com.mojang.logging.LogUtils;
import net.lxshh.fleshz.common.blockentity.ModBlockEntities;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.items.ModItems;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(FleshZ.MOD_ID)
public class FleshZ
{
    public static final String MOD_ID = "fleshz";
    private static final Logger LOGGER = LogUtils.getLogger();

    public FleshZ() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.ITEMS.register(modEventBus);
        ModBlocks.BLOCK.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITY.register(modEventBus);

        ModRecipes.SERIALIZERS.register(modEventBus);
        ModRecipes.TYPES.register(modEventBus);
    }
}
