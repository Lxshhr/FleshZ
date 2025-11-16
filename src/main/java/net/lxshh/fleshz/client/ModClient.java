package net.lxshh.fleshz.client;

import net.lxshh.fleshz.common.blockentity.ModBlockEntities;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

public class ModClient {
    public static void init(IEventBus modEventBus) {
        modEventBus.addListener(ModClient::registerBlockRenderers);
    }

    public static void registerBlockRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerBlockEntityRenderer(ModBlockEntities.WOOD_RACK_ENTITY.get(), ctx -> new WoodRackRenderer());
    }
}
