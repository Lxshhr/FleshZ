package net.lxshh.fleshz.common.items;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(FleshZ.MOD_ID);

    public static final DeferredItem<Item> ROTTEN_LEATHER = ITEMS.register("rotten_leather", () ->  new Item(new Item.Properties()));
    public static final DeferredItem<Item> HIDE = ITEMS.register("hide", () ->  new Item(new Item.Properties()));
    public static final DeferredItem<Item> PREPARED_HIDE = ITEMS.register("prepared_hide", () ->  new Item(new Item.Properties()));
}
