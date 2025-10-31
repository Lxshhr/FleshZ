package net.lxshh.fleshz.common.loot_modifiers;

import com.mojang.serialization.Codec;
import net.lxshh.fleshz.FleshZ;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModLootModifiers {
    public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> LOOT_MODIFIERS = DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FleshZ.MOD_ID);

    public static final RegistryObject<Codec<? extends IGlobalLootModifier>> LEATHER_TO_HIDE = LOOT_MODIFIERS.register(
            "leather_to_hide", LeatherHideModifier.CODEC
    );
}
