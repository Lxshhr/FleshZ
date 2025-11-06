package net.lxshh.fleshz.compat.emi;

import dev.emi.emi.api.EmiEntrypoint;
import dev.emi.emi.api.EmiPlugin;
import dev.emi.emi.api.EmiRegistry;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiStack;
import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.items.ModItems;
import net.lxshh.fleshz.common.recipes.ModRecipes;
import net.minecraft.resources.ResourceLocation;

@EmiEntrypoint
public class EmiIntegration implements EmiPlugin {

    public static final EmiRecipeCategory RACK_CATEGORY = new EmiRecipeCategory(ResourceLocation.tryBuild(FleshZ.MOD_ID, "rack"), EmiStack.of(ModItems.ROTTEN_LEATHER.get()));

    @Override
    public void register(EmiRegistry registry) {
        registry.addCategory(RACK_CATEGORY);
        registry.addWorkstation(RACK_CATEGORY, EmiStack.of(ModBlocks.OAK_WOOD_RACK.get()));

        registry.getRecipeManager().getAllRecipesFor(ModRecipes.RACK_TYPE.get())
                .forEach(recipe ->
                        registry.addRecipe(new EmiDryingRecipe(recipe.getId(), recipe))
                );
    }
}
