package net.lxshh.fleshz.compat.everycomp;

import net.lxshh.fleshz.client.WoodRackRenderer;
import net.lxshh.fleshz.common.ModTags;
import net.lxshh.fleshz.common.blockentity.ModBlockEntities;
import net.lxshh.fleshz.common.blockentity.WoodRackEntity;
import net.lxshh.fleshz.common.blocks.ModBlocks;
import net.lxshh.fleshz.common.blocks.WoodRackBlock;
import net.mehvahdjukaar.every_compat.EveryCompat;
import net.mehvahdjukaar.every_compat.api.PaletteStrategies;
import net.mehvahdjukaar.every_compat.api.SimpleEntrySet;
import net.mehvahdjukaar.every_compat.api.SimpleModule;
import net.mehvahdjukaar.moonlight.api.platform.ClientHelper;
import net.mehvahdjukaar.moonlight.api.set.wood.VanillaWoodTypes;
import net.mehvahdjukaar.moonlight.api.set.wood.WoodType;
import net.mehvahdjukaar.moonlight.api.util.Utils;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.CreativeModeTabs;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

public class WoodGood extends SimpleModule {

    public final SimpleEntrySet<WoodType, Block> woodRack;

    public WoodGood(String modId) {
        super(modId, "fleshz", EveryCompat.MOD_ID);

        this.woodRack = SimpleEntrySet.builder(WoodType.class, "wood_rack",
                        ModBlocks.OAK_WOOD_RACK, () -> VanillaWoodTypes.OAK,
                        w -> new WoodRackBlock(BlockBehaviour.Properties.copy(w.planks).strength(0.25F, 0.5F).noOcclusion())
                )
                .requiresChildren("slab")
                .setTabKey(CreativeModeTabs.BUILDING_BLOCKS)
                .addTag(ModTags.Items.RACKS, Registries.ITEM)
                .addTag(ModTags.Blocks.RACKS, Registries.BLOCK)
                .addTag(BlockTags.MINEABLE_WITH_AXE, Registries.BLOCK)
                .addTile(ModBlockEntities.WOOD_RACK_ENTITY)
                .defaultRecipe()
                .build();

        this.addEntry(woodRack);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void registerBlockEntityRenderers(ClientHelper.BlockEntityRendererEvent event) {
        super.registerBlockEntityRenderers(event);
        event.register(woodRack.getTile(WoodRackEntity.class), c -> new WoodRackRenderer());
    }
}
