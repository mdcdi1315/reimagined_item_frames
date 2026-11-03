package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.GlassedAdvancedItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class GlassedAdvancedItemFrameRenderer
        extends AbstractFramedItemFrameRenderer<GlassedAdvancedItemFrameEntity, RIFAdvancedRenderState>
{
    public GlassedAdvancedItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected int getBlockLightLevel(GlassedAdvancedItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }

    @Override
    protected void RenderAdditional(RIFAdvancedRenderState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        RenderFrame(state, pose_stack, collector);

        RenderItem(state, state.item_display_scale / 100f, pose_stack, collector);
    }

    @Override
    public void extractRenderState(GlassedAdvancedItemFrameEntity entity, RIFAdvancedRenderState state, float partial_ticks)
    {
        super.extractRenderState(entity, state, partial_ticks);
        state.item_display_scale = entity.GetItemDisplayScale();
    }

    @Override
    public RIFAdvancedRenderState createRenderState() { return new RIFAdvancedRenderState(); }

    @Override
    public StateDefinition<Block, BlockState> GetFakeDefinition() { return ModStaticDefinitionsBuilder.GLASSED_ADVANCED_ITEM_FRAME_FAKE_DEF; }
}
