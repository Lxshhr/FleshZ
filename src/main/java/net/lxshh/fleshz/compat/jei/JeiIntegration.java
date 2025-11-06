package net.lxshh.fleshz.compat.jei;

import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCatalystRegistration;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
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
        registry.addRecipeCatalyst(ModBlocks.OAK_WOOD_RACK.get(), DryingCategory.DRYING_RACK_TYPE);
    }
}
