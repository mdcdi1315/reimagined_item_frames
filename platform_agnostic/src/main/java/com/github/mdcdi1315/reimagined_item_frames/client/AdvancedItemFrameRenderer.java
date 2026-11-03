package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;

public final class AdvancedItemFrameRenderer
    extends AbstractFramedItemFrameRenderer<AdvancedItemFrameEntity, RIFAdvancedRenderState>
{
    public AdvancedItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    public void extractRenderState(AdvancedItemFrameEntity entity, RIFAdvancedRenderState state, float partial_ticks)
    {
        super.extractRenderState(entity, state, partial_ticks);
        state.item_display_scale = entity.GetItemDisplayScale();
    }

    @Override
    public RIFAdvancedRenderState createRenderState() { return new RIFAdvancedRenderState(); }

    @Override
    protected int getBlockLightLevel(AdvancedItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }

    @Override
    public StateDefinition<Block, BlockState> GetFakeDefinition() { return ModStaticDefinitionsBuilder.ADVANCED_ITEM_FRAME_FAKE_DEF; }

    @Override
    protected void RenderAdditional(RIFAdvancedRenderState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        super.RenderAdditional(state, pose_stack, collector);

        RenderItem(state, state.item_display_scale / 100f, pose_stack, collector);
    }
}
