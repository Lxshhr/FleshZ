package net.lxshh.fleshz.client;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import net.lxshh.fleshz.common.blockentity.WoodRackEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.core.Direction;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public class WoodRackRenderer implements BlockEntityRenderer<WoodRackEntity> {

    @Override
    public void render(WoodRackEntity woodRackEntity, float tickDelta, PoseStack poseStack, MultiBufferSource multiBufferSource, int light, int overlay) {
        if (!woodRackEntity.isEmpty() && woodRackEntity.getLevel() != null) {
            BlockState state = woodRackEntity.getLevel().getBlockState(woodRackEntity.getBlockPos());
            if (!state.isAir()) {
                Direction direction = state.getValue(BlockStateProperties.HORIZONTAL_FACING);
                poseStack.pushPose();
                if (direction == Direction.NORTH) {
                    poseStack.scale(1.8F, 1.8F, 1.8F);
                    poseStack.translate(0.28D, 0.12D, 0.5D);
                } else if (direction == Direction.SOUTH) {
                    poseStack.scale(1.8F, 1.8F, 1.8F);
                    poseStack.translate(0.28D, 0.12D, 0.05D);
                } else if (direction == Direction.EAST) {
                    poseStack.scale(1.8F, 1.8F, 1.8F);
                    poseStack.translate(0.05D, 0.12D, 0.28D);
                    poseStack.mulPose(Axis.YP.rotationDegrees(90F));
                } else if (direction == Direction.WEST) {
                    poseStack.scale(1.8F, 1.8F, 1.8F);
                    poseStack.translate(0.5D, 0.12D, 0.28D);
                    poseStack.mulPose(Axis.YP.rotationDegrees(90F));
                }
                Minecraft.getInstance().getItemRenderer().renderStatic(woodRackEntity.getStack(), ItemDisplayContext.GROUND, light, overlay, poseStack, multiBufferSource, woodRackEntity.getLevel(), 0);
                poseStack.popPose();
            }
        }
    }
}
