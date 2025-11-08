package net.lxshh.fleshz.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.ModTags;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeManager;

import java.util.List;

@JeiPlugin
public class JeiIntegration implements IModPlugin {

    @Override
    public ResourceLocation getPluginUid() {
        return new ResourceLocation(FleshZ.MOD_ID, "jei");
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(new DryingCategory(registry.getJeiHelpers().getGuiHelper()));
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        RecipeManager recipeManager = Minecraft.getInstance().level.getRecipeManager();

        List<RackRecipe> dryingRecipe = recipeManager.getAllRecipesFor(ModRecipes.RACK_TYPE.get());
        registry.addRecipes(DryingCategory.DRYING_RACK_TYPE, dryingRecipe);
    }

    @Override
    public void registerRecipeCatalysts(IRecipeCatalystRegistration registry) {
        addRecipeCatalyst(registry, ModTags.Items.RACKS, DryingCategory.DRYING_RACK_TYPE);
    }

    private static void addRecipeCatalyst(IRecipeCatalystRegistration registry, TagKey<Item> tag, RecipeType<?> recipeType) {
        List<Item> tagItems = BuiltInRegistries.ITEM.getOrCreateTag(tag).stream().map(Holder::value).toList();
        tagItems.forEach(item -> registry.addRecipeCatalyst(new ItemStack(item), recipeType));
    }
}
