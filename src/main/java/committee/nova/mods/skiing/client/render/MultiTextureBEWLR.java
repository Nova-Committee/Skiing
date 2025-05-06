package committee.nova.mods.skiing.client.render;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import committee.nova.mods.skiing.common.entity.AbstractMultiTextureEntity;
import committee.nova.mods.skiing.common.item.AbstractMultiTextureItem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.BlockEntityWithoutLevelRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.block.model.ItemTransforms;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.NotNull;

public class MultiTextureBEWLR extends BlockEntityWithoutLevelRenderer {
    private EntityType<?> entity;

    public MultiTextureBEWLR(BlockEntityRenderDispatcher renderDispatcher, EntityModelSet entityModelSet) {
        super(renderDispatcher, entityModelSet);
    }

    public MultiTextureBEWLR(EntityType<?> entity) {
        this(Minecraft.getInstance().getBlockEntityRenderDispatcher(), Minecraft.getInstance().getEntityModels());
        this.entity = entity;
    }

    @Override
    public void renderByItem(ItemStack stack, ItemDisplayContext transformType, @NotNull PoseStack poseStack,
                             @NotNull MultiBufferSource buffer, int combinedLight, int combinedOverlay) {
        if (stack.getItem() instanceof AbstractMultiTextureItem) {
            poseStack.pushPose();
            poseStack.translate(0.5, 0, 0.5);
            if (transformType == ItemDisplayContext.GUI) {
                poseStack.mulPose(Axis.YP.rotationDegrees(180));
            }

            if (transformType == ItemDisplayContext.FIXED) {
                poseStack.mulPose(Axis.XN.rotationDegrees(180));
            }

            Minecraft mc = Minecraft.getInstance();
            assert mc.level != null;
            AbstractMultiTextureEntity entity = (AbstractMultiTextureEntity) this.entity.create(mc.level);
            EntityRenderer<? super AbstractMultiTextureEntity> entityRenderer = mc.getEntityRenderDispatcher().getRenderer(entity);
            if (mc.level != null) {
                entity.level = mc.level;
            }

            CompoundTag compoundTag = stack.getTagElement("EntityTag");
            if (compoundTag != null && compoundTag.contains("skiing_material")) {
                Block block = ForgeRegistries.BLOCKS.getValue(new ResourceLocation(compoundTag.getString("skiing_material")));
                if (block != null) {
                    entity.setSkiingMaterial(block);
                }
            }

            entityRenderer.render(entity, 0, mc.getFrameTime(), poseStack, buffer, combinedLight);
            poseStack.popPose();
        }
    }
}
