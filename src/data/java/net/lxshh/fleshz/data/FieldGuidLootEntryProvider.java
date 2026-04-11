package net.lxshh.fleshz.data;

import com.google.gson.JsonElement;
import com.mojang.serialization.Codec;
import com.mojang.serialization.JsonOps;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;

import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;

public class FieldGuidLootEntryProvider implements DataProvider {
    private final PackOutput output;

    public FieldGuidLootEntryProvider(PackOutput output) {
        this.output = output;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cachedOutput) {
        Path base = this.output.getOutputFolder(PackOutput.Target.DATA_PACK).resolve("fleshz/fieldguide/loot_modifiers");

        ResourceLocation hide = ModItems.HIDE.getId();
        ResourceLocation leather = mcLoc("leather");

        // Additions modifier
        CustomLootModifier additionsModifier = new CustomLootModifier(
                false,
                List.of(
                        EntryValuePair.ofValue(mcLoc("cow"), hide),
                        EntryValuePair.ofValue(mcLoc("donkey"), hide),
                        EntryValuePair.ofValue(mcLoc("hoglin"), hide),
                        EntryValuePair.ofValue(mcLoc("horse"), hide),
                        EntryValuePair.ofValue(mcLoc("mooshroom"), hide),
                        EntryValuePair.ofValue(mcLoc("llama"), hide),
                        EntryValuePair.ofValue(mcLoc("mule"), hide),
                        EntryValuePair.ofValue(mcLoc("trader_llama"), hide)
                ),
                List.of()
        );

        // Removals modifier
        CustomLootModifier removalsModifier = new CustomLootModifier(
                false,
                List.of(),
                List.of(
                        EntryValuePair.ofValue(mcLoc("cow"), leather),
                        EntryValuePair.ofValue(mcLoc("donkey"), leather),
                        EntryValuePair.ofValue(mcLoc("hoglin"), leather),
                        EntryValuePair.ofValue(mcLoc("horse"), leather),
                        EntryValuePair.ofValue(mcLoc("mooshroom"), leather),
                        EntryValuePair.ofValue(mcLoc("llama"), leather),
                        EntryValuePair.ofValue(mcLoc("mule"), leather),
                        EntryValuePair.ofValue(mcLoc("trader_llama"), leather)
                )
        );

        return CompletableFuture.allOf(
                save(cachedOutput, additionsModifier, base.resolve("fleshz_loot_additions.json")),
                save(cachedOutput, removalsModifier,  base.resolve("fleshz_loot_removals.json"))
        );
    }

    private CompletableFuture<?> save(CachedOutput cache, CustomLootModifier modifier, Path path) {
        JsonElement json = CustomLootModifier.CODEC.encodeStart(JsonOps.INSTANCE, modifier).getOrThrow();
        return DataProvider.saveStable(cache, json.getAsJsonObject(), path);
    }

    @Override
    public String getName() {
        return "Field Guide Loot Modifiers";
    }

    private static ResourceLocation mcLoc(String path) {
        return ResourceLocation.withDefaultNamespace(path);
    }

    //

    public record CustomLootModifier(
            boolean replace,
            List<EntryValuePair> additions,
            List<EntryValuePair> removals
    ) {
        public static final Codec<CustomLootModifier> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        Codec.BOOL.fieldOf("replace").forGetter(CustomLootModifier::replace),
                        EntryValuePair.CODEC.listOf().optionalFieldOf("additions", List.of()).forGetter(CustomLootModifier::additions),
                        EntryValuePair.CODEC.listOf().optionalFieldOf("removals", List.of()).forGetter(CustomLootModifier::removals)
                ).apply(instance, CustomLootModifier::new)
        );
    }

    public record EntryValuePair(
            ResourceLocation entry,
            Optional<ResourceLocation> value,
            Optional<List<ResourceLocation>> values
    ) {
        public static EntryValuePair ofValue(ResourceLocation entry, ResourceLocation value) {
            return new EntryValuePair(entry, Optional.of(value), Optional.empty());
        }

        public static EntryValuePair ofValues(ResourceLocation entry, List<ResourceLocation> values) {
            return new EntryValuePair(entry, Optional.empty(), Optional.of(values));
        }

        public static final Codec<EntryValuePair> CODEC = RecordCodecBuilder.create(instance ->
                instance.group(
                        ResourceLocation.CODEC.fieldOf("entry").forGetter(EntryValuePair::entry),
                        ResourceLocation.CODEC.optionalFieldOf("value").forGetter(EntryValuePair::value),
                        ResourceLocation.CODEC.listOf().optionalFieldOf("values").forGetter(EntryValuePair::values)
                ).apply(instance, EntryValuePair::new)
        );
    }
}