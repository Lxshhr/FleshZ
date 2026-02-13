package net.lxshh.fleshz.data;

import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.advancements.Criterion;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import org.jetbrains.annotations.Nullable;

import java.util.LinkedHashMap;
import java.util.Map;

public class DryingRecipeBuilder implements RecipeBuilder {
    private final Ingredient ingredient;
    private final ItemStack result;
    private final int dryingTime;
    private final Map<String, Criterion<?>> criteria = new LinkedHashMap<>();

    public DryingRecipeBuilder(Ingredient ingredient, ItemStack result, int dryingTime) {
        this.ingredient = ingredient;
        this.result = result;
        this.dryingTime = dryingTime;
    }

    public static DryingRecipeBuilder dryingRecipe(Ingredient ingredient, ItemStack result, int dryingTime) {
        return new DryingRecipeBuilder(ingredient, result, dryingTime);
    }

    public static DryingRecipeBuilder dryingRecipe(ItemLike ingredient, ItemLike result, int dryingTime) {
        return new DryingRecipeBuilder(Ingredient.of(ingredient), new ItemStack(result), dryingTime);
    }

    @Override
    public RecipeBuilder unlockedBy(String criterionName, Criterion<?> criterion) {
        this.criteria.put(criterionName, criterion);
        return this;
    }

    @Override
    public RecipeBuilder group(@Nullable String groupName) {
        return this;
    }

    @Override
    public Item getResult() {
        return result.getItem();
    }

    protected Recipe<?> recipe() {
        return new RackRecipe(ingredient, result, dryingTime);
    }

    @Override
    public void save(RecipeOutput output, ResourceLocation recipeId) {
        output.accept(recipeId, recipe(), null);
    }
}
