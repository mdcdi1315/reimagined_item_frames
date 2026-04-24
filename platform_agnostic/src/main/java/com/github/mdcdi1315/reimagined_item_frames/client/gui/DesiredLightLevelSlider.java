package com.github.mdcdi1315.reimagined_item_frames.client.gui;

import com.github.mdcdi1315.basemodslib.utils.function.PrimitiveByteAction;
import net.minecraft.client.gui.components.AbstractSliderButton;
import net.minecraft.network.chat.Component;

public class DesiredLightLevelSlider
    extends AbstractSliderButton
{
    private final PrimitiveByteAction action;

    public DesiredLightLevelSlider(int x, int y, int width, int height, byte value, PrimitiveByteAction action)
    {
        super(x, y, width, height, Component.empty(), value / 15d);
        this.action = action;
        updateMessage();
    }

    @Override
    protected void updateMessage()
    {
        this.setMessage(
                Component.translatable(
                        "gui.reimagined_item_frames.desired_light_level_slider_control.message",
                        (int) (value * 15d)
                )
        );
    }

    @Override
    protected void applyValue() { action.action((byte) (value * 15d)); }
}
