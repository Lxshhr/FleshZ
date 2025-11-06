package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;

import java.util.function.Consumer;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(PackOutput pOutput) {
        super(pOutput);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
        generateWoodRackRecipe(consumer, ModBlocks.OAK_WOOD_RACK.get(), Items.OAK_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.SPRUCE_WOOD_RACK.get(), Items.SPRUCE_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.BIRCH_WOOD_RACK.get(), Items.BIRCH_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.JUNGLE_WOOD_RACK.get(), Items.JUNGLE_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.ACACIA_WOOD_RACK.get(), Items.ACACIA_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.CHERRY_WOOD_RACK.get(), Items.CHERRY_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.DARK_OAK_WOOD_RACK.get(), Items.DARK_OAK_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.MANGROVE_WOOD_RACK.get(), Items.MANGROVE_SLAB);
        generateWoodRackRecipe(consumer, ModBlocks.BAMBOO_WOOD_RACK.get(), Items.BAMBOO_SLAB);
    }

    private void generateWoodRackRecipe(Consumer<FinishedRecipe> consumer, Block block, Item slabItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###")
                .define('#', slabItem)
                .unlockedBy("has_slab", has(slabItem))
                .save(consumer);
    }
}
