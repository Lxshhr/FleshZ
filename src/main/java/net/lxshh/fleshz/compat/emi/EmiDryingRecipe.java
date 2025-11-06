package net.lxshh.fleshz.compat.emi;

import dev.emi.emi.api.recipe.EmiRecipe;
import dev.emi.emi.api.recipe.EmiRecipeCategory;
import dev.emi.emi.api.render.EmiTexture;
import dev.emi.emi.api.stack.EmiIngredient;
import dev.emi.emi.api.stack.EmiStack;
import dev.emi.emi.api.widget.WidgetHolder;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class EmiDryingRecipe implements EmiRecipe {
    private final ResourceLocation id;
    private final EmiIngredient input;
    private final EmiStack output;

    public EmiDryingRecipe(ResourceLocation id, RackRecipe recipe) {
        this.id = id;
        this.input = EmiIngredient.of(recipe.getIngredient());
        this.output = EmiStack.of(recipe.getResultItem(null));
    }

    @Override
    public EmiRecipeCategory getCategory() {
        return EmiIntegration.RACK_CATEGORY;
    }

    @Override
    public @Nullable ResourceLocation getId() {
        return this.id;
    }

    @Override
    public List<EmiIngredient> getInputs() {
        return List.of(this.input);
    }

    @Override
    public List<EmiStack> getOutputs() {
        return List.of(this.output);
    }

    @Override
    public int getDisplayWidth() {
        return 96;
    }

    @Override
    public int getDisplayHeight() {
        return 34;
    }

    @Override
    public void addWidgets(WidgetHolder widget) {
        widget.addTexture(EmiTexture.EMPTY_ARROW, 36, 9);
        widget.addSlot(this.input, 8, 9);
        widget.addSlot(this.output, 72, 9).recipeContext(this);
    }
}
