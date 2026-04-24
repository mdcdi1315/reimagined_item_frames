package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.reimagined_item_frames.menu.AbstractItemFrameMenu;
import com.github.mdcdi1315.reimagined_item_frames.menu.AdvancedItemFrameMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class AdvancedItemFrameMenuScreen
    extends AbstractItemFrameMenuScreen<AdvancedItemFrameMenu>
{
    private final RotationValuesSlider rotation;
    private final ItemDisplayScaleSlider item_scale;
    private final DesiredLightLevelSlider desired_light_level;

    public AdvancedItemFrameMenuScreen(AdvancedItemFrameMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        rotation = new RotationValuesSlider(0, 0, 110, 15, menu.GetRotation(), this::ApplyRotation);
        item_scale = new ItemDisplayScaleSlider(0, 0, 110, 15, menu.GetDesiredItemScale(), this::ApplyItemScale);
        desired_light_level = new DesiredLightLevelSlider(0, 0,110, 15, menu.GetDesiredLightLevel(), this::ApplyLightLevel);
    }

    private void ApplyRotation(int rot_in_degrees) { menu.SetRotation(rot_in_degrees); }

    private void ApplyItemScale(byte item_scale) { menu.SetDesiredItemScale(item_scale); }

    private void ApplyLightLevel(byte light_level) { menu.SetDesiredLightLevel(light_level); }

    @Override
    protected void init()
    {
        super.init();
        rotation.setY(topPos + imageHeight - SLIDER_DISPLACEMENT_FROM_BOTTOM);
        rotation.setX(leftPos + AbstractItemFrameMenu.RENDERED_ITEM_X_POINT + 60);
        addRenderableWidget(rotation);
        item_scale.setY(rotation.getY() - 20);
        item_scale.setX(rotation.getX());
        addRenderableWidget(item_scale);
        desired_light_level.setY(item_scale.getY() - 20);
        desired_light_level.setX(rotation.getX());
        addRenderableWidget(desired_light_level);
    }
}
