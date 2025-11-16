package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FleshZ.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        basicItem(ModItems.HIDE.get());
        basicItem(ModItems.PREPARED_HIDE.get());
        basicItem(ModItems.ROTTEN_LEATHER.get());
    }
}
