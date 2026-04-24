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

    private void RenderTransparentFrame(
            PoseStack pose_stack,
            MultiBufferSource buffer,
            BlockRenderDispatcher dispatcher,
            int packed_light
    ) {
        ModelManager modelmanager = dispatcher.getBlockModelShaper().getModelManager();
        pose_stack.pushPose(); // POSE PUSH UNSAFE BEGIN
        try {
            pose_stack.translate(-0.5F, -0.5F, -0.5F);
            dispatcher.getModelRenderer().renderModel(pose_stack.last(), buffer.getBuffer(Sheets.translucentCullBlockSheet()), null, modelmanager.getModel(TEX_LOCATION), 1.0F, 1.0F, 1.0F, packed_light, OverlayTexture.NO_OVERLAY);
        } finally {
            pose_stack.popPose(); // POSE PUSH UNSAFE END
        }
    }

    @Override
    protected void RenderAdditional(GlassedAdvancedItemFrameEntity entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderTransparentFrame(pose_stack, buffer, dispatcher, packed_light);

        ItemStack itemstack = entity.getItem();

        if (!itemstack.isEmpty())
        {
            MapId mapid = entity.getFramedMapId(itemstack);
            float display_scale = entity.GetItemDisplayScale() / 100f;
            pose_stack.translate(0.0F, 0.0F, 0.5F);
            pose_stack.mulPose(Axis.ZP.rotationDegrees(entity.getRotation()));
            if (mapid != null) {
                pose_stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                display_scale *= 0.0078125F;
                pose_stack.scale(display_scale, display_scale, display_scale);
                pose_stack.translate(-64.0F, -64.0F, 0.0F);
                pose_stack.translate(0.0F, 0.0F, -1.0F);
                MapItemSavedData mapitemsaveddata = MapItem.getSavedData(mapid, entity.level());
                if (mapitemsaveddata != null) {
                    Minecraft.getInstance().gameRenderer.getMapRenderer().render(pose_stack, buffer, mapid, mapitemsaveddata, true, packed_light);
                }
            } else {
                RenderItemStackToFrame(entity, display_scale, itemstack, pose_stack, buffer, packed_light);
            }
        }
    }
}
