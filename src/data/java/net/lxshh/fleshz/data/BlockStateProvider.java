package net.lxshh.fleshz.data;

import net.lxshh.fleshz.FleshZ;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {
    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FleshZ.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        woodRack(ModBlocks.OAK_WOOD_RACK.get(), "oak");
        woodRack(ModBlocks.SPRUCE_WOOD_RACK.get(), "spruce");
        woodRack(ModBlocks.BIRCH_WOOD_RACK.get(), "birch");
        woodRack(ModBlocks.JUNGLE_WOOD_RACK.get(), "jungle");
        woodRack(ModBlocks.ACACIA_WOOD_RACK.get(), "acacia");
        woodRack(ModBlocks.CHERRY_WOOD_RACK.get(), "cherry");
        woodRack(ModBlocks.DARK_OAK_WOOD_RACK.get(), "dark_oak");
        woodRack(ModBlocks.MANGROVE_WOOD_RACK.get(), "mangrove");
        woodRack(ModBlocks.BAMBOO_WOOD_RACK.get(), "bamboo");
    }

    private void woodRack(Block block, String woodType) {
        String blockName = woodType + "_wood_rack";

        ModelFile model = models().withExistingParent(blockName, modLoc("block/rack"))
                .texture("texture", mcLoc("block/" + woodType + "_planks"));

        getVariantBuilder(block)
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.SOUTH)
                .modelForState().modelFile(model).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.WEST)
                .modelForState().modelFile(model).rotationY(90).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.NORTH)
                .modelForState().modelFile(model).rotationY(180).addModel()
                .partialState().with(BlockStateProperties.HORIZONTAL_FACING, Direction.EAST)
                .modelForState().modelFile(model).rotationY(270).addModel();

        simpleBlockItem(block, model);
    }
}
