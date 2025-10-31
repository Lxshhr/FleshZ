package net.lxshh.fleshz.common.recipes;

import com.google.gson.JsonObject;
import net.minecraft.core.RegistryAccess;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;

public class RackRecipe implements Recipe<SimpleContainer> {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int dryingTime;
    private final ResourceLocation id;

    public RackRecipe(ResourceLocation id, Ingredient ingredient, ItemStack result, int dryingTime) {
        this.id = id;
        this.ingredient = ingredient;
        this.result = result;
        this.dryingTime = dryingTime;
    }

    @Override
    public boolean matches(SimpleContainer simpleContainer, Level level) {
        if (level.isClientSide) {
            return false;
        }

        return this.ingredient.test(simpleContainer.getItem(0));
    }

    @Override
    public ItemStack assemble(SimpleContainer simpleContainer, RegistryAccess registryAccess) {
        return this.result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int i, int i1) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return this.result;
    }

    public Ingredient getIngredient() {
        return this.ingredient;
    }

    public int getDryingTime() {
        return this.dryingTime;
    }

    @Override
    public ResourceLocation getId() {
        return this.id;
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

        @Override
        public RackRecipe fromJson(ResourceLocation recipeId, JsonObject json) {
            Ingredient ingredient = Ingredient.fromJson(GsonHelper.getAsJsonObject(json, "ingredient"));
            ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
            int dryingTime = GsonHelper.getAsInt(json, "drying_time", 600);

            return new RackRecipe(recipeId, ingredient, result, dryingTime);
        }

        @Override
        public @Nullable RackRecipe fromNetwork(ResourceLocation recipeId, FriendlyByteBuf buffer) {
            Ingredient ingredient = Ingredient.fromNetwork(buffer);
            ItemStack result = buffer.readItem();
            int dryingTime = buffer.readInt();

            return new RackRecipe(recipeId, ingredient, result, dryingTime);
        }

        @Override
        public void toNetwork(FriendlyByteBuf buffer, RackRecipe recipe) {
            recipe.ingredient.toNetwork(buffer);
            buffer.writeItem(recipe.result);
            buffer.writeInt(recipe.dryingTime);
        }
    }
}
