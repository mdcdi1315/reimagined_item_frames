package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedInvisibleItemFrameEntity;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;
import net.minecraft.client.Minecraft;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class AdvancedInvisibleItemFrameRenderer
    extends AbstractDerivedItemFrameRenderer<AdvancedInvisibleItemFrameEntity, RIFAdvancedRenderState>
{
    public AdvancedInvisibleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(RIFAdvancedRenderState state, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderItem(state, state.item_display_scale / 100f, pose_stack, buffer, packed_light);
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
