package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.StringUtils;

import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.entity.GlassedAdvancedItemFrameEntity;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.resources.model.ModelManager;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class GlassedAdvancedItemFrameRenderer
        extends AbstractNonInvisibleItemFrameRenderer<GlassedAdvancedItemFrameEntity>
{
    public static final ModelResourceLocation TEX_LOCATION = new ModelResourceLocation(
            RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "glassed_advanced_item_frame"),
            StringUtils.Empty
    );

    public GlassedAdvancedItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected int getBlockLightLevel(GlassedAdvancedItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }

    @Override
    protected void RenderAdditional(GlassedAdvancedItemFrameEntity entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(pose_stack, buffer.getBuffer(Sheets.translucentCullBlockSheet()), dispatcher, TEX_LOCATION, packed_light);

        RenderItem(entity, entity.GetItemDisplayScale() / 100f, pose_stack, buffer, packed_light);
    }
}
