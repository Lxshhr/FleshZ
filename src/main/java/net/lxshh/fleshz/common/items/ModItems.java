package net.lxshh.fleshz.common.items;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, FleshZ.MOD_ID);

    public static final RegistryObject<Item> ROTTEN_LEATHER = ITEMS.register("rotten_leather", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> HIDE = ITEMS.register("hide", () ->  new Item(new Item.Properties()));
    public static final RegistryObject<Item> PREPARED_HIDE = ITEMS.register("prepared_hide", () ->  new Item(new Item.Properties()));
}
