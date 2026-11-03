package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.SimpleInvisibleItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class SimpleInvisibleItemFrameRenderer
    extends AbstractDerivedItemFrameRenderer<SimpleInvisibleItemFrameEntity, RIFRenderState>
{
    public SimpleInvisibleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(RIFRenderState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        RenderItem(state, 1f, pose_stack, collector);
    }

    @Override
    public RIFRenderState createRenderState() { return new RIFRenderState(); }
}
