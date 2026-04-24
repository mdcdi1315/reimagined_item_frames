package com.github.mdcdi1315.reimagined_item_frames.client;

import com.github.mdcdi1315.DotNetLayer.System.StringUtils;
import com.github.mdcdi1315.basemodslib.registries.RegistryUtils;
import com.github.mdcdi1315.basemodslib.utils.function.ElementFunctionSupplier;

import com.github.mdcdi1315.reimagined_item_frames.ServerModInstance;
import com.github.mdcdi1315.reimagined_item_frames.entity.SimpleItemFrameEntity;

import com.mojang.math.Axis;
import com.mojang.blaze3d.vertex.PoseStack;

import net.minecraft.client.Minecraft;
import net.minecraft.world.item.MapItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.saveddata.maps.MapId;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.world.level.saveddata.maps.MapItemSavedData;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public final class SimpleItemFrameRenderer
    extends AbstractNonInvisibleItemFrameRenderer<SimpleItemFrameEntity>
{
    public static final ElementFunctionSupplier<SimpleItemFrameEntity, ModelResourceLocation> TEX_LOCATION = new ElementFunctionSupplier<>(new ModelResourceLocation(
            RegistryUtils.ConstructResourceLocation(ServerModInstance.MOD_ID, "simple_item_frame"),
            StringUtils.Empty
    ));

    public SimpleItemFrameRenderer(EntityRendererProvider.Context context) { super(context); }

    @Override
    protected void RenderAdditional(SimpleItemFrameEntity entity, float entity_yaw, float partial_ticks, PoseStack pose_stack, MultiBufferSource buffer, int packed_light)
    {
        RenderFrame(entity, pose_stack, buffer, dispatcher, TEX_LOCATION, packed_light);

        ItemStack itemstack = entity.getItem();

        if (!itemstack.isEmpty())
        {
            MapId mapid = entity.getFramedMapId(itemstack);
            pose_stack.translate(0.0F, 0.0F, 0.5F);
            pose_stack.mulPose(Axis.ZP.rotationDegrees(entity.getRotation()));
            if (mapid != null) {
                pose_stack.mulPose(Axis.ZP.rotationDegrees(180.0F));
                pose_stack.scale(0.0078125F, 0.0078125F, 0.0078125F);
                pose_stack.translate(-64.0F, -64.0F, 0.0F);
                pose_stack.translate(0.0F, 0.0F, -1.0F);
                MapItemSavedData mapitemsaveddata = MapItem.getSavedData(mapid, entity.level());
                if (mapitemsaveddata != null) {
                    Minecraft.getInstance().gameRenderer.getMapRenderer().render(pose_stack, buffer, mapid, mapitemsaveddata, true, packed_light);
                }
            } else {
                RenderItemStackToFrame(entity, 0.5f, itemstack, pose_stack, buffer, packed_light);
            }
        }
    }
}
