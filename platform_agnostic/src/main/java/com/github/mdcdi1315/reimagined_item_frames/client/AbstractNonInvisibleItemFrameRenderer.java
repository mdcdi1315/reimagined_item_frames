package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.AbstractBaseItemFrameEntity;

import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public abstract class AbstractNonInvisibleItemFrameRenderer<TE extends AbstractBaseItemFrameEntity>
    extends AbstractDerivedItemFrameRenderer<TE>
{
    protected final BlockRenderDispatcher dispatcher;

    protected AbstractNonInvisibleItemFrameRenderer(EntityRendererProvider.Context context)
    {
        super(context);
        dispatcher = context.getBlockRenderDispatcher();
    }
}
