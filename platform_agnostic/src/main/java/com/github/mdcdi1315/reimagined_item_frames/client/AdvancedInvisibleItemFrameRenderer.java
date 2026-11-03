package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedInvisibleItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class AdvancedInvisibleItemFrameRenderer
    extends AbstractDerivedItemFrameRenderer<AdvancedInvisibleItemFrameEntity, RIFAdvancedRenderState>
{
    public AdvancedInvisibleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(RIFAdvancedRenderState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        RenderItem(state, state.item_display_scale / 100f, pose_stack, collector);
    }

    @Override
    public void extractRenderState(AdvancedInvisibleItemFrameEntity entity, RIFAdvancedRenderState state, float partial_ticks)
    {
        super.extractRenderState(entity, state, partial_ticks);
        state.item_display_scale = entity.GetItemDisplayScale();
    }

    @Override
    public RIFAdvancedRenderState createRenderState() { return new RIFAdvancedRenderState(); }

    @Override
    protected int getBlockLightLevel(AdvancedInvisibleItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }
}
