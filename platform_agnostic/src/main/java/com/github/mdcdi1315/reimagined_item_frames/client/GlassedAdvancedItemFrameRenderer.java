package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.GlassedAdvancedItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class GlassedAdvancedItemFrameRenderer
        extends AbstractFramedItemFrameRenderer<GlassedAdvancedItemFrameEntity, RIFAdvancedRenderState>
{
    public GlassedAdvancedItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected int getBlockLightLevel(GlassedAdvancedItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }

    @Override
    protected void RenderAdditional(RIFAdvancedRenderState state, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(state, pose_stack, buffer.getBuffer(RenderType.translucent()), packed_light);

        RenderItem(state, state.item_display_scale / 100f, pose_stack, buffer, packed_light);
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
