package net.lxshh.fleshz.compat.jei;

import mezz.jei.api.gui.builder.IRecipeLayoutBuilder;
import mezz.jei.api.gui.drawable.IDrawable;
import mezz.jei.api.gui.widgets.IRecipeExtrasBuilder;
import mezz.jei.api.helpers.IGuiHelper;
import mezz.jei.api.recipe.IFocusGroup;
import mezz.jei.api.recipe.RecipeIngredientRole;
import mezz.jei.api.recipe.RecipeType;
import mezz.jei.api.recipe.category.AbstractRecipeCategory;
import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.items.ModItems;
import net.lxshh.fleshz.common.recipes.RackRecipe;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;

public class DryingCategory extends AbstractRecipeCategory<RackRecipe> {
    public static final ResourceLocation UID = FleshZ.loc("rack_drying");

    public static final RecipeType<RackRecipe> DRYING_RACK_TYPE = new RecipeType<>(UID, RackRecipe.class);

    private final IDrawable slot;

    public DryingCategory(IGuiHelper helper) {
        super(DRYING_RACK_TYPE, Component.translatable("jei.category.fleshz.drying"), helper.createDrawableItemLike(ModItems.ROTTEN_LEATHER.get()), 88, 32);
        this.slot = helper.getSlotDrawable();
    }

    @Override
    public void setRecipe(IRecipeLayoutBuilder builder, RackRecipe recipe, IFocusGroup focuses) {
        builder.addSlot(RecipeIngredientRole.INPUT, 6, 8).setStandardSlotBackground().addIngredients(recipe.getIngredient());
        builder.addSlot(RecipeIngredientRole.OUTPUT, 64, 8).setOutputSlotBackground().addItemStack(recipe.getResultItem(null));
    }

    @Override
    public void createRecipeExtras(IRecipeExtrasBuilder builder, RackRecipe recipe, IFocusGroup focuses) {
        builder.addRecipeArrow().setPosition(32, 8);
    }
}
