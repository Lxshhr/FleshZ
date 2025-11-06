package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.loot.LootTableProvider;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Collections;
import java.util.List;

@Mod.EventBusSubscriber(modid = FleshZ.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataProviders {
    @SubscribeEvent
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        generator.addProvider(event.includeServer(), new RecipeProvider(packOutput));

        generator.addProvider(event.includeServer(),
                new LootTableProvider(packOutput, Collections.emptySet(),
                        List.of(new LootTableProvider.SubProviderEntry(
                                BlockLootTableProvider::new,
                                LootContextParamSets.BLOCK
                        ))
                )
        );

        generator.addProvider(event.includeClient(), new BlockStateProvider(packOutput, existingFileHelper));
        generator.addProvider(event.includeClient(), new ItemModelProvider(packOutput, existingFileHelper));
    }
}
