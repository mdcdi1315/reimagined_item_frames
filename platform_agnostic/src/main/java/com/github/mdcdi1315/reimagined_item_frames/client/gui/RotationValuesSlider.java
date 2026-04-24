package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.basemodslib.utils.function.PrimitiveIntegerAction;

import net.minecraft.network.chat.Component;
import net.minecraft.client.gui.components.AbstractSliderButton;

public class RotationValuesSlider
    extends AbstractSliderButton
{
    private final PrimitiveIntegerAction action;

    public RotationValuesSlider(int x, int y, int width, int height, int rot_value, PrimitiveIntegerAction action)
    {
        super(x, y, width, height, Component.empty(), rot_value / 360d);
        this.action = action;
        updateMessage();
    }

    @Override
    protected void updateMessage()
    {
        this.setMessage(
                Component.translatable("gui.reimagined_item_frames.rotation_value_slider_control.message", (int)(value * 360d))
        );
    }

    @Override
    protected void applyValue() { action.action((int)(value * 360d)); }
}
