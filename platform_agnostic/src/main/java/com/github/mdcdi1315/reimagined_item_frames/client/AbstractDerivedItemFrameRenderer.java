package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Func2;

import com.github.mdcdi1315.basemodslib.utils.Extensions;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;
import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.inventory.InventoryMenu;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public abstract class AbstractDerivedItemFrameRenderer<TE extends AbstractBaseItemFrameEntity>
    extends EntityRenderer<TE>
{
    private final ItemRenderer item_renderer;

    protected AbstractDerivedItemFrameRenderer(EntityRendererProvider.Context context) {
        super(context);
        this.item_renderer = context.getItemRenderer();
    }

    @Override
    public final void render(TE entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        if (this.shouldShowName(entity)) {
            this.renderNameTag(entity, entity.getDisplayName(), pose_stack, buffer, packed_light, partial_ticks);
        }

        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            SetupFrameRendering(entity, partial_ticks, pose_stack);
            RenderAdditional(entity, entity_yaw, partial_ticks, pose_stack, buffer, packed_light);
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    protected abstract void RenderAdditional(TE entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light);

    protected final void SetupFrameRendering(TE entity, float partial_ticks, PoseStack pose_stack)
    {
        Vec3 vec3 = this.getRenderOffset(entity, partial_ticks);

        pose_stack.translate(-vec3.x(), -vec3.y(), -vec3.z());

        Direction direction = entity.getDirection();

        pose_stack.translate(direction.getStepX() * 0.46875d, direction.getStepY() * 0.46875d, direction.getStepZ() * 0.46875d);

        pose_stack.mulPose(Axis.XP.rotationDegrees(entity.getXRot()));

        pose_stack.mulPose(Axis.YP.rotationDegrees(180.0F - entity.getYRot()));
    }

    protected final void RenderItemStackToFrame(TE entity, float display_scale, ItemStack stack, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        // pose_stack.scale(0.5f, 0.5f, 0.5f);
        pose_stack.scale(display_scale, display_scale, display_scale);
        this.item_renderer.renderStatic(stack, ItemDisplayContext.FIXED, packed_light, OverlayTexture.NO_OVERLAY, pose_stack, buffer, entity.level(), entity.getId());
    }

    protected final void RenderFrame(
            TE entity,
            PoseStack pose_stack,
            MultiBufferSource buffer,
            BlockRenderDispatcher dispatcher,
            Func2<? super TE, ModelResourceLocation> location_getter,
            int packed_light
    ) {
        ModelManager modelmanager = dispatcher.getBlockModelShaper().getModelManager();
        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            pose_stack.translate(-0.5F, -0.5F, -0.5F);
            dispatcher.getModelRenderer().renderModel(pose_stack.last(), buffer.getBuffer(Sheets.solidBlockSheet()), null, modelmanager.getModel(location_getter.function(entity)), 1.0F, 1.0F, 1.0F, packed_light, OverlayTexture.NO_OVERLAY);
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    public final Vec3 getRenderOffset(TE entity, float partial_ticks) {
        return new Vec3(entity.getDirection().getStepX() * 0.3d, -0.25d, entity.getDirection().getStepZ() * 0.3d);
    }

    @Override
    public ResourceLocation getTextureLocation(TE entity) { return InventoryMenu.BLOCK_ATLAS; }

    protected final boolean shouldShowName(TE entity)
    {
        if (
                Minecraft.renderNames() &&
                        !entity.getItem().isEmpty() &&
                        entity.getItem().has(DataComponents.CUSTOM_NAME) &&
                        this.entityRenderDispatcher.crosshairPickEntity == entity
        ) {
            return this.entityRenderDispatcher.distanceToSqr(entity) < Extensions.Square(entity.isDiscrete() ? 32.0D : 64.0D);
        } else {
            return false;
        }
    }

    @Override
    protected int getBlockLightLevel(TE entity, BlockPos pos) { return 0; }
}
