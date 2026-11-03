package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.SimpleItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public final class SimpleItemFrameRenderer
    extends AbstractFramedItemFrameRenderer<SimpleItemFrameEntity, RIFRenderState>
{
    public SimpleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(RIFRenderState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        super.RenderAdditional(state, pose_stack, collector);

        RenderItem(state, 1f, pose_stack, collector);
    }

    @Override
    public RIFRenderState createRenderState() { return new RIFRenderState(); }

    @Override
    public StateDefinition<Block, BlockState> GetFakeDefinition() { return ModStaticDefinitionsBuilder.SIMPLE_ITEM_FRAME_FAKE_DEF; }
}
