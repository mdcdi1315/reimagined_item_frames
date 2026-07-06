package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.StringUtils;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.entity.AdvancedItemFrameEntity;

import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.core.BlockPos;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class AdvancedItemFrameRenderer
    extends AbstractNonInvisibleItemFrameRenderer<AdvancedItemFrameEntity>
{
    public static final ModelResourceLocation TEX_LOCATION = new ModelResourceLocation(
            RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "advanced_item_frame"),
            StringUtils.Empty
    );

    public AdvancedItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected int getBlockLightLevel(AdvancedItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }

    @Override
    protected void RenderAdditional(AdvancedItemFrameEntity entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(pose_stack, buffer.getBuffer(Sheets.solidBlockSheet()), dispatcher, TEX_LOCATION, packed_light);

        RenderItem(entity, entity.GetItemDisplayScale() / 100f, pose_stack, buffer, packed_light);
    }
}
