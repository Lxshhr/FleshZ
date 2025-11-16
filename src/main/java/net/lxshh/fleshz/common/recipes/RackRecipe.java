package net.lxshh.fleshz.common.recipes;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public class RackRecipe implements Recipe<RecipeInput> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int dryingTime;

    public RackRecipe(Ingredient ingredient, ItemStack result, int dryingTime) {
        this.ingredient = ingredient;
        this.result = result;
        this.dryingTime = dryingTime;
    }

    @Override
    public boolean matches(RecipeInput input, Level level) {
        if (level.isClientSide()) {
            return false;
        }
        return this.ingredient.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(RecipeInput recipeInput, HolderLookup.Provider provider) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider provider) {
        return this.result;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public int getDryingTime() {
        return this.dryingTime;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipes.RACK_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipes.RACK_TYPE.get();
    }

    public static class Serializer implements RecipeSerializer<RackRecipe> {
        public static final MapCodec<RackRecipe> CODEC = RecordCodecBuilder.mapCodec(i -> i.group(
            Ingredient.CODEC.fieldOf("ingredient").forGetter(r -> r.ingredient),
            ItemStack.STRICT_CODEC.fieldOf("result").forGetter(r -> r.result),
            Codec.INT.fieldOf("drying_time").forGetter(r -> r.dryingTime)
        ).apply(i, RackRecipe::new));

        public static final StreamCodec<RegistryFriendlyByteBuf, RackRecipe> STREAM_CODEC = StreamCodec.composite(
            Ingredient.CONTENTS_STREAM_CODEC, r -> r.ingredient,
            ItemStack.STREAM_CODEC, r -> r.result,
            ByteBufCodecs.INT, r -> r.dryingTime,
            RackRecipe::new
        );

        @Override
        public MapCodec<RackRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, RackRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
