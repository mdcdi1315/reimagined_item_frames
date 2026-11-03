package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.Diagnostics.CodeAnalysis.NotNull;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.block.BlockModelResolver;
import net.minecraft.client.renderer.entity.ItemFrameRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;

public abstract class AbstractFramedItemFrameRenderer<TE extends AbstractBaseItemFrameEntity, TState extends RIFRenderState>
    extends AbstractDerivedItemFrameRenderer<TE, TState>
{
    private final BlockModelResolver block_model_resolver;

    public AbstractFramedItemFrameRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        this.block_model_resolver = context.getBlockModelResolver();
    }

    @Override
    protected void RenderAdditional(TState state, PoseStack pose_stack, SubmitNodeCollector collector)
    {
        RenderFrame(state, pose_stack, collector);
    }

    @Override
    public void extractRenderState(TE entity, TState state, float partial_ticks)
    {
        super.extractRenderState(entity, state, partial_ticks);

        block_model_resolver.update(
                state.frame_model,
                GetFakeDefinition().any().setValue(BlockStateProperties.MAP, state.map_id != null),
                ItemFrameRenderer.BLOCK_DISPLAY_CONTEXT
        );
    }

    protected final void RenderFrame(
            RIFRenderState state,
            PoseStack pose_stack,
            SubmitNodeCollector collector
    ) {
        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            pose_stack.translate(-0.5F, -0.5F, -0.5F);
            state.frame_model.submit(
                    pose_stack,
                    collector,
                    state.lightCoords,
                    OverlayTexture.NO_OVERLAY,
                    state.outlineColor
            );
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    @NotNull
    public abstract StateDefinition<Block, BlockState> GetFakeDefinition();
}
