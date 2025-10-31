package net.lxshh.fleshz.common.recipes;

import net.lxshh.fleshz.FleshZ;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModRecipes {
    public static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, FleshZ.MOD_ID);

    public static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, FleshZ.MOD_ID);

    public static final RegistryObject<RecipeSerializer<RackRecipe>> RACK_SERIALIZER =
            SERIALIZERS.register("rack_drying", RackRecipe.Serializer::new);

    public static final RegistryObject<RecipeType<RackRecipe>> RACK_TYPE =
            TYPES.register("rack_drying", () -> new RecipeType<RackRecipe>() {
                @Override
                public String toString() {
                    return "rack_drying";
                }
            });
}
