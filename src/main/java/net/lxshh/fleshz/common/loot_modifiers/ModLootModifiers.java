package net.lxshh.fleshz.common.loot_modifiers;

import com.mojang.serialization.MapCodec;
import net.lxshh.fleshz.FleshZ;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModLootModifiers {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(NeoForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FleshZ.MOD_ID);

    public static final Supplier<MapCodec<LeatherHideModifier>> LEATHER_TO_HIDE = LOOT_MODIFIERS.register(
            "leather_to_hide", () -> LeatherHideModifier.CODEC);
}
