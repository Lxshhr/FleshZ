package net.lxshh.fleshz.datagen;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.conditions.ModLoadedCondition;

import java.util.concurrent.CompletableFuture;

public class RecipeProvider extends net.minecraft.data.recipes.RecipeProvider {
    public RecipeProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries);
    }

    @Override
    protected void buildRecipes(RecipeOutput output) {
        generateWoodRackRecipe(output, ModBlocks.OAK_WOOD_RACK.get(), Items.OAK_SLAB);
        generateWoodRackRecipe(output, ModBlocks.SPRUCE_WOOD_RACK.get(), Items.SPRUCE_SLAB);
        generateWoodRackRecipe(output, ModBlocks.BIRCH_WOOD_RACK.get(), Items.BIRCH_SLAB);
        generateWoodRackRecipe(output, ModBlocks.JUNGLE_WOOD_RACK.get(), Items.JUNGLE_SLAB);
        generateWoodRackRecipe(output, ModBlocks.ACACIA_WOOD_RACK.get(), Items.ACACIA_SLAB);
        generateWoodRackRecipe(output, ModBlocks.CHERRY_WOOD_RACK.get(), Items.CHERRY_SLAB);
        generateWoodRackRecipe(output, ModBlocks.DARK_OAK_WOOD_RACK.get(), Items.DARK_OAK_SLAB);
        generateWoodRackRecipe(output, ModBlocks.MANGROVE_WOOD_RACK.get(), Items.MANGROVE_SLAB);
        generateWoodRackRecipe(output, ModBlocks.BAMBOO_WOOD_RACK.get(), Items.BAMBOO_SLAB);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.PREPARED_HIDE.get())
                .requires(Items.SUGAR, 3)
                .requires(ModItems.HIDE.get())
                .unlockedBy("has_hide", has(ModItems.HIDE.get()))
                .save(output);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ROTTEN_LEATHER.get())
                .pattern("##")
                .define('#', Items.ROTTEN_FLESH)
                .unlockedBy("has_rotten_flesh", has(Items.ROTTEN_FLESH))
                .save(output);

        DryingRecipeBuilder.dryingRecipe(Items.KELP, Items.DRIED_KELP, 2400)
                .save(output, modLoc("rack/dried_kelp"));
        DryingRecipeBuilder.dryingRecipe(ModItems.ROTTEN_LEATHER.get(),  ModItems.HIDE.get(), 2400)
                .save(output, modLoc("rack/hide_from_rotten_leather"));
        DryingRecipeBuilder.dryingRecipe(ModItems.PREPARED_HIDE.get(),  Items.LEATHER, 4800)
                .save(output, modLoc("rack/leather"));


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.HIDE.get())
                .pattern("##")
                .pattern("##")
                .define('#', Items.RABBIT_HIDE)
                .unlockedBy("has_hide", has(Items.RABBIT_HIDE))
                .save(output, ResourceLocation.withDefaultNamespace("leather"));
    }

    private ResourceLocation modLoc(String name) {
        return ResourceLocation.fromNamespaceAndPath(FleshZ.MOD_ID, name);
    }

    private void generateWoodRackRecipe(RecipeOutput output, Block block, Item slabItem) {
        ShapedRecipeBuilder.shaped(RecipeCategory.BUILDING_BLOCKS, block)
                .pattern("###")
                .define('#', slabItem)
                .unlockedBy("has_slab", has(slabItem))
                .save(output);
    }
}
