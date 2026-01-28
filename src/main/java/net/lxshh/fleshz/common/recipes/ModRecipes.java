package net.lxshh.fleshz.common.recipes;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(Registries.RECIPE_SERIALIZER, FleshZ.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(Registries.RECIPE_TYPE, FleshZ.MOD_ID);

    public static final Supplier<RecipeSerializer<RackRecipe>> RACK_SERIALIZER =
            SERIALIZERS.register("rack_drying", RackRecipe.Serializer::new);

    public static final Supplier<RecipeType<RackRecipe>> RACK_TYPE =
            TYPES.register("rack_drying", () -> RecipeType.simple(FleshZ.loc("rack_drying")));
}
