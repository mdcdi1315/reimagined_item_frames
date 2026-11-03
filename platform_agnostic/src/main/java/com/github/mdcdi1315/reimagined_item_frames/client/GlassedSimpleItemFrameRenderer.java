package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.GlassedSimpleItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public final class GlassedSimpleItemFrameRenderer
        extends AbstractFramedItemFrameRenderer<GlassedSimpleItemFrameEntity, RIFRenderState>
{
    public GlassedSimpleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(RIFRenderState state, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(state, pose_stack, buffer.getBuffer(RenderType.translucent()), packed_light);

        RenderItem(state, 1f, pose_stack, buffer, packed_light);
    }

    @Override
    public RIFRenderState createRenderState() { return new RIFRenderState(); }

    @Override
    public StateDefinition<Block, BlockState> GetFakeDefinition() { return ModStaticDefinitionsBuilder.GLASSED_SIMPLE_ITEM_FRAME_FAKE_DEF; }
}