package committee.nova.mods.skiing.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import committee.nova.mods.skiing.common.block.SkiRackBlock;
import committee.nova.mods.skiing.common.blockentity.SkiRackBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.core.Direction;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.NotNull;

/**
 * @Project: skiing
 * @Author: cnlimiter
 * @CreateTime: 2025/5/6 14:41
 * @Description:
 */
public class SkiRackBlockEntityRenderer implements BlockEntityRenderer<SkiRackBlockEntity> {
    public SkiRackBlockEntityRenderer() {}

    @Override
    public void render(SkiRackBlockEntity blockEntity, float partialTicks, @NotNull PoseStack poseStack, @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        NonNullList<ItemStack> items = blockEntity.getItems();
        BlockState state = blockEntity.getBlockState();
        if (!items.isEmpty()) {

            poseStack.pushPose();

            Direction direction = state.getValue(SkiRackBlock.FACING);
            switch (direction) {
                case EAST -> {
                    poseStack.mulPose(Axis.YP.rotationDegrees(270f));
                    poseStack.translate(0f, 0f, -1f);
                }
                case SOUTH -> {
                    poseStack.mulPose(Axis.YP.rotationDegrees(180f));
                    poseStack.translate(-1f, 0f, -1f);
                }
                case WEST -> {
                    poseStack.mulPose(Axis.YP.rotationDegrees(90f));
                    poseStack.translate(-1f, 0f, 0f);
                }
                default -> {
                }
            }
            poseStack.translate(0.5f, 0.5f, 0.72f);
            for (int i = 0; i < items.size(); i++) {
                ItemStack stack = blockEntity.getItem(i);
                if (i < 2) {
                    if (i == 0) {
                        poseStack.translate(0.3f, 0f, 0f);
                    } else
                        poseStack.translate(-0.5f, 0f, 0f);
                    Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.FIXED, combinedLight, combinedOverlay, poseStack, buffer, blockEntity.getLevel(), OverlayTexture.NO_OVERLAY);
                }
            }
            poseStack.popPose();
        }
    }

}
