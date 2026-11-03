package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.NotNull;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;

import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.block.ModelBlockRenderer;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class AbstractFramedItemFrameRenderer<TE extends AbstractBaseItemFrameEntity, TState extends RIFRenderState>
    extends AbstractDerivedItemFrameRenderer<TE, TState>
{
    private final BlockRenderDispatcher block_render_dispatcher;

    public AbstractFramedItemFrameRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.block_render_dispatcher = context.getBlockRenderDispatcher();
    }

    @Override
    protected void RenderAdditional(TState state, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(state, pose_stack, buffer.getBuffer(GetRecommendedConsumer()), packed_light);
    }

    protected final RenderType GetRecommendedConsumer() { return RenderType.solid(); }

    protected final void RenderFrame(
            RIFRenderState state,
            PoseStack pose_stack,
            VertexConsumer model_constructor,
            int packed_light
    ) {
        var block_model = block_render_dispatcher.getBlockModel(
            GetFakeDefinition().any().setValue(BlockStateProperties.MAP, state.map_id != null)
        );
        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            pose_stack.translate(-0.5F, -0.5F, -0.5F);
            ModelBlockRenderer.renderModel(
                    pose_stack.last(),
                    model_constructor,
                    block_model,
                    1.0F, 1.0F, 1.0F,
                    packed_light,
                    OverlayTexture.NO_OVERLAY
            );
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    @NotNull
    public abstract StateDefinition<Block, BlockState> GetFakeDefinition();
}
