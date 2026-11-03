package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.NotNull;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MapRenderer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.item.ItemModelResolver;
import net.minecraft.client.renderer.texture.OverlayTexture;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public abstract class AbstractDerivedItemFrameRenderer<TE extends AbstractBaseItemFrameEntity, TState extends RIFRenderState>
    extends EntityRenderer<TE, TState>
{
    private final MapRenderer map_renderer;
    private final ItemModelResolver item_model_resolver;

    protected AbstractDerivedItemFrameRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.map_renderer = context.getMapRenderer();
        this.item_model_resolver = context.getItemModelResolver();
    }

    @Override
    public final void render(TState render_state, PoseStack pose_stack, MultiBufferSource buffer_source, int packed_light)
    {
        super.render(render_state, pose_stack, buffer_source, packed_light);
        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            SetupFrameRendering(render_state, pose_stack);
            RenderAdditional(render_state, pose_stack, buffer_source, packed_light);
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    @Override
    public void extractRenderState(TE entity, TState state, float partial_ticks)
    {
        super.extractRenderState(entity, state, partial_ticks);
        state.direction = entity.getDirection();
        ItemStack itemstack = entity.getItem();
        this.item_model_resolver.updateForNonLiving(state.item, itemstack, ItemDisplayContext.FIXED, entity);
        state.rotation = entity.getRotation();
        state.map_id = null;
        if (!itemstack.isEmpty())
        {
            MapId mapid = entity.getFramedMapId(itemstack);
            if (mapid != null) {
                MapItemSavedData mapitemsaveddata = entity.level().getMapData(mapid);
                if (mapitemsaveddata != null) {
                    this.map_renderer.extractRenderState(mapid, mapitemsaveddata, state.map_render_state);
                    state.map_id = mapid;
                }
            }
        }
    }

    protected abstract void RenderAdditional(TState state, PoseStack pose_stack, MultiBufferSource buffer, int packed_light);

    protected final void SetupFrameRendering(TState state, PoseStack pose_stack)
    {
        Vec3 vec3 = this.getRenderOffset(state);

        pose_stack.translate(-vec3.x(), -vec3.y(), -vec3.z());

        Direction direction = state.direction;

        pose_stack.translate(direction.getStepX() * 0.46875d, direction.getStepY() * 0.46875d, direction.getStepZ() * 0.46875d);

        float xp, yp;
        if (direction.getAxis().isHorizontal()) {
            xp = 0.0F;
            yp = 180.0F - direction.toYRot();
        } else {
            xp = (float)(-90 * direction.getAxisDirection().getStep());
            yp = 180.0F;
        }

        pose_stack.mulPose(Axis.XP.rotationDegrees(xp));
        pose_stack.mulPose(Axis.YP.rotationDegrees(yp));
    }

    protected final void RenderItem(
            RIFRenderState state,
            float display_scale,
            PoseStack pose_stack,
            MultiBufferSource buffer,
            int packed_light
    ) {
        if (state.item.isEmpty()) { return; }
        pose_stack.translate(0.0F, 0.0F, 0.5F);
        if (state.map_id == null) {
            pose_stack.mulPose(Axis.ZP.rotationDegrees(state.rotation));
            pose_stack.scale(display_scale, display_scale, display_scale);
            state.item.render(pose_stack, buffer, packed_light, OverlayTexture.NO_OVERLAY);
        } else {
            pose_stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
            display_scale *= 0.0078125F;
            pose_stack.scale(display_scale, display_scale, display_scale);
            pose_stack.translate(-64.0F, -64.0F, -1.0F);
            // pose_stack.translate(0.0F, 0.0F, -1.0F);
            map_renderer.render(state.map_render_state, pose_stack, buffer, true, packed_light);
        }
    }

    @Override
    public final Vec3 getRenderOffset(TState render_state)
    {
        return new Vec3(render_state.direction.getStepX() * 0.3d, -0.25d, render_state.direction.getStepZ() * 0.3d);
    }

    @NotNull
    @Override
    public abstract TState createRenderState();

    protected final boolean shouldShowName(TE entity, double d)
    {
        return Minecraft.renderNames() &&
                this.entityRenderDispatcher.crosshairPickEntity == entity &&
                entity.getItem().getCustomName() != null;
    }

    @Override
    protected int getBlockLightLevel(TE entity, BlockPos pos) { return 0; }
}
