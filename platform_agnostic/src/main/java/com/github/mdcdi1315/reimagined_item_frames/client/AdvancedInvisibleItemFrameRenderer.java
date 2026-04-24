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
    extends AbstractDerivedItemFrameRenderer<AdvancedInvisibleItemFrameEntity>
{
    public AdvancedInvisibleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(AdvancedInvisibleItemFrameEntity entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
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

    @Override
    protected int getBlockLightLevel(AdvancedInvisibleItemFrameEntity entity, BlockPos pos) { return entity.GetDesiredLightLevel(); }
}
