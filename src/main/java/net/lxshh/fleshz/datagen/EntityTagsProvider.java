package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.EntityTypeTagsProvider;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class EntityTagsProvider extends EntityTypeTagsProvider {
    public EntityTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, provider, FleshZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.Entities.LEATHER_TO_HIDE)
                .addOptionalTag(mcLoc("cow"))
                .addOptionalTag(mcLoc("donkey"))
                .addOptionalTag(mcLoc("hoglin"))
                .addOptionalTag(mcLoc("horse"))
                .addOptionalTag(mcLoc("mooshroom"))
                .addOptionalTag(mcLoc("llama"))
                .addOptionalTag(mcLoc("mule"))
                .addOptionalTag(mcLoc("trader_llama"));
    }

    private ResourceLocation mcLoc(String path) {
        return ResourceLocation.withDefaultNamespace(path);
    }

}
