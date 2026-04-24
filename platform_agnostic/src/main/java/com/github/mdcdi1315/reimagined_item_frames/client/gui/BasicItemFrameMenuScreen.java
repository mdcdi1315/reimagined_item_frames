package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.reimagined_item_frames.menu.BasicItemFrameMenu;
import com.github.mdcdi1315.reimagined_item_frames.menu.AbstractItemFrameMenu;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class BasicItemFrameMenuScreen
    extends AbstractItemFrameMenuScreen<BasicItemFrameMenu>
{
    private final RotationValuesSlider rotation;

    public BasicItemFrameMenuScreen(BasicItemFrameMenu menu, Inventory playerInventory, Component title)
    {
        super(menu, playerInventory, title);
        rotation = new RotationValuesSlider(0, 0, 110, 15, menu.GetRotation(), this::ApplyRotation);
    }

    private void ApplyRotation(int rot_in_degrees) { menu.ApplyRotation(rot_in_degrees); }

    @Override
    protected void init()
    {
        super.init();
        rotation.setY(topPos + imageHeight - SLIDER_DISPLACEMENT_FROM_BOTTOM - 20);
        rotation.setX(leftPos + AbstractItemFrameMenu.RENDERED_ITEM_X_POINT + 60);
        addRenderableWidget(rotation);
    }
}
